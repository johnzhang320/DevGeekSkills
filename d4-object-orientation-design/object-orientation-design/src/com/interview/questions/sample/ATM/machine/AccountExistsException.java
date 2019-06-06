package com.interview.questions.sample.ATM.machine;

public class AccountExistsException extends Exception {
	String message;

	public AccountExistsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
