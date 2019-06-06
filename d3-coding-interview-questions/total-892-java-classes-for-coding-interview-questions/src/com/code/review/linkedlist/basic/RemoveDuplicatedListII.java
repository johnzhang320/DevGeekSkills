package com.code.review.linkedlist.basic;

import com.code.utils.LinkedList.LinkedListUtil;

import junit.framework.TestCase;
import com.code.utils.LinkedList.Node;
public class RemoveDuplicatedListII extends TestCase {
	RemoveDuplicatedListII ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new  RemoveDuplicatedListII();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
/**
 *  LeetCode – Remove Duplicates from Sorted List II (Java)
 

	Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	
	For example, given 1->1->1->2->3, return 2->3.
	
	Java Solution
 */
	
	public Node deleteDuplicates(Node head) {
	    Node t = new Node(0);
	    t.next = head;
	 
	    Node p = t;
	    while(p.next!=null&&p.next.next!=null){
	        if(p.next.data == p.next.next.data){
	            int dup = p.next.data;
	            while(p.next!=null&&p.next.data==dup){
	                p.next = p.next.next;
	            }
	        }else{
	            p=p.next;
	        }
	 
	    }
	 
	    return t.next;
	}
	public void testAddTwoNumberOfLinkedList() {
		System.out.println("AddTwoNumberOfLinkedList()");
		Integer data [] = {1,1,1,2,2,3,3,5,6,6,8,9,9,4,4};
		L1.initialLinkedList(data);
		Node n = ref.deleteDuplicates(L1.head);		
		System.out.println("\n--------------Removed------------");
		L1.display(); 
		 
	}
}
