package com.interview.questions.sample.selling.machine;

public class NotSufficientChangeException extends RuntimeException {
	private String message;
	
	public NotSufficientChangeException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	} 
	
}
