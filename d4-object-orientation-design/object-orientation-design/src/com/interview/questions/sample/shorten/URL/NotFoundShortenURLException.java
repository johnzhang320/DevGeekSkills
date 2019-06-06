package com.interview.questions.sample.shorten.URL;

public class NotFoundShortenURLException extends Exception {
	private String message;

	public NotFoundShortenURLException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
