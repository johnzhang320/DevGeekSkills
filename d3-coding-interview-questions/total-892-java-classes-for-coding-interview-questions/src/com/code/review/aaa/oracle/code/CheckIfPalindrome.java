package com.code.review.aaa.oracle.code;

public class CheckIfPalindrome {
	/**
	 * A palindrome is a word, phrase, number, or other sequence of characters which 
	 * reads the same backward or forward, such as madam or kayak. 
	 * 
	 * Sentence-length 
	 * palindromes may be written when allowances are made for adjustments to capital 
	 * letters, punctuation, and word dividers, such as "A man, a plan, a canal, Panama!",
	 *  "Was it a car or a cat I saw?" or "No 'x' in Nixon".
	 *  
	 *  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

		For example, "Red rum, sir, is murder" is a palindrome, while "Programcreek is awesome" is not.
		
		Note:
		Have you consider that the string might be empty? This is a good question to ask during an interview.
		
		For the purpose of this problem, we define empty string as valid palindrome.
		
		Thoughts 
		
		From start and end loop though the string, i.e., char array. If it is not alpha or number, increase or 
		decrease pointers. Compare the alpha and numeric characters. The solution below is pretty straightforward
		left is forward 0~len-1 and right is backward, len-1 ~ 0
	 *  
	 *  This is symmetric problem, low and high pointer
	 *  
	 * @param args
	 */
	public static boolean isValidPalindrome(String s){
		if(s==null||s.length()==0) return false;
 
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		System.out.println(s);
		int len = s.length()-1;
		for(int i = 0; i < s.length() ; i++){
			if(s.charAt(i) != s.charAt(len - i)){
				return false;
			}
		}
 
		return true;
	}
	/**
	 *  more efficient
	 *  This is symmetric problem
	 *  (1) replace all special character to s, low =0; high = s.length-1;
	 *  (2) if s.charAt(low) ! = s.char(high) return false;
	 *  (3) if low>high means end of iteration , return true 
	 * 
	 */
	public static boolean isValidPalindrome2(String s) {
		if (null==s || 0==s.length()) return false;
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		System.out.println(s);
		int low = 0;
		int high = s.length()-1;
		while (low<high) {
			if (s.charAt(low)!=s.charAt(high)) {
				return false;
			} else {
				low++;
				high--;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		String str = "A man, a plan, a canal: Panama";
 
		System.out.println(isValidPalindrome2(str));
	}

}
