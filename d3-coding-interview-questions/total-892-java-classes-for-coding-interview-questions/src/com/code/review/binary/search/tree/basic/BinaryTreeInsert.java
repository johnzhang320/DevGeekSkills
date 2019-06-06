package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class BinaryTreeInsert extends TestCase {
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
	 public Node n5 = new Node(5);
	 public BinaryTreeInsert bst; 
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
		 bst = new BinaryTreeInsert();
	 
	}
	
	/**
	 *   Binary Search Tree Insert , we can do recursive and iteration
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * */
	
	/**
	 * I did recursive insert, which is looking dumb because we need to define the 
	 * root member variable of current class which create thread or root node management headache  
	 * Therefore I prefer use iteration way to solve this problem, keep root always top node
	 * Binary Search Tree insertion always insert new node below a leaf node
	 * we keep search key value base search BST, if key is greater than node.data,
	 * go root.right if less than node.data , goto left node, if node.left or node.right is null
	 * make left or right point to new node
	 * 
 	 */
	// Root always is root of binary search tree, first time to create the tree, pass root=null
	// second time pass return root always no change, always return top node of entire tree
	public Node iterateInsert(Node root, int key ) {
		if (root==null) {
			root= new Node(key);
			return root;
		}
		Node current = root;  // put top node of tree to current
		while (true) {  // we must find current.left==null to add current.left = new node(key)
			            // current.right==null to add current.right = new node(key)
			if (key<current.data) {
				if (current.left!=null) {
					current=current.left;
				} else { // if current.left==null , create a new node under current.left
					current.left = new Node(key);
					System.out.println("Create "+key+" under left of "+current.data);
					break;
				}
			}
			if (key>current.data) {
				if (current.right!=null) {
					current=current.right; 
				} else {
					current.right = new Node(key);
					System.out.println("Create "+key+" under right of "+current.data);
					break;
				}
			}
			if (key==current.data) {
				System.out.println("key "+key+" already exist!");
				break;
			}
		}
		 
		return root;
		
	}
	
	
	@Test
	public void testiterateInsert() {
		System.out.println("iterateInsert()");
		 
	 
		 Node root=null;
		 root = bst.iterateInsert(root,8); 
		 root = bst.iterateInsert(root,6);
		 root = bst.iterateInsert(root,10);
		 root = bst.iterateInsert(root,4);
		 root = bst.iterateInsert(root,7);
		 root = bst.iterateInsert(root,9);
		 root =bst.iterateInsert(root,12);
		 root =bst.iterateInsert(root,2);
		 root =bst.iterateInsert(root,5);	
		 root =bst.iterateInsert(root,5);	
		 root =bst.iterateInsert(root,3);
		 root =bst.iterateInsert(root,15);
		 root =bst.iterateInsert(root,17); 
		 root =bst.iterateInsert(root,19); 
		 List<Integer> list = bst.inOrderByStack(root);		
		 
		 int [] result=Utils.print(list);
		 list = new ArrayList<Integer>();
		 bst.inOrder(root,list);		
		 
		 Utils.print(list);
	  	 //Assert.assertArrayEquals(new int[]{2,4,5,6,7,8,9,10,12 } , result);
		 
		 System.out.println("is balance tree="+isBalance(root));
		 
		 
		System.out.println("IS this BST "+bst.isValidBST(root));
			 
		 
	} 
	

	public void inOrder(Node root,List<Integer> list) {
		if (root==null) {
			return;
		}
		inOrder(root.left,list);
		list.add(root.data);
		inOrder(root.right,list);
		
	}
	
	public List<Integer> inOrderByStack(Node root) {
		Node p = root;
		List<Integer> list = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		//stack || p!=null
		while (!stack.isEmpty() || p!=null) {
			if (p!=null) {
				/**
				 *  if p is not null, push left bottom to stack first
				 */
				stack.push(p);
				p = p.left;
			} else { // once p is null pop stack, p will be right child
				Node n = stack.pop();
				list.add(n.data);
				p = n.right;
				 
			}
		}
		return list;
		
	}
	//---------------Check if balance -----------------
	public int maxDepth(Node root) {
		if (root==null) return 0;
		return (1+Math.max(maxDepth(root.left), maxDepth(root.right)));
	}
	public int minDepth(Node root) {
		if (root==null) return 0;
		return (1+Math.min(minDepth(root.left), minDepth(root.right)));
	}
	public boolean isBalance(Node root) {
		return Math.abs(maxDepth(root)-minDepth(root))<=1;
	}
	
	// -----------------Check if valid ----------------
 
	public boolean help(Node root, int low, int high) {
		if (low>=root.data || high<=root.data) {
			return false;
		}
		if (root.left!=null && !help(root.left, low, root.data))  {
			return false;
		}
		if (root.right!=null && !help(root.right, root.data, high)) {
			return false;
		}
		return true;
	}
	public boolean isValidBST(Node root) {
		return help(root,Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	@Test 
	public void testIsValidBST() {
		System.out.println("IsValidBST");
		boolean result = bst.isValidBST(n8);
		System.out.println("IS this BST "+result);
		assertTrue(result);
	}
 
}
