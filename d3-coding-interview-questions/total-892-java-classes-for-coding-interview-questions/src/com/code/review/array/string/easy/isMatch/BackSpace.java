package com.code.review.array.string.easy.isMatch;

import java.util.Stack;

public class BackSpace {
	/**
	 * LeetCode passed
	 * 
	Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

		Example 1:
		
		Input: S = "ab#c", T = "ad#c"
		Output: true
		Explanation: Both S and T become "ac".
		Example 2:
		
		Input: S = "ab##", T = "c#d#"
		Output: true
		Explanation: Both S and T become "".
		Example 3:
		
		Input: S = "a##c", T = "#a#c"
		Output: true
		Explanation: Both S and T become "c".
		Example 4:
		
		Input: S = "a#c", T = "b"
		Output: false
		Explanation: S becomes "c" while T becomes "b".
		Note:
		
		1 <= S.length <= 200
		1 <= T.length <= 200
		S and T only contain lowercase letters and '#' characters.
		Follow up:
		
		Can you solve it in O(N) time and O(1) space?

	 */
	/**
	 * Use two stacks to handle this issue
	 * once '#' pop 
	 * finally compare each elements in two stacks
	 * when the length of stacks are same
	 * @param S
	 * @param T
	 * @return
	 */
	 public boolean backspaceCompare(String S, String T) {
	        if (null==S || null==T) return false;
	        Stack<Character> st1 = new Stack<Character>();
	        Stack<Character> st2 = new Stack<Character>();
	        int len1 = S.length();
	        int len2 = T.length();
	        for (int i=0;i<len1;i++) {
	            char cur = S.charAt(i);
	            if (cur=='#') {
	                if (!st1.isEmpty()) {
	                    st1.pop();
	                } 
	            } else {
	                st1.push(cur);
	            }
	        }
	        for (int i=0;i<len2;i++) {
	            char cur = T.charAt(i);
	            if (cur=='#') {
	                if (!st2.isEmpty()) {
	                    st2.pop();
	                } 
	            } else {
	                st2.push(cur);
	            }
	        }
	        if (st1.size()!=st2.size()) {
	            
	           return false;
	        } else {
	            for (int i=0; i<st1.size();i++) {
	                if (st1.pop()!=st2.pop()) return false;
	                
	            }
	            
	        }
	        return true;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
