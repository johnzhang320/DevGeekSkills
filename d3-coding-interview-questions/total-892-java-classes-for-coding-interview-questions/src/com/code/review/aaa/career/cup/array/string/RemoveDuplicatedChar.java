package com.code.review.aaa.career.cup.array.string;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatedChar {
	/**
	 *  yahoo-interview-questions


		Write a function to remove the duplicated characters from a string, and maintain the order 
		of the characters.
		
		ex. “abracadabra” ? “abrcd”
		
	 *  
	 */
	public static String Removedup(String s) {
		Set<Character> set = new HashSet<Character>();
		StringBuffer buf = new StringBuffer();
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (!set.contains(c)) {
				buf.append(c);
				set.add(c);
			}
		}
		return buf.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Removedup("abracadabra"));
	}

}
