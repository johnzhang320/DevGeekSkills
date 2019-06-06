package com.code.review.aaa.career.cup.array.string;

public class BinarySearchRotatedSortedArray {
	/**
	 *  amazon-interview-questions
 		
		You are given a rotated sorted array of size N. You have to search a given number into it.
		Example: [4,6,8,14,90,-9,-2,0,3], Search -2. Search less than Log(n) computation
		
		My Approach:
		(1) using binary search, define low =0 and high=A.length
		(2) A[mid] == target then return
		(3) if A[low] < A[mid], means low to mid is ascend,
		(4) if (3) yes, check if target<A[mid] and target> A[low] then high=mid-1 else low=mid+1
		(5) if (3) no, that A[low]>A[mid], means mid is in rotated part 
		(6) check if target>A[mid] and target <A[high] then low=mid+1 else high = mid-1
	 * 
	 */
	public static int binarySearchRotation(int A[], int target) {
		if (null==A || 0==A.length) return -1;
		int low=0;
		int high = A.length-1;
		while (low<high) {
			int mid = low+(high-low)/2;
			if (A[mid]==target) return mid;
			if (A[low]<A[mid]) { // this is regular situation low element < mid element
				if (target<A[mid] && target>A[low]) {
					high = mid-1;
				} else {
					low = mid+1;
				}
			} else {  // this is mid element is lower than low element because mid index is located in rotated range
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
		int arr[] = {4, 5, 6, 7, 0, 1 ,2, 3 };
		int target =2;
		System.out.println(binarySearchRotation(arr, target));
	}

}
