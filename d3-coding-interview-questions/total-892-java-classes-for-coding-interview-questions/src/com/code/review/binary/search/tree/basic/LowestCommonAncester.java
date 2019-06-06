package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class LowestCommonAncester extends TestCase {
	/**
	 *  lowestCommonAncestor (top->left->left->bottom->right) DFS
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	 

	 public Node n8 = new Node(8); 
	 public Node n6 = new Node(6);
	 public Node n10 = new Node(10);
	 public Node n4 = new Node(4);
	 public Node n7 = new Node(7);
	 public Node n9 = new Node(9);
	 public Node n12 = new Node(12);
	 public Node n2 = new Node(2);
	 public Node n3 = new Node(5);
	 public LowestCommonAncester bst; 
		/**
		 *   Create AVL tree
	     *        8 
		 *      /  \
		 *     6   10
		 *    / \  /  \
		 *   4   7 9  12
		 *  / \  
		 * 2   5
		 */
	   
	    
		public void setUp() {
			 bst = new LowestCommonAncester();
			 n8.connect(n6,n10);
			 n6.connect(n4, n7);
			 n10.connect(n9, n12);
			 n4.connect(n2, n3);
		}
		
	/**
	
		LeetCode – Lowest Common Ancestor of a Binary Search Tree (Java)
		 
		
		Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
		
		Analysis
		
		This problem can be solved by using BST property, i.e., left < parent < right for each node. 
		There are 3 cases to handle.
		
		Java Solution
	 */
		public Node lowestCommonAncestor(Node root, Node left, Node right) {
		    Node m = root;
		 
		    if(m.data > left.data && m.data < right.data){
		        return m;  
		    }else if(m.data>left.data && m.data > right.data){
		        return lowestCommonAncestor(root.left, left, right);
		    }else if(m.data<left.data && m.data < right.data){
		        return lowestCommonAncestor(root.right, left, right);
		    }
		 
		    return root;
		}
	@Test
	public void testlowestCommonAncestor() {
		System.out.println("lowestCommonAncestor()");
		 
		Node n=bst.lowestCommonAncestor(n8,n9,n12);		 
		System.out.println("result="+n.data); 
	}
	 
}
