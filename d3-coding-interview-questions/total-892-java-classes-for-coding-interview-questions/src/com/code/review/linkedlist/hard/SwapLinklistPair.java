package com.code.review.linkedlist.hard;

public class SwapLinklistPair {
	/**
	 * Definition for singly-linked list.
	 * Given a linked list, swap every two adjacent nodes and return its head.

		Example:
		
		Given 1->2->3->4, you should return the list as 2->1->4->3.
		Note:
		
		Your algorithm should use only constant extra space.
		You may not modify the values in the list's nodes, only nodes itself may be changed.

	 */
	   public class ListNode {
	       int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	   }
	  
	class Solution {
	    /*
	     *   
	     */  
	    public ListNode swapPairs(ListNode head) {
	        if (null==head || head.next==null) return head;
	        ListNode pre = new ListNode(0);
	        pre.next =head;
	        ListNode dummy = pre;
	        while (pre.next !=null && pre.next.next!=null) {
	            ListNode p1 = pre.next;
	            ListNode p2 = p1.next;
	            p1.next = p2.next;
	            p2.next = p1;
	            pre.next = p2;
	            pre=pre.next.next;
	        }
	        return dummy.next;
	    }
	}
}
