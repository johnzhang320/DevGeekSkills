package com.code.review.algorithm.depth.first.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.Test;

import com.code.utils.BST.Node;

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
		//D.connect(null, H);
		
	    H.connect(I, null);
		C.connect(F, G);
		F.connect(null, H);
		
		bst = new FindDeepestNodeInBinaryTree();
	}
	int maxDeep = Integer.MIN_VALUE;
	Node deepNode ; 
	public Node FindDeepestNodeInBinaryTree(Node root) {
		 
		if (null == root) return null;
		ArrayList<Node> currList = new ArrayList<Node>();
		List<List<Node>> result = new ArrayList<List<Node>>();
		currList.add(root);
		dfs(root,result,currList);
		int max = Integer.MIN_VALUE;
		for (List<Node> l : result) {
			if (l.size()>max) {
				max = l.size();
				deepNode = l.get(max-1);
			} 
		}
		return deepNode;
	
	}
	public void dfs(Node t, List<List<Node>> result, List<Node> l){
	    if(t.left==null && t.right==null ){
	        ArrayList<Node> temp = new ArrayList<Node>();
	        temp.addAll(l);
	        result.add(temp);
	    }
	 
	    //search path of left node
	    if(t.left != null){
	        l.add(t.left);
	        dfs(t.left,  result, l);
	        l.remove(l.size()-1);
	    }
	 
	    //search path of right node
	    if(t.right!=null){
	        l.add(t.right);
	        dfs(t.right,  result, l);
	        l.remove(l.size()-1);
	    }
	}
	@Test
	public void testLongestConsecutive() {
		System.out.println("FindDeepestNodeInBinaryTree()");
	 
		Node deepNode = bst.FindDeepestNodeInBinaryTree(A);
	    System.out.println("result deepest node is "+deepNode.data);
		 
	}
	/**
	 * Broad First Search , finally Node must be deepest ?
	 * @param root
	 * @return
	 */
	public Node findDeepestNode(Node root) {
		 
		Queue<Node> queue = new LinkedBlockingDeque<Node>();
		queue.add(root);
	
		Node tmp=null;
		while (!queue.isEmpty()) {
			tmp = queue.poll();
			if (tmp.left!=null) {
				queue.add(tmp.left);
			}
			if (tmp.right!=null) {
				queue.add(tmp.right);
			}
			System.out.println("tmp="+tmp.data);
		}
		
		return tmp;
	}
	public Node findDeepNode(Node root) {
		LinkedList<Node> queue = new LinkedList<Node>();
		Node node = null;
		queue.add(root);
		while(!queue.isEmpty()) {
			node = queue.remove();
			if (node.left!=null) {
				queue.add(node.left);
			}
			if (node.right!=null) {
				queue.add(node.right);
			}
		}
		return node;
	}
	@Test
	public void testfindDeepestNode() {
		System.out.println("findDeepestNode()");
	 
		Node deepNode = bst.findDeepNode(A);
	    System.out.println("result deepest node is "+deepNode.data);
		 
	}
}
