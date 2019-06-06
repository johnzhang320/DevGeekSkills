package com.code.review.linkedlist.basic;

public class MyStack {
	class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	Node top;
	public void push(int val) {
		if (top==null) {
			top = new Node(val);
		} else {
			Node tmp = new Node(val);
			tmp.next = top;
			top = tmp;
		}
	}
	public Node pop() {
		if (top==null) {
			return null;
		} else {
			Node tmp = top;
			top = top.next;
			return tmp;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
