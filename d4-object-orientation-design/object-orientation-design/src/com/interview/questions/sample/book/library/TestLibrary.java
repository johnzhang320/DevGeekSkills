package com.interview.questions.sample.book.library;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;




public class TestLibrary {
	 
	private static SunnyvaleLibrary su;
	@BeforeClass
    public static void setUp(){
        su = new SunnyvaleLibrary();
        
    }
	@AfterClass
    public static void tearDown(){
    	
        su = null;
    }
	@Test
    public void testInitializedData() throws WrongIDException {
		// run initialize() code in sub class
		 su.displayBook();
		 su.displayUser();
 	}
	
	@Test
    public void testPartiallySearch(){
		List<Book> books=su.partiallySearchWrapper("Highminway", SearchColumn.AUTHOR, true);
		assertEquals(2,books.size());
		books=su.partiallySearchWrapper("Program", SearchColumn.NAME, true);
		assertEquals(3,books.size());		
		List<Book> books1=su.partiallySearchWrapper("isbn321311456", SearchColumn.ISBN, true);
		assertEquals(1,books1.size());

 	}
	@Test
    public void testIndexSearch(){
		List<Book> books=su.indexSearchWrapper("Highminway", SearchColumn.AUTHOR);
		assertEquals(2,books.size());
		books=su.indexSearchWrapper("Program", SearchColumn.NAME);
		assertEquals(3,books.size());		
		List<Book> books1=su.indexSearchWrapper("isbn321311456", SearchColumn.ISBN);
		assertEquals(1,books1.size());
 	}
	@Test
    public void testborrowBook() throws WrongIDException {
		Book book = su.getBookMap().get(su.bookId);
		User user = su.getUserMap().get(su.userId);
		su.addBorrowMap(book, user);
		assertEquals(1,su.getBorrowMap().size());
		
 	}
	@Test
    public void testReturnBook() throws WrongIDException {
		Book book = su.getBookMap().get(su.bookId);
		User user = su.getUserMap().get(su.userId);
		su.returnBook(book, user);
		assertEquals(0,su.getBorrowMap().size());
  	}
}