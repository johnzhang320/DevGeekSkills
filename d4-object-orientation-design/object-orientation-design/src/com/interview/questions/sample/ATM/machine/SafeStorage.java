package com.interview.questions.sample.ATM.machine;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Save account , user and others are used as T for any Map<Integer,T>
 * Integer mean id of item, T is item reply on ID
 
 * @author jzhang
 *
 * @param <T>
 */
public class SafeStorage<T> {
	 
	// consider millions of user access concurrently, object level lock to write without lock entire collections 
	private Map<Integer, T> storage = new ConcurrentHashMap<Integer,T>();
	
	 
	public SafeStorage() {}
	
	public T get(Integer id) throws WrongIDException {
		T retVal=null;
		 
		if (storage.containsKey(id)) {
			retVal= storage.get(id);
		} else {
			throw new WrongIDException(id + " is wrong id");
		}
		 
		return retVal;
	}
	public synchronized void put(Integer id, T obj) {
		storage.put(id, obj);
	}
	public Iterator<Integer> getIterator() {
		return storage.keySet().iterator(); 
	}
	public boolean findId(Integer id) {
		boolean retVal=false;
		 
		if (storage.containsKey(id)) {
			retVal=true;
			 
		}  
		return retVal;
	}
	 
	public Map<Integer, T> getStorage() {
		return storage;
	}

	public void clear() {
		storage.clear();
	}
}
