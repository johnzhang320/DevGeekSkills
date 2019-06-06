package com.code.review.aaa.facebook.code.lab;

 public class ReverseLinkedListBetween {
	/**
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

		For example:
		Given 1->2->3->4->5->NULL, m = 2 and n = 4,
		
		return 1->4->3->2->5->NULL.
		
		    Note:
		    Given m, n satisfy the following condition:
		    1 ≤ m ≤ n ≤ length of list.
				
		    Note 2:
		    Usually the version often seen in the interviews is reversing the whole linked list which 
		    is obviously an easier version of this question.


	 * Node result = null;
		Node current = mid.next;
		Node next;
		Node head2=current; 
		while (current!=null) {
			next = current.next;
			current.next = result;
			result = current;
			head2=current;
			current = next;
		}
		mid.next = head2; 
		
		Definition for singly-linked list.
		  class ListNode {
		      public int val;
		      public ListNode next;
		      ListNode(int x) { val = x; next = null; }
		 }
		
	 * @param args
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	 public ListNode reverseBetween(ListNode head, int m, int n) {
	    if(m==n) return head;
	 
	    ListNode prev = null;//track (m-1)th node
	    ListNode first = new ListNode(0);//first's next points to mth
	    ListNode second = new ListNode(0);//second's next points to (n+1)th
	 
	    int i=0;
	    ListNode p = head;
	    while(p!=null){
	        i++;
	        if(i==m-1){
	            prev = p;
	        }
	 
	        if(i==m){
	            first.next = p;
	        }
	 
	        if(i==n){
	            second.next = p.next;  // p.next
	            p.next = null;
	        }
	 
	        p= p.next;
	    }
	    if(first.next == null)
	        return head;
	 
	    // reverse list [m, n]    
	    ListNode p1 = first.next;
	    ListNode p2 = p1.next;
	    p1.next = second.next;
	   
	   // while(p1!=null && p2!=null){
	    while(p2!=null){
	    	
	        ListNode t = p2.next;
	        p2.next = p1;
	        p1 = p2;
	        p2 = t;
	    }
	 
	    //connect to previous part
	    if(prev!=null)
	        prev.next = p1;
	    else
	        return p1;
	 
	    return head;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseLinkedListBetween ref = new ReverseLinkedListBetween();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(2);
		ListNode n3 = ref.new ListNode(3);
		ListNode n4 = ref.new ListNode(4);
		ListNode n5 = ref.new ListNode(5);
		ListNode n6 = ref.new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;
		int m= 1;
		int n = 3;
		ListNode list = ref.reverseBetween(n1,m, n);
		//ListNode list = n1;
		while (list!=null) {
			System.out.print(list.val+"->");
			list = list.next;
		}
	}

}
