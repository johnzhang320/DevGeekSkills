package com.code.review.queue.stack;

import java.util.Stack;

public class CheckIFLinkedListPalindrome  {
   /**
    *  mcafee-interview-questions
 
		find if linked list is palindrome
		a -> b->c->b->a->null
    *   (1) find middle point
    *   (2) from middle->next, save to stack
    *   (3) from head of linked list , compare node with stack.pop(), node = node->next
    */
	public class Node  {
		public String data;
		public Node next;	
		public Node(String data) {
			this.data = data;
			this.next=null;
		}
		 
	}
	public Node head,tail;
	public void append(String data) {
		if (head==null) {
			head = new Node(data);
			tail=head;
			
		} else {
			tail.next = new Node(data);
			tail = tail.next;
			tail.next = null;
		}
	}
	public boolean isPalindromeLinkedList(Node head) {
		Node slow = head;
		Node fast = head;
		while (fast!=null) {
			slow = slow.next;
			fast = fast.next;
			if (fast!=null) {
				fast = fast.next;
			}
		}
		Node middle = slow;
		slow = slow.next;
		Stack<Node> stack = new Stack<Node>();
		while (slow!=null) {
			stack.push(slow);
			slow = slow.next;
		}
		Node p = head;
		while (p.next != middle) {
			 
			if (!p.data.equals(stack.pop().data)) {
				return false;
			}
			
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
