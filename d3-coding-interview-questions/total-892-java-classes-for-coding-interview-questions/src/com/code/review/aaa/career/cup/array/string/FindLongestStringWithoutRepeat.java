package com.code.review.aaa.career.cup.array.string;

import java.util.HashSet;

public class FindLongestStringWithoutRepeat {
/**
 *  LeetCode � Longest Substring Without Repeating Characters (Java)
 

	Given a string, find the length of the longest substring without repeating 
	characters. For example, the longest substring without repeating letters for 
	"abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring 
	is "b", with the length of 1.
	
	
	
	
	
	
	
	
 *  Implementation
 *  (1) create HashSet<Integer>
 *  (2) define start=0 and maxValue= 0;
 *  (3) i = 0 to A.length-1; check if !set.contains(c) then add c to set
 *  (4) (3) No ,maxValue=max(maxValue,set.size()) while(start<i && s.charAt(start)!=c) then remove start char and start++;
 *  (5) start++;
 *  (6) end of iteration: maxValue = max(maxValue,set.size())
 */
	public static int lengthOfLongestSubstring(String s) {
	    if(s==null || s.length()==0)
	        return 0;
	 
	    HashSet<Character> set = new HashSet<Character>();
	 
	    int max=0;
	 
	    int i=0;
	    int start=0;
	    while(i<s.length()){
	        char c = s.charAt(i);
	        if(!set.contains(c)){
	            set.add(c);
	        }else{
	            max = Math.max(max, set.size());
	 
	            while(start<i&&s.charAt(start)!=c){
	                set.remove(s.charAt(start));
	                start++;
	            }
	            start++;
	        }
	 
	        i++;
	    }
	 
	    max = Math.max(max, set.size());
	 
	    return max;
	}

	/*
	 *  (1) i-start to max
	 *  (2) if
	 *  (3) if not in set add set.size() to max = Math.max(max,set.size())
	 *  (3) then check if start-th char  
	 *  
	 */
	public static int findMaxContiguousSequence(String str) {
		if (null==str || 0== str.length()) return 0;
		int max = Integer.MIN_VALUE;
		int start = 0;
		for (int i=0; i<str.length(); i++) {
			
			char curr = str.charAt(i);
			for (int k=start; k<=i; k ++) {				 
				if (str.charAt(k)==curr) {
					// keep O(n) even two level loop 
					start = k+1;
					break;
				}
			}
			max = Math.max(max,i-start);
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(str));
		System.out.println(findMaxContiguousSequence(str));
	}

}
