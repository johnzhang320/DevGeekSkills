package com.interview.questions.sample.selling.machine;

public class SoldOutException extends RuntimeException {
	private String message;

	public SoldOutException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
