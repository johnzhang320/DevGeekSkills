package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class InvertorMirror extends TestCase {
	/**
	 *   
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
	 public InvertorMirror bst; 
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
			 bst = new InvertorMirror();
			 n8.connect(n6,n10);
			 n6.connect(n4, n7);
			 n10.connect(n9, n12);
			 n4.connect(n2, n3);
		}
		
	/**
		Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t
	   invert a binary tree on a whiteboard so fuck off.	 
	   My Approach:
	   using preOrder , for recursive pre order, before call itself, do left node and right node
	   exchange (swap)
	*/
	public void invertByPreOrder(Node node, List<Integer> list) {
		if (node==null) return;
		Node temp = node.left;
		node.left = node.right;
	    node.right = temp;
	    list.add(node.data);
		 
		invertByPreOrder(node.left,list);	
	 
		invertByPreOrder(node.right,list);
		 
	 
	}
	 
	@Test
	public void testinvertByPreOrder() {
		System.out.println("invertByPreOrder()");
		List<Integer> list = new ArrayList<Integer>();
		bst.invertByPreOrder(n8,list);		 
		int [] result=Utils.print(list);
		 
	}
	/**
	 * Invert Binary tree, means , left -> right , right ->left 
	 * 
	 * @param root
	 * @return
	 */
	public void invertBinaryTree(Node node,List<Integer> list) {
		if (node==null) return;
		Node tmp = node.left;
		node.left = node.right;
		node.right = tmp;
		list.add(node.data);
		invertBinaryTree(node.left,list);		
		invertBinaryTree(node.right,list);
	}
	
	@Test
	public void testInvertBinaryTree() {
		System.out.println("invertBinaryTree()");
		List<Integer> list = new ArrayList<Integer>();
		bst.invertBinaryTree(n8,list);		 
		int [] result=Utils.print(list);
		//Assert.assertArrayEquals(new int[]{8 ,6, 4, 2, 5, 7, 10, 9, 12} , result);
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
	 * 8 6 4 2 5 7 10 9 12 
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
	@Test
	public void testPreOrderByStack() {
		System.out.println("PreOrderByStack()");
		List<Integer> list = bst.preOrderByStack(n8);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{8 ,6, 4, 2, 5, 7, 10, 9, 12} , result);
	}
}
