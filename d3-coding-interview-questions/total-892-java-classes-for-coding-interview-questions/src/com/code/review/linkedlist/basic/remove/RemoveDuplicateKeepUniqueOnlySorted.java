package com.code.review.linkedlist.basic.remove;

import com.code.review.linkedlist.basic.reverse.ReverseLinkedListBetween;
 
import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class RemoveDuplicateKeepUniqueOnlySorted  {
	/**
	 * 
	 *  LeetCode – Remove Duplicates from Sorted List II (Java)
 

		Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only 
		distinct numbers from the original list.
		
		For example, given 1->1->1->2->3, return 2->3.
	 *
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	public ListNode RemoveDupKeepUnique(ListNode head) {
		ListNode fake = new ListNode(0);   // using p.next
		fake.next = head;
		ListNode p = fake;
		while (p.next != null && p.next.next!=null) {
			if (p.next.val == p.next.next.val) {
				int repeated = p.next.val;
				while (p.next!=null && p.next.val==repeated) {
					p.next = p.next.next;
				}
			} else {
				p = p.next;
			}
		}
		return fake.next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoveDuplicateKeepUniqueOnlySorted  ref = new RemoveDuplicateKeepUniqueOnlySorted ();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(1);
		ListNode n3 = ref.new ListNode(1);
		ListNode n4 = ref.new ListNode(4);
		ListNode n5 = ref.new ListNode(5);
		ListNode n6 = ref.new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;
		 
		ListNode list = ref.RemoveDupKeepUnique(n1) ;
		//ListNode list = n1;
		while (list!=null) {
			System.out.print(list.val+"->");
			list = list.next;
		}
	}

}
