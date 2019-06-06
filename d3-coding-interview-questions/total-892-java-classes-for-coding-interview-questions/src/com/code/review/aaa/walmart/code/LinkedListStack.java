package com.code.review.aaa.walmart.code;

 

public class LinkedListStack<T> {
	class Node {
		T val;
		Node next;
		public Node(T val) {
			this.val = val;
		}
	}
	Node top;
	public void push(T val) {
		if (top==null) {
			top = new Node(val);
		} else {
			Node tmp = new Node(val);
			tmp.next = top;
			top = tmp;
		}
	}
	public T pop() {
		if (top==null) {
			return null;
		} else {
			Node tmp = top;
			 
			top = top.next;
 			return tmp.val;
		}
	}
	public T peek() {
		if (top==null) {
			return null;
		} else {
			 
 			return top.val;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListStack<Integer> ref = new LinkedListStack<Integer>();
		for (int i=0;i<12;i++) {
			ref.push(i);
			System.out.println("push "+i);
		}
		System.out.println("\n--------------------------------\n");
		for (int i=0;i<12;i++) {
			System.out.println("pop "+ref.pop());
		}
	}

}
