package com.code.review.aaa.career.cup.algorithm;

public class FindLongestConsectiveSequenceFromBST {
    /**
     * *(1) in order Traveral BST into list, then check if prev+1 == curr  nlogn time and O(n) space
     *  (2) n=n.left to find min node, keep finding min+1 in BST tree  nlogn time and O(1) space
     *  
     */
	class Node {
		int val;
		Node left;
		Node right;
		public Node(int val) {
			this.val = val;
			left=null;
			right=null;
			
		}
	}
	private Node findVal(Node root,int val) {
		if (root==null) {
			return null;
		}
		 
		if (root.val>val) {
		    return findVal(root.left,val);
		}
		if (root.val<val) {
			return findVal(root.right,val);
		}
		return root;
	}
	private Node findVal2(Node n, int val) {
		while (n!=null) {
			if (n.val>val) {
				n = n.left;
			} else if (n.val<val) {
				n = n.right;
			} else {
				return n;
			}
		}
		return null;
	}
	public int findLCSinBST(Node root) {
		Node p=root;
		Node p2 = root;
		int min=-1;
		while (p!=null) {
			p=p.left;
		}		
		min = p.val;
		int count = 0;
		while (findVal(p2,min)!=null) {
			min++;
			count++;
		}
		return count; 
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
	}

}
