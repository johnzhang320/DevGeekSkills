package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class MinMaxDepth extends TestCase {
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
	 public MinMaxDepth bst; 
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
		 bst = new MinMaxDepth();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 *   Find min and max depth, from top to bottom
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	
	/**
	 * Recursive practice for binary tree 
	 * First of all , recursively explore left and right, then compare left and right node
	 * to find max or min depth, left and righ
	 * max: larger one plus one then return  
	 * min: smaller one plus one then return
	 */
	public int maxDepth(Node node) {
		if (node==null) return 0;
	    int lDepth = maxDepth(node.left);
	    int rDepth = maxDepth(node.right);
	    if (lDepth > rDepth) {
	    	return lDepth+1;
	    } else {
	    	return rDepth+1;
	    }
	}
	public int minDepth(Node node) {
		if (node==null) return 0;
	    int lDepth = minDepth(node.left);
	    int rDepth = minDepth(node.right);
	    if (lDepth < rDepth) {
	    	return lDepth+1;
	    } else {
	    	return rDepth+1;
	    }
	}
	@Test
	public void testMinMaxDepth() {
		System.out.println("maxDepth()");
		 
		System.out.println(bst.maxDepth(n8));	
		
		System.out.println("minDepth()");
		 
		System.out.println(bst.minDepth(n8));	
	 
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
	@Test
	public void testInOrderByStack() {
		System.out.println("inInOrderByStack()");
		List<Integer> list = bst.inOrderByStack(n8);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{2,4,5,6,7,8,9,10,12 } , result);
	}
}
