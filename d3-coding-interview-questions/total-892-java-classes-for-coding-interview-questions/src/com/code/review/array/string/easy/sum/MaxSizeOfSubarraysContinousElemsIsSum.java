package com.code.review.array.string.easy.sum;

import java.util.Arrays;

public class MaxSizeOfSubarraysContinousElemsIsSum {
	/**
	 *  int A[] = {2,-1,1,1,3,3,6,2,-1,1,1,1,1,1], int target = 6
	 *  max array size summary to be target
	 *  my approach:
	 *  result =0;
	 *  count = i - start;
	 *  
	 *  if trySum > sum then trySum-=A[start], if trySum<sum then trySum+=A[i]
	 *  if trySum==sum then trySum = A[start+1] start = i; result = Max(result,count);
	 * 
	 */
	public static int MaxSubarraySize(int A[], int sum) {
		if (null==A || 0==A.length) return 0;
		int start = 0;
		int count=0;
		int result=0;
		int trySum = A[0];
		int i=1;
		while (i<A.length) {
			count = i-start;
			if (trySum<sum) {
			//	System.out.println("< start=" +start+",trySum="+trySum); 
				trySum +=A[i++];
			} else if (trySum > sum) {
			//	System.out.println("> start=" +start+",trySum="+trySum); 
				trySum -= A[start];
				start++;
			} else {
			//	System.out.println("= start=" +start+",trySum="+trySum); 
			//	System.out.println("start=" +start+",trySum="+trySum); 
				result = Math.max(result, count);
				trySum = A[start+1];
				i++;
			}
		}
		return result;
	}
	/**
	 *  This is contiguous sub array sum problem, only difference is when target==sum , we do not return result
	 *  if i-start> maxlen keep current result and maxLen = i-start
	 *  
	 */
	public static int[] maxSubarryIsSum(int A[],int target) {
		if (null==A || 0==A.length) return null;
		int maxLen = Integer.MIN_VALUE;
		int start=0;
		int sum=0;
		int result[]=null;
		for (int i=0;i<A.length;i++) {
			while(sum>target && start<i-1) {
				
				sum-=A[start++];
			}
			if (target==sum) {
				if (maxLen<i-start) {
					maxLen = i-start;
					result = Arrays.copyOfRange(A, start, i);
				}
			}
			if (i<A.length) {
				sum+=A[i];
			}
		}
		return result;
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2,-1,1,1,3,3,6,2,-1,1,1,1,1,1,1};
		System.out.println("Max="+MaxSubarraySize(A, 6));
		int result[] = maxSubarryIsSum(A, 6);
		System.out.println("Max Range="+Arrays.toString(result));
	}

}
