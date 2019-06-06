package com.code.review.facebook.code.lab;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class InsertSortLinkedList extends TestCase {
	InsertSortLinkedList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new InsertSortLinkedList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 *  Insertion Sort List:

		Sort a linked list using insertion sort.
		
		This is my accepted answer for LeetCode problem - Sort a linked list using insertion sort 
		in Java. It is a complete program.
		
		1. Create internalInsert method: which travels current linked list 
		if head.data>newNode.data 
		newNode.next = head;
		head = newNode;
		else
		 newNode.data<current.data
		newNode.next = current.next
		current.next = newNode;
		
		2. Create SortByInsert method: travel each node, if call sortedInsert method
		
	 */
	
    public Node interalInsert(Node head, Node newNode) {
    	if (head==null || head.data>=newNode.data) {  
    		newNode.next = head;
    		head = newNode;
    	} else {
    		Node current = head;
    		while (current.next!=null && current.next.data<=newNode.data) {
    			current = current.next;
    		}
    		newNode.next = current.next;
    		current.next = newNode;
    	}
    	return head; 
    }
	public Node sortbyInsert(Node head) {
		Node current = head;
		Node result = null;
		Node next;
		while (current!=null) {
			next = current.next;
			result = interalInsert(result, current);
			current = next;
		}
		head = result; 
		return head;
			
	}
	 
	public void testMergeTwoSorteds() {
		System.out.println("sortbyInsert()");
		Integer data [] = {7,3,2,4,6,9,5,10,1,15};
		 
		L1.initialLinkedList(data);  
	 
		Node n = ref.sortbyInsert(L1.head);		
		System.out.println("\n--------------Merged------------");
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n = n.next;
		}
		 
	}

}
