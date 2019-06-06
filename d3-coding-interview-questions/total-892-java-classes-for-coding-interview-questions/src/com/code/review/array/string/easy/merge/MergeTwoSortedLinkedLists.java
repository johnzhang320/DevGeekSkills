package com.code.review.array.string.easy.merge;

import com.code.utils.LinkedList.Node;
/**
 *  Merge sorted linked lists 
 *  example 
 *  
L1 2 4 6 9 10 15  
 
L2 1 3 5 7 11 12  
 
output 1 2 3 4 5 6 7 9 10 11 12 15  

 *  (1) create dummy node(0) 
 *  (2) curr point = dummy
 *  (3) check while p1!=null || p2!= null
 *  (4) check if p1!=null && p2!=null
 *   
 * 
 * @author jianzhang
 *
 */
public class MergeTwoSortedLinkedLists {
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
	public void display(Node head) {
		Node current = head;
		System.out.println(" ");
		while (current!=null) {
			System.out.print(current.data+" ");
			current = current.next;
		}
		System.out.println(" ");
	}
	public Node mergeTwo(Node a,Node b) {
		Node dummy= new Node(0);  // create dummy Node to return in future
		Node curr = dummy;        // create current Node
		Node p1 = a;
		Node p2 = b;
		while (p1!=null || p2!=null) {   // p1 and p2 is current point of original nodes
			if (p1!=null && p2!=null) {        // check if p1 and p2 are not empty at same time, merge both of them
				if (p1.data<p2.data) {
					curr.next = p1;
					
					p1 = p1.next;
				} else {
					curr.next = p2;
					 
					p2 = p2.next;
				}
				curr = curr.next;
			} else if (p1!=null) {   // end of loop
				curr.next = p1;
				p1=p1.next; 
				break;
			} else if (p2 != null) {
				curr.next = p2;
				p1=p1.next; 
				break;
			}
		
		}
		
		return dummy.next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data1 [] = {2,4,6,9,10,15};
		int  data2 [] = {1,3,5,7,11,12}; 
		MergeTwoSortedLinkedLists ref = new MergeTwoSortedLinkedLists();
		Node a = ref.addArray(data1);
		Node b = ref.addArray(data2);
		ref.display(a);
		ref.display(b);
		Node result=ref.mergeTwo(a,b);
		
		ref.display(result);
	}

}
