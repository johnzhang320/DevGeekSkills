package com.code.review.binary.search.tree.path.sum;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class SumPathValueIsTarget extends TestCase {
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
	 public SumPathValueIsTarget bst; 
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
		 bst = new SumPathValueIsTarget();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 *   inOrderRecursive(bottom Left -> Parent -> Right)
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	 /**  given sum, find one path which values of node along with the path sum to given sum
	  *   output each node on this path
	  *   solution we using preOrder (top to bottom travel) and travel by stack (depth first search) 
	  *   find leave node which make data from top node to this leave node is target sum
	  *   then return the path by search this node
    *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 */
	public List<Integer> getPathSumByStack(Node root, int sum) {
		 
		 List<Integer> list = new ArrayList <Integer>();
		 Stack<Node> stack = new Stack<Node>();
		 Stack<Integer> values = new Stack<Integer>(); 
		 Node item;
		 
		 if (null==root) return null;
		 
		 stack.add(root);
		 values.add(root.data);
		 
		 while (!stack.isEmpty()) {
			 
			 item =(Node) stack.pop();  // using remove from stack to avoid add visited flag
			 int pathSum = values.pop();
			 
			 
			 // leaf node left and right is null
			 if (item.left==null && item.right==null && pathSum == sum) {
				 // leaf node is item, search item.data
				 Node start = root;
				 while (start!=null) {
					 list.add(start.data);
					// System.out.println(start.data+" ");
					 if (start.data > item.data) {
						 start = start.left;						 
					 } else if (start.data < item.data){
						 start = start.right;
					 } else {
						 
						 return list;
					 }
				 }
				 
				 return list;
				 
			 }
			 // right must be first adding , then return later than left
			 
			 if (item.right!=null) {
				 
				 stack.add(item.right);
				 
				 values.add(pathSum+item.right.data);
			 }
			 
			 if (item.left!=null) {
				 
				 stack.add(item.left);
				 
				 values.add(pathSum+item.left.data);
			 }
			
		 }
		 return null;
	 } 		
	@Test
	public void testgetPathSumByStack() {
		System.out.println("getPathSumByStack()");
		List<Integer> list = new ArrayList<Integer>();
		list =bst.getPathSumByStack(n8,21);		 
		Utils.print(list);
		//Assert.assertArrayEquals(new int[]{8,10,12,9,6,7,5,4,2 } , result);
	}
	
	 
}
