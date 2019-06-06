package com.interview.questions.sample.ATM.machine;

public class NoSufficientFundException extends Exception {
	String message;

	public NoSufficientFundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
