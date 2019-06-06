package com.code.review.linkedlist.basic.sort;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class MergeTwoSorteds extends TestCase {
	MergeTwoSorteds ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new MergeTwoSorteds();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 *  LeetCode – Merge Two Sorted Lists (Java)
 

		Merge two sorted linked lists and return it as a new list. The new list 
		should be made by splicing together the nodes of the first two lists.
		Actually no new space to be created 
		
		Analysis
		
		The key to solve the problem is defining a fake head. Then compare the first 
		elements from each list. Add the smaller one to the merged list. Finally, 
		when one of them is empty, simply append it to the merged list, since it is already sorted. 
		 
	 * @param node1
	 * @param node2
	 * @return
	 */
	
	public Node MergeTwoSorteds(Node node1,Node node2) {
		Node head = new Node(0); // fake node to link two lists
		Node fake =head;
		Node p1 = node1;
		Node p2 = node2;
		 
		while (p1!=null || p2!=null) {
			if (p1!=null && p2!=null) {
				if (p1.data<p2.data) {
					fake.next = p1;
					p1 = p1.next;
				} else {
					fake.next = p2;
					p2 = p2.next;
				}
				fake = fake.next;
			} else if (p1!=null) {
				fake.next = p1;
				p1 = p1.next;				 
				break;
			} else if (p2!=null) {
				fake.next = p2;
				p2 = p2.next;
				break;
			}
		}
		return head.next;
 	}
	 
	public void testMergeTwoSorteds() {
		System.out.println("MergeTwoSorteds()");
		Integer data1 [] = {2,4,6,9,10,15};
		Integer data2 [] = {1,3,5,7,11,12}; 
		L1.initialLinkedList(data1);  
		L2.initialLinkedList(data2);  
		Node n = ref.MergeTwoSorteds(L1.head, L2.head);		
		System.out.println("\n--------------Merged------------");
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n = n.next;
		}
		 
	}

}
