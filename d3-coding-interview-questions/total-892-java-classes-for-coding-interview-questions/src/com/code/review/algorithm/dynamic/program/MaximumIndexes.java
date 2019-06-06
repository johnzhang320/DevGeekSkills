package com.code.review.algorithm.dynamic.program;

public class MaximumIndexes {
	/**
	 * Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].

		If there is no solution possible, return -1.
		
		Example :
		
		A : [3 5 4 2]
		
		Output : 2 
		for the pair (3, 4)


	 *  (1) Loop for i=0 to A.length, Loop j=i+1; j<A.length;i++
	 *  (2) check if A[i] <= A[j], then max = Math.max(max, j-i);
	 */
	public static int maximumIndexes(int A[]) {
		if (null==A || 0==A.length) return -1;
		int max = Integer.MIN_VALUE;
		for (int i=0; i<A.length;i++) {
			for (int j=i+1; j<A.length;j++) {
				if (A[i]<=A[j]) {
					max = Math.max(max, j-i);
				}
			}
		}
		if (max==Integer.MIN_VALUE) {
			return -1;
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {3, 5, 4, 2};
		System.out.println( maximumIndexes(A) );
		
		int A1[] = {5,4,3,2,1};
		System.out.println( maximumIndexes(A1) );
		
		int A2[] = {5,4,3,2,8,9};
		System.out.println( maximumIndexes(A2) );
		
	}

}
