package com.interview.questions.sample.traffic.junction;

public class NoTrafficException extends Exception {
	private String message;

	public NoTrafficException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
