package com.code.review.aaa.Fortinet.code;

import junit.framework.TestCase;

/**
 * 
 * https://mail.google.com/mail/u/0/?tab=wm#search/reverse/15e76edbe053c97b
 *
 */
public class LinkedListReverse extends TestCase {
	private LinkedListReverse ref =null;
	class Node{
		 Node next;
		 int value;
			 public Node(int val) {
				 value = val;
			 }
	}
	// Your resolution 
	// Time complexity:  O(n)
	// Space complexity: O(1) 
	public Node reverse(Node head){
		Node current =head;
		Node result = null;
		Node next;
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
			 
			
		}
		return result;
	}
	// JUnit test cases  
	public void setUp() {
		ref =  new LinkedListReverse();
	}
	public void testReverse() {
		Node n1 = new Node(1); 
		Node n2 = new Node(2); 
		Node n3 = new Node(4); 
		Node n4 = new Node(5); 
		Node n5 = new Node(7); 
		Node n6 = new Node(9); 
		n1.next = n2;
		n2.next= n3;
		n3.next = n4;
		n4.next=n5;
		n5.next = n6;
		n6.next=null;
		Node reversed = ref.reverse(n1);
		Node p = reversed;
		while (p!=null) {
			System.out.print(p.value+" ");
			p = p.next;
		}
	}
}
