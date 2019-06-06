package com.code.review.binary.search.tree.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class PrintBSTAllPaths extends TestCase {
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
	 public Node n7 = new Node(9);
	 public Node n9 = new Node(9);
	 public Node n12 = new Node(12);
	 public Node n2 = new Node(2);
	 public Node n3 = new Node(5);
	 public PrintBSTAllPaths bst; 
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
		 bst = new PrintBSTAllPaths();
		 n8.connect(n6,n10);
		 n6.connect(n4, n7);
		 n10.connect(n9, n12);
		 n4.connect(n2, n3);
	}
	
	/**
	 *  PathSumByStack find the sum of path from top to bottom is given sum data, print out all paths, 
	 *  using Depth first search
	 *  
     *        8 
	 *      /  \
	 *     6   10
	 *    / \  /  \
	 *   4   9 9  12
	 *  / \  
	 * 2   5
	 * */
	/**
	 *  
	 * Depth first Search  
	 * 8 6 4 2 5 9 10 9 12 
		purpose: This is changed format combinationSum dfs
		return paths, as there exist a root-to-leaf path 8->6->4->3 and 8->6->7 which sum is 21.
		
		Analysis:
		This is typical depth first search problem, 
		Which is very same way as CombinationSum recursive target-sum and start is change
		here is left and right node
		
	 * @param root
	 * @return
	 */
	public List<List<Integer>> FindPathSumIsGiven(Node root) {
	   List<List<Integer>> result = new ArrayList<List<Integer>>();
	    if(root == null) 
	        return result;
	 
	    ArrayList<Integer> l = new ArrayList<Integer>();
	    l.add(root.data);
	    dfs(root,  result, l);
	    return result;
	}
	 
	public void dfs(Node t, List<List<Integer>> result, List<Integer> l){
	    if(t.left==null && t.right==null){
	        ArrayList<Integer> temp = new ArrayList<Integer>();
	        temp.addAll(l);
	        result.add(temp);
	    }
	 
	    //search path of left node
	    if(t.left != null){
	        l.add(t.left.data);
	        dfs(t.left, result, l);
	        l.remove(l.size()-1);
	    }
	 
	    //search path of right node
	    if(t.right!=null){
	        l.add(t.right.data);
	        dfs(t.right, result, l);
	        l.remove(l.size()-1);
	    }
	}
	@Test
	public void testFindPathSumIsGiven() {
		System.out.println("FindPathSumIsGiven() , x=23");
		 List<List<Integer>> result = bst.FindPathSumIsGiven(n8);
		 
		 for (List<Integer> l1 : result) {
			 System.out.println("\n");
			 for (Integer i: l1) {
				 System.out.print(i+" ");
			 }
		 }
		 System.out.println(" ");
	}
}
