package com.code.review.linkedlist.basic.reverse;

import com.code.review.linkedlist.basic.reverse.ReverseLinkedListBetween.ListNode;

public class IsPalindromeLinkedList {

	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	 /**
	  *  LeetCode – Palindrome Linked List (Java)
 

		Given a singly linked list, determine if it is a palindrome.
		
		Java Solution 1 - Creat a new reversed list
		
		We can create a new list in reversed order and then compare each node. 
		The time and space are O(n).
	 
	  */
	 // Space O(n)
	 public boolean isPalindromeNewReverse(ListNode head) {
	    if(head == null)
	        return true;
	 
	    ListNode p = head;
	    ListNode prev = new ListNode(head.val);
	 
	    while(p.next != null){
	        ListNode temp = new ListNode(p.next.val);
	        temp.next = prev;
	        prev = temp;
	        p = p.next;
	    }
	 
	    ListNode p1 = head;
	    ListNode p2 = prev;
	 
	    while(p1!=null){
	        if(p1.val != p2.val)
	            return false;
	 
	        p1 = p1.next;
	        p2 = p2.next;
	    }
	 
	    return true;
	}
	 
    /**
     * 
     * Java Solution 2 - Break and reverse second half

		We can use a fast and slow pointer to get the center of the list, then reverse
	    the second list and compare two sublists. The time is O(n) and space is O(1).
     */
	 public boolean isPalindromeReverseSecondHalf(ListNode head) {
		 
	    if(head == null || head.next==null)
	        return true;
	 
	    //find list center
	    ListNode fast = head;
	    ListNode slow = head;
	 
	    while(fast.next!=null && fast.next.next!=null){
	        fast = fast.next.next;
	        slow = slow.next;
	    }
	 
	    ListNode secondHead = slow.next;
	    slow.next = null;
	 
	    //reverse second part of the list
	    ListNode p1 = secondHead;
	    ListNode p2 = p1.next;
	 
	    while(p1!=null && p2!=null){
	        ListNode temp = p2.next;
	        p2.next = p1;
	        p1 = p2;
	        p2 = temp;
	    }
	 
	    secondHead.next = null;     // secondHead.next = null
	 
	    //compare two sublists now
	    ListNode p = (p2==null?p1:p2);
	    ListNode q = head;
	    while(p!=null){
	        if(p.val != q.val)
	            return false;
	 
	        p = p.next;
	        q = q.next;
	 
	    }
	 
	    return true;
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IsPalindromeLinkedList ref = new IsPalindromeLinkedList();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(2);
		ListNode n3 = ref.new ListNode(3);
		ListNode n4 = ref.new ListNode(4);
		ListNode n5 = ref.new ListNode(3);
		ListNode n6 = ref.new ListNode(2);
		ListNode n7 = ref.new ListNode(1);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = null;
		int m= 2;
		int n = 4;
		boolean result = ref.isPalindromeNewReverse(n1);
		//ListNode list = n1;
	 
		System.out.println("isPalindromeNewReverse="+result);
		 
		//ListNode list = n1;
	 
		System.out.println("isPalindromeReverseSecondHalf="+ref.isPalindromeReverseSecondHalf(n1));	 
	}

}
