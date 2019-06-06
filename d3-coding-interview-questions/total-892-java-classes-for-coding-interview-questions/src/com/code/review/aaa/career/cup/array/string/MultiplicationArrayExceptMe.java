package com.code.review.aaa.career.cup.array.string;

public class MultiplicationArrayExceptMe {
	/**
	 * 
	 *   coupang-interview-questions
 
		You are given an integer array. You have to return/print an array where kth element of this array 
		is the multiplications of all the elements from 0 to k-1 and from k+1 to n-1.
		Example
		input: [1 2 5 6]
		output: [60 30 12 10]
		
		My Approach:
		Actually k = 0 to n (size of array is n) and suppose all elements product could be divide by any element
		(1) Multiple all elements, put into maxProd 
		(2) output element O[i] = maxProd/A[i].
		(3) O(n) computation and O(n) memory
	 * 
	 */
	public static int[] FindArrayMuliple(int A[]) {
		int result[] = new int[A.length];
		if (null==A || 0==A.length) return result;
		int maxProd = 1;
		boolean zeroExists=false;
		for (Integer i: A) {
			if (i!=0) {   // must consider element is zero
				maxProd *=i;   
			} else {
				zeroExists=true;  // if zero exists , prevent it from multiplying other elements
			}
		}
		
		for (int i=0; i<A.length;i++) {
			if (A[i]!=0) {           
				if (!zeroExists) {   // if zero not exists, divide maxprod by current element   
					result[i]=maxProd/A[i];
				} else {
					result[i]=0;     // if zero exists, all output elements should be zero except A[i]==0 
				}
			} else {
				result[i] = maxProd;   // if current A[i]==0, output elements should be all non element produce
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {4, 2, 5, 6};
		int result[] = FindArrayMuliple(A);
		for (Integer i: result) System.out.print(i+",");
		System.out.println("\n-----------------------");
		int A2[] = {4, 2, 0, 6};
		result = FindArrayMuliple(A2);
		for (Integer i: result) System.out.print(i+",");
	}

}
