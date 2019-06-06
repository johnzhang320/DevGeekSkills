package com.code.review.binary.search.tree.path.sum;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

/**
 *  yahoo-interview-questions

		
		Find the deepest node in a binary tree:
		
		Example:
		
		  A
		/   \
		B    C
	   / \  / \
	   D E  F G
		\
		 H

		Return Node �H�
 *    My Approach:
 *    1. Create Node which has character val, Node left and Node right
 *    2. This is not Binary Search Tree, no rule about left child < parent and right child > parent
 *    3. Using stack to DFS search each leaf node
 */


public class FindDeepestNodeInBinaryTree extends TestCase {
	class Node {
		char data;
		Node left;
		Node right;
		public Node(char data) {
			this.data = data;
			left=null;
			right=null;
		}
		public void connect(Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}
	FindDeepestNodeInBinaryTree bst;
	
	public Node A=new Node('A');
	public Node B=new Node('B');
	public Node C=new Node('C');
	public Node D=new Node('D');
	public Node E=new Node('E');
	public Node F=new Node('F');
	public Node G=new Node('G');
	public Node H=new Node('H');
	public Node I=new Node('I');
	
	/**
	 * 	  A
		/   \
		B    C
	   / \  / \
	   D E  F G
		\
		 H
        /
       I
	 */
	public void setUp() {
		A.connect(B, C);
		B.connect(D, E);
		D.connect(null, H);
		H.connect(I, null);
		C.connect(F, G);
		bst = new FindDeepestNodeInBinaryTree();
	}

	int max = Integer.MIN_VALUE;
	ArrayList<Node> result;
	public void FindDeepestNodeInBinaryTree(Node root) {
		 
		if (null == root) return ;
		ArrayList<Node> curList = new ArrayList<Node>();
		ArrayList<Node> result = new ArrayList<Node>(); 
		dfs(root,curList);
		System.out.println("\nresult="+result);
		
		
	
	}
	
	private void dfs(Node t, ArrayList<Node> curList) {
		
		// t from root to bottom
		
		
		if (t.left==null && t.right==null) {
			
				
			System.out.println("");
			if (curList.size()>max) {	
				
				result = new ArrayList(curList);	
				
				max = curList.size();
			}  
			curList = new ArrayList<Node>();
			return;
		}
		if (t.left!=null) {		
			curList.add(t);
			dfs(t.left,curList);
			curList.remove(curList.size()-1);
		}
		if (t.right!=null) {
			 
			dfs(t.right,curList);
			curList.remove(curList.size()-1);
		}
	}
	@Test
	public void testLongestConsecutive() {
		System.out.println("FindDeepestNodeInBinaryTree()");
	 
		 bst.FindDeepestNodeInBinaryTree(A);
	    //System.out.println("result deepest node is "+deepNode.data);
		 
	}
}
