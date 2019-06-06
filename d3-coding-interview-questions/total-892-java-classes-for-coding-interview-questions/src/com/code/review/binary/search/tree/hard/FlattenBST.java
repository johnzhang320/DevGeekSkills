package com.code.review.binary.search.tree.hard;

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
 

public class FlattenBST extends TestCase {
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
	 public FlattenBST bst; 
	 
   
    
	public void setUp() {
		 bst = new FlattenBST();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 * Given a binary tree, flatten it to a linked list in-place.
		For example,
		Given
		         1
		        / \
		       2   5
		      / \   \
		     3   4   6
		The flattened tree should look like:
		   1
		    \
		     2
		      \
		       3
		        \
		         4
		          \
		           5
		            \
		             6
		Thoughts
		Go down through the left, when right is not null, push right to stack. 
	 * @param root
	 */
	 public void flatten(Node root) {
        Stack<Node> stack = new Stack<Node>();
        Node p = root;
        while(p != null || !stack.empty()){

            if(p.right != null){
                stack.push(p.right);
            }

            if(p.left != null){
                p.right = p.left;
                p.left = null;
            }else if(!stack.empty()){
                Node temp = stack.pop();
                p.right=temp;
            }
             p = p.right;
        }
    }
	@Test
	public void testFlatten() {
		System.out.println("ZigZagBST()");
		bst.flatten(n8);		 
		 
	}
}
