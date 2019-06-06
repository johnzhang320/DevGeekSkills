package com.code.review.linkedlist.hard;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLRUCache<Key, Value> {
	/**
	 *   Java Cache : Static data loading
		 Application performance highly depends on how the data is loaded into memory and how 
		 it is reused during application processing. If our application does database query 
		 for each static \client data which is rarely changing then we need to consider putting
		 this static data in memory\cache. The application performance increase drastically 
		 if we reuse this static data from memory. In the market we have lots of open source 
		 cache implementation available like ehCache, osCache[Terracota], Guava library etc.
		 In some cases these open source implementation can be more complicated compare to 
		 application requirement. After java 1.5 concurrent collections it is become very
		 handy to write own cache. In static data cache we always want to control memory 
		 foot print and we use LRU [ Least recently Used] idiom to do this. We fix the
		 size of cache and clear the oldest cache data if the cache needs to be refreshed
		 behind its size. Here is example of LRU cache which is backed by ConcurrentHashMap 
		 and controlled by ConcurrentLinkedQueue.
		
		Cache simply means loading the data by some key and it should be in memory. If we are 		
		running our application in clustered mode then available implémentation is Coherence 
		which can isolated interaction with DB and provided fail over and syncronization between
		multiple application cache.
		
		Here is example how we can build LRU cache in java using concurrent package. This 
		implementation will be fragile and blocking if we would have implemented cache using 
		old synchronize idiom. Cache data is backed up by ConcurrentHashMap so that reading
	    from cache is not blocked and writing to cache will have concurrent effect, it means
		only the buckets will be locked during write operation.

	 * @param args
	 */
	

	private final int maxSize;
	private ConcurrentHashMap<Key, Value> map;
	private ConcurrentLinkedQueue<Key> queue;

	public ConcurrentLRUCache(final int maxSize) {
	    this.maxSize = maxSize;
	    map = new ConcurrentHashMap<Key, Value>(maxSize);
	    queue = new ConcurrentLinkedQueue<Key>();
	}

	/**
	 * @param key - may not be null!
	 * @param value - may not be null!
	 */
	public void put(final Key key, final Value value) {
	    if (map.containsKey(key)) {
	        queue.remove(key); // remove the key from the FIFO queue
	    }

	    while (queue.size() >= maxSize) {
	        Key oldestKey = queue.poll();
	        if (null != oldestKey) {
	            map.remove(oldestKey);
	        }
	    }
	    queue.add(key);
	    map.put(key, value);
	}

	/**
	 * @param key - may not be null!
	 * @return the value associated to the given key or null
	 */
	public Value get(final Key key) {
	    return map.get(key);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
