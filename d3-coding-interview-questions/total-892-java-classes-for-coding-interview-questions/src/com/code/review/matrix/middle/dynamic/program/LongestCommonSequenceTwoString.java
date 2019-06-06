package com.code.review.matrix.middle.dynamic.program;

public class LongestCommonSequenceTwoString {
	/**
	 * 
		Dynamic Programming | Set 4 (Longest Common Subsequence)
		
		We have discussed Overlapping Subproblems and Optimal Substructure properties in Set 1 and Set 2 
		respectively. We also discussed one example problem in Set 3. Let us discuss Longest Common 
		Subsequence (LCS) problem as one more example problem that can be solved using Dynamic Programming.
		
		LCS Problem Statement: Given two sequences, find the length of longest subsequence present in 
		both of them. A subsequence is a sequence that appears in the same relative order, but not 
		necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences 
		of “abcdefg”. So a string of length n has 2^n different possible subsequences.
		
		It is a classic computer science problem, the basis of diff (a file comparison program that 
		outputs the differences between two files), and has applications in bioinformatics.
	 */
	 /*
		Examples:
		LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
		LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
		
		Solution one Naive
	 *  The naive solution for this problem is to generate all subsequences of both given 
	 *  sequences and find the longest matching subsequence. This solution is exponential in term of 
	 *  time complexity.
	 *  
	 *  Solution two Dynamic Programming
	 *  Let us see how this problem possesses both important properties of a Dynamic Programming (DP)
	 *  Problem. 
	 *  
	 *  More detail , See my note for LCS of two string
	 *  formula about this problem
	 *  if chars in two strings are same, mean LCS increases one more, if same, keep max LCS 
	 *  dp[0][j]=0
	 *  dp[i][0]=0
	 *  if (str1[i] == str2[j]
	 *     dp[i][j] = 1 + dp[i-1][j-1]
	 *  else 
	 *     dp[i][j] = max(dp[i-1][j] , dp[i][j-1])   
	 *  
	 *  very be care for, we initialized dp one more row and col
	 *  the formula if fit for i=1 to i<=rows and j=1 to j<=cols   
	 */
	public int LengthOfTwoStringsLCS(String str1, String str2) {
		int cols = str1.length();
		int rows = str2.length();
		int dp[][] = new int[rows+1][cols+1];
		// initialize extra row[i]
		for (int i=0;i<=rows;i++) {
			dp[i][0] = 0;
		}
		// initialize extra col[i]
		for (int j=0;j<=cols;j++) {
			dp[0][j] =0;
		}
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=cols;j++) {
				if (str2.charAt(i-1)==str1.charAt(j-1)) {   // dp one more step then string
					dp[i][j] =1 + dp[i-1][j-1];
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		for (int i=1; i<=rows; i++) {
			for (int j=1;j<=cols;j++) {
				System.out.print(dp[i][j]+ " ");
			} 
			
			System.out.println(" ");
		}
		return dp[rows][cols];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "ABCDGHFGRQ";
		String str2 = "AEDFHRQP";
		LongestCommonSequenceTwoString ref = new LongestCommonSequenceTwoString();
		System.out.println(ref.LengthOfTwoStringsLCS(str1, str2));
	}

}
