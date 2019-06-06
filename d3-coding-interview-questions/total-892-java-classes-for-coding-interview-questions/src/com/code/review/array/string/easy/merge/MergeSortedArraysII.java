package com.code.review.array.string.easy.merge;

import java.util.Arrays;

public class MergeSortedArraysII {
	 /**
	 *  Merge two sorted arrays to one array , which is typical interview question
	 *  
	 *  
	 *  1. merge without extra data structure
	 *  
	 * Sorted Array arr1[m] and arr2[n], merge two arrays together which still exists arr1 and arr2 without extra memory buffer space O(1) 
	 * For example
	 *  Input:  ar1[] = {1, 5, 9, 10, 15, 20};
     *          ar2[] = {2, 3, 8, 13};
	 *	Output: ar1[] = {1, 2, 3, 5, 8, 9}
     *          ar2[] = {10, 13, 15, 20}  
     *  Algorithm:
     *  Loop from n to 0 for arr2 on i--, each loop, set last = arr1[m-1], loop arr1 by m-2 to zero on j--, each step check arr1[j] > arr2[i], yes ,
     *  arr1[j+1] = arr1[j], means move previous element to next position until find arr1[j] < arr2[i], then put arr2[i] to arr1[j+1] , last to arr2[i]
 
	 *  Pick up element as arr2Curr from tail of array2 to begin, place last element of array1 to last
	 *  compare arr2Curr with element of array1 from tail to begin,  if current element of array1
	 *  is greater than arr2Curr, put current element of array1 to next one element place.
	 *  if arr2Tail > current element, place it to current element +1 place
	 *  
 
	 * @param args
	 */
	public static void MergeSortedArraysII (int A[], int B[]) {
		int len1=A.length;
		int len2=B.length;
		int last =0;
		
		for (int index2=len2-1; index2>=0; index2--) {  // B loop
			boolean bLarger = false;		 
			last = A[len1-1];  // save last element of A so that previous elements could override it
			int currElem2 = B[index2];
			int index1=0;
			for (index1=len1-1; index1>=0; index1--) {  // A loop {
				if (currElem2 < A[index1]) {
					A[index1] = A[index1-1];  // move previous one to current one
				} else {	
				    if (index1!=len1-1) {  // Exchange A and B
						 A[index1+1] = currElem2;
						 B[index2] = last;
					}
					break;			
				}
			}
			 
		}
	}
	/**
	 * The idea is very simple. We consider each element of A[] and ignore the element if it is already in correct order ( the element smallest among
	 * all remaining elements) else we swap it with smallest element which happens to first element if B(B[0]. After swapping, we move the element B[0[]
	 * to its correct position in B[] to maintain the sorted order.
	 * 
	 * @param args
	 */
	public static void merge(int A[], int B[]) {
		int m=A.length;
		int n = B.length;
		for (int i=0;i<m;i++) {
			//check if A[i] > B[0] means smallest one in B
			if (A[i] > B[0]) {
				// swap both
				int tmp = A[i];
				A[i] = B[0];
				B[0] = tmp;
				// move B[0] to its correct position
				int b0 = B[0];
				// move B to left to find b0>b[i]
				int j=1; 
				for (j=1; j<n && b0>B[j];j++) {
					B[j-1]=B[j];
				}
				B[j-1]=b0;
			}
		}
	}
	/**
	 * (1) loop i= 0 A.length
	 * 
	 * (2) check if A[i]>B[0]
	 * (3) A[i] and B[0] swap
	 * (4) b0 = B[0] , loop j=1 to B.length 
	 * (5) check if b0>B[j] break else B[j-1] =B[i] means B elements move left to create a room to store b0 
	 * @param A
	 * @param B
	 */
	
	public static void merge2(int A[], int B[]) {
		for (int i=0; i<A.length;i++) {
			if (A[i]>B[0]) { // swap
				int tmp = A[i];
				A[i] = B[0];
				B[0] =tmp;
			}
			int b0 = B[0];
			for (int j=1; j<B.length;j++) {
				if (b0>B[j]) {
					B[j-1] = B[j];  // move left to find room to store b0
				} else {
					B[j-1] = b0;   // insert before B[j]
					break;
				}
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[] = {1, 5, 9, 10, 15, 20};
	    int arr2[] = {2, 3, 8, 13};
	    //MergeSortedArraysII(arr1, arr2);
	    merge2(arr1, arr2);
	    
	    System.out.print(Arrays.toString(arr1));
	    
	    System.out.print(Arrays.toString(arr2));
	    System.out.println(" ");
	}
}
