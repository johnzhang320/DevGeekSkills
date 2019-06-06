package com.code.review.aaa.oracle.code;

public class MertedSortedLinkedList {
	/**
	 * 
	 * 
		Merge two sorted linked lists
		
		Write a SortedMerge() function that takes two lists, each of which is sorted in increasing 
		order, and merges the two together into one list which is in increasing order. SortedMerge() 
		should return the new list. The new list should be made by splicing
		together the nodes of the first two lists.
		
		For example if the first linked list a is 5->10->15 and the other linked list b is 2->3->20, 
		then SortedMerge() should return a pointer to the head node of the merged list 2->3->5->10->15->20.
		
		There are many cases to deal with: either ‘a’ or ‘b’ may be empty, during processing either 
		‘a’ or ‘b’ may run out first, and finally there’s the problem of starting the result list 
		empty, and building it up while going through ‘a’ and ‘b’.
		Solution one
		Dummy Pointer
	 */
	public class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
			next=null;
		}
	}
	public Node MergeSortedLinkedList(Node head1, Node head2) {
		Node fake = new Node(0);
		Node p1 = head1;
		Node p2= head2;
		Node head = fake;
		while (p1!=null || p2 !=null) {
			if (p1==null) {
				fake.next = p2;
				break;
			} else if (p2==null) {
				fake.next = p1;
                break;			
			} else {
				if (p1.val<p2.val) {
					fake.next = p1;	
					p1 = p1.next;
				} else {
					fake.next = p2;			
					p2 = p2.next;
				}
				fake = fake.next;
			}
		}
		return head.next;
	 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MertedSortedLinkedList ref = new MertedSortedLinkedList();
		//5->10->15 and the other linked list b is 2->3->20,
		Node pp1=ref.new Node(5);
		Node p2=ref.new Node(10);
		Node p3=ref.new Node(15);
		Node p33=ref.new Node(25);
		pp1.next = p2;
		p2.next = p3;
		p3.next=p33;
		
		Node pp2=ref.new Node(2);
		Node p4=ref.new Node(3);
		Node p5=ref.new Node(20);
		pp2.next = p4;
		p4.next = p5;
		
		Node p = ref.MergeSortedLinkedList(pp1, pp2);
		while (p!=null) {
			System.out.print(p.val+" ");
			p=p.next;
		}
		
			
	}

}
