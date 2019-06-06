package com.interview.questions.sample.parkinglots;

public class NotFoundSlotException extends Exception {
	private String message;

	public NotFoundSlotException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
