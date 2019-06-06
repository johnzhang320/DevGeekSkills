package com.code.review.amazon.code.text;

import java.util.HashSet;

import com.code.review.binary.search.tree.basic.BinaryTreeInsert;
import com.code.utils.BST.Node;

public class FindNumberOfUniqueNodesFromBST {
	/**
	 *  Find all number of nodes which will not be repeated in node.data
	 *  My Approach:
	 *  Inorder traversal BST, add nodes to HastSet<Integer>
	 */
	public void InorderFind(Node root, HashSet<Integer> set) {
		if (root==null) {
			return ;
		}
		InorderFind(root.left,set);
		set.add(root.data);		
		InorderFind(root.right,set);
	}
	public int findUniqueNodes(Node root) {
		HashSet<Integer> set = new HashSet<Integer>();
		InorderFind(root, set);
		return set.size();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeInsert bst =new BinaryTreeInsert();
		 Node root=null;
		 root = bst.iterateInsert(root,8); 
		 root = bst.iterateInsert(root,6);
		 root =bst.iterateInsert(root,10);
		 root = bst.iterateInsert(root,4);
		 root =bst.iterateInsert(root,7);
		 root = bst.iterateInsert(root,9);
		 root =bst.iterateInsert(root,12);
		 root =bst.iterateInsert(root,2);
		 root =bst.iterateInsert(root,5);	
		 root =bst.iterateInsert(root,5);	
		 root =bst.iterateInsert(root,3);
		 root =bst.iterateInsert(root,15);
		 root =bst.iterateInsert(root,17); 
		 root =bst.iterateInsert(root,19); 
		 
		 FindNumberOfUniqueNodesFromBST ref = new FindNumberOfUniqueNodesFromBST();
		 System.out.println(ref.findUniqueNodes(root));
	}

}
