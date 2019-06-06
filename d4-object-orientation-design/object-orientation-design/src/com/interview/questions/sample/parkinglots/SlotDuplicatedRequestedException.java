package com.interview.questions.sample.parkinglots;

public class SlotDuplicatedRequestedException extends Exception {
	private String message;

	public SlotDuplicatedRequestedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
 	
}
