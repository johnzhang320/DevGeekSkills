package com.code.review.linkedlist.hard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.istack.internal.logging.Logger;

/**
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following 
 * operations: get and set.

	get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise 
	return -1.
	set(key, value) - Set or insert the value if the key is not already present. When the cache reached its 
	capacity, it should invalidate the least recently used item before inserting a new item.
	
	Analysis
	
	The key to solve this problem is using a double linked list which enables us to quickly move nodes. 
 *
 */
public class MyLRU<K,V> {
	static Logger Log = Logger.getLogger(MyLRU.class);
	 class Node {
			K key;
			V val;
			Node prev;
			Node next;
			public Node(K key, V val) {
				this.key=key;
				this.val=val;
				this.prev=null;
				this.next=null;
			}
	}
	public Node head=null;
	public Node tail=null;
	private long capacity=10000L;
	public MyLRU() {}
	public MyLRU(long capacity) {
		this.capacity = capacity;
	}
	private Map<K, Node> map = new ConcurrentHashMap<K,Node>(); 
	/*------------------Low level utilities -----------------*/
	// remove double linked list make O(1) possible, single linked list does not maintain previous element point
	// only keep next point, when we delete we have to scan again to find previous node(prev.next=n) of current node
	private void remove(Node n) { 
		Lock lock = new ReentrantLock();  // protect critical section
			lock.lock();
			if (n.prev!=null) {           // n.prev exist
				n.prev.next = n.next;     // n.prev.next point to n.next 
			} else {   // n.prev == null mean n current is head
				head=n.next;   // make head point to n.next
			}
			if (n.next!=null) {   // n.next element exist,  
				n.next.prev = n.prev;  // next element prev point to n.prev 
			} else {
				tail = n.prev;    // if n.next not exist, means end, tail = n.prev 
			}
		lock.unlock();
	}
	private void setHead(Node n) {
		Lock lock = new ReentrantLock();
	 	lock.lock();
		n.prev = null;
		n.next = head;
		if (head!=null) {
			head.prev = n;
		}
		head = n;
		if (tail==null) {
			tail = head;
		}
		lock.unlock();
	}
	/*------------------Public Service Utilities ------------------*/
	// case 1 Reuse , first of all we need to check if key in Map for reuse, remove node and sethead
	// case 2 new object, check if map.size() reaches capacity or not
	public synchronized void set(K key, V val) {
		if (map.containsKey(key)) {
			Node reuseNode = map.get(key);
			reuseNode.val = val;
			// refresh it age
			remove(reuseNode);
			setHead(reuseNode);
		} else { // totally new object
			Node newNode = new Node(key,val);
			if (map.size()>=capacity) {		
				//Log.info("Thread:"+Thread.currentThread().getName()+" find reach capacity!");
				map.remove(tail.key);
				remove(tail);				 
				map.put(key,newNode);
				setHead(newNode);
			} else {
				map.put(key,newNode);
				setHead(newNode);
			}
		}
	}
	public int size() {
		return map.size();
	}
	 
	 
	// get data if it exists in Map and then remove data and set it to head as refresh
	public synchronized V get(K key) {
		if (map.containsKey(key)) {
			Node n = map.get(key);
			remove(n);
			setHead(n);
			return n.val;
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
