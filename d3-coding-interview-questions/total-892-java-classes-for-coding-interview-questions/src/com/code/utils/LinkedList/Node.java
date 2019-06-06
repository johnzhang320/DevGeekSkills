package com.code.utils.LinkedList;

 

public class Node {
	 
    	public int data;
    	public Node random;
    	public Node next=null;
    	private  Node head;
    	private  Node tail;
    	private  int size=0;
    	public Node() {}
    	public Node(int data) {
    		this.data=data;
    	}
    	
    	public  Node getHead() {
			return head;
		}
		 
		public  Node getTail() {
			return tail;
		}
		 
		public int size() {
			return size;
		}
		 
		public void insert (int index, int data ) {
    		if (tail==null) {
    			head=new Node(data);
    			tail=head;
    			size++;
    			return;
    		} 
    		int i=1;
    		Node current = head;
    		if (index>size) {
    			AppendToTail(data);
    			size++;
    			return;
    		}
    		while (current!=null) {
    			if (i==index-1) {
    				Node next = current.next;
    				current.next = new Node(data);
    				current.next.next = next;
    				size++;
    				break;
    			}
    			i++;
    			current=current.next;
    		}
    		
    	}
    	public  void AppendToTail(int data) {
    		if (head==null) {
    			head = new Node(data);
    		    tail = head;
    		    head.next=null;
    			size++;
    			return;
    		}
    		
    		tail.next = new Node(data);
    		if (head.next==null) {
    			head.next = tail;
    		}
    		tail = tail.next;
    		size++;
    		 
    	}
    	 
    	public void AppendByNode(Node node) {
    		 
    		if (tail==null) {
    			head = node;
    			tail=head;
    			size++;
    			return;
    		}
    		tail.next = node;
    		tail = tail.next;
    		size++;
    		 
    	}
      public void cleanUp() {
    	  while (head!=null) {
    		  Node temp = head;
    		  head=head.next;
    		  temp=null;
    	  }
    	  tail=null;
    	  size=0;
      }
     public Node initialLinkedList(int d[]) {
           
          for (int i=0;i<d.length; i++ ) {
             this.AppendToTail(d[i]);
          } 
          return this.head;
      }
     public Node initialLinkedList(Integer d[]) {
         
         for (int i=0;i<d.length; i++ ) {
            this.AppendToTail(d[i]);
         } 
         return this.head;
     }
     public  void displayList() {
    	 Node n = head;
    	 while (n!=null) {
      	   System.out.print(" "+n.data);
      	   n=n.next;
         }
     }
	public void setHead(Node head) {
		this.head = head;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
     
}
