package com.code.review.aaa.oracle.code;

public class DeleteDuplicateFromSortedLinkedList {
	 Node head;  // head of list
	  
	    /* Linked list Node*/
	    class Node
	    {
	        int data;
	        Node next;
	        Node(int d) {data = d; next = null; }
	    }
	 
	    void removeDuplicates()
	    {
	        /*Another reference to head*/
	        Node current = head;
	 
	        /* Pointer to store the next pointer of a node to be deleted*/
	        Node next_next;
	 
	        /* do nothing if the list is empty */
	        if (head == null)    
	            return;
	 
	        /* Traverse list till the last node */
	        while (current.next != null) {
	 
	            /*Compare current node with the next node */
	            if (current.data == current.next.data) {
	                next_next = current.next.next;
	                current.next = null;
	                current.next = next_next;
	            }
	            else // advance if no deletion
	               current = current.next;
	        }
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
