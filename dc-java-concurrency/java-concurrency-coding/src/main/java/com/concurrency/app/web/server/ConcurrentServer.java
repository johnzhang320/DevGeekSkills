package com.concurrency.app.web.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

 

public class ConcurrentServer implements Runnable {
	private static Logger Log = Logger.getLogger(ConcurrentServer.class);
	private ServerSocket ssocket;
	private static final int MAX_THREADS = 32;
	public ConcurrentServer(int port) {
		try {
			ssocket = new ServerSocket(port);
		} catch (Exception e) {
			Log.info(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 *  (1) Create MAX_THREADS number of thread in threadpool
	 *  (2) accept client connection socket
	 */
	public void run() {
		ExecutorService exec = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i=0; i<MAX_THREADS; i++) {
	        exec.execute(new RequestProcessor());    
	    }
        exec.shutdown();
        while (true) {
        	try {
        		Log.info("Listen socket connection...");
	        	Socket csocket = ssocket.accept();  // block to listen client request
	        	Log.info("Obtain socket connection and adding to queue");
	        	RequestProcessor.addSocket(csocket);
	        	
	        } catch (Exception e) {
        		Log.info("Accept listen command error "+e.getMessage());
        		e.printStackTrace();
        	}
        }
        
	}
	public static void main(String arg[]) {
		ConcurrentServer server = new ConcurrentServer(8099);
		Thread t= new Thread(server);
		t.start();
		try {
			t.join();
		} catch (Exception e) {}
	}
}
