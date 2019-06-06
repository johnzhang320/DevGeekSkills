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


public class FindDeepestBSTPath extends TestCase {
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
	FindDeepestBSTPath bst;
	
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
		
		bst = new FindDeepestBSTPath();
	}
	 
	
	/**
	 *  Find Deepest path , we must use DFS
	 *    
	 */
	List<Node> result = new ArrayList<Node>();
	public void dfsforPath(Node t,List<Node> l, int max[]){
	    if(t.left==null && t.right==null ){
	    	if (l.size()>max[0]) {
		        ArrayList<Node> temp = new ArrayList<Node>();
		        temp.addAll(l);
		        result = temp;
		       
		        max[0] = l.size();
		         
	    	}
	    }
	 
	    //search path of left node
	    if(t.left != null){
	        l.add(t.left);
	        dfsforPath(t.left,   l,max);
	        l.remove(l.size()-1);
	    }
	 
	    //search path of right node
	    if(t.right!=null){
	        l.add(t.right);
	        dfsforPath(t.right,   l,max);
	        l.remove(l.size()-1);
	    }
	    
	}
	public void FindDeepestPath(Node root) {
		 
		if (null == root) return ;
		ArrayList<Node> currList = new ArrayList<Node>();
	
		currList.add(root);
		int max[] = new int[1];
		max[0]=0;
		 
		dfsforPath(root,currList,max);
		 
		result.forEach((x)->System.out.print(x.data+","));
		 
	
	}
	
	@Test
	public void testfindDeepestPath() {
		System.out.println("findDeepestPath()");
		bst.FindDeepestPath(A);  
	    //System.out.println("result deepest node is "+deepNode.data);
		 
	}
}
