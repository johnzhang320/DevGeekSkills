package com.code.review.aaa.career.cup.array.string;

public class FindContiguousElementISSum {
	/**
	 * 
	 *  snap-inc-interview-questions
 

		Write a function that takes an array of positive and negative integers and a number. This 
		function should return true if the array contains a contiguous sub array that sums up to t
		he number (2nd input).		He wanted an O(n) algorithm.
		Analysis, sum > target , sum-=A[start++] until it <= target, check if sum == target return true
		otherwise sum+=A[i]
		My Approach
		(1) define sum = A[0], start = 0
		(2) i = 1 to A.length-1
		(3) while loop check if sum>target and start <i-1
		(4) if (3) yes, we remove A[start] sum -=A[start], start++;
		(5) if (3) no, check if (sum==target) then return true
		(6) sum+=A[i]
	 	 * 
	 */
	public static boolean findContiguousElemsSum(int A[], int target) {
		if (null==A || 0==A.length) return false;
		int sum =A[0];
		int start = 0;
		for (int i=1; i<A.length;i++) {
			while (sum>target && start<i-1) {
				sum -= A[start];
				start++;
			}
			if (sum == target) {
				return true;
			}
			if (i<A.length) {
				sum+=A[i];
			}
		}
		return false; 
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {3,1,5,16, -1,13,8,0,9,10,33};
		int x =12 ;
		System.out.println("x="+x+", is "+findContiguousElemsSum(arr, x));
		
		x=12231;
		System.out.println("x="+x+", is "+findContiguousElemsSum(arr, x)); 
		
		x=-1;
		System.out.println("x="+x+", is "+findContiguousElemsSum(arr, x)); 
		
		x=-0;
		System.out.println("x="+x+", is "+findContiguousElemsSum(arr, x)); 
		
		x=3;
		System.out.println("x="+x+", is "+findContiguousElemsSum(arr, x)); 
	}

}
