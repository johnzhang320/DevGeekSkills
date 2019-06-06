package com.code.review.algorithm.depth.first.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.code.utils.BST.Node;
import com.code.utils.BST.Utils;

import junit.framework.TestCase;
 

public class CombinationSumBSTPaths extends TestCase {
	/**
	   Given following tree, given sum =  22, find all combination of paths sum is target sum
	          5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
       
       [
		   [5,4,11,2],
		   [5,8,4,5]
		] 
       This is typical combination summary for binary tree 
       using DFS , dfs left and dfs right, in dfs parameter, target sum - left.data and sum - right.data
       in temp list, add value before dfs and remove tail value after dfs
       check if sum==0 && left==null and right==null add temp list new instance to result
       
       (1)  void dfs (Node t, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> l)
       (2)  check if t.left==Null && t.right==Null && sum==0 then save l to result  , which mean t is leaf node and sum of all node current path is target sum
       (3)  check if t.left!=null then t.left.data to l , dfs recursive and l,remove(l.size()-1) to similiar to t.right
       
        
	 * */
	 public Node n5 = new Node(5); 
	 public Node n4 = new Node(4);
	 public Node n8 = new Node(8);
	 public Node n11 = new Node(11);
	 public Node n13 = new Node(13);
	 public Node n44 = new Node(4);
	 public Node n7 = new Node(7);
	 public Node n2 = new Node(2);
	 public Node n55 = new Node(5);
	 public Node n1 = new Node(1);
	 
	 public CombinationSumBSTPaths bst; 	
	public void setUp() {
		 bst = new CombinationSumBSTPaths();
		 n5.connect(n4,n8);
		 n4.connect(n11,null);
		 n8.connect(n13, n44);
		 n11.connect(n7, n2);	
		 n44.connect(n5, n1);
	}
	public List<List<Integer>> combinationSumBinaryTree(Node root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (null == root) return result;
		ArrayList<Integer> current = new ArrayList<Integer>();
		current.add(root.data);
		
		dfs(root,sum-root.data,result,current);
		System.out.println("Result="+result.toString());
		return result;
	}
	public void dfs(Node t, int sum, List<List<Integer>> result, ArrayList<Integer> l) {
	//	System.out.println("sum ="+sum);
		 if(t.left==null && t.right==null && sum==0){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.addAll(l);
			result.add(temp);
		}
		  //search path of left node
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
	@Test
	public void testLongestConsecutive() {
		System.out.println("combinationSumBinaryTree()");
		int sum = 22;
		List<List<Integer>> result = bst.combinationSumBinaryTree(n5, sum);
	    System.out.println("result.size()="+result.size());
		for (List<Integer> list: result) {
			System.out.print("\n[");
			for (Integer i: list) {
				System.out.print(i+" ");
			}
			
		}
		 
	}
}
