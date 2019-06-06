package com.security.app.crypto.service;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;



import org.apache.logging.log4j.Logger;

 
import com.mailfrontier.msgcenter.app.log.Logger;

public class RandomBufferManager {
	
	public static Logger Log =Logger.getLogger(RandomBufferManager.class.getName());
	
	private static Integer randExpiredMS = Constants.RANDOM_EXPIRED_DURATION_MSEC;
	
	/**
	 *  ConcurrentHashMap allow object lock and delete while iterator without CurrentModificationException throw like SynchronizedMap 
	 */
	
	private static ConcurrentHashMap<String, RandomBufferVO> concurrentHashMap =null;
	
	private static RandomBufferManager handler=null;
	
	public static synchronized RandomBufferManager getInstance() {
		
		if (null==handler) {
			
			handler = new RandomBufferManager();
		}
		
		return handler;
	}
	
	private RandomBufferManager() {
		
		concurrentHashMap = new ConcurrentHashMap<String, RandomBufferVO>();
		
	}
	
	public static Integer getRandExpiredMS() {
		return randExpiredMS;
	}

	public static void setRandExpiredMS(Integer randExpiredMS) {
		RandomBufferManager.randExpiredMS = randExpiredMS;
	}

	/**
	 * Save CC random number , CC TS and CC host name to buffer
	 * @param randomNumber
	 * @param ccTimestamp
	 * @param requestHost
	 * @return "repeated" if random number is found in concurrentHashMap.
	 *         "tooOld" if CC timestamp minus RA system stamp > limited time
	 *         "added" if random number is not in concurrentHashMap and CC timestamp minus RA system stamp < limited time
	 */
	public String addRandomBuffer(String randomNumber,String ccTimestamp,String requestHost) {
		
		 
		
		if (null==concurrentHashMap) {
			getInstance();
		}
		
		if (concurrentHashMap.containsKey(randomNumber)) {
			
			Log.info("Random number is repeated , random="+randomNumber);
			
			return Constants.RANDOM_REPEATED;
			
		}
		String raTS = TimeUtil.getDateTimeStampInMilliSeconds();
		
		if (!isTooOldTimestamp(ccTimestamp) ) {
			
			Log.info("Adding random and TS, , random="+randomNumber);
		
			RandomBufferVO vo = new RandomBufferVO(ccTimestamp, raTS, requestHost);
		
			concurrentHashMap.put(randomNumber, vo);
			
			 
			
		} else {
			
			Log.info("TS of random is too old, random="+randomNumber);
			
			return Constants.RANDOM_TOO_OLD;
		}
		
 		return Constants.RANDOM_ADDED;
		
	}
	/**
	 *  isRandomInBuffer(String randomNumber)
	 * @param randomNumber
	 * @return true if randomNumber in map
	 */
	public boolean isRandomInBuffer(String randomNumber) {
		Log.info("Begin");
		Log.info("Random buffer size="+concurrentHashMap.size());
		boolean retVal = true;
		
		if (null==concurrentHashMap) {
			
			getInstance();
		}
		
		if (concurrentHashMap.containsKey(randomNumber)) {
			
			Log.info("Random number is repeated , random="+randomNumber);
			
			return retVal;
			
		}
		Log.info("End");
		return false;
		
	}
	/**
	 * isTooOldTimestamp
	 * @param ccTimestamp
	 * @return tru
	 */
	public boolean isTooOldTimestamp(String ccTimestamp) {
		Log.info("Begin");
		
		boolean retVal=false;
		
		String currentTS = TimeUtil.getDateTimeStampInMilliSeconds();
		
		Long curTSint = new Long(currentTS);
		
		Long ccTSint = new Long(ccTimestamp);
		
		Long absDiff = Math.abs(curTSint - ccTSint);
		Log.info("isTooOldTimestamp Random buffer size="+concurrentHashMap.size()+", timestamp diff(sm)="+absDiff);
		if (absDiff > randExpiredMS) {
			
			Log.info("Random number TS is too old, user sent TS ="+ccTSint+", RA System TS="+currentTS);
			
			retVal=true;
			
		}
		Log.info("end");
		return retVal;
	}
	
