package com.code.review.linkedlist.basic;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class ReverseLinkedList extends TestCase {
	ReverseLinkedList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new ReverseLinkedList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	public Node reverse(Node head) {
		Node current = head;
		Node next;
		Node result = null;
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
		}
		return result;
	}
	/**
	 *  LeetCode standard reverse linked list
	 */
	public Node reverseList (Node head) {
		if (head==null || head.next ==null) return head;
		Node p1 = head;
		Node p2 = head.next;
		head.next = null;
		while (p1!=null && p2!=null) {
			Node t = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = t;
		}
		return p1;
	}
	public void testAddTwoNumberOfLinkedList() {
		System.out.println("Reverse Linked List()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
	/*	Node n = ref.reverse(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}*/
		System.out.println("-------- ReverseList----------------");
		Node n1 = ref.reverseList(L1.head);
		while (n1!=null) {
			System.out.print(n1.data+"->");
			n1= n1.next;
		}
		
	}

}
