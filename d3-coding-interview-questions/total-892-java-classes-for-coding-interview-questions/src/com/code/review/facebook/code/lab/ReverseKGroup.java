package com.code.review.facebook.code.lab;

import com.code.utils.LinkedList.Node;

public class ReverseKGroup {
	/**
	 * 
	 *  LeetCode – Reverse Nodes in k-Group (Java)
 

		Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
		
		If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
		
		You may not alter the values in the nodes, only nodes itself may be changed.
		
		Only constant memory is allowed.
		
		For example,
		Given this linked list: 1->2->3->4->5
		
		For k = 2, you should return: 2->1->4->3->5
		
		For k = 3, you should return: 3->2->1->4->5
	 * 
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	public ListNode reverseKGroup(ListNode head, int k) {
	    if(head==null||k==1)
	        return head;
	 
	    ListNode fake = new ListNode(0);
	    fake.next = head;
	    ListNode pre = fake;
	    int i=0;
	 
	    ListNode p = head;
	    while(p!=null){
	        i++;
	       // if(i%k==0){
	        if (i==k){
	        	pre = reverse(pre, p.next);
	        
	            p = pre.next;
	        }else{
	            p = p.next; 
	        }
	    }
	 
	    return fake.next; 
	}
	 
	/*
	 * 0->1->2->3->4->5->6
	 * |           |   
	 * pre        next
	 *
	 * after calling pre = reverse(pre, next)
	 * 
	 * 0->3->2->1->4->5->6
	 *          |  |
	 *          pre next 
	 */
	public ListNode reverse(ListNode pre, ListNode next){
	    ListNode last = pre.next;
	    ListNode curr = last.next;
	 
	    while(curr != next){
	        last.next = curr.next;
	        curr.next = pre.next;
	        pre.next = curr;
	        curr = last.next;
	    }
	 
	    return last; 
	}
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
	public ListNode reverseKGroup2(ListNode head, int k) {
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
		ListNode list = ref.reverseKGroup2(n1,3);
		//ListNode list = n1;
		
		while (list!=null) {
			System.out.print(list.val+"->");
			list = list.next;
		} 
	}

}
