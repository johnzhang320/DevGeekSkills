package com.interview.questions.sample.call.employee;

public class NotFoundFreeEmployeeException extends Exception {
	String message;

	public NotFoundFreeEmployeeException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	 
}
