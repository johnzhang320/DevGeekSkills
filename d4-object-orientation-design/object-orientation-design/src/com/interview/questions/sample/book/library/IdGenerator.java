package com.interview.questions.sample.book.library;

public class IdGenerator {
	private static final Long RANDOM_NUMBER_LENGTH = 1000000000L;
	public static Long getRandomNumber() {
		
		return  (long) (Math.random() * RANDOM_NUMBER_LENGTH);		
		 
	}
}
