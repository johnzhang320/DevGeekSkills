package com.interview.questions.sample.book.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *  T can be isbn, id, fully book name or partially name, author name or partially name
 */
 
public class BookIndex <T>{
	private Map<T,List<Long>> bookIndex = new HashMap<T,List<Long>>();

	/**
	 *  Get value of item  
	 */
	public int getQuantity(T item) {
		List count = bookIndex.get(item);
		return count==null? 0: count.size();
	}
	/**
	 *  hasItem()
	 */
	public boolean hasItem(T item) {
		return bookIndex.get(item).size()>0;
	}
	/**
	 *  Add Item
	 */
	public void put(T item,List<Long> list) {
		 
		bookIndex.put(item, list);
	}
	public List<Long> get(T item ) {
		if (bookIndex.containsKey(item)) { 
			return bookIndex.get(item);
		} 
		return null;
	} 
	/**
	 *  clear items
	 */
	public void clear() {
		bookIndex.clear();
	}
}
