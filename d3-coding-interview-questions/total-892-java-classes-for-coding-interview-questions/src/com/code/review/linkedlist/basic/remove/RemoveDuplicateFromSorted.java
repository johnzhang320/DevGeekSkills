package com.code.review.linkedlist.basic.remove;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class RemoveDuplicateFromSorted extends TestCase {
	RemoveDuplicateFromSorted ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new RemoveDuplicateFromSorted();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * remove duplicated data from linkedlist;
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.

		For example,
		
		Given 1->1->2, return 1->2.
		Given 1->1->2->3->3, return 1->2->3.

	 * @param head
	 * @return
	 */
	public Node removeDuplicated(Node head) {
		if (head==null || head.next==null) return head;
		Node prev = head;
		Node p = head.next;
	
		while(p!=null) {
			if (prev==null && p==null) break;
			
			int data1 =(int) prev.data;
			
			int data2 =(int) p.data;
			
			if (data1 == data2) {				
				prev.next = p.next;
			} else {
				prev = p;
			}
			p = p.next;
		}
		return head;
	}
	public void testAddTwoNumberOfLinkedList() {
		System.out.println("AddTwoNumberOfLinkedList()");
		Integer data [] = {1,1,1,2,2,3,3,5,6,6,8,9,9,4,4};
		L1.initialLinkedList(data);
		Node n = ref.removeDuplicated(L1.head);		
		System.out.println("\n--------------Removed------------");
		L1.display(); 
		 
	}

}
