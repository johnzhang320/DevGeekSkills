package com.code.review.linkedlist.basic;

import com.code.review.linkedlist.basic.CloneLinkedList.Node;

public class FindKthNodeFromLinkedList {
	/**
	 *  find kth node from end of list list
	 *  (1) first of all , find length of linked list n
	 *  (2) traversal n-k step , the node is kth of linkedList
	 */
	public class Node {
		public int val;
		public Node next;
		public Node(int val) {
			this.val=val;
		}
		public Node(int val,Node node) {
			this.val = val;
			this.next = node;
		}
	}
	public Node findKthNode(Node head, int k) {
		int n=0;
		Node curr = head;
		while (curr!=null) {
			n++;
			curr=curr.next;
		}
		curr = head;
		for (int i=0; i<n-k; i++) {
			curr = curr.next;
		}
		return curr;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[]= {3,4,1,5,10,8,9,21};
		FindKthNodeFromLinkedList ref = new FindKthNodeFromLinkedList(); 
		Node head = null;
		for (int i=data.length-1; i>=0;i--) {
			head = ref.new Node(data[i],head);
		}
		Node curr = ref.findKthNode(head, 3);
		 
	    System.out.print(curr.val+" ");
		 
	}
}
