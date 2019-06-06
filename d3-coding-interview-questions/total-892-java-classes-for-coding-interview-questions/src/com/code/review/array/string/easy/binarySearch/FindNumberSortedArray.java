package com.code.review.array.string.easy.binarySearch;

public class FindNumberSortedArray {
	/**
	 *  A[] = {1,4,5,7,9,10,11,15,20}
	 *  find 15 , return index=7
	 */
	public static int findDataInArray(int A[], int x) {
		if (null==A || 0==A.length) return -1;
		int low = 0;
		int high = A.length-1;
		int mid;
		while (low<high) {
			mid = low+(high-low)/2;
			if (A[mid]>x) {
				high = mid-1;
			} else if (A[mid]<x) {
				low = mid+1;
			}  else {
				return mid;
			}
		}
		return -1;
	}
	public static void main(String args[]) {
		int A[] = {1,4,5,7,9,10,11,15,20};
		System.out.println(findDataInArray(A, 15) );
	}
}
