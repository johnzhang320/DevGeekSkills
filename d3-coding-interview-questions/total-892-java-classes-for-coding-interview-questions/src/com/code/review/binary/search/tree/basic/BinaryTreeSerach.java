package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class BinaryTreeSerach extends TestCase {
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
	 public Node n5 = new Node(5);
	 public BinaryTreeSerach bst; 
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
		 bst = new BinaryTreeSerach();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n5);
	}
	
	/**
	 *   Recursively Binary Tree Search
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	
	/**
	 * first check if key equal to root val. if yes return , if greater than it , search right node, otherwise search left node 
	 */
	
	public boolean recursiveSearch(Node root,int key) {
		if (root==null) {
			System.out.println("Not found!");
			return false;
		}
		if (root.data == key) {
			System.out.println("find key:"+key);
			return true;
		}
		if (key>root.data) {
			return recursiveSearch(root.right,key);
		} else {
			return recursiveSearch(root.left,key);
		}
		
	}
	@Test
	public void testinOrderRecursive() {
		System.out.println("recursiveSearch()");
		int key = 4;
		boolean ret = bst.recursiveSearch(n8, key);		 
		 
		Assert.assertTrue(ret);
		
		key = 12;
		ret = bst.recursiveSearch(n8, key);		 
		 
		Assert.assertTrue(ret);
		
		key = 120;
		ret = bst.recursiveSearch(n8, key);		 
		 
		Assert.assertFalse(ret);
	} 
	/**
	 * first check if key equal to root val. if yes return , if greater than it , search right node, otherwise search left node 
	 */
	
	public boolean iterateSearch(Node root,int key) {
		
		while (root!=null) {			
		 
			if (key<root.data) {
				root = root.left;
			} else if (key>root.data) {
				root = root.right;
			} else {
				System.out.println("find key:"+key);
				return true;
			}
		}
		
	 
		System.out.println("Not found!");
		return false;
	 
	} 
	@Test
	public void testiterateSearch() {
		System.out.println("iterateSearch()");
		int key = 4;
		boolean ret = bst.iterateSearch(n8, key);		 
		 
		Assert.assertTrue(ret);
		
		key = 12;
		ret = bst.iterateSearch(n8, key);		 
		 
		Assert.assertTrue(ret);
		
		key = 120;
		ret = bst.iterateSearch(n8, key);		 
		 
		Assert.assertFalse(ret);
	}

}
