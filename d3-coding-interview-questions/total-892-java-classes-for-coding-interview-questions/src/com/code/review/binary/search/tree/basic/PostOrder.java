package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class PostOrder extends TestCase {
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
	 public PostOrder bst; 
	 
	public void setUp() {
		 bst = new PostOrder();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	/**
	 *   postOrderRecursive() (top to bottom, parent->left->bottom->right->top)
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
	 * PostOrder actually is executing or presentation part after recursive calls
	 * presentation is end to begin order
	 * 6 4 2 5 7 10 9 12 8  
	 * @param root
	 * @return
	 */
	public void postOrderRecursive(Node node,List<Integer> list) {
		if (node==null) return;
		// push left first and displays it after right displays
		postOrderRecursive(node.left,list);		 
		postOrderRecursive(node.right,list);
		list.add(node.data);
	}
	@Test
	public void testpostOrderRecursive() {
		System.out.println("postOrderRecursive()");
		List<Integer> list = new ArrayList<Integer>();
		bst.postOrderRecursive(n8,list);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{2,5,4,7,6,9,12,10,8} , result);
	}
}
