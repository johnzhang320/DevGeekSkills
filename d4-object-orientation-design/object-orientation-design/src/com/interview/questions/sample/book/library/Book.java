package com.interview.questions.sample.book.library;

import java.util.Date;

public class Book implements Comparable<Book> {
	private String name;
	private String author;
	private String isbn;
	private Long id;
	private boolean borrowed;
	private Category cat;
	private Date dueDate;
	
	public Book(String name, boolean borrowed) {
		super();
		this.name = name;
		this.borrowed = borrowed;
		this.id = IdGenerator.getRandomNumber();
	}
	 
	public Book(String name, String author, String isbn) {
		super();
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.id = IdGenerator.getRandomNumber();
	}

	public Book(String name, String author, String isbn, boolean borrowed, Category cat) {
		super();
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		
		this.borrowed = borrowed;
		this.cat = cat;
		this.id = IdGenerator.getRandomNumber();
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public boolean isBorrowed() {
		return borrowed;
	}
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public int compareTo(Book o2) {
		Book o1 = this;
		return o2.compareTo(o1); // reverse order, largest due date will be displayed first
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", isbn=" + isbn + ", id=" + id + ", borrowed=" + borrowed
				+ ", cat=" + cat + ", dueDate=" + dueDate + "]";
	}
	
}
