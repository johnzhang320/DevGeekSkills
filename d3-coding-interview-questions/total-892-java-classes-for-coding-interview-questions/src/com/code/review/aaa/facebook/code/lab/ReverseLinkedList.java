package com.code.review.aaa.facebook.code.lab;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class ReverseLinkedList extends TestCase {
	ReverseLinkedList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new ReverseLinkedList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	public Node reverse(Node head) {
		Node current = head;
		Node next;
		Node result = null;
		int i=0;
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
		}
		return result;
	}
	public void testAddTwoNumberOfLinkedList() {
		System.out.println("\nReverse Linked List()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
		Node n = ref.reverse(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		 
	}
	public Node reverseIterate(Node head) {
	    if(head==null||head.next==null)
	        return head;
	 
	    Node p1 = head;
	    Node p2 = p1.next;
	 
	    head.next = null;
	    
	    while(p1!=null&& p2!=null){
	        Node t = p2.next;
	        p2.next = p1;
	        p1 = p2;
	        p2 = t;
	    }
	 
	    return p1;
	}
	public void testreverseIterate() {
		System.out.println("\nreverseIterate Linked List()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
		Node n = ref.reverseIterate(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		System.out.println(""); 
	}
	// Java Solution 2 - Recursive

	public Node reverseRecursive(Node head) {
	    if(head==null || head.next == null)
	        return head;
	 
	    //get second node    
	    Node second = head.next;
	    //set first's next to be null
	    head.next = null;
	 
	    Node rest = reverseRecursive(second);
	    second.next = head;
	 
	    return rest;
	}
	public void testreverseRecursive() {
		System.out.println("\nreverseRecursive Linked List()");
		Integer data [] = {1,2,4,3,9,10,8};
		L1.initialLinkedList(data);
		Node n = ref.reverseRecursive(L1.head);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		System.out.println("");
		 
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
	public Node reverseCall(Node pre, Node next){
	    Node last = pre.next;
	    Node curr = last.next;
	  
	    while(curr != next){
	        last.next = curr.next;
	        curr.next = pre.next;
	        pre.next = curr;
	        curr = last.next;
	    }
	 
	    return last; 
	}
	public void testreverseCall() {
		System.out.println("\nreverseCall(Node pre, Node next)");
		Integer data [] = {1,2,4,3,9,10,11};
		L1.initialLinkedList(data);
		Node p1=L1.head;
		Node p2=L1.head;
		int i=0;
		while (i<3) {
			p2 = p2.next;
			i++;
		}
		//p1 = p1.next;
		
		Node n = ref.reverseCall(p1, p2);
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n= n.next;
		}
		System.out.println("");
		 
	}
	
}
