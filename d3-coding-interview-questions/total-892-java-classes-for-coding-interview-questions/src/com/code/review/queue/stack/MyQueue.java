package com.code.review.queue.stack;

 
public class MyQueue<T> {
	class Node {
		T val;
		Node next;
		public Node(T val) {
			this.val = val;
		}
	}
	Node first, last;
	public void enqueue(T val) {
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
