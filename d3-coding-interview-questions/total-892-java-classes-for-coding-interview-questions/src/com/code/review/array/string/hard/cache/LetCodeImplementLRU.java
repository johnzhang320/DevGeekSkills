package com.code.review.array.string.hard.cache;

import java.util.HashMap;

public class LetCodeImplementLRU {
	/**
	 * 
	 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the
	 *  following operations: get and set.

		get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
		otherwise return -1.
		set(key, value) - Set or insert the value if the key is not already present. When the cache 
		reached its capacity, it should invalidate the least recently used item before inserting a new item.
		
		Analysis
		
		The key to solve this problem is using a double linked list which enables us to quickly move nodes.
		
		LRU-Cache
		
		The LRU cache is a hash table of keys and double linked nodes. The hash table makes the time of 
		get() to be O(1). The list of double linked nodes make the nodes adding/removal operations O(1). 
	 */
	class Node{
	    int key;
	    int value;
	    Node pre;
	    Node next;
	 
	    public Node(int key, int value){
	        this.key = key;
	        this.value = value;
	    }
	}
	public class LRUCache {
	    int capacity;
	    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	    Node head=null;
	    Node end=null;
	 
	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	    }
	 
	    public int get(int key) {
	        if(map.containsKey(key)){
	            Node n = map.get(key);   // get from map O(1)
	            remove(n);               // remove n from anywhere from double linked list
	            setHead(n);              // add n to head of double linked list, refresh n from cache
	            return n.value;
	        }
	 
	        return -1;
	    }
	    /**
	     * Remove is most complicated, we need check if n is head node or end node
	     * Remove node from double linked list
	     * Prev Use Case 1: n.pre !=null means n is after head node, n.pre.next = n.next
	     * Prev Use Case 2: n.pre ==null means n is head node, head = n.next
	     * 
	     * Next: Use Case 1: n.next !=null, means n is not end node, then n.next.pre = n.pre;
	     * Next: Use Case 2: n.next ==null, means n is end node, then end=n.pre
	     * 
	     */
	    public void remove(Node n){
	        if(n.pre!=null){
	            n.pre.next = n.next;
	        }else{
	            head = n.next;
	        }
	 
	        if(n.next!=null){
	            n.next.pre = n.pre;
	        }else{
	            end = n.pre;
	        }
	 
	    }
	    /**
	     *  set n to head of double linked list
	     *  Use Case 1: always change n.next point to head of list, then n.next = head; 
	     *  Use Case 2: always make n.pre = null
	     *  Use Case 3: if current head!=null, head.pre = n;
	     *  Use Case 4: head always is set to n, head = n after Use Case 1 to 3 finished
	     *  Use Case 5: if end==null, end = head;
	     * 
	     */
	    public void setHead(Node n){
	        n.next = head;
	        n.pre = null;
	 
	        if(head!=null)
	            head.pre = n;
	 
	        head = n;
	 
	        if(end ==null)
	            end = head;
	    }
	    /**
	     * set key/value to LRU cache
	     * Use Case 1: key exists in Cache, refresh value of old node, remove it from anywhere from double linked list and place it to head
	     *             (1) check if key is in map, if exists, fetch value from map, update value with new value
	     *             (2) remove old node from anywhere of double linked list
	     *             (3) set updated node(with new value) to head of linked list
	     *  Use Case 2: key does not exists in Cache, check if map.size() >= capacity, if yes, remove end 
	     *              node from Map and double linked list, create a new node and set it at head of linked list
	     *              if map.size() <capacity, just add new node object to map
	     */
	    public void set(int key, int value) {
	        if(map.containsKey(key)){
	            Node old = map.get(key);
	            old.value = value;
	            remove(old);
	            setHead(old);
	        }else{
	            Node created = new Node(key, value);
	            if(map.size()>=capacity){
	                map.remove(end.key);
	                remove(end);
	                setHead(created);
	 
	            }else{
	                setHead(created);
	            }    
	 
	            map.put(key, created);
	        }
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
