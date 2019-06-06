package com.code.review.facebook.code.lab;

import java.util.ArrayList;

public class KthPermutation {
	/**
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.

		By listing and labeling all of the permutations in order,
		We get the following sequence (ie, for n = 3 ) :
		
		1. "123"
		2. "132"
		3. "213"
		4. "231"
		5. "312"
		6. "321"
		
		Given n and k, return the kth permutation sequence.
		
		For example, given n = 3, k = 4, ans = "231"
		
		    Good questions to ask the interviewer :
		
		        What if n is greater than 10. How should multiple digit numbers be represented in string?
		        > In this case, just concatenate the number to the answer.
		        > so if n = 11, k = 1, ans = "1234567891011"
		
		        Whats the maximum value of n and k?
		        > In this case, k will be a positive integer thats less than INT_MAX.
		        > n is reasonable enough to make sure the answer does not bloat up a lot.

		My Approach:
		Apply Original Permutation (recursive permutation)
		the difference is start == k return 
	 *  
	 */
	public static String permutation(int num[],int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (null==num || 0==num.length) {
			return "";
		}
	
		dfs(result, 0, num, k);
		ArrayList<Integer> retVal = result.get(k-1);
		StringBuffer sb = new StringBuffer();
		for (Integer i: retVal) {
			sb.append(i);
		}
		return sb.toString();
	}
	public static void dfs(ArrayList<ArrayList<Integer>> result, int start, int num[], int k) {
		if (start >= k-1) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (Integer i:num) {
				tmp.add(i);
			}
			result.add(tmp);
			return;
		}
		for (int i=start; i<num.length; i++) {
			swap(num,start,i);
			dfs(result, start+1, num,k);
			swap(num,start,i);
		}
	}
	private static void swap(int num[],int start, int j) {
		int tmp = num[start];
		num[start] = num[j];
		num[j] = tmp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num[] = {1,2,3};
		int k=4;
		String result = permutation(num,k);
		System.out.print(result); 
		 
	}

}
