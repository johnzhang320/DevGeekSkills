package com.code.review.array.string.easy.largest;

public class LongestContinuousIncrease {
	/*
	 * Longest Continuous Increasing Subsequence
		Easy
		
		393
		
		90
		
		Favorite
		
		Share
		Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
		
		Example 1:
		
		Input: [1,3,5,4,7]
		Output: 3
		Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
		Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
		Example 2:
		
		Input: [2,2,2,2,2]
		Output: 1
		Explanation: The longest continuous increasing subsequence is [2], its le
		
		my approach was passed leetcode !!!!
		if A[i]-A[i-1] >0 count ++ max = max(max,count) else count =1
	 */
	 public static int findLengthOfLCIS(int[] A) {
	        if (null==A || A.length==0) return 0;
			if (A.length==1) return 1;
	        int n = A.length;
			int diff =0;
	        int max = 1;
	        int count=1;
	        for (int i=1; i<n;i++) {
	            diff = A[i] - A[i-1];
	            if (diff>0) {
	                count++;
	                max = Math.max(max,count);
	            } else {
	                count=1;
	            }
	            
	        }
	        System.out.println("max="+max);
			return max;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,3,5,4,7};
		findLengthOfLCIS(A);
		int A2[] = {2,2,2,2,2};
		findLengthOfLCIS(A2);
	}

}
