package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class PrintAllPaths extends TestCase {
	/**
	 *   
     *        8 
	 *      /  \
	 *     6   6
	 *    / \  /  \
	 *   4   7 7  4
	 *     
	 *  
	 * */
	 

	 public Node n8 = new Node(8); 
	 public Node n6 = new Node(6);
	 public Node n10 = new Node(6);
	 public Node n4 = new Node(4);
	 public Node n7 = new Node(7);
	 public Node n9 = new Node(7);
	 public Node n12 = new Node(4);
	 
	 public PrintAllPaths bst; 
		/**
    *        8 
	 *      /  \
	 *     6   6
	 *    / \  /  \
	 *   4   7 7  4
		 */
	   
	    
		public void setUp() {
			 bst = new PrintAllPaths();
			 n8.connect(n6,n10);
			 n6.connect(n4, n7);
			 n10.connect(n9, n12);
			  
		}
		
	/**
		PreOrder print out all path from top to one of leave node
		
	*/
	public boolean isSymmetricTree(Node root) {
		if (root==null) return false;
		return isSymmetricTree(root.left,root.right);
	}
	public boolean isSymmetricTree(Node n1, Node n2) {
		if (n1 == null && n2== null) {
			return false;
		} if (n1==null || n2==null) {
			return false;
		}
		if (n1.data!=n2.data) {
			return false;
		}
	    if (!isSymmetricTree(n1.left, n2.right)) {
	    	return false;
	    } 
	    if (!isSymmetricTree(n1.right,n2.left)) {
	    	return false;
	    }
	    return true;
	}
	@Test
	public void testPreOrderByStack() {
		System.out.println("isSymmetricTree:");
		boolean result = bst.isSymmetricTree(n8);		 
		System.out.println(result);
		 
	}
}
