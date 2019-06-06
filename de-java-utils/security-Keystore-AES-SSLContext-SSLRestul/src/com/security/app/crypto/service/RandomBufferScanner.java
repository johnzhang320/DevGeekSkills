package com.security.app.crypto.service;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.Logger;


import com.mailfrontier.msgcenter.app.log.Logger;
 

public class RandomBufferScanner extends TimerTask{
	public static Logger Log =Logger.getLogger(RandomBufferScanner.class.getName());
	public void run() {
	//	Log.debug("Scan Random Number Buffer"); 
		RandomBufferManager.getInstance().scanRandomBuffer();
	}
	
	public void startTimeTaskOfRandomBuffer() {
		
		TimerTask timerTask = new RandomBufferScanner();
		
		Timer timer = new Timer(true); 

        timer.scheduleAtFixedRate(timerTask , 0, Constants.RANDOM_BUFFER_SCAN_DURATION_MSEC); 

	}
	public void startTimeTaskOfRandomBuffer(int scanDurationMS) {
		
		TimerTask timerTask = new RandomBufferScanner();
		
		Timer timer = new Timer(true); 

        timer.scheduleAtFixedRate(timerTask , 0, scanDurationMS); 

	}
	
}
