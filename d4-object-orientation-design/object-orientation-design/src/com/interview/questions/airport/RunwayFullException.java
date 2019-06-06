package com.interview.questions.airport;

public class RunwayFullException extends Exception {
	private String message;

	public RunwayFullException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
