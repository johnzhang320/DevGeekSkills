package com.code.review.array.string.easy.duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 *  yahoo-interview-questions
 

	Given a String find the first non repeating char in a single pass of the string.
	Assume a big character set like utf-8 (eleminate use of char[256])
	Avoid any subloop to have a very optimal solution
	My Approach:
	1. create a hashset<Character>
	2. check if not exist in set, add to result
 *
 */
public class FindNonRepeatCharsSinglePass {
	public static String findNonRepeatedChar(String s) {
		if (null==s || s.length()==0) return s;
		Set<Character> set = new HashSet<Character>();
		StringBuffer result = new StringBuffer();
		for (int i=0; i<s.length();i++) {
			char key = s.charAt(i);
			if (!set.contains(key)) {
				result.append(key);
			}
		}
		return result.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
