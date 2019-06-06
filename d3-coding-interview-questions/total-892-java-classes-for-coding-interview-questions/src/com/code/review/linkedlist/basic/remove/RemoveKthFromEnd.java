package com.code.review.linkedlist.basic.remove;

import com.code.review.linkedlist.basic.reverse.ReverseLinkedListBetween;
 
import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class RemoveKthFromEnd  {
	/**
	 * 
	 *  LGiven a linked list, remove the nth node from the end of list and return its head.

		For example, given linked list 1->2->3->4->5 and n = 2, the result is 1->2->3->5.
		One pass O(n) 
		fast pointer and slow pointer
		fast go k step
		slow and fast go together
		
	 *
	 */
	
	
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	public ListNode RemoveKthNode(ListNode head, int k) {
		
		if (head==null) return null;
	   	if (head.next==null && k==1) return null;
	   	if (head.next.next==null && k==1) {   // if k==1 and list has two element, remove second element
	   		head.next=null;
	   		return head;		
	   	
	   	}

	    ListNode fast = head;
	    ListNode slow = head;
	    int count = 0;
	  
	    while (fast!=null && count<k) {
	       	System.out.println("in loop fast reach data is "+fast.val);  	
	     	fast = fast.next;
	     	count++;
	    }
	    if (fast==null) {
	    		head = head.next;
	    		return head;
	    }
	    if (fast==head) {
	    		return head;
	    }
	    System.out.println("fast reach data is "+fast.val);
	    while (fast.next!=null) {
		    	slow = slow.next;
		    	fast = fast.next;
	    }
	    System.out.println("current deleting data is "+slow.val);
	    slow.next = slow.next.next;
		return head;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveKthFromEnd  ref = new RemoveKthFromEnd ();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(2);
		ListNode n3 = ref.new ListNode(3);
		ListNode n4 = ref.new ListNode(4);
		ListNode n5 = ref.new ListNode(5);
		ListNode n6 = ref.new ListNode(6);
		ListNode n7 = ref.new ListNode(7);
		ListNode n8 = ref.new ListNode(8);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next=null;
		System.out.println("start node data="+n1.val); 
		ListNode list = ref.RemoveKthNode(n1,3) ;
		//ListNode list = n1;
		while (list!=null) {
			System.out.print(list.val+"->");
			list = list.next;
		}
	}

}
