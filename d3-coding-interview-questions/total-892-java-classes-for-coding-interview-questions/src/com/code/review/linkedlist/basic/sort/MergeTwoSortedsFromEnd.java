package com.code.review.linkedlist.basic.sort;

import com.code.review.array.string.easy.merge.MergeTwoSortedLinkedLists;
import com.code.review.array.string.easy.merge.MergeTwoSortedLinkedLists.Node;
/**
 *  Two increasing sorted linkedLists, merge two lists into one decreasing order
 *  L1 {1,3,5}
 *  L2 {2,6,7,10}
 *  output {10,7,6,5,3,2,1}
 * Solution one: reverse two linked lists and merge it
 * Solution two: merge two first and then reverse result   
 * 
 * @author jianzhang
 *
 */
public class MergeTwoSortedsFromEnd {
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
	/**
	 * Main
	 * @param a
	 * @param b
	 * @return
	 */
	public Node mergeTwo(Node a, Node b) {
		Node dummy = new Node(0);
		Node curr = dummy;
		Node p1 = a;
		Node p2 = b;
		while (p1!=null || p2!=null) {
			if (p1!=null && p2!=null) {
				if (p1.data<p2.data) {
					curr.next = p1;
					p1 = p1.next;
				} else {
					curr.next = p2;
					p2 = p2.next;
				}
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data1 [] = {2,4,6,9,10,15};
		int  data2 [] = {1,3,5,7,11,12}; 
		MergeTwoSortedsFromEnd ref = new MergeTwoSortedsFromEnd();
		Node a = ref.addArray(data1);
		Node b = ref.addArray(data2);
		
		
		Node middle = ref.mergeTwo(a,b);
		ref.display(middle);
		Node result = ref.reverse(middle);
		ref.display(result);
	}

}
