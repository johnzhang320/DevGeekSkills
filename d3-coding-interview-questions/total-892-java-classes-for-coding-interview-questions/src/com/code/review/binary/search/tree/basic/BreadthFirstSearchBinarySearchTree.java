package com.code.review.binary.search.tree.basic;

import java.util.LinkedList;

public class BreadthFirstSearchBinarySearchTree {
	/** Breadth-first search
	 * produces a binary search tree using Comparable
	 * Breath-first search actually is level order search
	 * Breath-first search always use queue to search,
	 * add left first and right second
	 * BFS is same as BST tree level order
	 * use queue to store left and then right
	 * current = queue.remove
	 * if (current.left!=null) {
	 *     queue.add(current.left);
	 *      
	 *  }
	 * if (current.right!=null) {
	 *     queue.add(current.right);
	 *     
	 *  }    
	 * 
	 *
	 */
	/**
	 *   Create AVL tree
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * 
	 * 
	 * */
	public class Node {
		 Node left;
		 Node right;
		 int data;
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
	 public void BFSTraversal(Node root) {
		 LinkedList queue = new LinkedList();
		 
		 Node item;
		 
		 if (null==root) return;
		 
		 queue.add(root);
		 
		 while (!queue.isEmpty()) {
			 
			 item =(Node) queue.remove();  // using remove from queue to avoid add visited flag
			 
			 System.out.print(item.data+" ");
			 
			 if (item==null) break;
			 
			 if (item.left!=null) {
				 
				 queue.add(item.left);
			 }
			 
			 if (item.right!=null) {
				 
				 queue.add(item.right);
			 }
		 }
	 }
	 public static void main(String[] asd) {
		 BreadthFirstSearchBinarySearchTree bfs = new BreadthFirstSearchBinarySearchTree();
		 Node n8 = bfs.new Node(8); 
		 Node n6 = bfs.new Node(6);
		 Node n10 = bfs.new Node(10);
		 Node n4 = bfs.new Node(4);
		 Node n7 = bfs.new Node(7);
		 Node n9 = bfs.new Node(9);
		 Node n12 = bfs.new Node(12);
		 Node n2 = bfs.new Node(2);
		 Node n3 = bfs.new Node(5);
		 
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
		 bfs.BFSTraversal(n8);
		 
		
		}
	}

	 

 
