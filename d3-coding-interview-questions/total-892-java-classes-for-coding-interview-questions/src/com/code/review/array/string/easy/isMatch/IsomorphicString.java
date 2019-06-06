package com.code.review.array.string.easy.isMatch;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {
	/**
	 *   LeetCode – Isomorphic Strings (Java)
 

		Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic 
		if the characters in s can be replaced to get t.
		
		For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.
		
		Analysis
		
		We can define a map which tracks the char-char mappings. If a value is already mapped, 
		it can not be mapped again. 
		Implementation 
		Set map<Character, Character> , char1 of str1 as key and Char2 of Str2 as value
		if char g of str1 map to char d of str1, say egg and adp, secondly g of str1 could not map to p of str2
	 */
	 public static boolean IsomorphicString(String str1, String str2) {
	 
		 if (str1.length()!=str2.length()) {
			 return false;
		 }
		 Map<Character,Character> map = new HashMap<Character,Character>();
		 
		 for (int i=0; i< str1.length(); i++) {
			 char c1 = str1.charAt(i);
			 char c2 = str2.charAt(i);
			 if (!map.containsKey(c1)) {
				 map.put(c1, c2);
			 } else {
				 if (c2!=map.get(c1)) {
					 return false;
					 
				 }
			 }
		 }
		 return true;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 ="egg";
		String str2 ="adp";
		System.out.println(IsomorphicString(str1, str2));
		str1 ="egg";
		str2 ="app";
		System.out.println(IsomorphicString(str1, str2));
		
				
	}

}
