package com.code.review.array.string.easy.merge;

public class MergeArrayContainZero {
	/**
	 * Two sorted Arrays A and B
	 * Array A[] cantain zero and its size is exactly able to contain B[]
	 * Merge B to A and keep two array sorted
	 * Example
	 * A[] ={0,1,0,3,5,0,7,9,10,0,11,0}
	 * B[] ={2,4,6,12,14}
	 * Solution:
	 * (1) move non zero elements left size for A[]
	 * (2) merge two Arrays together
	 * @param args
	 */
	public static int[] mergeTwoSortArray(int A[],int B[],int m, int n) {
		int k=m+n+1;
		while (m>=0 && n>=0) {
			if (A[m]>B[n]) {
				A[k--] = A[m--];
				
			} else {
				A[k--] = B[n--];
			}
		}
		while (n>=0) {
			A[k--]=B[n--];
		}
		return A;
	}
	
	public static int[] merge(int A[],int B[]) {
		int k=0;
		for (int i=0;i<A.length;i++) {
			if (A[i]!=0) {
				A[k++]=A[i];
			}
		}
		return mergeTwoSortArray(A,B,k-1,B.length-1);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] ={0,1,0,3,5,0,7,9,10,0,11,0};
		int B[] ={2,4,6,12,14};
		int result[]=merge(A,B);
		for (int i=0;i<result.length;i++) {
			System.out.print(result[i]+" ");
		}
	}

}
