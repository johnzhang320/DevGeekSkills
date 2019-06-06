package com.code.review.array.string.easy.duplicate;

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
	public static int findLargestNoRepeatedString(String s) {
	    if(s==null || s.length()==0)
	        return 0;
	    int result =0;
	    int start = 0;
	    int len = s.length();
	    for (int i=0; i<len; i++) {
	    		result = Math.max(result, i-start);
		    	for (int j=start;j<i;j++) {
		    		if (s.charAt(i)==s.charAt(j)) {
		    			start = j+1;
		    			break;
		    		}
		    	}
	    }
      	   
	    
	    return result;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str ="deabcadebg";
		System.out.println(findLargestNoRepeatedString(str));
		str="abcabcbb";
		System.out.println(findLargestNoRepeatedString(str));
		str="bbbbbb";
		System.out.println(findLargestNoRepeatedString(str));
		char a='1';
		int i = a-'0';
		System.out.println((i)+","+a);
	}

}
