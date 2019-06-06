package com.code.review.amazon.code.text;

 
import com.code.utils.LinkedList.Node;

import java.util.Stack;

import com.code.utils.LinkedList.LinkedListUtil;

import junit.framework.TestCase;
/**
 * 
 *  amazon-interview-questions 

	Add two link list, can not modify the link list, can not reverse the link list first and second.
	
	Link list 1 - 1->2->3->7
	Link list 2 - 2->9
	Out put Sum - 1->2->6->6
 *  My Approach:
 *  Recursive two linkedLists to add, it is too challenge to coordinate addition
 *  Therefore, we push two linkedlists into stacks, then push data to add and
 *  result = digit+"->" + result
 *  
 */

public class AddLinkedList extends TestCase {
	AddLinkedList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new AddLinkedList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	
	public String addLinkedList(Node l1, Node l2) {
		if (l1==null && l2==null) return "";
		Node n1 = l1;
		Node n2 = l2;
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		while (n1!=null) {
			stack1.push(n1);
			n1 = n1.next;
		}
		while (n2!=null) {
			stack2.push(n2);
			n2 = n2.next;
		}
		String result="";
		int cary=0;
		String arrow ="";
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			int v1 =0, v2=0;
		
			if (!stack1.isEmpty()) {
				v1 = stack1.pop().data;
				 
			}
			if (!stack2.isEmpty()) {
				v2= stack2.pop().data;
				 
			}
			int dsum = v1+v2+cary;
			int digit = dsum%10;
			cary = dsum/10;
			System.out.println("digit="+digit);			
			result = String.valueOf(digit)+arrow+result;
			arrow = "->";
		}
		if (cary>0) {
			result = String.valueOf(cary)+arrow+result;
		}
		return result.toString();
	}
	
	public void testPrintRecursive() {
		// TODO Auto-generated method stub
		Integer A [] = {2,3,1,4};
		Integer B [] =   {6,7,9};
		L1.initialLinkedList(A);
		L2.initialLinkedList(B);
		System.out.println(ref.addLinkedList(L1.head,L2.head));		
	}
	 

}
