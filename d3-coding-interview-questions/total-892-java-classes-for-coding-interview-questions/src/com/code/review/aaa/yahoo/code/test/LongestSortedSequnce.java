package com.code.review.aaa.yahoo.code.test;

import java.util.Arrays;
import java.util.LinkedList;

public class LongestSortedSequnce {
	/**
	 * 
	 *   yahoo-interview-questions
 
		Longest increasing subsequence:
		
		Given n numbers A1..An determine subsequence of maximum length values in the subsequence form a 
		strictly increasing sequence.
		
		ex: input 5,2,6,3,4,1,9,9,8,9,5
		output: 2,3,4,8,9
	 * 
	 *  My Implementation O(N)
	 *  (1) create currentList and LongestList
	 *  (2) find element of A[] > currentList.getLast() then add it to currentList
	 *  (3) if (2) NO, check if currentList.size() > LongestList.size() then currentList saved to longestList
	 *  (4) after i to len end check if currentList.size() > LongestList.size() then currentList saved to longestList
	 */
	public static LinkedList<Integer> longestSortedSequnce(int A[]) {
		if (null == A || 0==A.length) return null;
		
		LinkedList<Integer> currentList= new LinkedList<Integer>();
		LinkedList<Integer> longestList= new LinkedList<Integer>(); 
		for (int i=0;i<A.length;i++) {
			if (currentList.size()==0 || A[i] >= currentList.getLast()) {
				currentList.add(A[i]);
			} else {
				if (currentList.size()>longestList.size()) {
					longestList = currentList;
				}
				currentList = new LinkedList<Integer>();
				currentList.add(A[i]);
			}
		}
		if (currentList.size()>longestList.size()) {
			longestList = currentList;
		}
		return longestList;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {5,2,6,3,4,1,9,9,8,9,5};
		System.out.println(longestSortedSequnce(A) );
	}

}
