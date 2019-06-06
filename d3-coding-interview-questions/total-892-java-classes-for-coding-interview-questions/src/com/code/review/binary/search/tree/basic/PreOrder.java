package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class PreOrder extends TestCase {
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
	 public PreOrder bst; 
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
			 bst = new PreOrder();
			 n8.connect(n6,n10);
			 n6.connect(n4, n7);
			 n10.connect(n9, n12);
			 n4.connect(n2, n3);
		}
		
	/**
	 * purpose: typical depth first search for tree, get path of tree
	 * Recursive practice for binary tree one
	 * PreOrder actually is depth first search, that is executing or presentation part before recursive calls
	 * presentation before recursive call is begin to end order
	 * 8 6 4 2 5 7 10 9 12 
	 * @param root
	 * @return
	 */
	public void preOrderRecursive(Node node,List<Integer> list) {
		if (node==null) return;
		list.add(node.data);
		preOrderRecursive(node.left,list);		
		preOrderRecursive(node.right,list);
	}
	@Test
	public void testPreOrderRecursive() {
		System.out.println("preOrderRecursive()");
		List<Integer> list = new ArrayList<Integer>();
		bst.preOrderRecursive(n8,list);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{8 ,6, 4, 2, 5, 7, 10, 9, 12} , result);
	}
	/**
	 *   preOrderByStack()  
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
  
	/**
	 * PreOrder is depth first , above search result is:
	 * depth first search is using stack , add right first and left
	 * 8 6 4 2 5 7 10 9 12 
	 * pre order is DFS search
	 * @param root
	 * @return
	 */
	public List<Integer> preOrderByStack(Node root) {
		if (null==root) return null;
		List<Integer> list = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node n = stack.pop();
			list.add(n.data);
			if (n.right!=null) {  // first push right side , 
				stack.push(n.right);
			}
			if (n.left!=null) {
				stack.push(n.left);
			}
		}
		return list; 
	}
	/**
	 *  Pre Order is pre recursion by depth first, output node first, then go to left, if it still have left node
	 *  output this node and go to left node,  if have node sub left, go to right, so we have to push right first
	 *  push left second, ensure left node always pop out first, last in and first out
	 */
	
	public List<Integer> preOrderIterate(Node root) {
		List<Integer> list = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()) {
			//output Node first
			Node current = stack.pop();
			list.add(current.data);
			if (current.right!=null) {
				stack.push(current.right);
				 
			}
			if (current.left!=null) {
				stack.push(current.left);
				 
			}
			
		}
		return list;
	}
	@Test
	public void testPreOrderByStack() {
		System.out.println("PreOrderByStack()");
		List<Integer> list = bst.preOrderByStack(n8);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{8 ,6, 4, 2, 5, 7, 10, 9, 12} , result);
	}
}
