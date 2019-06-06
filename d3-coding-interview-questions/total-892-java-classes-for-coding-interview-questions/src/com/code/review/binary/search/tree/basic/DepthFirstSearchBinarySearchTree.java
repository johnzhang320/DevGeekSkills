package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.Stack;



public class DepthFirstSearchBinarySearchTree {
	/* Breadth-first search
	 * produces a binary search tree using Comparable
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
	// using stack to traversal the binary tree
	 public ArrayList<ArrayList<Integer>> DFSTraversal(Node root) {
		 
		 Stack<Node> stack = new Stack<Node>();
		 ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		 Node node;
		 
		 if (null==root) return null;
		 
		 stack.add(root);
		 
		 while (!stack.isEmpty()) {
			 
			 node =(Node) stack.pop();  // using remove from stack to avoid add visited flag
			 if (node.left==null && node.right==null) {
				 ArrayList<Integer> list = new ArrayList<Integer>();
				 stack.forEach((n)->list.add(n.data));
				 result.add(list);
			 }
			 System.out.print(node.data+" ");
			 
			 if (node==null) break;
			 // right must be first adding , then return later than left
			 
			 if (node.right!=null) {
				 
				 stack.add(node.right);
			 }
			 
			 if (node.left!=null) {
				 
				 stack.add(node.left);
			 }
			
		 }
		 return result;
	 } 
	 public static void main(String[] asd) {
		 DepthFirstSearchBinarySearchTree bfs = new DepthFirstSearchBinarySearchTree();
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
		 ArrayList<ArrayList<Integer>> result=  bfs.DFSTraversal(n8);
		
		 for (ArrayList l:result) {
			 System.out.println("");
			 l.forEach((n)->System.out.print(n+" "));
			 
		 }
		 /*
			Tree t = new Tree();
			String s = "qwertyuiop";
			int len = s.length();
			t.bst(s);
			t.infix();
			t.breadth();
			System.out.println("Breadth first search");
			System.out.println();
			*/
		}
	}

	 

 
