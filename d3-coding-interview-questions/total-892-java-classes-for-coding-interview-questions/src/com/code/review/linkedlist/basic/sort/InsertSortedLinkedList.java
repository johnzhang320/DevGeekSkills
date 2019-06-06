package com.code.review.linkedlist.basic.sort;

import com.code.review.linkedlist.basic.sort.MergeTwoSortedsFromEnd.Node;

public class InsertSortedLinkedList {
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
//-------------------------------------------
	/**
	 * (1) create a SortInsertOneNode sub routine
	 *    1> check if node is one node 
	 *       case one head=null, head = newNode
	 *       case two head!=null and head.next=null, check if newNode.data<head.data, newNode.next = head ; head = newNode else
	 *    2> while (current.next!=null && current.next.data <= newNode.data)   
	 * @param args
	 */
	
	public Node sortInsertOneNode(Node head, Node newNode) {
		Node current = head;
		if (head==null) {
			head = newNode;
		} else if (head.next==null) {
			if (newNode.data<head.data) {
				newNode.next = head;
				head = newNode;
			} else {
				head.next = newNode;
			}
 		} else {
 			while (current.next !=null && current.next.data<=newNode.data) {
 				current=current.next;
 			}
 			newNode.next = current.next;
 			current.next = newNode;
 		}
		return head;
	}
	public Node sortInsertWhole(Node head) {
		Node current = head;
		Node next = null;
		Node result=null;
		while (current!=null) {
			next = current.next;
			result = sortInsertOneNode(result, current);
			current = next;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data1 [] = {13,5,4,11,9,10,15,3,6};
		InsertSortedLinkedList ref = new InsertSortedLinkedList();
		Node a = ref.addArray(data1);
		Node result = ref.sortInsertWhole(a);
		ref.display(result);
	}

}
