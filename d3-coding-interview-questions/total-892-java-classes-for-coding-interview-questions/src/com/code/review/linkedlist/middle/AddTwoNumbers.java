package com.code.review.linkedlist.middle;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class AddTwoNumbers extends TestCase {
	AddTwoNumbers ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new AddTwoNumbers();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * You are given two linked lists representing two non-negative numbers.
	 *  The digits are stored in reverse order and each of their nodes contain a single digit. 
	 *  Add the two numbers and return it as a linked list.

		Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		Output: 7 -> 0 -> 8
		
		Analysis
		Reverse two original linked lists(p1,p2) and create new dummy linked list newNode, p3
		carry += px.data
		p3.next = new Node(carry%10); // save tail of carry to new node
		p3 = p3.next;
		
		carry /=10;   // save real carry to carry variable 
		
	 * @param node1
	 * @param node2
	 * @return
	 */
	
	public Node addTwoNumberOfLinkedList(Node node1,Node node2) {
		Node p1 = reverse(node1);
		Node p2 = reverse(node2);
		Node newNode = new Node(0);
		Node p3 = newNode;
		int carry=0;
		while (p1!=null || p2!=null) {
			if (p1!=null) {
				carry += (int)p1.data;
				p1 = p1.next;
			}
			if (p2!=null) {
				carry+=(int) p2.data;
				p2=p2.next;
			}
			p3.next = new Node(carry%10);  // take tail digit of carry as current node;
			p3 = p3.next;
			carry /=10;     // take first digit as carry
		}
		if(carry==1) 
            p3.next=new Node(1);
 
		return reverse(newNode.next);
 	}
	// Judged Letcode
	 public Node addTwoNumbers(Node l1, Node l2) {
	        int carry = 0;
	        Node p1 = l1;
	        Node p2 = l2;
	        Node result =new Node(0);
	        Node p3 = result;
	        
	        while (p1 != null || p2 != null) {
	            if (p1!=null) {
	                carry +=p1.data;
	                p1 = p1.next;
	            }
	            if (p2!=null) {
	                carry +=p2.data;
	                p2 = p2.next;
	            }
	            p3.next = new Node(carry%10);
	            carry /=10;
	            p3 = p3.next;
	        }
	        if (carry==1) {
	            p3.next = new Node(1);
	            p3 = p3.next;
	        }
	        p3.next = null;
	        return result.next;
	    }
	public Node reverse(Node head) {
		Node current = head;
		Node next;
		Node result = null;
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
		}
		return result;
	}
	public void testAddTwoNumberOfLinkedList() {
		System.out.println("AddTwoNumberOfLinkedList()");
		Integer data1 [] = {2,6,3,5};
		Integer data2 [] = {1,2,4,4}; 
		L1.initialLinkedList(data1);  
		L2.initialLinkedList(data2);  
		Node n = ref.addTwoNumbers(L1.head, L2.head);		
		System.out.println("\n--------------Non Carried------------");
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n = n.next;
		}
		System.out.println("\n--------------Carried------------");
		Integer data3 [] = {2,6,3,5};
		Integer data4 [] = {9,2,4,6}; 
		L3.initialLinkedList(data3);  
		L4.initialLinkedList(data4);  
		Node n2 = ref.addTwoNumberOfLinkedList(L3.head, L4.head);		
		 
		while (n2!=null) {
			System.out.print(n2.data+"->");
			n2 = n2.next;
		}
	}

}
