package com.code.review.array.combinations.permutations;

import java.util.ArrayList;

public class PermutationSequence {
	/**
	 *  LeetCode � Permutation Sequence (Java)
 

		The set [1,2,3..,n] contains a total of n! unique permutations.
		
		By listing and labeling all of the permutations in order,
		We get the following sequence (ie, for n = 3):
		
		"123"
		"132"
		"213"
		"231"
		"312"
		"321"
		
		Given n and k, return the kth permutation sequence. (Note: Given n will be between 1 and
		 9 inclusive.)
	 * @param args
	 */
	public String getPermutation(int n, int k) {
		 
		// initialize all numbers
		ArrayList<Integer> numberList = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			numberList.add(i);
		}
 
		// change k to be index
		k--;
 
		// set factorial of n
		int mod = 1;
		for (int i = 1; i <= n; i++) {
			mod = mod * i;
		}
 
		StringBuffer result = new StringBuffer() ;
 
		// find sequence
		for (int i = 0; i < n; i++) {
			mod = mod / (n - i);
			// find the right number(curIndex) of
			int curIndex = k / mod;
			// update k
			k = k % mod;
 
			// get number according to curIndex
			result.append( numberList.get(curIndex));
			// remove from list
			numberList.remove(curIndex);
		}
 
		return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermutationSequence ref = new PermutationSequence();
		System.out.println(ref.getPermutation(3, 4)); 
	}

}
