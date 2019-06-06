package com.code.review.array.string.easy.largest;

public class FindMaxProductContiguousSubarray {
	/**
	 * 	Find the contiguous subarray within an array (containing at least one number) which has the 
	 *  largest product.
		Return an integer corresponding to the maximum product possible.
		
		Input : [2, 3, -2, 4]
		Return : 6 
		
		Possible with [2, 3]
		
		my approach:
		if no negative number , all continuous elements must produce max product
		Therefore i keep go further, record all elements product as maximum number
		once find current product is smaller than max product, current product =1
		recalculate again 
		
	 */
	/**
	 * (1) define currentProd which is *=A[i]
	 * (2) define currentMax is larger one between currentProd and currentMax
	 * (3) if currentProd < currentMax , then currentPro=1 current 
	 * @param A
	 * @return
	 */
	public static int findMaxProduct(int A[]) {
		if (null==A || 0==A.length) return 0;
		int max = Integer.MIN_VALUE;   // historic Max
		int maxProd =Integer.MIN_VALUE; // current Max
		int currentProd = 1;
		for (int i =0; i< A.length; i++) {
			currentProd *=A[i];
			max = Math.max(max, maxProd);  // record historic
			if (currentProd>=maxProd) {
				maxProd = currentProd;
				
			} else {
				currentProd = 1;
				maxProd = Integer.MIN_VALUE;
			}
		}
		max = Math.max(max, maxProd);
		return max;
	}
	/*
	 * dp[i] = max(A[i], A[i]*dp[i-1])
	 * leetcode consider:
	 * (1) consider {-2, 3, -4} , max = 24 , 
	 * (2) consider {0,2} , max =2
	 * (3) {2,3,-2,4} , max = 6
	 * 
	 * from test case (1) and (3) , check if negative numnber is even , multiply all, odd 
	 */
	public static int longestProddp(int A[]) {
		  if (null==A || 0==A.length ) return 0;
			int n = A.length;
			int maxProd [] = new int[n+1];
	        int minProd [] = new int[n+1];
	        int result = A[0];
	        maxProd[0] = A[0];
	        minProd[0] = A[0];
	        for (int i=1; i<n;i++) {
	            if (A[i]>0) {
	                maxProd[i] = Math.max(A[i],maxProd[i-1]*A[i]);
	                minProd[i] = Math.min(A[i],minProd[i-1]*A[i]);
	            } else {
	                maxProd[i] = Math.max(A[i],minProd[i-1]*A[i]);
	                minProd[i] = Math.min(A[i],maxProd[i-1]*A[i]);
	            }
	            result = Math.max(result,maxProd[i]);
	        }
			return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2, 3, -2, 4,2};
		System.out.println(findMaxProduct(A));
		longestProddp(A);
		int A2[] = {2, 3, -2, 4, 2, -4, 5,2};
		System.out.println(findMaxProduct(A2));
		longestProddp(A2);
		int A3[] = {2,3,-2,4};
		longestProddp(A3);
		int A4[] = {-2, 3, -4};
		longestProddp(A4);
		int A5[]={0,2};
		longestProddp(A5);
	}
}
