package com.code.review.aaa.amazon.code.text;

public class FindMaxDupChar {
	/**
	 * 
	 *  amazon-interview-questions
 

		A = "ffgggtvshjsdhjfffffffhvjbjcharu"
		Find the max consecutive repitative character
		Output : f -> 7
		My Approach
		in i loop, count = i - start, check if prev != curr character
		start = i; 
		if (count>maxCount) then maxCount = count, maxChar = prev
		in i loop bottom always prev = curr
	 */
	
	public static char findMaxDupChar(String s) {
		if (null == s || 0 == s.length()) return 0;
		int maxCount = Integer.MIN_VALUE;
		int count =0;
		int start = 0;
		char maxChar='0';
		char prev = s.charAt(0);
		for (int i=1; i<s.length();i++) {
			count = i-start;
			char curr = s.charAt(i);
			if (prev !=curr) {
				start = i;
				if (count>maxCount) {
					maxCount = count;
					maxChar = prev;
				}
			}
			prev = curr;
		}
		return maxChar;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ffgggtvshjsdhjfffffffhvjbbbbbbbbbbbbbjcharu";
		System.out.println("Most often duplicated char ="+findMaxDupChar(s));
	}

}
