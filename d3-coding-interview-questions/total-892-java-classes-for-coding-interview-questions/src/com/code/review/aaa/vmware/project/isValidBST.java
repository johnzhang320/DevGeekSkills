package com.code.review.aaa.vmware.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class isValidBST extends TestCase {
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
	 public Node n10 = new Node(10);
	 public Node n4 = new Node(4);
	 public Node n7 = new Node(7);
	 public Node n9 = new Node(9);
	 public Node n12 = new Node(12);
	 public Node n2 = new Node(2);
	 public Node n3 = new Node(5);
	 public isValidBST bst; 
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
		 bst = new isValidBST();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 *   inOrderRecursive(bottom Left -> Parent -> Right)
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	
	/**
	 *  Validate BST , 
	 *  Role: left data must be less than parent data and right data must be larger then parent data
	 *  help Root , low and high
	 *  low greater than root.data or high < root.data false
	 *  if left!=null && !help(root.left, low, root.data)
	 *     high!=null && !help(root.right,root.data,high)
	 *  Recursive solution
	 */
	public boolean help(Node root, int low, int high) {
		if (low>=root.data || high<=root.data) {
			return false;
		}
		if (root.left!=null && !help(root.left, low, root.data))  {
			return false;
		}
		if (root.right!=null && !help(root.right, root.data, high)) {
			return false;
		}
		return true;
	}
	public boolean isValidBST(Node root) {
		return help(root,Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	@Test 
	public void testIsValidBST() {
		System.out.println("IsValidBST");
		boolean result = bst.isValidBST(n8);
		System.out.println("IS this BST "+result);
		assertTrue(result);
	}
}
