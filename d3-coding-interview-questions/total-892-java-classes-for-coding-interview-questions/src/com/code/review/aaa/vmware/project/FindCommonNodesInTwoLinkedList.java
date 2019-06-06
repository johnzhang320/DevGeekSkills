package com.code.review.aaa.vmware.project;

public class FindCommonNodesInTwoLinkedList {
	/**
	 * 
	 * Write a program to find the node at which the intersection of two singly linked lists begins.

	For example, the following two linked lists:
	
	A:          a1 → a2
				                   ↘
				         c1 → c2 → c3
				                   ↗            
	B:     b1 → b2 → b3
	
	begin to intersect at node c1.
	
	Notes:

    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.

	 * 
	 */
	Node head;  // head of list
	 
    /* Linked list Node*/
    class Node
    {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }
 
    /* Function to get Union of 2 Linked Lists */
    void getUnion(Node head1, Node head2)
    {
        Node t1 = head1, t2 = head2;
 
        //insert all elements of list1 in the result
        while (t1 != null)
        {
            push(t1.data);
            t1 = t1.next;
        }
 
        // insert those elements of list2 that are not present
        while (t2 != null)
        {
            if (!isPresent(head, t2.data))
                push(t2.data);
            t2 = t2.next;
        }
    }
 
    void getIntersection(Node head1, Node head2)
    {
        Node result = null;
        Node t1 = head1;
 
        // Traverse list1 and search each element of it in list2.
        // If the element is present in list 2, then insert the
        // element to result
        while (t1 != null)
        {
            if (isPresent(head2, t1.data))
                push(t1.data);
            t1 = t1.next;
        }
    }
    /* Utility function to print list */
    void printList()
    {
        Node temp = head;
        while(temp != null)
        {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }
 
 
    /*  Inserts a node at start of linked list */
    void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);
 
        /* 3. Make next of new Node as head */
        new_node.next = head;
 
        /* 4. Move the head to point to new Node */
        head = new_node;
    }
 
 
    /* A utilty function that returns true if data is present
       in linked list  else return false */
    boolean isPresent (Node head, int data)
    {
        Node t = head;
        while (t != null)
        {
            if (t.data == data)
                return true;
            t = t.next;
        }
        return false;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
