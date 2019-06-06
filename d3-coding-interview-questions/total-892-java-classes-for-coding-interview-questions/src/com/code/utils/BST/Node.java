package com.code.utils.BST;

 

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
	 public Node() {}
	 public void connect(Node left, Node right) {
		this.left = left;
		this.right = right;
	 }
}
