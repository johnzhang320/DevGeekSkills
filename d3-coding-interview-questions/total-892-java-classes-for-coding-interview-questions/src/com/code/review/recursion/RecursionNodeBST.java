package com.code.review.recursion;

import java.util.ArrayList;
import java.util.List;

import com.code.review.binary.search.tree.basic.BreadthFirstSearchBinarySearchTree;
import com.code.review.binary.search.tree.basic.BreadthFirstSearchBinarySearchTree.Node;

public class RecursionNodeBST {
	public class Node {
		 Node left;
		 Node right;
		 int data;
		 public Node(int data) {
			 this.left = null;
			 this.right = null;
			 this.data = data;
		 }
		 public void connect(Node left, Node right) {
			this.left = left;
			this.right = right;
		 }
	 }
	
	/**
	 * Recursion 
	 * Count node of binary search tree
	 * (1) TreeNode p=null return 
	 * (2) Recursion all nodes from left and right , put the left and right to stack
	 * (3) Non Tail Recursion , pop out all node from call stack 
	 * 
	 * @param args
	 */
	/**
	 *   Create AVL tree
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   7 9  12
	 *  / \  
	 * 2   5
	 * 
	 * public int countBSTNode(Node node) {
		if (node==null) {
			return 0;
		}
		int left = countBSTNode(node.left);
		int right = countBSTNode(node.right);
		System.out.println("node from stack:"+node.data);
		return 1+left+right;
	   }
	    Just like the bottom to top level over if pop out from stack (non-tail recursion)
		node pop() out from stack:2
		node pop() out from stack:5
		node pop() out from stack:4
		node pop() out from stack:7
		node pop() out from stack:6
		node pop() out from stack:9
		node pop() out from stack:12
		node pop() out from stack:10
		node pop() out from stack:8
		Node number=9
	 * 
	 * */
	public int countBSTNode(Node node) {
		if (node==null) {
			return 0;
		}
		int left = countBSTNode(node.left);
		int right = countBSTNode(node.right);
		System.out.println("node pop() out from stack:"+node.data);
		return 1+left+right;
	}
	
	/**
	 *  Combination Sum 
	 *  Give a BST , find path sum is target
	 *  (1) find path from top to bottom , put node val to current list before recursion call
	 *  (2) if node ==null and sum==0  as current list to result list
	 *  (3) in left or right checking and if node==null
	 *  
	 *  
	 * 
	 */
	public List<List<Integer>> combinationSumBinaryTree(Node root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (null == root) return result;
		ArrayList<Integer> current = new ArrayList<Integer>();
		current.add(root.data);
		
		dfs(root,sum-root.data,result,current);
		return result;
	}
	 
	public void dfs(Node t, int sum, List<List<Integer>> result, ArrayList<Integer> l) {
		System.out.println("sum ="+sum);
		 if(t.left==null && t.right==null && sum==0){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.addAll(l);
				result.add(temp);
				 
				return;
			}
		  //search path of left node
		if (sum<0) {
			return;
		}
	    if(t.left != null){
	        l.add(t.left.data);
	        dfs(t.left, sum-t.left.data, result, l);
	        l.remove(l.size()-1);
	    }
	 
	    //search path of right node
	    if(t.right!=null){
	        l.add(t.right.data);
	        dfs(t.right, sum-t.right.data, result, l);
	        l.remove(l.size()-1);
	    }
	   
	}
	public static int maxDepth(Node root) {
		   if (root == null) {
			   return 0;
		   }
		   return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
		}
		   
	    public static int minDepth(Node root) {
		    if (root == null) {
			   return 0;
		     }
	     	 return 1 + Math.min(minDepth(root.left), minDepth(root.right));
	    }
	    // max depth - min depth <=1 
	    public static boolean isBalanced(Node root){
	     return (maxDepth(root) - minDepth(root) <= 1);
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecursionNodeBST bfs = new RecursionNodeBST();
		 Node n8 = bfs.new Node(8); 
		 Node n6 = bfs.new Node(6);
		 Node n10 = bfs.new Node(10);
		 Node n4 = bfs.new Node(4);
		 Node n7 = bfs.new Node(7);
		 Node n9 = bfs.new Node(9);
		 Node n12 = bfs.new Node(12);
		 Node n2 = bfs.new Node(2);
		 Node n5 = bfs.new Node(5);
		 
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n5);
		 System.out.println("Node number="+bfs.countBSTNode(n8));
		 
		 List<List<Integer>> lists= bfs.combinationSumBinaryTree(n8, 20);
		 
		 for (List list:lists) {
			 System.out.println(list.toString());
		 }
		 
	}

}
