package com.code.review.aaa.vmware.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.code.utils.BST.Node;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * 
 * In BST, find max value of leaf node values
 * 
 */

public class BinaryTree extends TestCase {
	BinaryTree ref = null;
	/**
	 *  preOrderRecursive (top->left->left->bottom->right) DFS
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \    /\
	 * 2   5  7  14
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
	 public Node n77 = new Node(7);
	 public Node n14 = new Node(14);
	 
	 public isValidBST bst; 
	/**
	 *   Create AVL tree
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \    /\
	 * 2   5  7  14
	 */
   
    
	public void setUp() {
		 ref = new BinaryTree();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
		 n9.connect(n77,n14);
	}
	/**
	 *  My solution:
	 *  (1) Create inOrder method and return List<Node>
	 *  (2) find node.left = null and node.right=null, max = Math.max(max,node.val)
	 *  
	 */
	public void myInOrder(Node n, List<Node> result ) {
		if (n==null) {
			return;
		}
		myInOrder(n.left,result);
		result.add(n);
		myInOrder(n.right,result);
	}
	public int findMaxValueOfLeafNodeValues(Node root) {
		List<Node> result = new ArrayList<Node>();
		myInOrder(root,result);
		int max = Integer.MIN_VALUE;
		for (Node r: result) {
			if (r.left==null && r.right==null) {
				max = Math.max(max, r.data);
			}
		}
		return max;
	}
	public void testFindMaxValue() {
		int max = findMaxValueOfLeafNodeValues(n8);
		Assert.assertEquals(14, max);
		System.out.println("max value of leaf nodes is "+max);
		int maxi[]=new int[1];
		maxi[0] = Integer.MIN_VALUE;
		findMaxValue(n8,maxi);
		System.out.println("Using method2 find max value of leaf nodes is "+maxi[0]);
		
	}
	public void findMaxValue(Node n, int max[]) {
		if (n==null) {
			return;
		}
		findMaxValue(n.left,max);
		if (n.data>max[0] && n.left==null && n.right==null) {
			max[0] = n.data;
		}
		findMaxValue(n.right,max);
	}
} 
