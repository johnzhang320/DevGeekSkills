package com.interview.questions.sample.selling.machine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * An Adapter over Map to create Inventory to hold cash and 
 * Items inside Vending Machine
 *  
 */
 
public class Inventory <T>{
	private Map<T,Integer> inventory = new ConcurrentHashMap<T,Integer>();

	/**
	 *  Get value of item  
	 */
	public int getQuantity(T item) {
		Integer count = inventory.get(item);
		return count==null? 0: count;
	}
	/**
	 *  hasItem()
	 */
	public boolean hasItem(T item) {
		return inventory.get(item)>0;
	}
	/**
	 *  Add Item
	 */
	public void add(T item) {
		if (inventory.containsKey(item)) {
			inventory.put(item, inventory.get(item)+1);
			
		} else {
			inventory .put(item, 1);
		}
	}
	public void put(T item, Integer count) {
		inventory.put(item, count);
	}
	/**
	 *  Deduct item
	 */
	public void deduct(T item) {
		if (hasItem(item)) {
			inventory.put(item, inventory.get(item)-1);
		}
	}
	/**
	 *  clear items
	 */
	public void clear() {
		inventory.clear();
	}
}
