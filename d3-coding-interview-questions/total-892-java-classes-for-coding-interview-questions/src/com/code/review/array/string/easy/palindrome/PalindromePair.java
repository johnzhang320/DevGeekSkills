package com.code.review.array.string.easy.palindrome;

import java.util.ArrayList;
import java.util.List;

public class PalindromePair {
	/**
	 *  LeetCode – Palindrome Pairs (Java)
 

		Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the
		concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
		
		Example 1:
		Given words = ["bat", "tab", "cat"]
		Return [[0, 1], [1, 0]]
		The palindromes are ["battab", "tabbat"]
		my approach:
		
		(1) loop word array, if i!=j such that s = append(word[i],word[j]), check if s isPalindrome
		    add the i and j to arraylist
	 * 
	 */
	
	public static List<List<Integer>> palindromePair(String word[]) {
		if (null==word || 0==word.length) return null;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i=0; i<word.length;i++) {
			
			for (int j=0; j<word.length; j++) {
				if (i!=j) {
					StringBuffer sb = new StringBuffer();
					sb.append(word[i]);
					sb.append(word[j]);
					if (isPalindrome(sb.toString())) {
						List<Integer> sl =new ArrayList<Integer>();
						sl.add(i);
						sl.add(j);
						result.add(sl);
					}
				}
			}
		}
		return result;
	}
	
	public static boolean isPalindrome(String s) {
		int low=0;
		int high = s.length()-1;
		while (low<high) {
			if (s.charAt(low)!=s.charAt(high)) {
				return false;
			}
			low++;
			high--;
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String words[] = {"bat", "tab", "cat"};
		List<List<Integer>> result = palindromePair(words);
		 
			for (List<Integer> list:result) {
				System.out.print("\n[ ");
				for (Integer i: list) {
					System.out.print(i+" ");
				}
				System.out.print("]\n");
			}
		
	}

}
