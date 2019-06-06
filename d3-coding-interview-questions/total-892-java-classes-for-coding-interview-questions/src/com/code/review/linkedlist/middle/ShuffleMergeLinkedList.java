package com.code.review.linkedlist.middle;

import javax.swing.text.html.ParagraphView;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

public class ShuffleMergeLinkedList {
	/**
	 * Shuffe Merge two lists dummy node 
	 * in infinite loop, check p1==null, tail.next = p2 else p1, 
	 * tail.next =p1, tail = p1, p1= p1.next .... 
	 * @param args
	 */
	public static Node shuffleMerge(Node p1, Node p2) {
		Node dummy = new Node(-1);
		Node tail = dummy;
		dummy.next=null;
		while (p1!=null && p2!=null) {
			if (p1 == null) {
				tail.next = p2;
				break;
			} else if (p2 == null) {
				tail.next = p1;
				break;
			} else {
				tail.next = p1;
				tail = p1;
				p1 = p1.next;
				tail.next = p2;
				tail = p2;
				p2 = p2.next;
			}
		}
		return (dummy.next);
	}
	public static Node shuffleMerge(Node p1, Node p2, Node appendNode) {
		Node dummy = new Node(-1);
		Node tail = dummy;
		Node prev=tail;
		dummy.next=null;
		while (p1!=null && p2!=null) {
			if (p1 == null) {
				tail.next = p2;
				break;
			} else if (p2 == null) {
				tail.next = p1;
				break;
			} else {
				tail.next = p1;
				tail = p1;
				p1 = p1.next;
				tail.next = p2;
				tail = p2;
				prev = p2;
				p2 = p2.next;
			}
		}
		prev.next = appendNode;
		p2 = prev;
		
		return (dummy.next);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   LinkedListUtil L1 = new LinkedListUtil();
	 	    
		   int d[] = {3,4,5,5,0,3,70,12,4,6};
	       L1.initialLinkedList(d);

	       LinkedListUtil L2 = new LinkedListUtil();
	       
	       int d2[] = {13,14,15,10,12,17,12,14,16,19,4,4,4};
	       L2.initialLinkedList(d2);
	       System.out.print("L1");
	       L1.display();
	       System.out.print("L2");
	       L2.display(); 
	       
	       Node head1 = L1.head;
	       Node head2 = L2.head;
 	      
	      
	      head1 = shuffleMerge(head1,head2); 
	     System.out.println("\nMerged List:");
	      while (head1!=null) {
	    	   System.out.print(head1.data+" ");
	    	   head1 = head1.next;
	      }
	}

}
