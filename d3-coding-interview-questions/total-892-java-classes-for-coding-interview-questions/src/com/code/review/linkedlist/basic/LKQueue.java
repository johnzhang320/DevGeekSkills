package com.code.review.linkedlist.basic;

public class LKQueue<T> {   // class definition provide Parameter
	class Node<T> {
		T data;
		Node<T> next;
		public Node (T data) {
			this.data = data;
		}
		
	}
	Node<T> top,bottom;
	public LKQueue() {}
	public void enqueue(T data) {
		if (top==null) {
			top=new Node<T>(data);
			top.next=null;
			bottom = top;
		} else if (top.next==null) {
			top.next = new Node<T>(data);
			bottom = top.next;
		} else {
			bottom.next = new Node<T>(data);
			bottom = bottom.next;
		}
	}
	public Node<T> dequeue() {    // return type provide Parameter
		Node<T> returnNode = top;
		top = top.next;
		return returnNode;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data[] = {"this", "is", "crazy","rich","asia","girl"};
		LKQueue lkn = new LKQueue<String>();  // provide parameter when instantiate object
		// enqueue
		for (String d: data) {
			lkn.enqueue(d);
		}
		// dequeue
		for (int i=0; i<data.length;i++) {
			LKQueue.Node n = lkn.dequeue();   // LKQueue.Node is not provided with Parameter because lkn instance got Parameter already
			System.out.print(n.data+" ");
		}
	}

}
