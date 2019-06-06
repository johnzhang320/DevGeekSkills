package com.interview.questions.sample.ATM.machine;

public class UserNotFoundException extends Exception {
	String message;

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
