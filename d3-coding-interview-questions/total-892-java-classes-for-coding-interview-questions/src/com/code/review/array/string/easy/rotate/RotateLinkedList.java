package com.code.review.array.string.easy.rotate;

public class RotateLinkedList {
	 public class ListNode {
		     int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
	  public ListNode rotateRight(ListNode head, int k) {
	        ListNode back = head;
	        ListNode front = head;
	        int count =0;
	        while (front!=null && count<k ) {
	            front = front.next;
	            count++;
	        }
	        if (front==null) return head;
	        while (front.next!=null) {  // element one at prior to len - k , data =3 above picture
	            back = back.next;
	            front = front.next;
	        }
	     /** //  System.out.println("back.val="+back.val);
	        ListNode tail = back;
	        ListNode newhead = back.next;
	        tail.next = null;
	        back = newhead;
	        while (back.next!=null) {
	        	back = back.next;
	        }
	       // System.out.println("newhead.val="+newhead.val+",back.val="+back.val+",tail.val="+tail.val+",back.next= "+back.next);
	        back.next = head;
	        head = newhead; 
	        */
	        
	        /**
	         * head     back     back.next                front
	         *  1,   2,   3,         4,                      5
	         *                   newhead = back.next       front.next = head
	         *                   back.next=null
	         *                   head = newhead                 
	         */
	        
	        ListNode newhead = back.next;
	        back.next = null;
 	        front.next = head;
 	        head = newhead;
 	        
	       
	        return head;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RotateLinkedList ref = new RotateLinkedList();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(2);
		ListNode n3 = ref.new ListNode(3);
		ListNode n4 = ref.new ListNode(4);
		ListNode n5 = ref.new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		 
		int k=2;
		
		ListNode head = ref.rotateRight(n1, k);
		while (head!=null) {
			System.out.print(head.val+" ");
			head = head.next;
		}
		
	}

}
