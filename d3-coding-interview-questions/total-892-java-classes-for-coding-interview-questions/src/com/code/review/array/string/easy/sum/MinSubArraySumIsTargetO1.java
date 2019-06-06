package com.code.review.array.string.easy.sum;
/**
 * LeetCode – Minimum Size Subarray Sum (Java)
 
	Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
	
	For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length of 2 under the problem constraint.
 *  
 *  Sum is greater than and equal to target
 *  
 *  Solution
 *  maintenance low and high point from zero, using sum windows, while low < A.length, sum is less than target , high increase, add more element to sum
 *  if sum>=target, len = min(len, high-low); if high==low-1, return 1, sum-=A[low] low++
 *  check sum>=s then find min len and sum-=A[low]; low++
 */
public class MinSubArraySumIsTargetO1 {
	public static int greaterEqualTarget(int A[], int target) {
		if (null==A || 0==A.length) return -1;
		int sum=0;
		int low=0;
		int high=0;
		int len = Integer.MAX_VALUE;
		while (high<A.length) {
			if (sum<target) {
				sum+=A[high];
				high++;
			} else {  // sum>=target
				len = Math.min(len, high-low);
				if (high-low==1) {
					return 1;
				}
				sum -=A[low];
				low++;
			}
		}
		while (sum>=target) {
			len = Math.min(len, high-low);
			sum -=A[low++];
		}
		return len;
	}
	public static void main(String[] args) {
		int A[] = {2,3,1,2,4,3};
		int target = 7;
		System.out.println(greaterEqualTarget(A, target));
	}
}
