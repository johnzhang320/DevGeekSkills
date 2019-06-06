package com.code.review.array.string.easy.palindrome;

public class LargestPalindromeString {
	/**
	 * 
	 *  Leetcode � Longest Palindromic Substring (Java)
 

		Finding the longest palindromic substring 
		input "aacbbcaaabgt"
		return aacbbcaa
		
		is a classic problem of coding interview. This post 
		summarizes 3 different solutions for this problem.
		
		1. Dynamic Programming
		
		Let s be the input string, i and j are two indices of the string. Define a 2-dimension array 
		"table" and let table[i][j] denote whether a substring from i to j is palindrome.
		
		Changing condition:
		
		table[i+1][j-1] == 1 && s.charAt(i) == s.charAt(j)
		=>
		table[i][j] == 1
		 For example, if the input string is "dabcba", the final matrix would be the following:

			1 0 0 0 0 0 
			0 1 0 0 0 1 
			0 0 1 0 1 0 
			0 0 0 1 0 0 
			0 0 0 0 1 0 
			0 0 0 0 0 1 

		
		Time O(n^2) Space O(n^2)
	 */
	public static String longestPalindrome(String s) {
	    if(s==null || s.length()<=1)
	        return s;
	 
	    int len = s.length();
	    int maxLen = 1;
	    boolean [][] dp = new boolean[len][len];
	 
	    String longest = null;
	    for(int l=0; l<s.length(); l++){
	        for(int i=0; i<len-l; i++){
	            int j = i+l;
	            if(s.charAt(i)==s.charAt(j) && (j-i<=2||dp[i+1][j-1])){
	                dp[i][j]=true;
	 
	                if(j-i+1>maxLen){
	                   maxLen = j-i+1; 
	                   longest = s.substring(i, j+1);
	                }
	            }
	        }
	    }
	 
	    return longest;
	}
	/**
	 * 2. A Simple Algorithm
	 *    This algorithm define i = 0 to len, i++
	 *    for odd, i is center, move from i to left and i to right, save maximum length if c(begin) == c(end) 
	      for even i and i+1 , move forward two side check if c[begin]==c[end], save end-left if max
		Time O(n^2), Space O(1)
	 * @param args
	 */
	public static String longestPalindromeSimple(String s) {
		if (s.isEmpty()) {
			return null;
		}
	 
		if (s.length() == 1) {
			return s;
		}
	 
		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			// get longest palindrome with center of i
			String tmp = helper(s, i, i);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
	 
			// get longest palindrome with center of i, i+1
			tmp = helper(s, i, i + 1);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
		}
	 
		return longest;
	}
	 
	// Given a center, either one letter or two letter, 
	// Find longest palindrome
	public static String helper(String s, int begin, int end) {
		while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}
	
/**
 *   What is longest palindrome problem
 *   
	Finding the longest palindromic substring , which means no matter how long the string 
	is, only find palindronic substring in original string
	input "etretaacbbcaaabgt"
	return aacbbcaa
	(1) we create subroutine from low to zero and high to length, if (s.charAt(low) == s.charAt(high) low-- , high++
	(2) if loop break; we return s.subString(low+1,high)
	(3) i = 0 to length-1, call i,i for odd and i, i+1 for even , save longestStr
	(4) return longestString    
 * 
 */
	public static String findCurrentLongestPalindrome(String s, int low, int high) {
		if (null==s || 0==s.length()) return "";
		int len = s.length();
		while (low>=0 && high<=len-1) {
			if (s.charAt(low)==s.charAt(high)) {
				low--;
				high++;
			} else {
				break;
			}
		}
		return s.substring(low+1, high);
	}
	public  static String longestPalindromeSimple2(String s)  {
		if (null==s || 0==s.length()) return null;
		if (1==s.length()) return s;
		String longest = s.substring(0,1);
		for (int i=0; i<s.length() ; i++) {
			// for odd number of elements, center is i
			String tmp = findCurrentLongestPalindrome(s, i,i); 
			if (tmp.length()>longest.length()) {
				longest = tmp;
			}
			tmp = findCurrentLongestPalindrome(s,i,i+1);
			if (tmp.length()>longest.length()) {
				longest = tmp;
			}
		}
		return longest;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "etretaacbbcaaabgt";
		String result = longestPalindrome(s);
		System.out.println(result);
		result = longestPalindromeSimple2(s);
		System.out.println(result);
	}

}
