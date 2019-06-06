package com.code.review.facebook.code.lab;

import java.util.Arrays;

public class RemoveDuplicateInSortedArray {
	/**
	 * Remove duplicated element without extra memory,
	 * {1,1,3,5,6,6,7,9,10,10,10};
	 * output 
	 * (1,3,5,6,7,9,10}
	 * My Approach
	 * (1) create tail point =0, if A[i-1] ! = A[i], A[++tail]; 
	 * @param args
	 */
	public static void removeDuplicateSort(int A[]) {
		if (null==A || A.length==0) return;
		if (A.length==1) return;
		int tail=0;
		for (int i=1; i< A.length; i++) {
			if (A[i-1] != A[i]) {
				A[++tail] = A[i];
				
			}
		}
		for (int i = tail+1; i<A.length;i++) {
			A[i] = 0;
		}
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,1,3,5,6,6,7,9,10,10,10,12};
		removeDuplicateSort(A);
		for (Integer i: A)
		System.out.print(i+" ");
	}

}
