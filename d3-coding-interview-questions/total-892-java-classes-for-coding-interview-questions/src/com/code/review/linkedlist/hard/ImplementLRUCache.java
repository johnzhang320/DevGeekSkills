package com.code.review.linkedlist.hard;

import java.util.HashMap;
import java.util.Map;

public class ImplementLRUCache {
/**
 *  Design and implement a data structure for Least Recently Used (LRU) cache. It should support the 
 *  following operations: get and set.
 *  
 *  Algorithm of Least Recently Used (LRU): 
 *  (1) Update or Insert data always save new data to LinkedList head to ensure newest data could be
 *      stay in cache longest, non refreshed data will be pushed closer and closer to end of linked list
 *      which mean data the less changed, the more possible to be aged out from cache
 *  (2) Why we used double linked list and map, any time we read or change data which already exists in 
 *      cache we had to re-adjust data location to head in linked list, which mean we have to delete data from
 *      current location and put it at head of linked list, when we do the deletion operation we must know previous node
 *      of current node, single linked list must be search from head to previous one of current node, worest 
 *      case is O(N) , use double linked list avoid this searching operation by pick up previous node directly and ensure O(1) to delete node

	get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
	otherwise return -1.
	set(key, value) - Set or insert the value if the key is not already present. When the cache 
	reached its capacity, it should invalidate the least recently used item before inserting a new item.
	
	Analysis
	
	The key to solve this problem is using a double linked list which enables us to quickly move nodes. 
	Important thing is
	check if key is available in map, if yes, delete key node from current position from double linked list
	insert it to head of linked list 
	
	
	
 * @param args
 */
	/**
	 * double linked list
	 * 
	 * @param args
	 */
	class Node {
		int key;
		int value;
		Node pre;
		Node next;
		
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.pre = null;
			this.next = null;
		}
	}
	
	int capacity;
	Map<Integer, Node> map = new HashMap<Integer,Node>();
	public Node head = null;
	public Node end = null;   // why we have to use double linked list instead of single linked list
	                   // because when we delete end element, we have to let prev.next =null
	                   // besides traversal entire single linked list, there is no way to know previous 
	                   // element of end element. However when size() >= capacity , actually we always delete
	                   // end element first
	
	public ImplementLRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer,Node>(this.capacity);
	}
	/**
	 *  
	 * @param n
	 */
	// Double linked list, head.prev = null, end.next = null and end.prev = head
	public void linkedListRemove(Node n) {
		// now process previous node next point to n.next 
		if (n.pre!=null) {  // if n.prev exist, means middle element, n.prev.next = n.next
			n.pre.next = n.next;   
		} else {
			head = n.next;   // if n.prev not exist, we try to delete head, head = n.next
		}
		// now process previous pointer
		if (n.next==null) {  // if n.next == null, means end pointer, end become previous 
			end = n.pre;
		} else {
			n.next.pre = n.pre;   // n.next !=null, middle element , next element previous pointer to n.pre
		}
	}
	public void setHead(Node n) {
		// always add element at head, no matter head empty or not, n.next = head for sure, n.pre = null
		n.next = head;
		n.pre = null;
		if (head!=null) {  // if head !=null, head.pre must pointer to n
			head.pre = n;
		}
		head = n;          // move head to n
		if (end == null) { // if end is empty, it must be head
			end = head;
		}
	}
	public void set(int key, int value) {
		if (map.containsKey(key)) {
			Node old = map.get(key);
			old.value = value;
			linkedListRemove(old);   // delete current node
			setHead(old);            // put it to head of linkedList
		} else {
			Node newNode = new Node(key,value);
			if (map.size()>capacity) {
				map.remove(end.key);
				linkedListRemove(end);
				map.put(key, newNode);
				setHead(newNode);
			} else {
				setHead(newNode);
				map.put(key, newNode);
			}
		}
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			Node n = map.get(key);
			linkedListRemove(n);
			setHead(n);
			return n.value;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int key[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		int value[] = {10,20,30,40,50,60,70,80,90,100,110,120,130,140};
		int capacity = 15;
		ImplementLRUCache ref =new ImplementLRUCache(capacity); 
		for (int i=0; i<key.length;i++) {
			ref.set(key[i], value[i]);
		}
		System.out.println("Initialized:");
		Node p=ref.head;
		int i=0;
		while (i<14) {
			System.out.println("key:"+p.key+",value:"+p.value);
			i++;
			p=p.next;
		}
		System.out.println("Get value:"+ref.get(5));
		System.out.println("After get(5):");
		p=ref.head;
		i=0;
		while (i<13) {
			System.out.println("key:"+p.key+",value:"+p.value);
			i++;
			p=p.next;
		}
		
	}

}
