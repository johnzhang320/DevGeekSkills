package com.code.utils.LinkedList;

public class LinkedListUtil {
	public Node head=null;
	public Node tail=null;
	public int size=0;
	public LinkedListUtil () {
 	}
	public void appendTail(int data) {
		if (head==null) {
			head = new Node(data);
			head.next = null;
			tail = head;
			size=1;
		} else if (head.next==null){
			head.next = new Node(data);
			tail = head.next;
			size++;
		} else {
			tail.next = new Node(data);
			tail = tail.next;
			size++;
		}
	}
	public void initialLinkedList(int d[]) {
        for (int i=0;i<d.length; i++ ) {
            appendTail(d[i]);
         } 
    }
	public void initialLinkedList(Integer d[]) {
        for (int i=0;i<d.length; i++ ) {
            appendTail(d[i]);
         } 
    }
	public void display() {
		Node current = head;
		System.out.println(" ");
		while (current!=null) {
			System.out.print(current.data+" ");
			current = current.next;
		}
		System.out.println(" ");
	}
}
