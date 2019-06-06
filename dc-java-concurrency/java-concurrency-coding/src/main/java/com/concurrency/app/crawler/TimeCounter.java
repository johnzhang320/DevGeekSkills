package com.concurrency.app.crawler;

public class TimeCounter {
	private static TimeCounter handler = null;
	private static long start = 0L;
	public static TimeCounter getInstance() {
		if (handler==null) {
			start = System.currentTimeMillis();
			handler = new TimeCounter();
			
		}
		return handler;
	}
	public static long takeSeconds() {
		long sec = (System.currentTimeMillis()-start)/1000L;
		return sec;
	}
	private TimeCounter () {}
}
