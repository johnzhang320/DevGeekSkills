package com.code.review.array.string.easy.largest;

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
			if (A[i]-A[i-1]==1) {    // contiguously count and store if count is max
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
	/**
	 * (1) define x,y,z, x and y is initialize MAX, z = A[i]
	 * (2) if x>=z then x = z else if y>=z then y=z else return true
	 *     because x first x>=z, x = z, then if z greater then x must check y>=z
	 *     
	 * 
	 */
	public static boolean increaseTripletSequence2(int A[]) {
		if (A==null || A.length==0) return false;
		int len = A.length;
		int x=Integer.MAX_VALUE;
		int y=Integer.MAX_VALUE;
		for (int i=0;i<len;i++) {
			int z = A[i];
			if (x>=z) {
				x=z;
			} else if (y>=z) {  // if z >x which must be finished initialized by previous z
				y=z;
			} else {  // x and y must be initialized by increase elements 
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {1,2,3,4,5,3};
		int A2[] = {6,5,4,3,2,1};
		int A3[] = {5,1,6,0,2,3};
		System.out.println(increaseTripletSequence(A1));
		System.out.println(increaseTripletSequence(A2));
		System.out.println(increaseTripletSequence2(A3));
	}

}
