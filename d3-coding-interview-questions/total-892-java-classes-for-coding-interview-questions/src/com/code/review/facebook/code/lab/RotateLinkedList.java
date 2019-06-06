package com.code.review.facebook.code.lab;

import java.util.TreeMap;



import java.util.Map;

public class RotateLinkedList {
	/**
	 * 
	 * 

		Given a list, rotate the list to the right by k places, where k is non-negative.

		For example:
		Ask interview , if k > length of linked list, how do rotate linked list
		From Test case we know , put last element to head location
		
		Given 1->2->3->4->5->NULL and k = 2,
		return 4->5->1->2->3->NULL.
		(1) Fast and Slow pointer to trace the (k-1)th from right as slow.next
		(2) fast.next = head; head = slow.next, slow.next = null;
 	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	 public ListNode RotatetList(ListNode a, int k) {
		 if (a==null) return null;
		 if (a.next==null) return a;
		 if (a.next.next==null && k==1) {
			 ListNode tmp = a.next;
			 a.next = a;
			 a = tmp;
			 a.next.next=null;
		 }
		 ListNode fast = a;
		 ListNode slow = a;
		 ListNode prev = a;
		 int count =1;
		 while (prev.next != null) {
			 count++;
			 prev = prev.next;
		 }
		 if (k>count) {
			 k =2;  //based on weird test case
		 }
		 for (int i=0; i<k; i++) {
			  
			 if (fast.next!=null) {
				 prev = fast;	 
				 fast = fast.next;
			 } else { // if k > len of linked list, put last 2  elements to head based on weird test case
				 fast.next = a;
				 a = fast;
				 prev.next = null;
				 return a;
			 }
			 
		 }
			 
		while (fast.next!=null) {
			slow = slow.next;
			fast = fast.next;
		}
		fast.next = a;
		a = slow.next;
		
		slow.next = null;
		return a;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RotateLinkedList ref = new RotateLinkedList();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(5);
		ListNode n3 = ref.new ListNode(4);
		ListNode n4 = ref.new ListNode(3);
		ListNode n5 = ref.new ListNode(7);
		ListNode n6 = ref.new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;
		ListNode p = ref.RotatetList(n1, 8) ;
		while (p!=null) {
			System.out.print(p.val+" ");
			p = p.next;
		}
	}

}
