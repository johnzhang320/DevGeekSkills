package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class PathSum extends TestCase {
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
	 public PathSum bst; 
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
		 bst = new PathSum();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
 /**
   Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that 
   adding up all the values along the path equals the given sum.

		For example:
		Given the below binary tree and sum = 22,
		
		              5
		             / \
		            4   8
		           /   / \
		          11  13  4
		         /  \      \
		        7    2      1
		
		return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
    */

	    public boolean hasPathSum(Node root, int sum) {
	        if(root == null) return false;
	 
	        LinkedList<Node> nodes = new LinkedList<Node>();
	        LinkedList<Integer> values = new LinkedList<Integer>();
	 
	        nodes.add(root);
	        values.add(root.data);
	 
	        while(!nodes.isEmpty()){
	            Node curr = nodes.poll();
	            int sumValue = values.poll();
	 
	            if(curr.left == null && curr.right == null && sumValue==sum){
	                return true;
	            }
	 
	            if(curr.left != null){
	                nodes.add(curr.left);
	                values.add(sumValue+curr.left.data);
	                System.out.println(curr.left.data);
	            }
	 
	            if(curr.right != null){
	                nodes.add(curr.right);
	                values.add(sumValue+curr.right.data);
	                System.out.println(curr.right.data);
	            }
	            
	        }
	 
	        return false;
	    }
	@Test
	public void testgetPathSumByStack() {
		System.out.println("PathSum()");
		 
		boolean result =bst.hasPathSum(n8, 23);		 
		 
		
		System.out.println(result);
		//12+10+8+6+4+5=45
	}
}
