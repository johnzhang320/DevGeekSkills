package com.interview.questions.sample.book.library;

import java.util.List;

public class User {
	private String name;
	private List<Book> borrowedBooks;
	private Long userId;
	
	public User(Long userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	public User(String name) {
		super();
		this.userId = IdGenerator.getRandomNumber(); 
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", borrowedBooks=" + borrowedBooks + ", userId=" + userId + "]";
	}
	
	
	
}
