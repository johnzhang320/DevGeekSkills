package com.code.review.array.string.easy.merge;

public class MergeTwoSortedLinkedList {
	/**
	 * Merge two sorted linked lists and return it as a new list. The new list should be made 
	 * by splicing together the nodes of the first two lists.
 	 * Analysis
 	 * The key to solve the problem is defining a fake head. Then compare the first elements 
	 * from each list. Add the smaller one to the merged list. Finally, when one of them is empty, simply append it to the merged list, since it is already sorted. 
	 * @param args
	 */
	class Node {
		public int data;
		public Node next;
		public Node(int data) {
			next = null;
			this.data=data;
			head = this;
			tail = this;
		}
		public Node head=null;
		public Node tail=null;
		
		public void add(int data) {
			if (head==null) {
				head = new Node(data);
				tail = head;
			} else {
				tail.next = new Node(data);
				tail = tail.next;
			}
		}
		
	}
	public Node mergeTwoLinkedList(Node n1, Node n2) {
		Node head = new Node(0);
		Node p = head;
		while (true) {
			if (n1!=null && n2!=null) {
				if (n1.data<n2.data) {
					p.next = n1;
					n1= n1.next;
				} else  {
					p.next = n2;
					n2 = n2.next;
				}
				p=p.next;
			} else if (n1==null) {
				p.next = n2;
				break;
			} else if (n2==null) {
				p.next = n1;
				break;
			} else {
				break;
			}
		}
		return head.next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 MergeTwoSortedLinkedList ref = new  MergeTwoSortedLinkedList();
		 Node n1 = ref.new Node(4);
		 n1.add(6);
		 n1.add(8);		 
		 n1.add(10);
		 n1.add(12);
		 n1.add(14);
		 
		 Node n2 = ref.new Node(2);
		 n2.add(5);
		 n2.add(7);		 
		 n2.add(9);
		 n2.add(11);
		 n2.add(13);
		 
		Node list = ref.mergeTwoLinkedList(n1, n2);
		while (list!=null) {
			System.out.print(list.data+ " ");
			list = list.next;
		}
		
	}

}
