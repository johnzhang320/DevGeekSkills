package com.code.review.array.string.easy.sum;

public class ContiguousElemsIsMaxSum {
	/**
	 * From Larry He
	 * Given Array with positive and negative integers, find contiguous subarray sum is max
	 * example arr[] = {1,-2,3,10,-4,7,2,-5} , maximum sum is {3,10,-4, 7, 2} , sum 18
	 * solution:
	 * set current sum when it is or less than zero , take current element as start element to 
	 * contiguously add to current sum. 
	 * 
	 * @param args
	 */
	 public static int ContiguousElemsIsMaxSum(int arr[]) {
		 int currSum=0;
		 
		 int maxSum = Integer.MIN_VALUE;
		 for (int i=0; i< arr.length;i++) {
			 if (currSum<=0) {
				 currSum = arr[i];
			 } else {
				currSum+=arr[i]; 
				if (currSum>maxSum) {
					maxSum = currSum;
				}
			 }			 
		 }
		 return maxSum;
	 }
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,-2,3,10,-4,7,2,-5}; 
		System.out.println(ContiguousElemsIsMaxSum(arr));
	}

}
