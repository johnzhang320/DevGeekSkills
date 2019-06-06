package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class KthLargestElement extends TestCase {
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
	 public KthLargestElement bst; 
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
		 bst = new KthLargestElement();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 *  Kth Smallest Element in a BST (Java) 
	 *  Given a binary search tree, write a function kthSmallest to find the kth smallest element in it. (1 ≤ k ≤ BST's total elements)

		Java Solution 1 - Inorder Traversal
		
		We can inorder traverse the tree and get the kth smallest element. Time is O(n).
		because inorder is go left side till to leaf, then up level right and repeat left to leaf 
	 *  
	 * @param root
	 * @return
	 */
	public int findKthSmallestElement(Node root, int k) {
		Stack<Node> stack = new Stack<Node>();
		 
	    Node p = root;
	    int result = 0;
	 
	    while(!stack.isEmpty() || p!=null){
	        if(p!=null){
	            stack.push(p);
	            p = p.left;
	        }else{
	            Node t = stack.pop();
	            k--;
	            if(k==0) {
	                result = t.data;
	                break;
	            }
	            p = t.right;
	        }
	    }
	 
	    return result;
	}
	@Test
	public void testfindKthSmallestElement() {
		System.out.println("findKthSmallestElement()");
		 
		int result =bst.findKthSmallestElement(n8,3);		 
		Utils.print(result);
		 
	}
}
