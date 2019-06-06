package com.code.review.linkedlist.hard;

import com.code.utils.LinkedList.LinkedListUtil;
import com.code.utils.LinkedList.Node;

import junit.framework.TestCase;


public class ReorganizeLinkedList extends TestCase {
	ReorganizeLinkedList ref;
	LinkedListUtil L1,L2,L3,L4;
	public void setUp() {
		ref = new ReorganizeLinkedList();
		L1 = new LinkedListUtil(); 
		L2 = new LinkedListUtil();
		L3 = new LinkedListUtil();
		L4 = new LinkedListUtil();
	}
	/**
	 * Given a single linked list 1->2->3->4->5->6->7
	 * Change it to 1->7->2->6->3->5->4 using O(1) space, suppose odd elements in linked list
	 * If we find middle of linkedlist, then reverse later half of linked list
	 * then we use p1=head and p2=middle to change points to get result
	 * Solution:
	 * (1) p1=head, find middle point -> mid, p2=mid
	 * (2) from mid.next to end, reverse linked list
	 * (3) while (p1!=mid ) {
	 *     Node tmp = p1.next;
	 *     p1.next = p2.next;
	 *     p1= tmp;
	 *     p2 = p2.next
	 *   }
	 *   p2.next = mid;
	 *   mid.next = null;  
	 *  
	 *  
	 */
	public Node reorganize(Node head) {
		// find middle of linkedlist
		Node mid = head;
		Node fast = head;
		while (fast.next!=null) {
			mid = mid.next;
			fast = fast.next;
			if (fast!=null) fast = fast.next;
			 
		}
		System.out.println("");
		System.out.println("Middle="+mid.data);
		
		// reverse right half linked list 
		Node result = null;
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
		System.out.println("");
		// reorganize linked list
		Node p1 = head;
		while(p1!=null ) {
			
			System.out.print(p1.data+" ");
			p1 = p1.next;
			
		}
		System.out.println("");
		p1 =head;
		Node p2 = mid.next;
		
		// Shuffle p1 and p2 , append mid after shuffle finished
		
		head = shuffleMerge(p1, p2, mid);
		
		//LetCode did not work 
		return head;
	}
	// Shuffle merge linkedlist, create dummy node which never create extra memory
	// that only point original node while shuffle two nodes
	public  Node shuffleMerge(Node p1, Node p2,Node mid) {
		Node dummy = new Node(-1);
		Node tail = dummy;
		dummy.next=null;
		Node prev = p2;
		while (p2!=null && p1!=null) {
			if (p1 == null) {
				tail.next = p2;
				break;
			} else if (p2 == null) {
				tail.next = p1;
				break;
			} else {
				tail.next = p1;
				tail = p1;
				p1 = p1.next;
				tail.next = p2;
				tail = p2;
				prev = p2;
				p2 = p2.next;
			}
		}
		
		p2 = prev;
		mid.next = null;
		p2.next = mid;
		return (dummy.next);
	}
	
	public void reorderList(Node head) {
		 
		if (head != null && head.next != null) {
 
			Node slow = head;
			Node fast = head;
 
			//use a fast and slow pointer to break the link to two parts.
			while (fast != null && fast.next != null && fast.next.next!= null) {
				//why need third/second condition?
				System.out.println("pre "+slow.data + " " + fast.data);
				slow = slow.next;
				fast = fast.next.next;
				System.out.println("after " + slow.data + " " + fast.data);
			}
 
			Node second = slow.next;
			slow.next = null;// need to close first part
 
			// now should have two lists: head and fast
 
			// reverse order for second part
			second = reverseOrder(second);
 
			Node p1 = head;
			Node p2 = second;
 
			//merge two lists here
			while (p2 != null) {
				Node temp1 = p1.next;
				Node temp2 = p2.next;
 
				p1.next = p2;
				p2.next = temp1;		
 
				p1 = temp1;
				p2 = temp2;
			}
		}
	}
 
	public static Node reverseOrder(Node head) {
 
		if (head == null || head.next == null) {
			return head;
		}
 
		Node pre = head;
		Node curr = head.next;
 
		while (curr != null) {
			Node temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
 
		// set head node's next
		head.next = null;
 
		return pre;
	}
	public void testReorganizeLinkedList() {
		System.out.println("ReorganizeLinkedList()");
		Integer data [] = {1,2,3,4,5,6,7};
		 
		L1.initialLinkedList(data);  
	 
		Node n = ref.reorganize(L1.head);		
		 
		System.out.println("\n--------------After Reorganized------------");
		 
		while (n!=null) {
			System.out.print(n.data+"->");
			n = n.next;
		}
	}
}
