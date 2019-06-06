package com.code.review.binary.search.tree.basic;

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
		 n4.connect(n3, n2);
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
	
	/**
	 *  Iteratively traversal all nodes by in-order , ensure prev.data is less than current.data 
	 *  O(n)
	 */
	public List<Integer> inorderIterate(Node root) {
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		List<Integer> list = new ArrayList<Integer>();
		while(!stack.isEmpty() || current!=null) {
			if (current!=null) { // keep traversal deeply by going left
				stack.push(current);
				current = current.left;
			} else {  // reach null, pop out from deepest node first from stack 
				Node verifyNode = stack.pop(); // get last node and remove it
				list.add(verifyNode.data);     // output data of verifyNode
				current = verifyNode.right;                   // traversal the right node of verifyNode
			}
		}
		return list;
	}
	
	public boolean inorderIterateValidateBST(Node root) {
		Node current = root;  // stack is empty at first time , add current 
		Stack<Node> stack = new Stack<Node>();
		int prev=-1;
		while(!stack.isEmpty() || current!=null) {
			if (current!=null) { // keep traversal deeply by going left
				stack.push(current);
				current = current.left;
			} else {  // reach null, pop out from deepest node first from stack 
				Node verifyNode = stack.pop(); // get last node and remove it
				if (prev>verifyNode.data) return false;    // output data of verifyNode
				prev = verifyNode.data;
				current = verifyNode.right;                   // traversal the right node of verifyNode
			}
		}
		return true;
	}
	
	
	public boolean inOrderRecursiveValidate(Node node,int prev) {
		if (node==null) return true;
		// push left first and displays it after right displays
		inOrderRecursiveValidate(node.left,node.data);		 
		if (prev>node.data) return false;
		inOrderRecursiveValidate(node.right,node.data);
		return true;
	}
	//@Test 
	public void testHelperIsValidBST() {
		System.out.println("HelperIsValidBST()");
		boolean result = bst.isValidBST(n8);
		System.out.println("IS this BST "+result);
		 
	}
	//@Test 
	public void testRecusiveInOrderValidateBST() {
			System.out.println("\nRecusiveInOrderValidateBST");
			boolean result = bst.inOrderRecursiveValidate(n8,-1);
			System.out.println("IS this BST "+result);
			
		}
	
	//@Test 
	public void testInorderIterateValidateBST() {
		System.out.println("\nInorderIterateValidateBST");
			 
		boolean result = bst.inorderIterateValidateBST(n8);
		
		System.out.println("IS this BST "+result);
	
		System.out.println("\n");
	}
	
	//@Test 
		public void testInorderIterate() {
			System.out.println("\ninorderIterate");
				 
			List<Integer> result = bst.inorderIterate(n8);
			
			result.forEach(x->System.out.print(x+" "));
		
			System.out.println("\n");
		}
}
