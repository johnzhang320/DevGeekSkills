package com.code.review.facebook.code.lab;

public class LastWordLength {
	/**
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
	 *  return the length of last word in the string.

		If the last word does not exist, return 0.
		
		Note: A word is defined as a character sequence consists of non-space characters only.
		
		Example:
		
		Given s = "Hello World",
		
		return 5 as length("World") = 5.
		
		Please make sure you try to solve this problem without using library functions. Make 
		sure you only traverse the string once.
	 *  my Solution:
	 *  
	 */
	public static int lastWordLength(String s) {
		if (null == s || s.length()==0 || s.length()==1) {
			return 0;
		}
		for (int p = s.length()-2; p>0; p--) {
			if (s.charAt(p-1)!=' ' && s.charAt(p)==' '&& s.charAt(p+1)!=' ') {
				return s.length()-(p+1);
			}
		}
		return 0;
 	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "Hello World";
		System.out.println(lastWordLength(s));
		s = "World";
		System.out.println(lastWordLength(s));
	}

}
