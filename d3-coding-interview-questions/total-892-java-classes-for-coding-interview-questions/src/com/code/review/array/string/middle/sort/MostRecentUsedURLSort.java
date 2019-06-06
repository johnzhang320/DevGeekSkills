package com.code.review.array.string.middle.sort;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

public class MostRecentUsedURLSort {
    /**
     *  bloomberg-lp-interview-questions
 

		Given a list of URLs entered by a user write a program to print unique and most recently used URLs. 
		For example if user entered the following: -
		1. google.com
		2. yahoo.com
		3. wsj.com
		4. google.com
		
		The output should be :-
		1. google.com
		2. wsj.com
		3.yahoo.com
     *  
     *  
     *  My Implementation
     *  
     *  (1) Create double linked list
     *  (2) Create map<URL,LinkedListNode>
     *  (3) Always add URL object to head of LinkedList and delete duplicated object from linkedList by told by Map<URL,Node>
     *  (4) Print out from head of linkedList
     *  (5) Save/Read object O(1) because of double linkedList and Map
     *  (6) In order to save time to implement LRU, we just use java built in LinkedHashSet to replace double linked list
     */
	
	
	public static void MostRecentUsedURLSort(String [] URL) {
		 Stack<String> urlList = new Stack<String>();
		 if (null==URL || 0==URL.length) return ;
		 for (String s: URL) {
			 if (urlList.contains(s)) {
				 urlList.remove(s);
				 urlList.add(s);
			 } else {
				 System.out.println(s);
				 urlList.add(s);
			 }
			 
		 }
		 System.out.println(urlList.size()); 	 
		 int len = urlList.size();
		 for (int i=0;i<len;i++) {
			 System.out.println(urlList.pop());
		 }
		 
		 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String URL[] = {"google.com","yahoo.com","wsj.com","google.com","wsj.com","wsj.com"};
		MostRecentUsedURLSort(URL); 
	}

}
