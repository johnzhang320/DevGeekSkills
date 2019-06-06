package com.code.review.aaa.career.cup.array.string;

import java.util.Arrays;

public class DeleteOddElemFromArray {
	/**
	 *  bloomberg-lp-interview-questions 

		You are given a vector of integers. You have to delete the odd numbers from it.
		Expected complexity is O(N) Time and O(1) space
	 * 
	 */
	/**
	 *  (1) assign two pointer p=0 and i=0
	 *  (2) if A[i]%2 ==0, A[p] = A[i]; p++; 
	 *  (3) return Arrays.copyOf(A,p);  (C++, A[p]='\n';)
	 * 
	 */
	public static int[] removeOddNumber(int A[]) {
		if (null==A || 0==A.length) return A;
		int p=0;
		for (int i=0;i<A.length;i++) {
			if (A[i]%2==0) {
				A[p]=A[i];
				p++;
			}
		}
		return Arrays.copyOf(A, p);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[]={3,2,6,7,1,9,8,10};
		int result [] = removeOddNumber(A);
		for (Integer i: result) {
			System.out.print(i+",");
		}
	}

}
