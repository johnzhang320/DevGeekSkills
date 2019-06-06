package com.code.review.linkedlist.hard;

import com.code.review.linkedlist.basic.ListNode;

public class RotatedLinkedList {
	/**
	 * 61. Rotate List
		Medium
		429
		730
		
		
		Given a linked list, rotate the list to the right by k places, where k is non-negative.
		
		Example 1:
		
		Input: 1->2->3->4->5->NULL, k = 2
		Output: 4->5->1->2->3->NULL
		Explanation:
		rotate 1 steps to the right: 5->1->2->3->4->NULL
		rotate 2 steps to the right: 4->5->1->2->3->NULL
		Example 2:
		
		Input: 0->1->2->NULL, k = 4
		Output: 2->0->1->NULL
		Explanation:
		rotate 1 steps to the right: 2->0->1->NULL
		rotate 2 steps to the right: 1->2->0->NULL
		rotate 3 steps to the right: 0->1->2->NULL
		rotate 4 steps to the right: 2->0->1->NULL
	 */
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	/* Leet Code passed
	 * (1) each step to rotation is a operation which last node.next to head , head = last
	   (2) prev node.next of last node is point to null
	   (3) create current and prev points, end of loop, prev.next =null and curr.next =head and head = curr;
	   (4) if length of list < k , k =%klen, so we go one pass to get length first  
	   
	 */ 
	    public ListNode rotateRight(ListNode head, int k) {
	        if (head==null) return null;
	        if (head.next==null) return head;
	        ListNode prev = head;
	        ListNode curr = head.next;
	        int len =0;
	        while (prev!=null) {
	            len++;
	            prev=prev.next;
	        }
	        System.out.println("len="+len);
	        if (k>len) {
	            k = k%len;
	        }
	        prev=head;
	        for (int i=0;i<k;i++) {
	            while (curr.next!=null) {
	                prev =curr;
	                curr = curr.next;
	            }
	            prev.next = null;
	            curr.next = head;
	            head = curr;
	        }
	        return head;
	    }
}
