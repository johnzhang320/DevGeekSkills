package com.code.review.linkedlist.hard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyLRUTest {
	private static final int MAX_THREADS=4;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newFixedThreadPool(MAX_THREADS);
		for (int i=0;i<MAX_THREADS; i++) {
			exec.execute(new ReadWindowsThread());
			Thread.sleep(100);
		}
		exec.shutdown();
		
	}

}
