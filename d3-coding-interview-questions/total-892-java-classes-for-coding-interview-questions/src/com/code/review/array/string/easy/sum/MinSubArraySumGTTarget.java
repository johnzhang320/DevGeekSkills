package com.code.review.array.string.easy.sum;

import java.util.Arrays;

public class MinSubArraySumGTTarget {
/**
 *  Given an sorted array of integers, find the length of smallest contiguous sub array whose sum of elements is greater
 *  than the given positive number
 *  For example
 *  
 *  input A[]= {1,2,3,5,6,9,10,11};
 *  k = 18
 *  output {9,10} instead of {5,6,9}
 *  Using sliding window , if sum of sliding window elements are greater than k , keep the min len and remove left element
 *  
 */
	 public static int[] getMaxSumArray(int A[], int k) {
		 if (null==A || A.length==0) return null;
		 int minlen = Integer.MAX_VALUE;
		 int left =0;
		 int windowSum=0;
		 int right =0; 
		 for (right = 0; right<A.length;right++) {
			 windowSum+=A[right];
			 if (windowSum<0) { // kagan algorithm
				 windowSum=0;
				 left = right;
			 }
			 while (windowSum > k && left<=right) {
				 minlen = Math.min(minlen, right-left+1);
				 windowSum -= A[left];
				 left++;
			 }
		 }
		 return Arrays.copyOfRange(A, left-1,right);
	 }
	 
	 public static int[] getMaxSumArray2(int A[], int k) {
		 if (null==A || A.length==0) return null;
		 int minlen = Integer.MAX_VALUE;
		 int start =0;
		 int windowSum=0;
		 int i;
		 for (i = 0; i<A.length;i++) {
			 windowSum+=A[i];
			 if (windowSum<0) { // kagan algorithm
				 windowSum=0;
				 start = i;
			 }
			 while (windowSum > k && start<=i) {
				 minlen = Math.min(minlen, i-start+1);
				 windowSum -= A[start];
				 start++;
			 }
		 }
		 return Arrays.copyOfRange(A, start-1,i);
	 }
	 /**
	  * Find max size sub array which summary is target
	  * @param A
	  * @param target
	  * @return
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
	 
	 public static void main(String args[]) {
		 int A[]= {1,2,3,5,6,9,10,11};
		 System.out.println(Arrays.toString(getMaxSumArray(A, 14)));
		 System.out.println(Arrays.toString(getMaxSumArray(A, 29)));
		 int A1[]= {10,12,3,5,6,9,10,11,2}; 
		 System.out.println(Arrays.toString(maxSubarryIsSum(A1, 23)));
	 }
}
