package com.code.review.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class LevelOrderTopBottom extends TestCase {
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
	 public LevelOrderTopBottom bst; 
	 
	 public void setUp() {
		 bst = new LevelOrderTopBottom();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	 
	 /**
		 *   levelOrderTop2BottomByQueueBFS(BFS)(from top to bottom, print each level nodes) 
	     *        8 
		 *      /  \
		 *     6   10
		 *    / \  /  \
		 *   4   7 9  12
		 *  / \  
		 * 2   5
		 * */
		/**
		 * LevelOrder using Broad First Search by queue, top to bottom, level order
		 * 8,6,10,4,7,9,12,2,5 
		 * @param node
		 * @return
		 */
		
		public List<Integer> levelOrderTop2BottomByQueueBFS(Node node) {
			
			
			List<Integer> list = new ArrayList <Integer>();
			Queue<Node> queue = new LinkedBlockingDeque<Node>();		 
			queue.add(node);
			 
			while (!queue.isEmpty()) {
				Node n = queue.remove();
				list.add(n.data);
				if (n.left!=null) {
					queue.add(n.left);   // queue always from left to right
				}
				if (n.right!=null) {
					queue.add(n.right);
				}
			}
			return list;
		}
		@Test
		public void testlevelOrderTop2BottomByQueueBFS() {
			System.out.println("levelOrderTop2BottomByQueueBFS()");
			List<Integer> list =bst.levelOrderTop2BottomByQueueBFS(n8);		 
			int [] result=Utils.print(list);
			Assert.assertArrayEquals(new int[]{8,6,10,4,7,9,12,2,5} , result);
		}
	 
}
