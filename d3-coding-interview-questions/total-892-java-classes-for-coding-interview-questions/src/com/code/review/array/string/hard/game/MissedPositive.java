package com.code.review.array.string.hard.game;

public class MissedPositive {
	/**
	 *  LeetCode – First Missing Positive (Java)
 

		Given an unsorted integer array, find the first missing positive integer. For example, 
		given [1,2,0] return 3 and [3,4,-1,1] return 2.
		
		Your algorithm should run in O(n) time and uses constant space.
		
		Analysis
		
		This problem can solve by using a bucket-sort like algorithm. Let's consider finding first
		missing positive and 0 first. The key fact is that the ith element should be i, so we have:
		i==A[i]
		A[i]==A[A[i]]
		
		For example, given an array {1,2,0,4}, the algorithm does the following:
	 * 
	 */
	public static int firstMissingPositiveAnd0(int A[]) {
		int n = A.length;
		for (int i = 0; i < n; i++) {
			// when the ith element is not i
			while (A[i] != i) {
				// no need to swap when ith element is out of range [0,n]
				if (A[i] < 0 || A[i] >= n)
					break;
	 
				//handle duplicate elements
				if(A[i]==A[A[i]])
	             	break;
				// swap elements
				int temp = A[i];
				A[i] = A[temp];
				A[temp] = temp;
			}
		}
	 
		for (int i = 0; i < n; i++) {
			if (A[i] != i)
				return i;
		}
		System.out.println("positive number is "+n);
		return n;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] ={3,4,-1,1};
		int A2[] ={1,2,0};
		
		firstMissingPositiveAnd0(A1);
		
		firstMissingPositiveAnd0(A2);
	}

}
