package com.code.review.aaa.career.cup.algorithm;

import java.util.HashSet;
import java.util.Set;

public class FindLongestConsectiveSequence {
	/**
	 * Given an array of integers, find the length of the longest sub-sequence such that elements 
	 * in the subsequence are consecutive integers, the consecutive numbers can be in any order. 
	 * Input: arr[] = {1, 9, 3, 10, 4, 20, 2};
		Output: 4
		The subsequence 1, 3, 4, 2 is the longest subsequence
		of consecutive elements
		
		Input: arr[] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42}
		Output: 5
		The subsequence 36, 35, 33, 34, 32 is the longest subsequence
		of consecutive elements. 
		
		input [100, 4, 200, 1, 3, 2] output [4,1,3,2]
		
		output 4
	 */
	/**
	 *  My Approach O(n):
	 *  1) Create HashSet and add all element in set
	 *  2) try to find if A[i] -1 in set, if not found, this A[i] could be start of sequence, put to start
	 *  3) check if start exists in set, if yes, start++; 
	 *  4) check if start - A[i] > max, put into max
	 * 
	 */
	public static int findConsectiveSequence(int A[]) {
		if (null==A || 0==A.length) return 0;
		Set<Integer> set =new  HashSet<Integer>();
		for (Integer num:A) {
			set.add(num);
		}
		int max = Integer.MIN_VALUE;
		int start = 0;
		
		for (int i=0;i<A.length;i++) {
			// check if A[i]-1 not exists in set, if yes then A[i] = start;
			if (!set.contains(A[i]-1)) {
				start = A[i];
				// check if sequence element starting with start and increment one
				while (set.contains(start)) {
					start++;
				}
				// store max one
				if (start-A[i]>max) {
					max = start-A[i];
				}
			} 			
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[] = {1, 9, 3, 10, 4, 20, 2};
		int arr2[] = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
		int arr3[] = {100, 4, 200, 1, 3, 2};
		
		int arr4[] = {100, 4, 200, 1, 3, 2,6,7,8,9,10};
		
		System.out.println("for arr1:"+findConsectiveSequence(arr1));
		System.out.println("for arr2:"+findConsectiveSequence(arr2));
		System.out.println("for arr3:"+findConsectiveSequence(arr3));
		System.out.println("for arr4:"+findConsectiveSequence(arr4));
	}
	/*
	 * (1) create HashSet and add all element to it
	 * (2) i to len , check if A[i]-1 in set, if yes, start = A[i]
	 * (3) keep check if start -1 in set, if yes, start ++ ,loop
	 * (4) end internal loop, save start-A[i] to max 
	 */
	public int findMaxSeq(int A[]) {
		if (null==A || 0==A.length) return 0;
		int max = Integer.MIN_VALUE;
		Set<Integer> set = new HashSet<Integer>();
				
		for (int i=0; i<A.length;i++) {
			if (set.contains(A[i]-1)) {
				int start = A[i];
				while(set.contains(start)) {
					start++;
				}
				if (start-A[i]>max) {
					max=start-A[i];
				}
			}
			
		}
		return max;
	}

}
