package com.code.review.aaa.amazon.code;

public class IntegerBinaryIsPalindrome {
	/**
	 *  amazon-interview-questions
 
		Write a function that returns true if the binary representation of an integer is a palindrome.
		
		9 = 1001 = palindrome
		8 = 1000 = not palindrome
	 *  First of All, convert decimal to binary
	 *  mod = d%2
	 *  remaider = d/2
	 *  mod = remainder % 2
	 *  
	 */
	public static String decimalToBinaryString(int num) {
		int d = num;
		StringBuffer result = new StringBuffer();
		while (d>0) {
			result.append(d%2);
			d = d/2;			
		}
		return result.reverse().toString();
	}
	public static boolean isPalindrome(int num) {
		String s = decimalToBinaryString(num);
		int low =0;
		int high = s.length()-1;
		while (low < high) {
			if (s.charAt(low)==s.charAt(high)) {
				low++;
				high--;
			} else {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome(9));
		System.out.println(isPalindrome(11));
		System.out.println(isPalindrome(5));
	}

}
