package com.code.review.amazon.code.text;

public class BinarySearchRotatedArray {
	/**
	 *  amazon-interview-questions
 
		You are given a rotated sorted Aay of size N. You have to search a given 
		number into it.
		Example: [4,6,8,14,90,-9,-2,0,3], Search -2.
		
		My Approach:
		while (low<=high), check if target == A[mid] return mid
		if A[low] < A[mid] means in A[low] to A[mid] is ascend , therefore , 
		if target is within range A[low] to A[mid] , high = mid-1 , if out of A[low] to A[high]
		low=mid+1
		if target is not within range A[mid] to A[high] , low = mid+1 , if with A[low] to A[high], high=mid-1
		
	 * 
	 */
	public static int BinarySearchRotatedAay(int A[], int target) {
		if (null==A || A.length==0) return -1;
		if (A.length==1 ) {
			if (A[0]!=target) {
				return -1;
				
 			} else {
 				return 0;
 			}
 		}
		// check if rotated
		int len = A.length;
		int low = 0;
		int high = len-1;
		while (low<=high) {
			int mid = low + (high-low)/2;
			if (A[mid]==target) {
				return mid;
			}
			if (A[low]<A[mid]) {
				if (target>A[low] && target<A[mid]) { // target located left hand side of middle
					high = mid-1;
				} else {
					low = mid+1;
				}
			} else { // above middle zone 
				if (target>A[mid] && target < A[high]) {
					low = mid+1;					
				} else {
					high = mid-1;
				}
 			}
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {4,6,8,14,80,-9,-2,0,3};
		System.out.println(BinarySearchRotatedAay(A, -2));
	}

}
