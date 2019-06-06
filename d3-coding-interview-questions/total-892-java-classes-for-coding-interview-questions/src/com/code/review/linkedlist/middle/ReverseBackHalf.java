package com.code.review.linkedlist.middle;

import org.junit.Assert;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class ReverseBackHalf extends TestCase {
	ReverseBackHalf ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new ReverseBackHalf();
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
	 
	public static Node reverseBackHalf(Node head) {
		// find middle of linkedlist
		Node mid = head;
		Node fast = head;
		while (fast.next!=null) {
			mid = mid.next;
			fast = fast.next;
			if (fast!=null) fast = fast.next;
			 
		}
		System.out.println("\n");
		System.out.println("Middle="+mid.data);
		System.out.println("\n");
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
	public void testAddTwoNumberOfLinkedList() {
		System.out.println("Reverse back half Linked List()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
		Node n = ref.reverseBackHalf(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		 
	}

}
