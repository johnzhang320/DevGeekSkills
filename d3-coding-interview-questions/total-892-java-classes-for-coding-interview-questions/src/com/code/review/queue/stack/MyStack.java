package com.code.review.queue.stack;

public class MyStack<T> {
	class Node {
		T val;
		Node next;
		public Node(T val) {
			this.val = val;
		}
	}
	private Node top;
	public void push(T val) {
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
	public Node peek() {
		if (top!=null) {
			return top;
		}
		return top;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyStack<String> stack = new MyStack<String>();
		stack.push("Hello");
		stack.push("World");
		System.out.println(stack.pop().val);
		System.out.println(stack.pop().val);
		
	}

}
