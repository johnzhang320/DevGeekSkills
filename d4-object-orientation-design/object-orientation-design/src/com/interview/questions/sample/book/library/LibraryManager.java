package com.interview.questions.sample.book.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LibraryManager {
	// Define storage
	 private static SafeStorage<Book> bookMap =new SafeStorage<Book>();
	 
	 private static SafeStorage<User> userMap = new SafeStorage<User>();
	 
	 private static Map<Book, User> borrowMap = new ConcurrentHashMap<Book,User>();
	 /**
	  *  Save keywords user applied associated with book id , save space and remove duplicated saving books
	  */
	 private BookIndex<String> bookNameIndex = new BookIndex<String>();
	 private BookIndex<String> authorIndex = new BookIndex<String>();
	 private BookIndex<String> isbnIndex = new BookIndex<String>();
	 public LibraryManager() {
		 initialize();
	 }
	 public abstract void initialize();
	 /**
	  * Partially search book storage
	  * @param keyword
	  * @param column
	  * @param index
	  * @return List<Book>
	  */
	 public List<Book> partiallySearchWrapper(String keyword, SearchColumn column,boolean index) {
		 List<Long> ids = partiallySearch(keyword,  column,index);
		 return getBooksbyIds(ids);
	 }
	 public List<Book> indexSearchWrapper(String keyword, SearchColumn column) {
		 List<Long> ids= indexSearch(keyword, column);
		 return getBooksbyIds(ids);
	 }
	 
	 /**
	  *  Book Id maps book because book objects could be changed in dueDate, borrowed column
	  *  we can not directly create index in book
	  *  Using id list can get refresh book objects even in index Search
	  *  Ensure only one copy of book store and keep no data consistent
	  */
	 public List<Long> partiallySearch(String keyword, SearchColumn column,boolean index) {
		 List<Long> result = new ArrayList<Long>();
		 Iterator<Long> it = bookMap.getIterator(); 
		 try {
			 while(it.hasNext()) {
				 Long key = it.next();
				 Book b = bookMap.get(key);
				 if (column==SearchColumn.NAME) {
					 if (b.getName().indexOf(keyword)!=-1) {
						 result.add(b.getId());
					 }
				 } else if (column==SearchColumn.AUTHOR) {
					 if (null!=b.getAuthor() && b.getAuthor().indexOf(keyword)!=-1) {
						 result.add(b.getId());
					 }
				 } else if (column==SearchColumn.ISBN) {
					 if (null!=b.getIsbn() && b.getIsbn().equalsIgnoreCase(keyword)) {
						 result.add(b.getId());
						 return result;
					 }
	 			 }  // can add more search
	 		 }
		 } catch (WrongIDException e) {
			 System.out.println(e.getMessage());
		 }
		 /**
		  *  If user set index is true , create book index when user is searching book
		  */
		 if (index) {
			 
			 if (column==SearchColumn.NAME) {
				 bookNameIndex.put(keyword, result);
			 }  
			 if (column==SearchColumn.AUTHOR) {
				 authorIndex.put(keyword, result);
			 }  
			 if (column==SearchColumn.ISBN) {
				 isbnIndex.put(keyword, result);
			 }  
		 }
		 return result;
	 }
	 /**
	  * Search by book index to improve search performance, if keywords do not exist, just using partial search
	  *  Book Id maps book because book objects could be changed in dueDate, borrowed column
	  *  we can not directly create index in book
	  *  Using id list can get refreshed book objects even in index Search
	  */
	 public List<Long> indexSearch(String keyword, SearchColumn column) {
		 List<Long> result = new ArrayList<Long>();
		 if (column==SearchColumn.NAME) {
			 result = bookNameIndex.get(keyword);
			 
		 }  
		 if (column==SearchColumn.AUTHOR) {
			 result = authorIndex.get(keyword);
		 }  
		 if (column==SearchColumn.ISBN) {
			 result = isbnIndex.get(keyword);
		 }  
		 if (result==null) { // index did not found , call partially search
			 result = partiallySearch(keyword, column,true); 
		 }
		 return result;
	 }
	 /**
	  *  Convert id list to object list.  
	  * @param ids
	  * @return
	  */
	 public List<Book> getBooksbyIds(List<Long> ids) {
		 List<Book> blist = new ArrayList<Book>();
		 try {
			 for (Long id : ids) {
				 System.out.println("id="+id);
				 Book b = bookMap.get(id);				 
				 blist.add(b);
			 }
		 } catch (WrongIDException e) {
			 System.out.println(e.getMessage());
		 }
		 return blist;
	 }
	 // add user using random number as id
	 public void addUser(User user) {
		 
		 userMap.put(user.getUserId(), user);
	 }
	 // add user using random number as id
	 public void updateUser(Long id, User user) {
		  
		 userMap.put(id, user);
	 }
	// add user using random number as id
	 public void userBorrows(Long id, List<Book> borrows)  {
		 try { 
			 User user =userMap.get(id);
			 user.setBorrowedBooks(borrows);
 		 } catch (WrongIDException e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 // add user using random number as id
	 public void addBook(Book book) {		 
		 
		 bookMap.put(book.getId(), book);
	 }
	 // add user using random number as id
	 public void updateBook(Long id, Book book) {
		  
		 bookMap.put(id, book);
	 }
	 // add user using random number as id
	 public void borrowBook(Long id, boolean borrowed)  {
		 try { 
			 Book book = bookMap.get(id);
			 book.setBorrowed(borrowed);  
 		 } catch (WrongIDException e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 public void reset() {
		 bookMap.clear();
		 userMap.clear();
		 bookNameIndex.clear();
		 authorIndex.clear();
		 isbnIndex.clear();
	 }
	 public void displayUser() throws WrongIDException {
		 Iterator<Long> itr = userMap.getIterator();
		 while (itr.hasNext()) {
			 Long key = itr.next();
			 User book = userMap.get(key);
			 System.out.println(book.toString());
		 }
	 }
	 
	 public void displayBook() throws WrongIDException {
		 Iterator<Long> itr = bookMap.getIterator();
		 while (itr.hasNext()) {
			 Long key = itr.next();
			 Book book = bookMap.get(key);
			 System.out.println(book.toString());
		 }
	 }
	 public void addBorrowMap(Book book, User user) {
		 List<Book> list = user.getBorrowedBooks();
		 if (list==null) {
			 list = new ArrayList<Book>();
		 }
		 user.setBorrowedBooks(list);
		 book.setBorrowed(true);
		 borrowMap.put(book, user);
	 }
	 public void returnBook(Book book, User user) {
		 List<Book> list = user.getBorrowedBooks();
		 if (list!=null) {
			 list.remove(book);
		 }
		 user.setBorrowedBooks(list);
		 book.setBorrowed(false);
		 borrowMap.remove(book);
	 }
	public static SafeStorage<Book> getBookMap() {
		return bookMap;
	}
	public static void setBookMap(SafeStorage<Book> bookMap) {
		LibraryManager.bookMap = bookMap;
	}
	public static SafeStorage<User> getUserMap() {
		return userMap;
	}
	public static void setUserMap(SafeStorage<User> userMap) {
		LibraryManager.userMap = userMap;
	}
	public static Map<Book, User> getBorrowMap() {
		return borrowMap;
	}
	public static void setBorrowMap(Map<Book, User> borrowMap) {
		LibraryManager.borrowMap = borrowMap;
	}
	 
}