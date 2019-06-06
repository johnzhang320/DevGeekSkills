package com.custom.annotation.process;

public class JsonSerializationException extends Exception{
	private String message;
	public JsonSerializationException(String message) {
		this.message = message;
		System.out.println(message);
	}
}