	public void scanRandomBuffer() {
		 
		if (null==concurrentHashMap) {
			
			getInstance();
		}
		
		Iterator itr = concurrentHashMap.keySet().iterator();
		
		Long currentTS = new Long(TimeUtil.getDateTimeStampInMilliSeconds());
		
		while (itr.hasNext()) {
			
			String key = (String) itr.next();
			
			RandomBufferVO vo = (RandomBufferVO) concurrentHashMap.get(key);
			
			String ccTS = vo.getCcTimestamp();
			
			Long ccTSInt = new Long(ccTS) ;
			
			Long diff = (currentTS - ccTSInt);
			
			if (diff > randExpiredMS) {
				Log.info("\n Scanner find:\n Too Old Record will be removed: \n cchost="+vo.getRequestCC()+", Cc timestamp="+vo.getCcTimestamp()
						+"\n but Current Timestamp="+currentTS+"\n diff="+diff+", System allow diff is "+randExpiredMS); 
				itr.remove();
			}
		}
		 
	}
	
	
	
	public static ConcurrentHashMap<String, RandomBufferVO> getConcurrentHashMap() {
		return concurrentHashMap;
	}

	public static void setConcurrentHashMap(
			ConcurrentHashMap<String, RandomBufferVO> concurrentHashMap) {
		RandomBufferManager.concurrentHashMap = concurrentHashMap;
	}

	public static String getRandomNumber() {
		
		Integer rval = new Integer( (int) (Math.random() * Constants.RANDOM_NUMBER_LENGTH));
		
		return rval.toString();
	}	
	
	
	
	public static void main(String args[]) {
		
		RandomBufferManager handler =  RandomBufferManager.getInstance();
		
		handler.setRandExpiredMS(3000);
		
		/**
		 *  Set one second as scanner period
		 */
		RandomBufferScanner scanner = new RandomBufferScanner();
		
		scanner.startTimeTaskOfRandomBuffer();
		
		String random1= getRandomNumber();
		String ccTimestamp=null;
		/**
		 *  Test added random number
		 */
		try {
			 ccTimestamp = TimeUtil.getDateTimeStampInMilliSeconds();	 
			Thread.sleep(1000);
		} catch (Exception e) {}
		
	 
		
		String ret= handler.addRandomBuffer(random1,ccTimestamp,"jzhang-462834");
		
		if (ret.equalsIgnoreCase(Constants.RANDOM_ADDED)) {
			Log.info("Random1 = "+random1+" was added!");
			ConcurrentHashMap<String, RandomBufferVO> map = handler.getConcurrentHashMap();
			RandomBufferVO vo = map.get(random1);
			Log.info("CC timeStamp="+vo.getCcTimestamp()+",RA ReceivedTimestamp="+vo.getReceivedTimestamp()+",CC hostname="+vo.getRequestCC());
			
		}
		
		/**
		 *  Test repeated random number
		 */
		
		try {
			Thread.sleep(1000);
			 ccTimestamp = TimeUtil.getDateTimeStampInMilliSeconds();
			 Log.info("Secondly adding ccTimestamp="+ccTimestamp);
		} catch (Exception e) {}
		
		Log.info("ccTimestamp="+ccTimestamp);
		
		ret= handler.addRandomBuffer(random1,ccTimestamp,"jzhang-462834");
		if (ret.equalsIgnoreCase(Constants.RANDOM_REPEATED)) {
			Log.info("Random1 = "+random1+" was repeated!");
		}
		/**
		 *  Test too old random number will be removed
		*/
		
		try {
			Thread.sleep(11000);		
		} catch (Exception e) {}
		
		
		/**
		 *  Test too old random number (hacked) could not be allowed to added
		 */
		 
		Log.info("ccTimestamp="+ccTimestamp);
		
		ret= handler.addRandomBuffer(random1,ccTimestamp,"jzhang-462834");
		if (ret.equalsIgnoreCase(Constants.RANDOM_TOO_OLD)) {
			Log.info("Random1 = "+random1+" was too old to be added, it must be hacked !");
		}
		 
	}
  
}
