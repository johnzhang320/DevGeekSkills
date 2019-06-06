package com.interview.questions.sample.traffic.junction;

public class Util {
	public static void sleap(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {};
	}
 
}
