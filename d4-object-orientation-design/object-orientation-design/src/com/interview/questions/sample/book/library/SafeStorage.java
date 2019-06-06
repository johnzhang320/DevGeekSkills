package com.interview.questions.sample.book.library;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Save book , user and others are used as T for any Map<Long,T>
 * Long mean id of item, T is item reply on ID
 * When we create search index , the keywords should be associated id of item
 * instead of item object
 * 
 * @author jzhang
 *
 * @param <T>
 */
public class SafeStorage<T> {
	 
	// consider millions of user access concurrently, object level lock to write without lock entire collections 
	private Map<Long, T> storage = new ConcurrentHashMap<Long,T>();
	
	 
	public SafeStorage() {}
	
	public T get(Long id) throws WrongIDException {
		T retVal=null;
		 
		if (storage.containsKey(id)) {
			retVal= storage.get(id);
		} else {
			throw new WrongIDException(id + " is wrong id");
		}
		 
		return retVal;
	}
	public synchronized void put(Long id, T obj) {
		storage.put(id, obj);
	}
	public Iterator<Long> getIterator() {
		return storage.keySet().iterator(); 
	}
	public boolean findId(Long id) {
		boolean retVal=false;
		 
		if (storage.containsKey(id)) {
			retVal=true;
			 
		}  
		return retVal;
	}
	public void clear() {
		storage.clear();
	}
}
