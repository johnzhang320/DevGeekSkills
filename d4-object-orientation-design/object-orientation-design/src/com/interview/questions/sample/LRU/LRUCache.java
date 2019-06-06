package com.interview.questions.sample.LRU;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {
	/**
	 *  Create Map<key, Node>,consider million of user access, apply object lock 
	 *  ConcurrentHashMap
	 */
	public Map<Integer,Node> map = new ConcurrentHashMap<Integer,Node>();
	Node head=null;
	Node end=null;
	int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * setHead, must check head is empty and end is empty
	 * handle n.prev and n.next
	 * @param n
	 */
	private synchronized void setHead(Node n) {
		n.next = head;
		n.prev = null;
		if (head!=null) {
			head.prev = n;
		}
		head = n;
		if (end==null) {
			end = head;
		}
	}
	/**
	 * Remove Node, check n.prev and n.next is null,
	 * double linked list, head.prev=null , end.prev = head, end.next = null
	 * @param n
	 */
	private synchronized void removeNode(Node n) {
		if (n.prev==null) { // n is head
			head = n.next;
			head.prev= null;
		} else {
			n.prev.next = n.next;
		}
		if (n.next ==null) { // n is end
			n.prev.next = null;
			end = n.prev;
		} else {
			n.next.prev = n.prev;
		}
 	}
	/**
	 *  APIs Manage LRU Map
	 */
	/**
	 * Check if key registered in map , once it exists, 
	 * fetch value from map and delete it from linked list
	 * @param key
	 * @return
	 */
	public int get(int key) {
		int retVal=-1;
		try {
			if (map.containsKey(key)) {
				Node n = map.get(key);
				retVal = n.value;
				removeNode(n);   // remove from current location
				setHead(n);      // add to head , which means refresh object
				return retVal;
			}
			throw new KeyNotFoundException("key "+key+" was not found in LRU cache");
		} catch(KeyNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return retVal;
	}
	/**
	 * Check if key exists in map, if exists, do not care capacity, just remove node and add it to head
	 * if not exists, create new node, check if map.size() exceeds capacity or not, if yes, just remove end
	 * node from map and linked list, add new node to head and map
	 * @param key
	 * @param value
	 */
	public void set(int key, int value) {
		if (map.containsKey(key)) {
			Node n = map.get(key);
			removeNode(n);
			setHead(n);
		} else {
			Node currNode = new Node(key,value);
			if (map.size()>=this.capacity) {
				System.out.println("adding value "+value+" deleting end value "+end.value);
				map.remove(end.key);
				removeNode(end);
			}
			map.put(key, currNode);
			setHead(currNode);
		}
	}
	public void display() {
		System.out.println("\nLinked List");
		Node n = head;
		while(n!=null) {
			System.out.print("Key("+n.key+") value("+n.value+")"+" ");
			n=n.next;
		}
		System.out.println("\nMap ");
		Iterator<Integer> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next();
			Node node = map.get(key);
			int value = node.value;
			System.out.print("Key("+key+") value("+value+")"+" ");
		}
	}
}
