package com.code.review.aaa.amazon.code.text;

public class FindStackMinBigOone {
	/**
	 *  amazon-interview-questions
 
		Answers
		
		Implement a stack that in addition to push and pop has a function that 
		returns the min value of the stack.
		
		I came up with a O(logn) solution, but he wanted a O(1) for the whole algorithm. 
		
		My approach:
		Create node class, int val, int min and Node next
		then define a node top point, when we push, take min = Math.min(val, curr.min) 
	 * 
	 */
	class Node {
		int val;
		int min;
		Node next;
		
	}
	Node top=null;
	public void push(int val) {
		if (top==null) {
			top = new Node();
			top.val = val;
			top.min = val;
		} else {
			Node tmp =  new Node();
			tmp.val = val;
			tmp.min = Math.min(val, top.min);
			tmp.next = top;
			top = tmp;
		}
	}
	public Node pop() {
		if (top!=null) {
			Node tmp = top;
			top = top.next;
			return tmp;
		}
		return null;
	}
	// get Min in O(1)
	public int getMin() {
		if (top!=null) {
			return top.min;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
