package com.code.review.linkedlist.basic.partition;

import com.code.review.linkedlist.basic.remove.RemoveKthFromEnd;


public class PartitionLinkedList {
	/**
	 *  Partition the given linkedlist

		Given a linked list and a value x, partition it such that all nodes less than x come before
		nodes greater than or equal to x.
		
		You should preserve the original relative order of the nodes in each of the two partitions.
		
		For example,
		Given 1->4->3->2->5->2 and x = 3,
		return 1->2->2->4->3->5.
		My Approach:
		Create two pointers , smaller and larger, 
	 * 
	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	public ListNode PartitionLinkedList(ListNode head, int x) {
		if (head==null) return null;
		ListNode fk1 = new ListNode(0);
		ListNode fk2 = new ListNode(0);
		fk1.next = head;
		fk2.next = null;
		ListNode smaller = fk1;
		ListNode greater = fk2;
		ListNode p = head;
		
		while (p!=null) {
			if (p.val<x) {
				smaller.next = p;
				smaller = smaller.next;
			} else {
				greater.next = p;
				greater = greater.next;
			} 
			p = p.next;
		}
		
		greater.next = null;
		smaller.next = fk2.next;
		 
		return fk1.next;
	}
	 public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
 
        ListNode fakeHead1 = new ListNode(0);
        ListNode fakeHead2 = new ListNode(0);
        fakeHead1.next = head;
 
        ListNode p = head;
        ListNode p1 = fakeHead1;
        ListNode p2 = fakeHead2;
 
        while(p != null){
            if(p.val < x){
                p = p.next;
                p1 = p1.next;
            }else{
 
                p2.next = p;
                p1.next = p.next;
 
                p = p1.next;
                p2 = p2.next;
            } 
        }
 
        // close the list
        p2.next = null;
 
        p1.next = fakeHead2.next;
 
        return fakeHead1.next;
    }
	 /**
	  * Leet code passsed
	  *  
 
     (1) Create Node p1,p2 with val=0, p1.next=head
      (2) Create dummy1=null and dummy2=null
      (3) Create curr = head;
      (4) while(curr!=null) {
       
      }
    
 
	  * 
	  * @param head
	  * @param x
	  * @return
	  */
	 public ListNode partitionLeetCode(ListNode head, int x) {
	        if (null==head) return null;
	        
	        ListNode p1 = new ListNode(0);
	        ListNode p2 = new ListNode(0);
	       
	        ListNode dummy1=new ListNode(-1);
	        dummy1.next =head;
	        ListNode dummy2=new ListNode(-1);
	        ListNode curr = head;
	        while(curr!=null) {
	            if (curr.val<x) {
	               p1.next = new ListNode(curr.val);
	              
	                if (dummy1.next==head) {
	                    dummy1.next = p1.next;
	                }
	                p1 = p1.next;
	            } else {
	                p2.next = new ListNode(curr.val);
	                if (dummy2.next==null) {
	                    dummy2.next = p2.next;
	                }
	                p2 = p2.next;
	            }
	            curr = curr.next;
	        }
	       
	        p1.next = dummy2.next;
	        
	        return dummy1.next;
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		PartitionLinkedList  ref = new PartitionLinkedList ();
				ListNode n1 = ref.new ListNode(9);
				ListNode n2 = ref.new ListNode(5);
				ListNode n3 = ref.new ListNode(4);
				ListNode n4 = ref.new ListNode(2);
				ListNode n5 = ref.new ListNode(1);
				ListNode n6 = ref.new ListNode(3);
				n1.next = n2;
				n2.next = n3;
				n3.next = n4;
				n4.next = n5;
				n5.next = n6;
				n6.next = null;
				System.out.println("Before:");
				ListNode list = n1;
				while (list!=null) {
					System.out.print(list.val+"->");
					list = list.next;
				}
				int x = 4;
				System.out.println("\nAfter: x ="+x);
				list = ref.partition(n1,x) ;
				//ListNode list = n1;
				while (list!=null) {
					System.out.print(list.val+"->");
					list = list.next;
				}
	}

}
