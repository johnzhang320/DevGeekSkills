package com.code.review.array.string.easy.sum;

public class ContinuousArraySumIsMax {
	/**
	 *  bloomberg-lp-interview-questions
 
		You are given an array of integers both negative and positive.
		Print the maximum continuous sum of the array and if all the elements are negative, print
        the smallest of them.
		Ex : [-20, -5, -2, -9] :> -2(-2)
		Ex : [20, -19, 6, 9, 4] :-> 20(20)
		Ex : [10, -3, 4, -2, -1, 10] -> 18 (10, -3, 4, -2, -1, 10)
		Ex : [20, -25, 6, 9, 8] :-> 23(6,9,8)
		Ex : [-2,-3,-4,-5] : -> -14 (-2,-3,-4,-5)
		
		Analysis
		ensure each element has chance to be max, max = A[0]
		Implementation
		(1) max = A[0], sum=A[0], List<Integer>, 
		(2) i = 1 to A.length, sum+=A[i], 
		(3) if sum>max then  max = sum 
		(4) if sum<0 then sum=0 
		 
	 * 
	 */
	public static int ContinuousArraySumIsMax(int A[]) {
		int max = A[0];
		int currSum = A[0];
		
		int sum = A[0];
		boolean allNegative=true;
		for (Integer i: A) {
			if (i>0) {
				allNegative = false;
			}
		}
		if (!allNegative) {
			for (int i=1; i<A.length;i++) {
				currSum +=A[i];
				 
			    if (currSum<=0) {
			    	currSum=0;
			    } else {			    	
			    	if (currSum>sum) {
			    		sum=currSum;
			    	}
			    }
			}
		} else {
			for (int i=1; i<A.length;i++ ) {  // all negative elements , summay all of them must be smallest
				sum+=A[i];
				
			}
			 
		}
		return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {20, -19, 6, 9, 4};
		int A2[] = {10, -3, 4, -2, -1, 10};
		int A3[] = {20, -25, 6, 9, 8};
		int A4[] = {-2,-3,-4,-5};
		System.out.println(ContinuousArraySumIsMax(A1));
		System.out.println(ContinuousArraySumIsMax(A2));
		System.out.println(ContinuousArraySumIsMax(A3));
		System.out.println(ContinuousArraySumIsMax(A4));
	}

}
