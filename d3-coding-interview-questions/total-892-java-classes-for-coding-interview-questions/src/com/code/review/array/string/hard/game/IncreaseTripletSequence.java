package com.code.review.array.string.hard.game;

public class IncreaseTripletSequence {
	/**
	 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in 
	 * the array.

		Examples:
		Given [1, 2, 3, 4, 5],
		return true.
		
		Given [5, 4, 3, 2, 1],
		return false.
		
		Analysis
		
		This problem can be converted to be finding if there is a sequence such that 
		the_smallest_so_far < the_second_smallest_so_far < current. We use x, y and z to 
		denote the 3 number respectively. 
		my approach
		set count+1 if prev<current else count=1; , if count >=3 
	 * @param args
	 */
	public static boolean increaseTripletSequence(int A[]) {
		if (A==null || A.length==0) return false;
		int len = A.length;
		int count=1;
		int max = Integer.MIN_VALUE;
		for (int i=1; i<len;i++) {
			if (A[i-1]<A[i]) {
				count++;
			} else {
				count=1;
			}
			
			max = Math.max(max, count);
			 
		}
		if (max>=3) {
			return true;
		} 
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {1,2,3,4,5,3};
		int A2[] = {6,5,4,3,2,1};
		int A3[] = {5,6,7,1,2};
		System.out.println(increaseTripletSequence(A1));
		System.out.println(increaseTripletSequence(A2));
		System.out.println(increaseTripletSequence(A3));
	}

}
