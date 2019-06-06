package com.code.review.array.string.easy.duplicate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class FirstCharNonRepeatedOnePass {
    /**
     *  yahoo-interview-questions
 
	    Find the first unrepeated character in a given string. Solve this in a single pass.
	    
	    My implement O(1), real one pass
     *  (1) Create HasMap<Character, count>
     *  (2) LinkedHashSet<Character>
     *  (3) in loop, if number exists in map and count>1 remove number from set if set exist
     *  (4) if not in map, add it to map and hashset 
     *  (5) return first one character in linkedHashSet
     */
	public static char firstCharNonRepeatedOnePass(String str) {
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		Set<Character> set = new LinkedHashSet<Character>();
		for (int i=0; i<str.length(); i++) {
			char key = str.charAt(i);
			if (map.containsKey(key)) {
				int count = map.get(key);
				if (count>=1) {
					if (set.contains(key)) {
						set.remove(key);
					}					
				} 
				map.put(key,count+1);
			} else {
				map.put(key,1);
				set.add(key);
			}
		}
		char first=set.iterator().next();
		 
		return first;
	}
	// from len to 0, if curr is not in Hashtable, add, record index i to first,  then return first
	public static char findFirstChar(String s) {
		int len = s.length()-1;
	 
		int first = -1;
		Set<Character> set = new HashSet<Character>();
		for (int i=len;i>=0;i--) {			
			char curr = s.charAt(i);
			if (!set.contains(curr)) {
				first=i;			 
				set.add(curr);
			} 
			if (first!=-1 && curr == s.charAt(first) && s.charAt(first)!=' ') {
				System.out.println("curr="+curr+",first="+first);
				first=-1;
			}
		}
		if (first!=-1) { 
			return s.charAt(first);
		}  
		return ' ';
	}
	/**
	 * (1) we make i=len to 0
	 * (2) create HashMap<Character, index>, if A[i] is not in HashMap, first= i;
	 * (3) if A[i] is in HashMap, check if first == map.get(curr) , make first =-1
	 * 
	 * @param s
	 * @return
	 */
	public static char findFirstNonRepeatedChar(String s) {
		char A[] = s.toCharArray();
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		int first =-1;
		for (int i=A.length-1; i>=0; i--) {
			if (map.containsKey(A[i])) {
				if (first==map.get(A[i])) {
					first =-1;
				} 
			} else {
				map.put(A[i], i);
				 
				first = i ;
			}
		}
	 
		if (first!=-1) {
			return A[first];
		}
		return ' ';
	}  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String s= "this is abcd this";
        System.out.println(findFirstNonRepeatedChar(s) );
        
         
        
        s = "abcqtabcgcbat";
        
        System.out.println(findFirstNonRepeatedChar(s));
        
	}

}
