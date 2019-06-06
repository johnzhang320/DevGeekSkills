package com.code.review.linkedlist.hard;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class CopyListWithRandomPointer extends TestCase {
	CopyListWithRandomPointer ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new CopyListWithRandomPointer();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	 /**
	  *   LeetCode – Copy List with Random Pointer
 

		A linked list is given such that each node contains an additional random pointer which
		 could point to any node in the list or null.
		
		Return a deep copy of the list.
		
		Java Solution 1
		
		We can solve this problem by doing the following steps:
		
		    copy every node, i.e., duplicate every node, and insert it to the list
		    copy random pointers for all newly created nodes
		    break the list to two
	  
	  */
	
	public Node copyRandomList(Node head) {
		 
		if (head == null)
			return null;
	 
		Node p = head;
	 
		// copy every node and insert to list
		while (p != null) {
			Node copy = new Node(p.data);
			copy.next = p.next;
			p.next = copy;
			p = copy.next;
		}
	 
		// copy random pointer for each new node
		p = head;
		while (p != null) {
			if (p.random != null)
				p.next.random = p.random.next;
			p = p.next.next;
		}
	 
		// break list to two
		p = head;
		Node newHead = head.next;
		while (p != null) {
			Node temp = p.next;
			p.next = temp.next;
			if (temp.next != null)
				temp.next = temp.next.next;
			p = p.next;
		}
	 
		return newHead;
	}
	 

}
