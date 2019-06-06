package com.code.review.linkedlist.basic;

public class CloneLinkedList {
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
	
	public Node  cloneLinkedList(Node head) {
		Node current = head;
		Node tail = null;
		Node newList = null;
		while (current!=null) {
			if (tail==null) {
				newList = new Node(current.val,newList);
				tail=newList;
			} else {
				tail.next = new Node(current.val,tail.next);
				tail=tail.next;
			}
			current=current.next;
		}
		return newList;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[]= {3,4,1,5,10,8,9,21};
		CloneLinkedList ref = new CloneLinkedList(); 
		Node head = null;
		for (int i=data.length-1; i>=0;i--) {
			head = ref.new Node(data[i],head);
		}
		Node newList = ref.cloneLinkedList(head);
		Node curr = newList;
		while (curr!=null) {
			System.out.print(curr.val+" ");
			curr=curr.next;
		}
	}

}
