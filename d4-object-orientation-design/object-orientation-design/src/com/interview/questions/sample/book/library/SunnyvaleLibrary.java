package com.interview.questions.sample.book.library;

public class SunnyvaleLibrary extends LibraryManager {
	public Long userId;
	public Long bookId;
	public void initialize() {
		Book book = new Book("Old Man and See", "Ernest Highminway", "isbn321311455");
		bookId = book.getId();
		addBook(book);
		book = new Book("To Whom the bell sound", "Ernest Highminway", "isbn321311456");
		addBook(book);
		
		book = new Book("Data Structure Master", "Richard Steven", "isbn321311665");
		addBook(book);
		
		book = new Book("Unix System Programming", "Richard Steven", "isbn3213114677");
		addBook(book);
		
		book = new Book("Networking Programming", "Richard Steven", "isbn321311455");
		addBook(book);
		
		book = new Book("TCP/IP Stack", "Eric Huston", "isbn3213114878");
		addBook(book);
		
		book = new Book("Linux Kernel Programming", "Eric Huston", "isbn3213114878");
		addBook(book);
		
		book = new Book("Software as Service for Cloud", "Eric Huston", "isbn3213114566");
		addBook(book);
		
		book = new Book("Cloud Storage", "Li Bin", "isbn3213114998");
		addBook(book);
		
		book = new Book("Cloud Photo Box", "Li Bin", "isbn3213119654");
		addBook(book);
		
		User user = new User("John Zhang");
		addUser(user);
		userId = user.getUserId();
		user = new User("Suresh Aiyer");
		addUser(user);
		
		user = new User("Carl Elon");
		addUser(user);
		
		user = new User("Elon Mask");
		addUser(user);
		
		user = new User("Steve Jobs");
		addUser(user);
		
		user = new User("Bill Gates");
		addUser(user);
		
		user = new User("Dennie Cha");
		addUser(user);
		
		user = new User("Junny Liu");
		addUser(user);
		
		user = new User("Jing Lan");
		addUser(user);
	}
}
