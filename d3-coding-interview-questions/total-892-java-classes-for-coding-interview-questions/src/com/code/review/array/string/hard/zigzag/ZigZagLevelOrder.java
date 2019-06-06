package com.code.review.array.string.hard.zigzag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class ZigZagLevelOrder extends TestCase {
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
	 public ZigZagLevelOrder bst; 
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
		 bst = new ZigZagLevelOrder();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	  *  amazon-interview-questions
		Write a function which does zig-zag traverse of binary tree and prints out nodes.
		Zig-Zag means walking in Z symbol trace 
	 /**
	 *  
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * 
	 Output: 8,6,10,12,9,7,4,2,5 
	 Apply Top to Bottom Level Order Travel using Breath search first using queue
		
    my implementation
    
    
    belowLevel queue always accept left or right node data first from currentLevel queue
    Using level order of binary tree, if levelFlag = true, queue left and queue right, false 
    queue right and queue.left
  */

	public List<Integer> ZigZagBST(Node node) {	
	
		List<List<Integer>> resultList = new ArrayList <List<Integer>>();
		List<Integer> levelList = new ArrayList <Integer>();
	
		
		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> belowLevel = new LinkedList<Node>();
		currentLevel.add(node); 
		boolean zigZag= true; 
		while (!currentLevel.isEmpty()) {
			Node n = currentLevel.remove();
			levelList.add(n.data);
			 
			if (n.left!=null) {
				belowLevel.add(n.left);
			}
			if (n.right!=null) {
				belowLevel.add(n.right);
			}
			 
			 
			if (currentLevel.isEmpty()) {
				currentLevel = belowLevel;
				if (zigZag) {
					Collections.reverse(levelList); 
				}
				belowLevel = new LinkedList<Node>();				
				resultList.add(levelList);
				levelList = new ArrayList<Integer>();
				zigZag =!zigZag;
			}
		}
		List<Integer> reversedList = new ArrayList<Integer>();
		 
		for (int i=0;  i< resultList.size(); i++) {
			for (Integer data: resultList.get(i)) {
				
				reversedList.add(data);
			}
		}
		return reversedList;
		 
	}
	@Test
	public void testZigZagBST() {
		System.out.println("ZigZagBST()");
		List<Integer> list =bst.ZigZagBST(n8);		 
		int [] result=Utils.print(list);
		Assert.assertArrayEquals(new int[]{8,6,10,12,9,7,4,2,5 } , result);
	}
}
