package com.code.review.array.string.easy.merge;

import com.code.review.linkedlist.middle.MergeTwoSorteds;
import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;

public class MergeSortedLinkedList extends TestCase {
	/**
	 *  LeetCode – Merge Two Sorted Lists (Java)
 

		Merge two sorted linked lists and return it as a new list. The new list should be made by 
		splicing together the nodes of the first two lists.
		
		Analysis
		
		The key to solve the problem is defining a fake head. Then compare the first elements from 
		each list. Add the smaller one to the merged list. Finally, when one of them is empty, 
		simply append it to the merged list, since it is already sorted. 
			 * @param args
	 */
	
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
			 
		 *  Analysis:
		 *  key point is create a empty linked list, links two exist linkedlist, keep sorted order after merge
		 *  (1) create empty node(0) => head
		 *  (2) make Node fake = head; ensure we always use fake.next
		 *  (3) Node p1 = node1 and Node p2 = node2
		 *  (3) Iterate p1!=null or p2!=null
		 *  (4) if p1!=null and p2!=null
		 *  (5) if p1.data < p2.data then fake.next = p1; fake = fake.next; p1=p1.next else fake.next = p2; fake=fake.next; p2=p2.next
		 *  (6) if p1!=null and p2==null
		 *  (7) fake.next = p1; fake = fake.next; p1=p1.next; break;
		 *  (8) if p1==null and p2!=null
		 *  (9) fake.next = p2; fake = fake.next; p2=p2.next; break;

		 */
		
		public Node MergeTwoSorteds(Node node1,Node node2) {
			Node head = new Node(0); // fake node to link two lists
			Node fake =head;
			Node p1 = node1;
			Node p2 = node2;
			 
			while (p1!=null || p2!=null) {   // p1 || p2 not null
				if (p1!=null && p2!=null) {  // p1 && p2 not null
					if (p1.data<p2.data) {
						fake.next = p1;
						p1 = p1.next;
					} else {
						fake.next = p2;
						p2 = p2.next;
					}
					fake = fake.next;
				} else if (p1!=null) {   // only p1!=null and p2 ==null
					fake.next = p1;
					p1 = p1.next;				 
					break;
				} else if (p2!=null) {   // only p2!=null and p1 ==null
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
