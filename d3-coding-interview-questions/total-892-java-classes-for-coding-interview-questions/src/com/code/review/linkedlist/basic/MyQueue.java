package com.code.review.linkedlist.basic;
 

public class MyQueue {
	class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	Node first, last;
	public void enqueue(int val) {
		if (first==null) {
			first=new Node(val);
			last = first;
		} else {
			Node tmp = new Node(val);
			last.next = tmp;
			last = last.next;
		}
	}
	public Node dequeue() {
		if (first==null) {
			return null;
		} else {
			Node tmp = first;
			first = first.next;
			return tmp;
		}
	}
	
}
