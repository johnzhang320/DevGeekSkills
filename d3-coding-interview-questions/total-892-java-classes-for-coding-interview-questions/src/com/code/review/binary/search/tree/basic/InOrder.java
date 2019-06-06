package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class InOrder extends TestCase {
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
	 public InOrder bst; 
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
		 bst = new InOrder();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
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
	 * purpose: get sorted elements of binary tree
	 * Recursive practice for binary tree two
	 * inOrder actually is executing or presentation part after recursive calls
	 * presentation is end to begin order
	 * 2,4,5,6,7,8,9,10,12 
	
	 * @param root
	 * @return
	 */
	public void inOrderRecursive(Node node,List<Integer> list) {
		if (node==null) return;
		// push left first and displays it after right displays
		inOrderRecursive(node.left,list);		 
		list.add(node.data);
		inOrderRecursive(node.right,list);
	}
	@Test
	public void testinOrderRecursive() {
		System.out.println("inOrderRecursive()");
		List<Integer> list = new ArrayList<Integer>();
		bst.inOrderRecursive(n8,list);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{2,4,5,6,7,8,9,10,12 } , result);
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
	 * Recursive practice for binary tree two
	 * inOrder actually is executing or presentation part after recursive calls
	 * presentation is end to begin order
	 * 2,4,5,6,7,8,9,10,12 
	 * @param root
	 * @return
	 */
	/**
	 * In Order implemented using stack
	 * Try to left first until p=null, then right 
	 */
	public List<Integer> inOrderByStack(Node root) {
		Node p = root;
		List<Integer> list = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		//stack || p!=null
		while (!stack.isEmpty() || p!=null) {
			if (p!=null) {
				/**
				 *  if p is not null, push left bottom to stack first
				 */
				stack.push(p);
				p = p.left;
			} else { // once p is null pop stack, p will be right child
				Node n = stack.pop();
				list.add(n.data);
				p = n.right;
				 
			}
		}
		return list;
		
	}
	
	/**
	 *  Inorder is kind of DFS algorithm, it traversal node from left side nodes, keep put node into stack
	 *  or recursively put nodes into system stock until find null, pop out node from stack and output it
	 *  then traversal the right node and output it and put it to current node , repeat it
	 *  Based on this analysis , create iterative way 
	 */
	public List<Integer> inorderIterate(Node root) {
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		//stack.add(root);
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
	
	@Test
	public void inorderIterate() {
		System.out.println("inorderIterate()");
		List<Integer> list = bst.inorderIterate(n8); //bst.inOrderByStack(n8);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{2,4,5,6,7,8,9,10,12 } , result);
	}
	
	@Test
	public void testInOrderByStack() {
		System.out.println("inOrderByStack()");
		List<Integer> list = bst.inOrderByStack(n8); //bst.inOrderByStack(n8);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{2,4,5,6,7,8,9,10,12 } , result);
	}
}
