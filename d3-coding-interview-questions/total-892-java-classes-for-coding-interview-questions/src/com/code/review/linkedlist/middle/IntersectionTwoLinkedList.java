package com.code.review.linkedlist.middle;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class IntersectionTwoLinkedList extends TestCase {
	IntersectionTwoLinkedList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new IntersectionTwoLinkedList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	 /**
	  *  Leetcode – Linked List Cycle
 

		Given a linked list, determine if it has a cycle in it.
		
		Analysis
		
		If we have 2 pointers - fast and slow. It is guaranteed that the fast one will 
		meet the slow one if there exists a circle.
		
		The problem can be demonstrated in the following diagram:
	  
	  */
	
	  public boolean hasCycle(Node head) {
	        Node fast = head;
	        Node slow = head;
	 
	        while(fast != null && fast.next != null){
	            slow = slow.next;
	            fast = fast.next.next;
	 
	            if(slow == fast)
	                return true;
	        }
	 
	        return false;
	  }
	 

}
