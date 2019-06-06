package com.interview.questions.sample.ATM.machine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.core.java.utils.MyUtil;
/**
 * An Adapter over Map to create index to hold cash and 
 * Items inside Vending Machine
 *  
 */
 
public class IndexStorage <T>{
	private Map<T,Integer> index = new ConcurrentHashMap<T,Integer>();

	/**
	 *  Get value of item  
	 */
	public int getQuantity(T item) {
		Integer count = index.get(item);
		return count==null? 0: count;
	}
	/**
	 *  hasItem()
	 */
	public boolean hasItem(T item) {
		return index.get(item)>0;
	}
	/**
	 *  Add Item
	 */
	public void add(T item) {
		if (index.containsKey(item)) {
			index.put(item, index.get(item)+1);
			
		} else {
			index .put(item, 1);
		}
	}
	public void put(T item, Integer count) {
		index.put(item, count);
	}
	
	/**
	 *  clear items
	 */
	public void clear() {
		index.clear();
	}
}
