package com.code.review.linkedlist.hard;

import com.code.review.linkedlist.basic.sort.InsertSortedLinkedList;
import com.code.review.linkedlist.basic.sort.MergeTwoSortedsFromEnd.Node;

/**
 *  One LinkedList, re-organize it in the way so that first pair contains lowest node with highest node, second pair is second low node with
 *  second high node... 
 *  Example
 *  L {1,3,4,6,9,10,5,7,12}
 *  output 
 *    {1,12,3,7,4,5,6,10,9}
 *    
 *  Solution:
 *  (1) found point node, 
 *  (2) from point node is second half
 *  (3) reverse second half,
 *  (4) merge first half with second half
 *    
 * 
 * 
 * @author jianzhang
 *
 */

public class RerangeLowHighPairs {
	public class Node {
		public int data;
		public Node next;
		public Node(int data) {
			this.data = data;
		}
	}
	Node tail=null;
	Node head=null;
	public void appendTail(int data) {
		if (head==null) {
			head = new Node(data);
			tail=head;
			head.next=null;
		} else if (head.next==null) {
			head.next = new Node(data); 
			tail = head.next;
			 
		} else {
			tail.next = new Node(data);
			tail = tail.next;
		}
		
	}
	public Node addArray(int A[]) {
		if (null==A || A.length==0) return null;
		head=null;  // List must be new
		for (Integer i:A) {
			appendTail(i);
		}
		return head;
	}
	public Node reverse(Node head) {
		Node current = head;
		Node result = null;
		Node next = null;
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
		}
		return result;
	}
	public void display(Node head) {
		Node current = head;
		System.out.println(" ");
		while (current!=null) {
			System.out.print(current.data+" ");
			current = current.next;
		}
	}
	public Node mergeTwo(Node a, Node b) {
		Node dummy = new Node(0);
		Node curr = dummy;
		Node p1 = a;
		Node p2 = b;
		while (p1!=null || p2!=null) {
			if (p1!=null && p2!=null) {
				 
				curr.next = p1;
				p1 = p1.next;
				curr = curr.next;
				curr.next = p2;
				p2 = p2.next;
				curr = curr.next;
				
			} else if(p1!=null) {
				curr.next = p1;
				p1 = p1.next;
				break;
			} else if (p2!=null) {
				curr.next = p2;
				p2 = p2.next;
				break;
			}
		}
		return dummy.next;
	}
	// --------------------------------------------------
	public Node reorganize(Node head) {
		Node slow = head;
		Node fast = head;
		Node tail=null;
		// find middle node
		while (fast.next!=null) {
			tail = slow;
			slow = slow.next;
			fast = fast.next;
			fast = fast.next;
		}
		//System.out.println("Middle.data="+slow.data);
		Node secondHalf = reverse(slow);
		tail.next = null;
		display(head);
		display(secondHalf);
		Node result = mergeTwo(head,secondHalf); 
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data1 [] = {1,3,4,6,9,10,5,7,12};
		RerangeLowHighPairs ref = new RerangeLowHighPairs();
		Node a = ref.addArray(data1);
		Node result = ref.reorganize(a);
		ref.display(result);
	}
}
