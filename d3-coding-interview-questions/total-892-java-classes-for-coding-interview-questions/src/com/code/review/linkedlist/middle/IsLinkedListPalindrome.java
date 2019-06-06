package com.code.review.linkedlist.middle;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class IsLinkedListPalindrome extends TestCase {
	
	
	  
	IsLinkedListPalindrome ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new IsLinkedListPalindrome();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * /**
	 *   LeetCode – Intersection of Two Linked Lists (Java)
 

		Problem
		
		Write a program to find the node at which the intersection of two singly linked lists begins.
		
		For example, the following two linked lists:
		
		A:          a1 -> a2
		                    ->
		                      c1 -> c2 -> c3
		                    ->           
		B:     b1 -> b2 -> b3
		
		begin to intersect at node c1.
		
		Java Solution
		
		First calculate the length of two lists and find the difference. Then start from the longer 
		list at the diff offset, iterate though 2 lists and find the node.	  
	 */
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
