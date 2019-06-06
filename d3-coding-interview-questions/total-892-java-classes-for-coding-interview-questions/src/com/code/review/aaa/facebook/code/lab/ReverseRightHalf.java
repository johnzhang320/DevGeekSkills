package com.code.review.aaa.facebook.code.lab;

import org.junit.Assert;

 
import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class ReverseRightHalf extends TestCase {
	ReverseRightHalf ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new ReverseRightHalf();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * Reverse back half linkedList after middle node
	 * 
	 * Solution we have to create head2 to save the current node before it change
	 * then mid.next = head2;
	 * @author John Zhang
	 *
	 */
	 
	public static Node reverseRightHalf(Node head) {
		// find middle of linkedlist
		Node mid = head;
		Node fast = head;
		while (fast.next!=null) {
			mid = mid.next;
			fast = fast.next;
			if (fast!=null) fast = fast.next;
			 
		}
		 
		// reverse right half linked list 
		Node result = null;
		Node current = mid.next;
		Node next;
		Node head2=current; 
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			head2=current;
			current = next;
		}
		mid.next = head2; 
		return head;
	}
	
	public void testreverseRightHalf() {
		System.out.println("Reverse back half Linked List()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
		Node n = ref.reverseRightHalf(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		 
	}
	// Second half reverse using swap concept for reverse linked list
	public Node reverseSecondHalf(Node head) {
		if (null == head || null==head.next) return head;
		Node fast = head;
		Node slow = head;
		while (fast.next!=null && fast.next.next!=null) {
			slow = slow.next;
			fast = fast.next;
			fast = fast.next;
		}
		Node secondHead = slow.next;
		slow.next = null;
		Node p1 = secondHead;
		Node p2 = p1.next;
		while (p2 !=null) {
			Node tmp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = tmp;
		}
		secondHead.next = null;
		
		slow.next = p1;
		return head;
		 
		
		//return head;
	}
	public void testreverseSecondHalf() {
		System.out.println("\nreverseSecondHalf()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
		Node n = ref.reverseSecondHalf(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		System.out.println(""); 
	}
}
