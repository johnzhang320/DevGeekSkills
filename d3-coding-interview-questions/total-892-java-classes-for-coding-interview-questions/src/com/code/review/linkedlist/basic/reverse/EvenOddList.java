package com.code.review.linkedlist.basic.reverse;

import org.junit.Assert;

 
import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class EvenOddList extends TestCase {
	EvenOddList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new EvenOddList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	  LeetCode – Odd Even Linked List (Java)
 
		
		Problem
		
		Given a singly linked list, group all odd nodes together followed by the even nodes.
		 Please note here we are talking about the node number and not the value in the nodes.
		
		The program should run in O(1) space complexity and O(nodes) time complexity.
		
		Example:
		
		Given 1->2->3->4->5->NULL,
		return 1->3->5->2->4->NULL.
		
		Analysis
		
		This problem can be solved by using two pointers. We iterate over the link and move the two pointers. 
	 */
	 
	public Node oddEvenList(Node head) {
	    if(head == null) 
	        return head;
	 
	    Node result = head;
	    Node p1 = head;
	    Node p2 = head.next;
	    Node connectNode = head.next;
	 
	    while(p1 != null && p2 != null){
	            Node t = p2.next;
	            if(t == null)
	                break;
	 
	            p1.next = p2.next;
	            p1 = p1.next;
	 
	            p2.next = p1.next;
	            p2 = p2.next;
	    }
	 
	    p1.next = connectNode;
	 
	    return result;
	}
	
	public void testreverseRightHalf() {
		System.out.println("oddEvenList()");
		Integer data [] = {1,2,3,4,5,6,7,8};
		L1.initialLinkedList(data);
		Node n = ref.oddEvenList(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		 
	}
 
}
