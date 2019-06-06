package com.code.review.matrix.middle.dynamic.program;

public class TotalDistinctSequence {
	/**
	 *  LeetCode – Distinct Subsequences Total (Java)
 

		Given a string S and a string T, count the number of distinct subsequences of T in S.
		
		A subsequence of a string is a new string which is formed from the original string by 
		deleting some (can be none) of the characters without disturbing the relative positions 
		of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
		
		Here is an example:
		S = "rabbbit", T = "rabbit"
		
		Return 3.
		
		Analysis
		
		The problem itself is very difficult to understand. It can be stated like this:
		Give a sequence S and T, how many distinct sub sequences from S equals to T?
		How do you define "distinct" subsequence? Clearly, the 'distinct' here mean different 
		operation combination, not the final string of subsequence. Otherwise, the result is 
		always 0 or 1. -- from Jason's comment
		
		When you see string problem that is about subsequence or matching, dynamic programming 
		method should come to mind naturally. The key is to find the initial and changing condition.
		
		Java Solution 1
		
		Let W(i, j) stand for the number of subsequences of S(0, i) equals to T(0, j). 
		If S.charAt(i) == T.charAt(j), W(i, j) = W(i-1, j-1) + W(i-1,j); Otherwise, W(i, j) = W(i-1,j).
	 *  
	 */
	public static int numDistincts(String S, String T) {
		int[][] table = new int[S.length() + 1][T.length() + 1];
	 
		for (int i = 0; i < S.length(); i++)
			table[i][0] = 1;
	 
		for (int i = 1; i <= S.length(); i++) {
			for (int j = 1; j <= T.length(); j++) {
				if (S.charAt(i - 1) == T.charAt(j - 1)) {
					table[i][j] += table[i - 1][j] + table[i - 1][j - 1];
				} else {
					table[i][j] += table[i - 1][j];
				}
			}
		}
	 
		return table[S.length()][T.length()];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "rabbbit";
		String T = "rabbit";
		System.out.println(numDistincts(S, T));
	}

}
