package com.interview.questions.sample.shorten.URL;

public class DuplicatedURLShortenException extends Exception {
	private String message;

	public DuplicatedURLShortenException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
