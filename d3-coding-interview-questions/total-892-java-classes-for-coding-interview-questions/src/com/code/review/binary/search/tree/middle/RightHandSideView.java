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
 

public class RightHandSideView extends TestCase {
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
	 public RightHandSideView bst; 
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
			 bst = new RightHandSideView();
			 n8.connect(n6,n10);
			 n6.connect(n4, n7);
			 n10.connect(n9, n12);
			 n4.connect(n2, n3);
		}
		
		/**
		Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom. For example, given the following binary tree,

		 *       8            <---
		 *      /  \
		 *     6   10          <----
		 *    / \  /  \
		 *   4   7 9  12       <----
		 *  / \  
		 * 2   5               <----   

		You can see [8, 10, 12,5].
		My Approach:
		We can apply level order , collect the end element of each level
		we create currentLevelQueue, addingLevelQueue, once currentLevelQueue polled out, take last
		element by lastData 
	*/
	public List<Integer> rightHandSideView(Node root) {
		 List<Integer> result = new ArrayList<Integer>();
		 LinkedList<Node> currentQueue = new LinkedList<Node>();
		 LinkedList<Node> addingQueue = new LinkedList<Node>();
		 result.add(root.data);
		 currentQueue.add(root);
		 Node right=root;
		 while (!currentQueue.isEmpty()) {
			 
			 Node n = currentQueue.remove();
			 if (n.left!=null) {				
				 addingQueue.add(n.left);
			 }
			 if (n.right!=null) {
				 addingQueue.add(n.right);
				 right = n.right;
			 }
			 if (currentQueue.isEmpty()) {
				 currentQueue = addingQueue;
				 if (right!=null)
				 result.add(right.data);
				 right = null;
				 addingQueue = new LinkedList<Node>();
			 }
		 }
		 return result;
	}
	 
	@Test
	public void testRightHandSideView() {
		System.out.println("rightHandSideView()");
		List<Integer> result=bst.rightHandSideView(n8);		 
		Utils.print(result);
	}
}
