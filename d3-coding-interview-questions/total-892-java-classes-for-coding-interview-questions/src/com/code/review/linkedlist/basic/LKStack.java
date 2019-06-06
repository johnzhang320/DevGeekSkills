package com.code.review.linkedlist.basic;

public class LKStack<T> {
	class Node<T> {
		public T data;
		public Node<T> next;
		public Node(T data) {
			this.data = data;
		}
	}
	public Node<T> head;
	public Node<T> tail;
	public LKStack() {}
	public void push(T data) {
		if (head==null) {
			head = new Node<T>(data);
			tail = head;
			head.next = null;
		} else if (head.next==null) {
			head.next = new Node<T>(data);
			tail = head.next;
			
		}  else {
			tail.next = new Node<T>(data);
			tail = tail.next;
		}
	}
	public Node<T> pop() {
		
		Node<T> returnNode=head;
		if (tail==null || tail==head) {
			return head;
		} else if (tail!=head){
			returnNode = tail;
			Node<T> current = head;
			while (current.next !=tail) {
				current = current.next;
			}
			
			current.next =null;
			tail = current;
			
		}  
		return returnNode;
	}
	
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data[] = {"this", "is", "crazy","rich","asia","girl"};
		LKStack lkn = new LKStack<String>();
		for (String d: data) {
			lkn.push(d);
		}
		LKStack.Node n = lkn.head;
		while (n!=null) {
			System.out.print(n.data+" ");
			n=n.next;
		}
		System.out.println();
		for (int i = 0;i<data.length;i++) {
			LKStack.Node n2 = lkn.pop();
			System.out.print(n2.data+" ");
		}
	}

}
