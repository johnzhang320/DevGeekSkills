package com.code.review.linkedlist.middle;



public class TwoNumberPLus {
	/**
	 * 
		 

		You are given two linked lists representing two non-negative numbers. The digits are 
		stored in reverse order and each of their nodes contain a single digit. Add the two numbers 
		and return it as a linked list.
		
		Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		Output: 7 -> 0 -> 8
		
		    342 + 465 = 807
		
		Make sure there are no trailing zeros in the output list
		So, 7 -> 0 -> 8 -> 0 is not a valid response even though the value is still 807.

	}
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	public ListNode addTwoNumbers(ListNode a, ListNode b) {
	    if (null==a && null==b) {
	        return null;
	    } 
	    if (null==a && b!=null) {
	        return b;
	    }
	    if (a!=null && b==null) {
	        return a;
	    }
	    ListNode p1 = a;
	    ListNode p2 = b;
	    ListNode newNode = new ListNode(0);
	    ListNode p3 = newNode;
	    int carry = 0;
	    while (p1!=null || p2!=null) {
	        if (p1!=null) {
	            carry +=p1.val;
	            p1 = p1.next;
	        }
	        if (p2!=null) {
	            carry +=p2.val;
	            p2 = p2.next;
	        }
	        p3.next = new ListNode(carry%10);
	        p3 = p3.next;
	        carry /=10;
	    }
	    if (carry==1) {
	        p3.next = new ListNode(1);
	        p3 = p3.next;
	        p3.next = null;
	    }
	    
	    return newNode.next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
