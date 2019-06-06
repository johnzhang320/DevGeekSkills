package com.interview.questions.sample.LRU;

public class KeyNotFoundException extends Exception {
	String message;

	public KeyNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
