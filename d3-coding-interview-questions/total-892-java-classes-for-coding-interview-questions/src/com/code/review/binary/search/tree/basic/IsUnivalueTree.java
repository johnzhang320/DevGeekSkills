package com.code.review.binary.search.tree.basic;

import java.util.ArrayList;
import java.util.List;
/**
 * A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.
 * Input: [1,1,1,1,1,null,1]
Output: true
Input: [2,2,2,5,2]
Output: false
 *
 */
public class IsUnivalueTree {
	 class TreeNode {
		 int val;
		 TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	 }
	 public boolean isUnivalTree(TreeNode root) {
	        List<Integer> list = new ArrayList<>();
	        helper(root,list);
	        Integer prev = list.get(0);
	        for (int i=1; i<list.size();i++) {
	            Integer cur = list.get(i);
	            if (prev!=cur && cur!=null && prev!=null) {
	                return false;
	            }  
	        }
	        return true;
	    } 
	    public void helper(TreeNode root,List<Integer> list)  {
	        if (root==null) {
	            return ;
	        }
	        list.add(root.val); 
	        helper(root.left,list);
	        helper(root.right,list);
	    }  
}
