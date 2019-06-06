package com.security.app.crypto.service;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import com.mailfrontier.msgcenter.app.log.Logger;

public class AuthenticateManager {
	public static Logger Log =Logger.getLogger(AuthenticateManager.class.getName());
	
	/**
	 * For master data retrieved from Web Service Client 
	 * @param commandType
	 * @param hostname
	 * @param pathDescription
	 * @param pathId 
	 */
	public NewMessageAuthVO generateNewMessageAuthVO (String commandType, String hostname) {
	 
		return generateNewMessageAuthVO (commandType, hostname, "*",null,null);
		
	}
		
	
	/**
	 * For vmta data retrieved from Web Service Client 
	 * @param commandType
	 * @param hostname
	 * @param pathDescription
	 * @param pathId 
	 */
	public NewMessageAuthVO generateNewMessageAuthVO (String commandType, String hostname, String vmtaname,  String pathDescription, String pathId) {
		Log.info("begin");
		NewMessageAuthVO retVal= new NewMessageAuthVO();
		
		if (null==hostname || ""==hostname) {			 
			
			 Log.error("\nError: No Hostname!");
			 
			 retVal.setCommandType(Constants.ERR_READ_RA_NO_HOST);
			 
			 return retVal;
		}
		if (null==commandType || ""==commandType) {
			
			 Log.error("\nError: No CommandType!");
			 
			 retVal.setCommandType(Constants.ERR_NO_COMMAND_TYPE);
			 
			 return retVal;
		}
		
		Log.info("NewMessageAuthVO Step A");
		
		EncryptionUtils handler= EncryptionUtils.getInstance();
		
		Log.info("NewMessageAuthVO Step B");
		
		String message = getRandomNumber(); 		 
		
		String timeStampInCC = TimeUtil.getDateTimeStampInMilliSeconds();		 
		
		Log.info ("Original Request random number ="+message+",timeStampInCC = "+timeStampInCC);
		
		 try {
			 
			 String enTS = handler.encrypt(timeStampInCC);			 
			
			 
			 String ccLocalhost = getLocalhost();
			 
			 if (null==ccLocalhost) {
			
				 ccLocalhost="dummyhost";
			 }
			 
			 String enCCHost = handler.encrypt(ccLocalhost);			 
			 
			 String enText = handler.encrypt(message);			 
			 		 
			 String command = handler.encrypt(commandType);			
			 
			 String host = handler.encrypt(hostname);					
			 
			 Log.debug("Encrypted commandType="+commandType+",hostname="+hostname);
			 
			 String digestMsg = handler.getHexdigestMsgBySha256(message);		
			 
			 Log.debug("\nEncryptedMessage = "+enText+"\nhashedMessage = "+digestMsg+"\nTimestamp="+enTS+"\nseedStr4IV = "+handler.getseedStr4IV());	 
			 
			 NewMessageAuthVO vo = null;
			 
			 if (null==pathDescription) {	
			 
				vo = new NewMessageAuthVO(enText,digestMsg,handler.getseedStr4IV(),command, host);
				 
				 vo.setVmtaname(vmtaname); 
				 
				 vo.setPathDescription(null);
				 
				 vo.setCcHostname(enCCHost);
				 
				 vo.setTimestamp(enTS);
				
				 		
			 } else {				
				 
				vo = new NewMessageAuthVO(enText,digestMsg,handler.getseedStr4IV(),command, host,pathDescription,pathId); 
				 
				 vo.setVmtaname(vmtaname); 
				 
				 vo.setCcHostname(enCCHost);
				 
				 vo.setTimestamp(enTS);
				  
			 }
			
			 retVal = vo;
			 
		 } catch (Exception e) {
			 
			 Log.error("Failed to generate NewMessageAuthVO !");
			 
			 retVal.setCommandType(Constants.CONNECTION_REFUSED);
			 
			 return retVal;
		 }
		 if (null!=retVal) {
			  JSONObject jsonObj = new JSONObject(retVal);
			  try {
			  jsonObj.remove("class");
			  jsonObj.remove("serialversionuid");			
			  jsonObj.put("testSubdir","null");
			  jsonObj.put("pathId","null");
			  jsonObj.put("testCommandType","null");
			  jsonObj.put("pathDescription","null");
			  jsonObj.put("RAhostname",hostname);
			  } catch (Exception e) {
				  Log.error("Editing POJO Json object error"+e.getMessage());
			  }
			  String JsonStr= jsonObj.toString();
			  Log.debug("Next POJO Object for RA :"+hostname);
			  Log.debug(JsonStr);
			  
		  }	
		 Log.info("end");
		 return retVal;
	}
	
	 
	
	public static String getRandomNumber() {
		
		Integer rval = new Integer( (int) (Math.random() * Constants.RANDOM_NUMBER_LENGTH));
		
		return rval.toString();
	}
	
