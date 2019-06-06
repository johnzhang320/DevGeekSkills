package com.code.review.linkedlist.basic;

 

public class MergeTwoLinkedListAlternately {
	/**
	 *  Merge two linked alternately into first linked list, remain element to second linked list
	 *  l1 = {2,4,6,9,10,8,5,11}
	 *  l2 = {1,3,7,12}
	 *  output 
	 *  l1 = {2,1,4,3,6,7,10,12}
	 *  l2 = {8,5,11}
	 * 
	 */
	
	public class Node {
		public int val;
		public Node next;
		public Node() {
			
		}
		
		public Node(int val,Node node) {
			this.val = val;
			this.next = node;
		}
	}
	/**
	 * Create dummy node and give it to tail		 
	 * link a and b to tail, a and b go ahead
	 * check if a.next==null or b.next==null 
	 * put a or b to tail, break
	 */
	public Node mergeLinkedList(Node a, Node b) {
		Node dummy = new Node();
		Node tail = dummy;
		while (true) {
			if (a==null) {
				tail.next = null;
				 
				break;
			} else 
			if (b==null) {
				tail.next=a;
				 
				break;
			} else {
				tail.next = a;
				tail = tail.next;
				a = a.next;
				
				tail.next = b;
				tail = tail.next;
				b = b.next;
			}
			
		}
		 
		a = dummy.next;
		
		return a;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[]= {3,4,1,5,10,8,9,21,20};
		int data2[] = {2,6,7,11};
		MergeTwoLinkedListAlternately ref = new MergeTwoLinkedListAlternately(); 
		Node a = null;
		for (int i=data.length-1; i>=0;i--) {
			a = ref.new Node(data[i],a);
		}
		Node b = null;
		for (int i=data2.length-1; i>=0;i--) {
			b = ref.new Node(data2[i],b);
		}
		Node newList= ref.mergeLinkedList(a, b) ;
		Node curr = newList;
		while (curr!=null) {
			System.out.print(curr.val+" ");
			curr=curr.next;
		}
	/*	System.out.println("-----");
		curr = newList[1];
		while (curr!=null) {
			System.out.print(curr.val+" ");
			curr=curr.next;
		}*/
	}

}
