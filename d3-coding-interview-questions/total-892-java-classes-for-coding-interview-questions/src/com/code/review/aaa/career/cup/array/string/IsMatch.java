package com.code.review.aaa.career.cup.array.string;

public class IsMatch {
	/**
	 * 
	 *  
	 *  yahoo-interview-questions
 

		write custom pattern match function to match following logic
		'.’ Matches any single character.
		‘*’ Matches zero or more of the preceding element.
		The matching should cover the entire input string (not partial).
		The function prototype should be:
		
		bool isMatch(const char *s, const char *p)
		Some examples:
		isMatch(“aa�?,�?a�?) → false
		isMatch(“aa�?,�?aa�?) → true
		isMatch(“aaa�?,�?aa�?) → false
		isMatch(“aa�?, “a*�?) → true
		isMatch(“aa�?, “.*�?) → true
		isMatch(“ab�?, “.*�?) → true
		isMatch(“aab�?, “c*a*b�?) → true
		isMatch(“ccca�?, “c*a�?) → true
		
		My Approach
		'.’ Matches any single character. after comparison of chars of s and p, pointer of p must increased
		‘*’ Matches zero or more of the preceding element. if front chars of '*' match s char,
		    s's pointer increase and p's pointer not increase at all;  
  		if chars between s and p matched but p's pointer is not '.' and '*', points of s and p go further
  		 
	 */
	
	public static boolean isMatch(String str, String pattern) {
		int p=0;
		int s=0;
		int slen = str.length();
		int plen = pattern.length();
		boolean retVal = false;
		for (s=0;s<slen;s++) {
			char sc = str.charAt(s);
			char pc = str.charAt(p);
			if (pc=='*') {
				retVal=true;
				continue; 
			} else if (pc==sc || pc=='.') {
				p++;
				if (p==plen) {
					if (s<slen-1) {
						return false;
					} else {
						return true;
					}
				}
				continue;
			} else {
				return false;
			}
		}
		return retVal;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 	isMatch(“aa�?,�?a�?) → false
		isMatch(“aa�?,�?aa�?) → true
		isMatch(“aaa�?,�?aa�?) → false
		isMatch(“aa�?, “a*�?) → true
		isMatch(“aa�?, “.*�?) → true
		isMatch(“ab�?, “.*�?) → true
		isMatch(“aab�?, “c*a*b�?) → true
		isMatch(“ccca�?, “c*a�?) → true
		 */
		System.out.println(isMatch("aa","a"));
		System.out.println(isMatch("aa","aa"));
		System.out.println(isMatch("aaa","aa"));
		System.out.println(isMatch("aa","a*"));
		System.out.println(isMatch("aa",".*"));
		System.out.println(isMatch("ab",".*"));
		System.out.println(isMatch("aab","c*a*b"));
		
		System.out.println(isMatch("ccca","c*a"));
	}

}
