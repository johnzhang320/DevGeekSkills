package com.code.review.aaa.career.cup.array.string;

public class FindSubArrayWithKElemsToMaxSum  {
	/**
	 * 
	 *  facebook-interview-questions
 

		For a given array, find the subarray (containing at least k number) which has 
		the largest sum.
		Example:
		// [-4, -2, 1, -3], k = 2, return -1, and the subarray is [-2, 1]
		// [1, 1, 1, 1, 1, 1], k = 3, return 6, and the subarray is [1, 1, 1, 1, 1, 1]
		try to do it in O(n) time
		Followup, if input is stream, how to solve it
		public int maxSubArray(int[] nums, int k) {}
		
	 *  
	 */
	/**
	    (1) first , find 0 - k summary called ksum
	    (2) i = k to len, currSum += A[i] - A[i-k]
	    (3) max = Math.max(max, currSum);
	 *  
	 */
 
	public int kSummary(int A[], int k) {
		if (A.length<k) {
			return -1;
		}
	    int ksum = 0;
	    
	    int currSum = 0;
	    for (int i=0; i<k ; i++) {	    	
	    	ksum +=A[i];
	    	 
	    }
	    currSum = ksum;
	   
	    for (int i=k; i<A.length; i++) {
	    	currSum +=A[i]-A[i-k];
	    	ksum = Math.max(ksum, currSum);
	    }
	    return ksum;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {20, -19, 6, 9, 4};
		int A2[] = {10, -3, 4, -2, -1, 10};
		int A3[] = {20, -25, 6, 17, 8};
		int A4[] = {-2, -5, 6, -2, -3, 2, 5, -6};
		FindSubArrayWithKElemsToMaxSum ref = new FindSubArrayWithKElemsToMaxSum();
		System.out.println("--------------Kadane Algorithm(O(n))----------------");
		
		int A5[] = {-2, -3, 4, -1, -2, 1, 5, -13, 23};
		int k = 2;
		System.out.println(ref.kSummary(A1,k));
		System.out.println(ref.kSummary(A2,k));
		System.out.println(ref.kSummary(A3,k));
		System.out.println(ref.kSummary(A4,k));
		System.out.println(ref.kSummary(A5,k));
	}

}
