package com.interview.questions.airport;

public class DuplicatedFlightRequestException extends Exception {
	private String message;

	public DuplicatedFlightRequestException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
