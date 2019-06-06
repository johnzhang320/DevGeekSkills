package com.code.review.array.string.easy.palindrome;
/**
 * 
 * 
	Check if a number is Palindrome
	
	Given an integer, write a function that returns true if the given number is palindrome, 
	else false. For example, 12321 is palindrome, but 1451 is not palindrome.
	
	check-if-a-number-is-palindrome
	Solution one
	convert number to string, low =0 , high = s.length; s.charAt(low++) == s.charAt(high--) until low>high
	Solution two
	using x%10 x/10 reverse number , check if equals, let use do it
 */
 
public class checkIfNumberPalindrome {
	public static boolean isPalindrome(int x) {
		int xx = x;
		int reverse=0;
		
		while (x>0) {
			reverse=reverse*10+x%10;
			x = x/10;
		}
		 
		return reverse == xx;
	}
	public static void main(String args[]) {
		int x= 1230321;
		System.out.println(x+" is palindrome ? "+isPalindrome(x));
		x= 1452;
		System.out.println(x+" is palindrome ? "+isPalindrome(x));
	}
}
