package com.code.review.array.string.hard.cache;

import junit.framework.TestCase;

public class DoubleLinkedList<T> extends TestCase {
	class Node<T> {
		public T data;
		public Node<T> prev;
		public Node<T> next;
		public Node(T data) {
			this.data = data;
		}
 	}
	
	Node<T> head;
	Node<T> tail;
	 
	 
}
