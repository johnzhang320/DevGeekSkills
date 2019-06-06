package com.code.review.linkedlist.basic.reverse;

import com.code.review.linkedlist.basic.reverse.ReverseLinkedListBetween.ListNode;

public class ReverseKGroup {
	/**
	 * 
	 *  LeetCode � Reverse Nodes in k-Group (Java)
 

		Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
		
		If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
		
		You may not alter the values in the nodes, only nodes itself may be changed.
		
		Only constant memory is allowed.
		
		For example,
		Given this linked list: 1->2->3->4->5
		
		For k = 2, you should return: 2->1->4->3->5
		
		For k = 3, you should return: 3->2->1->4->5
	 *  (1) Create reverse sub routine reverse only k nodes
	 *  (2) Go to k steps and record kth node to p2
	 *  (3) reverse 1 to k
	 *  (4) go to end of reversed linked list p1.next
	 *      p1.next = p1
	 *      
	 *  
	 *  
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	// This my remembered code for reversing link list;
	// We do not need remember so many way to reverse linked list
	 
	// This my remembered code for reversing link list;
	public ListNode reverse(ListNode head,int k) {
		ListNode current = head;
		ListNode next;
		ListNode result = null;
		int i=0;
		while (current!=null && i<k) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
			i++;
		}
		return result;
	}
	// using standard reverse linked list to do ReverseKGroup
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode p2 = head;
		int i=0;
		while (p2!=null && i<k) {
			p2=p2.next;
			i++;
		}
		head=reverse(head,k);
		ListNode p1 = head;
		while (p1.next!=null) {
			p1 = p1.next;
		}
		p1.next = p2;
		return head;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseKGroup ref = new ReverseKGroup();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(2);
		ListNode n3 = ref.new ListNode(3);
		ListNode n4 = ref.new ListNode(4);
		ListNode n5 = ref.new ListNode(5);
		ListNode n6 = ref.new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;
		ListNode list = ref.reverseKGroup(n1, 3);
	 
		//ListNode list = n1;
		while (list!=null) {
			System.out.print(list.val+"->");
			list = list.next;
		} 
	}

}
