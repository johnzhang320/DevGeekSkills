package com.interview.questions.sample.book.library;

import java.util.Date;

public enum SearchColumn {
	NAME("name"),AUTHOR("author"),ISBN("isbn"),DUEDATE("dueDate");
	String name;

	private SearchColumn(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	 
}
