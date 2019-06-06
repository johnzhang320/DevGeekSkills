package com.code.review.array.string.hard.game;

public class ShortestPalindrome {
/**
 *  LeetCode – Shortest Palindrome (Java)
 

	Given a string S, you are allowed to convert it to a palindrome by adding characters in 
	front of it. Find and return the shortest palindrome you can find by performing this transformation.
	
	For example, given "aacecaaa", return "aaacecaaa"; given "abcd", return "dcbabcd".
	
	Java Solution 1
 * @param args
 */
	public static String shortestPalindrome(String s) {
		int i=0;
		int j = s.length()-1;
		while (j>=0) {
			if (s.charAt(i) == s.charAt(j)) {
				i++;
			}
			j--;
		}
		if (i==s.length()) {
			return s;
		}
		String suffix = s.substring(i);
		String prefix = new StringBuffer(suffix).reverse().toString();
		String mid = shortestPalindrome(s.substring(0, i));
		return prefix+mid+suffix;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aacecaaa";
		String s2 = "abcd";
		System.out.println(shortestPalindrome(s));
		System.out.println(shortestPalindrome(s2));
	}

}