	public static String getLocalhost() {
		
		String localHostname=null;
		 
		InetAddress ip;
	         
	    try {
	            ip = InetAddress.getLocalHost();
	            
	            localHostname = ip.getHostName();
	            
	            Log.debug("found localhost name="+localHostname);
	            
	    } catch (UnknownHostException e) {
	    	 
	        	Log.error("Failure to get local hostname",e);
        }
		 
	     return localHostname;
	} 
	/**
	 * Protect following situation
	 * (1) not PMTA main CC
	 * (2) outside cluster CC access!
	 * (3) random number is too old, hackers manually re-send my POJO NewMessageAuthVO
	 * (4) random number is repeated, hackers use auto machine instantly send my POJO NewMessageAuthVO 
	 * @param mdVO
	 * @return handler.AUTH_PASSED if authentication passed, otherwise return value as code shown
	 */
	public String ccUserAuthentication(NewMessageAuthVO mdVO) {
		
		EncryptionUtils handler = new EncryptionUtils(mdVO.getSeedStr4IV());
		
		if (null == mdVO.getCcHostname() || ""== mdVO.getCcHostname()) {
			Log.error(Constants.ERR_CCHOSTNAME_NOT_AVAIBLE);
			return Constants.ERR_CCHOSTNAME_NOT_AVAIBLE;
		}
		String ccHostname = null;
		try {
			ccHostname= handler.decrypt(mdVO.getCcHostname());
		} catch (Exception e) {
			Log.error(Constants.ERR_CCHOSTNAME_FAILED_DECRYPT);
			return Constants.ERR_CCHOSTNAME_FAILED_DECRYPT;
		}
		String random = null;
		try {
			random= handler.decrypt(mdVO.getEncryptedMessage());
		} catch (Exception e) {
			Log.error(Constants.ERR_RANDOM_NUMER_FAILED_DECRYPT);			 
			return Constants.ERR_RANDOM_NUMER_FAILED_DECRYPT;
		}
		String ccTS = null;
		try {
			ccTS= handler.decrypt(mdVO.getTimestamp());
		} catch (Exception e) {
			Log.error(Constants.ERR_CC_TIMESTAMP_FAILED_DECRYPT);
			 
			return Constants.ERR_CC_TIMESTAMP_FAILED_DECRYPT;
		}
		
		String retVal = handler.AUTH_PASSED;
		
	 		
	 

		RandomBufferManager rbRef = RandomBufferManager.getInstance();
		
		Log.info("decrypted random="+random+",decrypted CC TS="+ccTS);
		
		if (rbRef.isRandomInBuffer(random)) {
			retVal = Constants.ERR_RANDOM_NUMBER_REPEATED;
			Log.error(retVal);
			return retVal;
		}
		
		if (rbRef.isTooOldTimestamp(ccTS)) {
			retVal = Constants.ERR_RANDOM_NUMBER_TOO_OLD;
			Log.error(retVal);
			return retVal;
		}
		
		try {
			
			/**
			 *  last stage, compare the hash1 with hash2
			 */
			 retVal = handler.userAuthentication(mdVO);		
			 
			 if (handler.AUTH_PASSED.equalsIgnoreCase(retVal)) {
				 
				String addOK = rbRef.addRandomBuffer(random, ccTS,ccHostname);
				 
				 if (!Constants.RANDOM_ADDED.equalsIgnoreCase(addOK)) {
					 retVal="Repeated Random number or too old random number";
					 Log.error(retVal);
				 }
			 }
			
			 Log.info("Server Auth retVal="+retVal);
			 
		} catch (Exception e) {
			 Log.info("Failed Authentication!"+e.getMessage());
			 retVal = handler.AUTH_FAILED;
		}
		
		return retVal;
	}
	
	public static void main(String[] args) {
	   /**
	    *  Start scheduler to scan
	    */
		//RandomBufferScanner authscan = new RandomBufferScanner();
		//authscan.startTimeTaskOfRandomBuffer();
		/**
		 *  Added first random number
		 */ 
		AuthenticateManager handler = new AuthenticateManager();
	
		
		NewMessageAuthVO vo=handler.generateNewMessageAuthVO (Constants.CMD_READ_RA_VMTA_INFO, "jzhang-462834");
		Log.info("NewMessageAuthVO vo host="+vo.getCcHostname()+",vo commandType="+vo.getCommandType()+"\n getEncryptedMessage()="+vo.getEncryptedMessage()+"\n timestamp="+vo.getTimestamp());
		String retVal=handler.ccUserAuthentication(vo);
		Log.info("First Request is "+retVal);
		/**
		 *  Add same random number again in 1000ms
		 */
		try {
			Thread.sleep(1000);
			Log.info("NewMessageAuthVO vo host="+vo.getCcHostname()+",vo commandType="+vo.getCommandType()+"\n getEncryptedMessage()="+vo.getEncryptedMessage()+"\n timestamp="+vo.getTimestamp());
			retVal=handler.ccUserAuthentication(vo);
			Log.info("Second Request is "+retVal);
		} catch (Exception e) {}
		
		/**
		 *  Add same random number again in 11000 ms
		 */
		try {
			Thread.sleep(11000);
			Log.info("NewMessageAuthVO vo host="+vo.getCcHostname()+",vo commandType="+vo.getCommandType()+"\n getEncryptedMessage()="+vo.getEncryptedMessage()+"\n timestamp="+vo.getTimestamp());
			retVal=handler.ccUserAuthentication(vo);
			Log.info("Third Request is "+retVal);
		} catch (Exception e) {}
		
	}

}
