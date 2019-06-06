package com.interview.questions.sample.book.library;

public class WrongIDException extends Exception {
	String message;

	public WrongIDException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
