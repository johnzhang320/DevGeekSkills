package com.code.review.array.string.easy.sum;

public class FindContiguousElemIndexRangeSum {
	/**
	 * 
	 *  snap-inc-interview-questions


		You are given an array of integers and a number K. You have to find the any continue 
		sub-array whose elements sum is K. Please note that, the array may have positive, negative, 
		and zeros as its element.
		The desired complexity is O(N).
		Example:
		Input: [7 0 9 -10 0 789], K = 0
		Output: Array from index 1 to Index 1.
		Input: [1 2 3 5 -10] K = 0
		Output: Array from Index 1 to Index 4.
		If K = -2, Output would have been SubArray from Index 2 to Index 4.
	 *  
	 *  My Approach
	 *  In a loop, elements of i - start, if sum > target, sum-=A[start] , start++, 
	 *  if start<i, keep i, if Sum< target ,  sum +=A[i], i++ 
	 */
	public static int[] findContiguousElemIndexRange(int A[], int target) {
		int result[] = new int[2];
		if (null==A || 0==A.length) return result;
		int sum = A[0];
		int start = 0;
		for (int i=1; i<A.length;i++) {
			while (sum>target && start<i-1) {
				sum -=A[start];
				start++;
			}
			if (sum==target) {
				result[0] = start;
				result[1] = i-1;
				return result;
			}
			if (i<A.length) {
				sum +=A[i];
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {3,1,5,16, -1,13,8,0,9,10,33};
		int x =15 ;
		int result[] = findContiguousElemIndexRange(arr, x);
		System.out.println("x="+x+", Index from "+result[0]+" to "+result[1]);
		x =12;
		result = findContiguousElemIndexRange(arr, x);
		System.out.println("x="+x+", Index from "+result[0]+" to "+result[1]);
		
		x =-1;
		result = findContiguousElemIndexRange(arr, x);
		System.out.println("x="+x+", Index from "+result[0]+" to "+result[1]);
		
		x =0;
		result = findContiguousElemIndexRange(arr, x);
		System.out.println("x="+x+", Index from "+result[0]+" to "+result[1]);
		
		x =17;
		result = findContiguousElemIndexRange(arr, x);
		System.out.println("x="+x+", Index from "+result[0]+" to "+result[1]);
		
		x =33;
		result = findContiguousElemIndexRange(arr, x);
		System.out.println("x="+x+", Index from "+result[0]+" to "+result[1]);
	}

}
