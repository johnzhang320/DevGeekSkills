package com.code.review.linkedlist.middle;

import com.code.utils.LinkedList.Node;

public class BobbleSortLinkedList {
	/**
	 *  define two pointers: p1 point to last time sorted node and p2 to the node to be sorted
	 *  check p1.value > p2.value in internal loop, if yes, just swap, inner loop n time O (n^2)
	 */
	public static Node bobbleSortLinkedList(Node head) {
		
		Node current = head;
		while (current!=null) {
			Node p1 = head;
			Node p2 = head.next;	
			while (p2!=null) {
				int p1data = (Integer) p1.data;
				int p2data = (Integer) p2.data;
				if (p1data> p2data) {
					p2.data = p1data;
					p1.data = p2data;
				}
				p1 = p1.next;
				p2 = p2.next;
			}
			current = current.next;
		}
		return head;
	}
	public static void printReverseLinkedList(Node head) {
		if (head!=null) {
			if (head.next!=null) {
				printReverseLinkedList(head.next);
			}
			System.out.print(head.data+" ");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node node = new Node();
		int arr[] = {7,1,5,3,2,0,6};
		for (int i=0; i<arr.length;i++) {
			node.AppendToTail(arr[i]);
		}
		Node head = node.getHead();
		bobbleSortLinkedList(head);
		node.displayList();
		System.out.println("");
		printReverseLinkedList(head);
	}

}
