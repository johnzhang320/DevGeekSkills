package com.code.review.aaa.walmart.code;
 

public class LinkedListQueue<T> {
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
	public T dequeue() {
		if (first==null) {
			return null;
		} else {
			Node tmp = first;
			first = first.next;
			return tmp.val;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListQueue<Integer> ref = new LinkedListQueue<Integer>();
		for (int i=0;i<12;i++) {
			ref.enqueue(i);
			System.out.println("push "+i);
		}
		System.out.println("\n--------------------------------\n");
		for (int i=0;i<12;i++) {
			System.out.println("pop "+ref.dequeue());
		}
	}
}
