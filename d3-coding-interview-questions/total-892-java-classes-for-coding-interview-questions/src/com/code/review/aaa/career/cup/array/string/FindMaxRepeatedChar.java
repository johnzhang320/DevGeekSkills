package com.code.review.aaa.career.cup.array.string;

public class FindMaxRepeatedChar {
	/**
	 * /**
	 * 
	 *  amazon-interview-questions
 

		A = "ffgggtvshjsdhjfffffffhvjbjcharu"
		Find the max consecutive repitative character
		Output : f -> 7
		
	 */
	/**
	 * (1) create index start which always point start char if char is repeated
	 * (2) create variable char prev, compare curr char with prev
	 * (3) create max variable record max count of repeated char and result variable record max repeated char
	 * (4) if curr does not match prev, then count = i-start, start=i; if count > max, save prev to maxChar, count to max     
	 * (5) always prev = A[i];
	 */
	public static char findMaxRepeatedChar(String s) {
		if (null==s ||0 == s.length()) return '\n';
		int start=0;
		char prev=s.charAt(0);
		int max = Integer.MIN_VALUE;
		char maxChar=' '; 
		int count=0;
		for (int i=1; i<s.length();i++) {
			char curr = s.charAt(i);
			count = i -start;
			if (curr!=prev) {
				start = i;
				if (count>max) {
					max = count;
					maxChar = prev;
				}
			}
			prev = curr;
		}
		return maxChar; 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String A = "ffgggtvshjsdhjfffffffhvjbjcharu";
		System.out.println(findMaxRepeatedChar(A));
	}

}
