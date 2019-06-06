package com.code.review.aaa.oracle.code;
/**
 * 
 * 
	Largest Sum Contiguous Subarray
	
	Write an efficient C program to find the sum of contiguous subarray within a one-dimensional 
	array of numbers which has the largest sum.
	
	Input   {-2, -3, 4, -1, -2, 1, 5, -3}
	output 7 (4, -1 , -2, 1,5]
	This is 
	kadane-algorithm
	(1) define max_end_here=0 and max_so_far=0
	(2) max_end_here +=A[i], if max_end_here <0 then max_end_here =0
	(3) if max_end_here > max_so_far, then max_so_far=max_end_here
	(4) return max_so_far
 *
 */

public class LongestContiguousSequenceArrayNegative {
	public static int LCSArrayNegative(int A[]) {
		if (null==A|| 0==A.length) return 0;
		int max_end_here=0;
		int max_so_far=0;
		for (int i=0;i<A.length;i++) {
			max_end_here+=A[i];
			if (max_end_here<0) max_end_here=0;
			if (max_end_here>max_so_far) max_so_far = max_end_here;
		}
		return max_so_far;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] =  {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println(LCSArrayNegative(A));
	}

}
