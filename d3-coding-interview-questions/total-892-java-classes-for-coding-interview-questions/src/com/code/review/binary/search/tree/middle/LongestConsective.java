package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class LongestConsective extends TestCase {
	/**
	 *  preOrderRecursive (top->left->left->bottom->right) DFS
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 3   5
	   /
	 2 
	 * */
	 

	 public Node n8 = new Node(8); 
	 public Node n6 = new Node(6);
	 public Node n10 = new Node(10);
	 public Node n4 = new Node(4);
	 public Node n7 = new Node(9);
	 public Node n9 = new Node(9);
	 public Node n12 = new Node(12);
	 public Node n3 = new Node(3);
	 public Node n2 = new Node(2);
	 public Node n5 = new Node(5);
	 public LongestConsective bst; 
	/**
	 *   Create AVL tree
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 3   5
	  /
	 2
	 */
   
    
	public void setUp() {
		 bst = new LongestConsective();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n3, n5);
		 n3.connect(n2, null);
	}
	
	/**
	 * LongestConsecutive using Depth first search 
	 * Given a binary tree, find the length of the longest consecutive sequence path.
		The path refers to any sequence of nodes from some starting node to any node 
		in the tree along the parent-child connections. The longest consecutive path 
		need to be from parent to child (cannot be the reverse).
	 *  
	 *  
	 *  (2) LongestConsecutive  using Depth first search
	 *  
     *        8 
	 *       /  \
	 *      6   10
	 *     / \  /  \
	 *    4   7 9  12
	 *   / \  
	 *  3   5
	 * /
	  2
	 * inorder 
	 * 2, 3, 4, 5, 6, 7, 8, 9, 10, 12
	 * 
	 * */
	
	
	
	
	public int longestConsecutive(Node root) {
	    if(root==null)
	        return 0;
	 
	    LinkedList<Node> nodeQueue = new LinkedList<Node>();
	    LinkedList<Integer> sizeQueue = new LinkedList<Integer>();
	 
	    nodeQueue.offer(root);
	    sizeQueue.offer(1);
	    int max=0;
	 
	    while(!nodeQueue.isEmpty()){
	        Node head = nodeQueue.poll();
	        int size = sizeQueue.poll();
	 
	        if(head.left!=null){
	            int leftSize=size;
	            if(head.data==head.left.data-1){
	                leftSize++;
	                max = Math.max(max, leftSize);
	            }else{
	                leftSize=1;
	            }
	 
	            nodeQueue.offer(head.left);
	            sizeQueue.offer(leftSize);
	        }
	 
	        if(head.right!=null){
	            int rightSize=size;
	            if(head.data==head.right.data-1){
	                rightSize++;
	                max = Math.max(max, rightSize);
	            }else{
	                rightSize=1;
	            }
	 
	            nodeQueue.offer(head.right);
	            sizeQueue.offer(rightSize);
	        }
 	    }
 	    return max;
	}
	
	int max=0;
	 
    public int longestConsecutiveRecursive(Node root) {
        helper(root);
        return max;
    }
 
    public int helper(Node root) {
        if(root==null)
            return 0;
 
        int l = helper(root.left);
        int r = helper(root.right);
 
        int fromLeft = 0;
        int fromRight= 0;
 
        if(root.left==null){
            fromLeft=1;
        }else if(root.left.data-1==root.data){
            fromLeft = l+1;
        }else{
            fromLeft=1;
        }
 
        if(root.right==null){
            fromRight=1;
        }else if(root.right.data-1==root.data){
            fromRight = r+1;
        }else{
            fromRight=1;
        }
 
        max = Math.max(max, fromLeft);
        max = Math.max(max, fromRight);
 
        return Math.max(fromLeft, fromRight);
    }
	@Test
	public void testLongestConsecutive() {
		System.out.println("longestConsecutive()");
		int result = bst.longestConsecutiveRecursive(n8);
	 
		System.out.println("Result="+ result);
		 
	}
}
