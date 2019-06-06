package com.code.review.aaa.facebook.code.lab;

import java.util.TreeMap;



import java.util.Map;

public class SortLinkedListNLogN {
	/**
	 * 
	 * 

		Sort a linked list in O(n log n) time using constant space complexity.
		
		Example :
		
		Input : 1 -> 5 -> 4 -> 3
		
		Returned list : 1 -> 3 -> 4 -> 5
		My Approach
		Even Merge Sort Linked List or Insert Sort LinkedList directly
		Find middle or Insert point worse case is O(N) total O(n^2)
		Ask interviewer if elements of linked list could be duplicated
		(1) If duplicated we create TreeMap , if not duplicated we create TreeSet
		(2) We implemented using TreeMap, add value of linked list to TreeMap and Count duplicate
		(3) Loop TreeMap and Count to add back to original linked list value only
 	 */
	 class ListNode {
	      public int val;
	      public ListNode next;
	      ListNode(int x) { val = x; next = null; }
	}
	 public ListNode sortList(ListNode a) {
		 if (a==null) return null;
		 if (a.next==null) return a;
		 ListNode p = a;
		 Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
		 while (p!=null) {
			 int key = p.val;
			 if (map.containsKey(key)) {
				 map.put(key, map.get(key)+1);
			 } else {
				map.put(key,1);
			 }
			 p = p.next;
		 }
		 
		 p = a;
		 int count=0;
		 for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
			count = entry.getValue(); 
		 
			if (count==1) {
				p.val = entry.getKey();
				p = p.next;
			} else {
				for (int i=0;i<count;i++) { // duplicated 
					p.val = entry.getKey();
					p = p.next;
				}
			}
		}
		return a;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortLinkedListNLogN ref = new SortLinkedListNLogN();
		ListNode n1 = ref.new ListNode(1);
		ListNode n2 = ref.new ListNode(5);
		ListNode n3 = ref.new ListNode(4);
		ListNode n4 = ref.new ListNode(3);
		ListNode n5 = ref.new ListNode(7);
		ListNode n6 = ref.new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;
		ListNode p = ref.sortList(n1);
		while (p!=null) {
			System.out.print(p.val+" ");
			p = p.next;
		}
	}

}
