package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class ParentNode extends TestCase {
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
	 public ParentNode bst; 
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
			 bst = new ParentNode();
			 n8.connect(n6,n10);
			 n6.connect(n4, n7);
			 n10.connect(n9, n12);
			 n4.connect(n2, n3);
		}
		
		/**
		Find parent node when child node data is k
		for example k= 5 and return parent node data 4, k= 4 , parent data is 6 
		Standard value search of BST, using prev record parent before go left or right child
	*/
	public Node findParentNode(Node root , int k) {
		 Node p = root;
		 Node prev=null;
		 Boolean found = false;
		 while(p!=null) {
			 if (p.data<k) {
				prev = p;
				p = p.right;
			 } else if (p.data>k) {
				 prev = p;				
				 p = p.left;
			 } else {
				found = true;
				break;
			 }
		 }
		 return found? prev : null;
				 
	}
	 
	@Test
	public void testFindParentNode() {
		System.out.println("findParentNode()");
		Node n=bst.findParentNode(n8,5);		 
		System.out.println("x parent is "+ (n!=null ? n.data:null ));
		
		n=bst.findParentNode(n8,4);		 
		System.out.println("x parent is "+ (n!=null ? n.data:null ));
		
		n=bst.findParentNode(n8,15);		 
		System.out.println("x parent is "+ (n!=null ? n.data:null ));
		 
	}
}
