package com.code.review.linkedlist.basic;

import com.code.utils.LinkedList.Node;

public class BasicLinkedList<T> {
	public class Node<T>  {
		public T data;
		public Node<T> next;
		public Node(T data) {
			this.data = data;
		}
		
	}
	Node<T> head;
	Node<T> tail;
	public void append(T data) {
		if (head==null) {
			head = new Node(data);
			tail = head;
			head.next = null;
		} else if (head.next==null) {
			head.next = new Node(data);
			tail = head.next;
			head = head.next;
		} else {
			tail.next= new Node(data);
			tail = tail.next;
		}
	}
	public void deleteDuplicate(Node<T> head) {
	    Node<T> p=head.next;
	    Node<T> prev=head;
	    while (p!=null) {
	    	   if (p==null && prev==null) break;
	    	   T pData = p.data;
	    	   T prevData = prev.data;
	    	   if (pData==prevData) {
	    		   prev.next = p.next; 
	    	   } else {
	    		   prev = p;
	    	   }
	    	   p = p.next;
	    }
	}  
	public Node<T> reverse(Node<T> head) {
		Node<T> current = head;
		Node<T> next = head.next;
		Node<T> result = null;
		while(current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			current = next;
			 
		}
		return result;
	}
	public void traverse(Node<T> head) {
		Node<T> p =head;
		System.out.println("");
		while (p!=null) {
			System.out.print(p.data+" ");
			p=p.next;
		}
		System.out.println("");
	}
	public Node<T> bobbleSorting(Node<T> head) {
		
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
	public static void main(String args[]) {
		
	}
}
