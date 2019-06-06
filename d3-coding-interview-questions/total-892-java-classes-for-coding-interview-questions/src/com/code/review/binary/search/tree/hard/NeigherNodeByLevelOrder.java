package com.code.review.binary.search.tree.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class NeigherNodeByLevelOrder extends TestCase {
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
	 public NeigherNodeByLevelOrder bst; 
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
		 bst = new NeigherNodeByLevelOrder();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 *  amazon-interview-questions
		 
		
		You have a BST and you need to assign an appropriate value to neighbor of all nodes
		(Explained in below example)
		Node Structure
		
		node {
             node leftChild,
             node rightChild,
             T data,
              node neighbor
             }
		
		  A
		/   \
		B    C
	   / \  / 
	  D  E F
		
		Based on above tree,
		
		Node: Neighbor
		A: NULL
		B: C
		D: E
		E: F
		
		My Approach description:
		
	 * (1) currentLevel queue top data poll out to LevelList as current level
	 * (2) left and right data is put into belowLevel queue
	 * (3) keeping poll out , currentLevel queue must be empty and belowLevel queue
	 *     will be placed to currentLevel and itself must be point to creating new queue object
	 *     add currentLevel to result as one level
	 * (4) once tree visited finish, reverse result only in level
		
		We just using level order of BST, record each level node 
		List<List<Node>> 
		In each level list, A.neighor = B, B.neighor = C .... last element put null 
	 */
	public List<List<Node>> SetNeighorByLevelOrder(Node node) {
		
		
		List<List<Node>> resultList = new ArrayList <List<Node>>();
		List<Node> levelList = new ArrayList <Node>();
	
		Queue<Node> currentLevel = new LinkedBlockingDeque<Node>();		
		Queue<Node> belowLevel = new LinkedBlockingDeque<Node>();
		currentLevel.add(node); 
		 
		while (!currentLevel.isEmpty()) {
			Node n = currentLevel.remove();
			levelList.add(n);
			if (n.left!=null) {
				belowLevel.add(n.left);
			}
			
			if (n.right!=null) {
				belowLevel.add(n.right);
			}
			if (currentLevel.isEmpty()) {
				currentLevel = belowLevel;
				resultList.add(levelList);
				belowLevel = new LinkedBlockingDeque<Node>();
				levelList = new ArrayList<Node>();
			}
			 
		}
		// fetch each level list from resultList
		
		 
		for (int level=0; level<resultList.size(); level++)  {
			List<Node> lnodes = resultList.get(level);
			for (int i =0; i< lnodes.size()-1; i++) {
				if (level==0) {
				   lnodes.get(i).neighbor = null;
				} else {
					 
					lnodes.get(i).neighbor = lnodes.get(i+1);
					 
				}
			}
		}
		return resultList;
	}
	@Test
	public void testSetNeighorByLevelOrder() {
		System.out.println("SetNeighorByLevelOrder()");
		List<List<Node>> resultList =bst.SetNeighorByLevelOrder(n8);		 
		for (int level=0; level<resultList.size(); level++)  {
			List<Node> lnodes = resultList.get(level);
			for (int i =0; i< lnodes.size(); i++) {
				 	 
				System.out.println(lnodes.get(i).data+"=>"+(lnodes.get(i).neighbor==null? null:lnodes.get(i).neighbor.data));
			}
		}
		//Assert.assertArrayEquals(new int[]{2,5,4,7,9,12,6,10,8 } , result);
	}
}
