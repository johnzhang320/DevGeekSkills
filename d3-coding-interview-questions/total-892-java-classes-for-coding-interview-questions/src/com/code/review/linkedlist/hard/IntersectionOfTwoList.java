package com.code.review.linkedlist.hard;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;

public class IntersectionOfTwoList extends TestCase {
	CopyListWithRandomPointer ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new CopyListWithRandomPointer();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * There are two singly linked lists in a system. By some programming error the end node of one of the 
	 * linked list got linked into the second list, forming a inverted Y shaped list. Write a program to 
	 * get the point where two linked list merge. 
	 * A:          a1 -> a2
                    ->
                      c1 -> c2 -> c3
                    ->           
	   B:     b1 -> b2 -> b3
	    intersect at node c1.
	    My Approach:
	    First calculate the length of two lists and find the difference. Then start from the longer list at 
	    the diff offset, iterate though 2 lists and find the node.
	 */
	public class Solution {
	    public Node getIntersectionNode(Node headA, Node headB) {
	        int len1 = 0;
	        int len2 = 0;
	        Node p1=headA, p2=headB;
	        if (p1 == null || p2 == null)
	            return null;
	 
	        while(p1 != null){
	            len1++;
	            p1 = p1.next;
	        }
	        while(p2 !=null){
	            len2++;
	            p2 = p2.next;
	        }
	 
	        int diff = 0;
	        p1=headA;
	        p2=headB;
	 
	        if(len1 > len2){
	            diff = len1-len2;
	            int i=0;
	            while(i<diff){
	                p1 = p1.next;
	                i++;
	            }
	        }else{
	            diff = len2-len1;
	            int i=0;
	            while(i<diff){
	                p2 = p2.next;
	                i++;
	            }
	        }
	 
	        while(p1 != null && p2 != null){
	            if(p1.data == p2.data){
	                return p1;
	            }else{
	 
	            }
	            p1 = p1.next;
	            p2 = p2.next;
	        }
	 
	        return null;
	    }
	}
	
	public void testreverseRightHalf() {
		System.out.println("IntersectionOfTwoList ()");
		Integer data [] = {1,2,3,4,5,6,7,8};
		L1.initialLinkedList(data);
		
		Integer data1 [] = {1,2,3,4,5,6,7,8};
		L2.initialLinkedList(data1);
		 
		 
	}

}
