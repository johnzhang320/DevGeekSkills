package com.code.review.array.string.easy.palindrome;

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
	/**
	 * Java Solution 2

		We can solve this problem by using one of the methods which is used to solve the 
		longest palindrome substring problem.
		
		Specifically, we can start from the center and scan two sides. If read the left boundary, 
		then the shortest palindrome is identified.
	 * @param args
	 */
	public String shortestPalindromeScan(String s) {
		if (s == null || s.length() <= 1)
			return s;
	 
		String result = null;
	 
		int len = s.length();
		int mid = len / 2;	
	 
		for (int i = mid; i >= 1; i--) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				if ((result = scanFromCenter(s, i - 1, i)) != null)
					return result;
			} else {
				if ((result = scanFromCenter(s, i - 1, i - 1)) != null)
					return result;
			}
		}
	 
		return result;
	}
	 
	private String scanFromCenter(String s, int l, int r) {
		int i = 1;
	 
		//scan from center to both sides
		for (; l - i >= 0; i++) {
			if (s.charAt(l - i) != s.charAt(r + i))
				break;
		}
	 
		//if not end at the beginning of s, return null 
		if (l - i >= 0)
			return null;
	 
		StringBuilder sb = new StringBuilder(s.substring(r + i));
		sb.reverse();
	 
		return sb.append(s).toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aacecaaa";
		String s2 = "abcd";
		System.out.println(shortestPalindrome(s));
		System.out.println(shortestPalindrome(s2));
	}

}
