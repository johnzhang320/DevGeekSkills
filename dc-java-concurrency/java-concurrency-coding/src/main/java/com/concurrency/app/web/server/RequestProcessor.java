package com.concurrency.app.web.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

/**
 *   I already test 
 * 
 *
 */

public class RequestProcessor implements Runnable {
	static Logger Log = Logger.getLogger(RequestProcessor.class);
	static final int MAX_QUEUE_SIZE=1000;
	//static LinkedList  <Socket> queue = new LinkedList<Socket>();	
	static BlockingQueue<Socket> queue = new LinkedBlockingQueue<Socket>(MAX_QUEUE_SIZE);
	public static void addSocket(Socket socket) {
		synchronized (queue) {
			queue.add(socket);   // add element to automatically notice the thread which block queue
			//queue.notifyAll();
		}
	}
	
	public void run() {
		while(true) {
			Socket connection;
			
			// if BlockQueue is empty, block here 
			
			
			//Simulate Processing user request, read data from input socket 
			try {
				if (queue.isEmpty()) {
					Log.info("Thread:"+Thread.currentThread().getName()+" will be blocked cause it finds queue is empty, ");
				}
				connection = queue.take();   // we must use take() which block thread here 
				Log.info("Thread "+Thread.currentThread().getName()+" starting read request....."); 
				Long start = System.currentTimeMillis();
				InputStream is = connection.getInputStream();			 
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				StringBuilder  buf = new StringBuilder(); 
				String line=null;
				while ((line=br.readLine())!=null) {
					buf.append(line);
					//Log.info(line);
				}
				 
				Log.info("Thread "+Thread.currentThread().getName()+" finished reading Client file: "+buf.length()+" characters"+", took "+(System.currentTimeMillis()-start)+" ms");
				
			} catch (Exception e) {
				Log.info ("Thread "+Thread.currentThread().getName()+" Reading Input Socket data error "+e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}
}
