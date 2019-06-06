package com.code.review.queue.stack;

import java.util.ArrayList;
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
 

public class LevelOrderOutputList extends TestCase {
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
	 public LevelOrderOutputList bst; 
	 
	 public void setUp() {
		 bst = new LevelOrderOutputList();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
  
	/**
	 *  levelOrderBottom2UpByQueueBFS ( From Bollom to Top, print each level nodes)
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	/**
	 * 
	 * hitachi-data-systems-interview-questions 
	 * level order from top to bottom , output:
	 * [[8], [6,10],[4,7,9,12],[2,5]]
	 * 
	 *  Desigred Complexity : O(N) + O(N).
	 *  
	 * set queue to current<Node> Each time currentQueue remove, 
	 * use Queue belowLevel(Node.left) or belowLevel(Node.right)
	 * =check if currentQueue.isEmpty();, if yes, currentQueue = belowLevel
	 * eachLevel record each level node, reverse it by size-1 to 0
	 * 
	 * (1) currentLevel queue top data poll out to LevelList as current level
	 * (2) left and right data is put into belowLevel queue which record current level
	 * (3) keeping poll out , currentLevel queue must be empty and belowLevel queue
	 *     will be placed to currentLevel and itself must be point to creating new queue object
	 *     add currentLevel to result as one level
 
 
	 * 
	 * @param node
	 * @return
	 */
	
	public List<List<Integer>> LevelOrderByBFS(Node node) {
		
		List<List<Integer>> resultList = new ArrayList <List<Integer>>();
		List<Integer> levelList = new ArrayList <Integer>();
		// Using LinkedList as Queue
		LinkedList<Node> currentLevel = new LinkedList<Node>();		
		LinkedList<Node> belowLevel = new LinkedList<Node>();
		currentLevel.add(node); 
		 
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
				belowLevel = new LinkedList<Node>();
				resultList.add(levelList);
				levelList = new ArrayList<Integer>();
			}
		}
		 
		return resultList;
	}
	@Test
	public void testlevelOrderBottom2UpByQueueBFS() {
		System.out.println("levelOrderBottom2UpByQueueBFS()");
		List<List<Integer>> result=bst.LevelOrderByBFS(n8);		 
		System.out.println(result.toString());
		 
	}
}
