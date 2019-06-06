package com.code.review.binary.search.tree.middle;
/**
 * 
 * /*
Given two binary trees and imagine that when you put one of them to cover the other, 
some nodes of the two trees are overlapped while the others are not.
You need to merge them into a new binary tree. 
The merge rule is that if two nodes overlap, 
then sum node values up as the new value of the merged node. 
Otherwise, the NOT null node will be used as the node of new tree.
Example 1:
Input: 
    Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
         3
        / \
       4   5
      / \   \ 
     5   4   7
Note: The merging process must start from the root nodes of both trees.
 In Fact this merge is pre order or DFS algorithm , usually we consider single
 BST now we consider two tree merge
*/


 
//  Definition for a binary tree node.
 
 
 
public class MergeTwoBinaryTree {
	 public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	 }
	 public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
	        if (t1 == null && t2 == null) {
	            return null;
	        }
	        TreeNode node = new TreeNode(0);
	       
	        
	        node.val += t1 == null ? 0 : t1.val;
	        node.val += t2 == null ? 0 : t2.val;
	        TreeNode t1LeftChild = t1 == null ? null : t1.left;
	        TreeNode t2LeftChild = t2 == null ? null : t2.left;
	        TreeNode t1RightChild = t1 == null ? null : t1.right;
	        TreeNode t2RightChild = t2 == null ? null : t2.right;
	        
	         
	        
	        node.left = mergeTrees(t1LeftChild, t2LeftChild);
	        node.right = mergeTrees(t1RightChild, t2RightChild);

	        return node;
	    }
}
