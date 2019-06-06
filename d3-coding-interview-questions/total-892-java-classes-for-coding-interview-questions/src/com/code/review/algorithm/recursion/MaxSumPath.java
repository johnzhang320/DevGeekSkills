package com.code.review.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class MaxSumPath extends TestCase {
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
	 public MaxSumPath bst; 
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
		 bst = new MaxSumPath();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
 /**
  *   LeetCode 124
    * Calculate Max Summary from all path which from root to bottom
    * Recursively travel left and right, accumulate all paths from leaf to top, 
    * then compare left summary with right summary 
    * @param args
    */
   public int maxPathSum(Node root) {
		int max[] = new int[1]; 
		max[0] = Integer.MIN_VALUE;
		calculateSum(root, max);
		return max[0];
	}
	 
	public int calculateSum(Node root, int[] max) {
		if (root == null)
			return 0;
	 
		int left = calculateSum(root.left, max);
		int right = calculateSum(root.right, max);
	 
		int current = Math.max(root.data, Math.max(root.data + left, root.data + right));
	 
		max[0] = Math.max(max[0], Math.max(current, left + root.data + right));
	 
		return current;
	}	
	@Test
	public void testgetPathSumByStack() {
		System.out.println("maxPathSum()");
		 
		int result =bst.maxPathSum(n8);		 
		Utils.print(result);
		Assert.assertEquals(45 , result);
	}
}
