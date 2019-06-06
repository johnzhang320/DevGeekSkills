package com.code.review.middle.depth.first.search;

 

public class Node {
	 public Node left;
	 public Node right;
	 public int data;
	 public Node neighbor;   // 
	 public Node(int data) {
		 this.left = null;
		 this.right = null;
		 this.data = data;
		 
	 }
	 public void connect(Node left, Node right) {
		this.left = left;
		this.right = right;
	 }
}
