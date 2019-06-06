package com.code.review.aaa.career.cup.algorithm;

import com.code.review.linkedlist.basic.remove.RemoveKthFromEnd;


public class PartitionLinkedList {
	/**
	 *  Partition the given linkedlist

		Given a linked list and a value x, partition it such that all nodes less than x come before
		nodes greater than or equal to x.
		
		You should preserve the original relative order of the nodes in each of the two partitions.
		
		For example,
		Given 1->4->3->2->5->2 and x = 3,
		return 1->2->2->4->3->5.
		My Approach:
		Create two pointers , smaller and larger, 
	 * 
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	public ListNode PartitionLinkedList(ListNode head, int x) {
		if (head==null) return null;
		ListNode fk1 = new ListNode(0);
		ListNode fk2 = new ListNode(0);
		fk1.next = head;
		fk2.next = null;
		ListNode smaller = fk1;
		ListNode greater = fk2;
		ListNode p = head;
		
		while (p!=null) {
			if (p.val<x) {
				smaller.next = p;
				smaller = smaller.next;
			} else {
				greater.next = p;
				greater = greater.next;
			} 
			p = p.next;
		}
		
		greater.next = null;
		smaller.next = fk2.next;
		 
		return fk1.next;
	}
	 public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
 
        ListNode fakeHead1 = new ListNode(0);
        ListNode fakeHead2 = new ListNode(0);
        fakeHead1.next = head;
 
        ListNode p = head;
        ListNode prev = fakeHead1;
        ListNode p2 = fakeHead2;
 
        while(p != null){
            if(p.val < x){
                p = p.next;
                prev = prev.next;
            }else{
 
                p2.next = p;
                prev.next = p.next;
 
                p = prev.next;
                p2 = p2.next;
            } 
        }
 
        // close the list
        p2.next = null;
 
        prev.next = fakeHead2.next;
 
        return fakeHead1.next;
    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PartitionLinkedList  ref = new PartitionLinkedList ();
				ListNode n1 = ref.new ListNode(9);
				ListNode n2 = ref.new ListNode(5);
				ListNode n3 = ref.new ListNode(4);
				ListNode n4 = ref.new ListNode(2);
				ListNode n5 = ref.new ListNode(1);
				ListNode n6 = ref.new ListNode(3);
				n1.next = n2;
				n2.next = n3;
				n3.next = n4;
				n4.next = n5;
				n5.next = n6;
				n6.next = null;
				System.out.println("Before:");
				ListNode list = n1;
				while (list!=null) {
					System.out.print(list.val+"->");
					list = list.next;
				}
				int x = 4;
				System.out.println("\nAfter: x ="+x);
				list = ref.partition(n1,x) ;
				//ListNode list = n1;
				while (list!=null) {
					System.out.print(list.val+"->");
					list = list.next;
				}
	}

}
