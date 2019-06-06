package com.concurrency.app.web.server;

import java.net.Socket;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class SocketQueue {
	Logger Log = Logger.getLogger(SocketQueue.class);
	static LinkedList <Socket> queue = new LinkedList<Socket>();
	private final static SocketQueue handler= new SocketQueue();
	public static SocketQueue getInstance() {		 
		return handler;
	}
	public synchronized void add(Socket socket) {
		queue.add(socket);  // add to tail of linkedlist
		queue.notifyAll();
	}
	public Socket remove() {
		if (queue.isEmpty()) {
			return null;
		} 
		return queue.remove();
	}
	public static LinkedList<Socket> getQueue() {
		return queue;
	}
	 
	public synchronized void waitQueue() {
		while (queue.isEmpty()) {
			try {
				queue.wait();
			} catch (InterruptedException e) {
				Log.info("wait queue error "+e.getMessage());
			}
		}
	}
}
