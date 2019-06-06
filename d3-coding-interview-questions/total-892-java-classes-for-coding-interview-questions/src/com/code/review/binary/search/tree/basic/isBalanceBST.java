package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 
/**
 * Java 8 most internal improvement is 
 * When Java 7 or previous versions use linkedlist when hashcode of object are same , this 
 * object will be save in linkedlist in HashMap, which may cause O(n) worse case search
 * Java 8 change the linklist to balanced binary tree, which ensure search and insert complexity 
 * constantly O(logN)
 *   
 * @author jianzhang
 *
 */
public class isBalanceBST extends TestCase {
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
	 public Node n1 = new Node(1);
	 public Node n3 = new Node(5);
	 public isBalanceBST bst; 
	/**
	 *   Create AVL tree
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	  /
     1
    */
	public void setUp() {
		 bst = new isBalanceBST();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
		 n2.connect(n1, null);
	}
	
	/**
	 *   Balance Search Tree, difference between max depths and min depths should not larger than 1
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	
	public static int maxDepth(Node root) {
	   if (root == null) {
		   return 0;
	   }
	   return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}
	   
    public static int minDepth(Node root) {
	    if (root == null) {
		   return 0;
	     }
     	 return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
    // max depth - min depth <=1 
    public static boolean isBalanced(Node root){
     return (maxDepth(root) - minDepth(root) <= 1);
    }
    @Test 
	public void testIsValidBST() {
		System.out.println("IsBalancedBST");
		boolean result = bst.isBalanced(n8);
		System.out.println("IS this balanced BST "+result);
		assertTrue(result);
	}
}
