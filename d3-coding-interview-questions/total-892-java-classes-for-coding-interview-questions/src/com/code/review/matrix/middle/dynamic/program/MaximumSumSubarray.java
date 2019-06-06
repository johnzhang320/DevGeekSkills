package com.code.review.matrix.middle.dynamic.program;

public class MaximumSumSubarray {
	/**
	 * 
	 * Find the contiguous subarray within an array (containing at least one number) which has 
	 * the largest sum.

		For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] 
		has the largest sum = 6.
		
		1. Dynamic Programming Solution
		
		The changing condition for dynamic programming is "We should ignore the sum of the previous 
		n-1 elements if nth element is greater than the sum."
		dynamic programming thought:  From bottom to top, calculate sum[i] check if max is maxmium so far
		sum[0] = A[0]
		max=0;
		sum[1] = Math.max(A[1], sum[0]+A[1]);
		max = Math(max,sum[1]);
		sum[2] = Math.max(A[2], sum[1]+A[2]);
		max = Math(max,sum[2]);
		sum[3] = Math.max(A[3], sum[2]+A[3]);
		max = Math(max,sum[3]);
		
	 */
	public static int getMaxSum(int A[]) {
		if (null==A || A.length==0) return 0;
		int len = A.length;
		int sum[] = new int[len];
		// get bottom data sum[0]
		sum[0] = A[0];
		int max=Integer.MIN_VALUE;
		for (int i=1; i<len ; i++) {
			sum[i] = Math.max(A[i], sum[i-1]+A[i]);
			max = Math.max(max, sum[i]);
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(getMaxSum(A));
		
	}

}
