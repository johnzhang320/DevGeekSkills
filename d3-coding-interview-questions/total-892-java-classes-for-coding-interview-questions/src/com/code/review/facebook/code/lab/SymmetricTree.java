package com.code.review.facebook.code.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class SymmetricTree extends TestCase {
	/**
	 *  preOrderRecursive (top->left->left->bottom->right) DFS
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
	 public Node n10 = new Node(6);
	 public Node n4 = new Node(3);
	 public Node n7 = new Node(4);
	 public Node n9 = new Node(4);
	 public Node n12 = new Node(3);
	// public Node n2 = new Node(2);
	// public Node n3 = new Node(5);
	 public SymmetricTree bst; 
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
		 bst = new SymmetricTree();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		// n4.connect(n2, n3);
	}
	
	/**
		Problem
		
		Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
		
		For example, this binary tree is symmetric:
		
		    1
		   / \
		  2   2
		 / \ / \
		3  4 4  3
		
		But the following is not:
		
		    1
		   / \
		  2   2
		   \   \
		   3    3

	 * */
	
	/**
	 * Recursive practice for binary tree 
	 * First of all , recursively explore left and right, then compare left and right node
	 */
	public boolean isSymmetric(Node left, Node right) {
		if (left==null && right==null) {
			return true;
		} else if (left==null || right==null){
			return false;
		}
		if (left.data != right.data) {
			return false;
		}
		if (!isSymmetric(left.left,right.right)) {
			return false;
		}
		if (!isSymmetric(right.right,left.left)) {
			return false;
		}
		return true;
	}
	@Test
	public void testInOrderByStack() {
		System.out.println("isSymmetric()");
		System.out.println(isSymmetric(n8.left, n8.right));		 
		
	 
	}
}
