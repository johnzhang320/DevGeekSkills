package com.code.review.linkedlist.basic;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;
import junit.framework.TestCase;

public class PrintSingleLinkedListBackward extends TestCase {
	PrintSingleLinkedListBackward ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new PrintSingleLinkedListBackward();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * Print Single Linked List backward
	 * No change to double linked, no reverse linked list
	 * use O(1)~O(n) space
	 * (1) Tail Recurse linked list
	 * (2) add linked list to stack
	 * @param args
	 */
	public void printRecursive(Node head) {
		if (head==null) return;
		printRecursive(head.next);
		System.out.print(head.data+" ");
 	}
	public void testPrintRecursive() {
		// TODO Auto-generated method stub
		Integer data [] = {2,3,11,4,5,6,7,10};
		L1.initialLinkedList(data);
		ref.printRecursive(L1.head);		
	}

}
