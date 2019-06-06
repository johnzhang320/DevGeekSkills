package com.interview.questions.sample.traffic.junction;

public class IdGenerator {
	private static final int RANDOM_NUMBER_LENGTH = 10000000;
	public static int getRandomNumber() {
		
		return  (int) (Math.random() * RANDOM_NUMBER_LENGTH);		
		 
	}
}
