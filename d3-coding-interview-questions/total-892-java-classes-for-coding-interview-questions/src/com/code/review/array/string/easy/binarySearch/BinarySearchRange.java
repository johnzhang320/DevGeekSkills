package com.code.review.array.string.easy.binarySearch;

import java.util.Arrays;

public class BinarySearchRange {
	/**
	 * LeetCode – Search for a Range (Java)
 
		Given a sorted array of integers, find the starting and ending position of a given target value. 
		Your algorithm's runtime complexity must be in the order of O(log n). If the target is not found 
		in the array, return [-1, -1]. For example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	    Solution
	    First Step binary search mid target value , this time we use recursively binary search
	    then go left and go right side to process the duplicate one 

	 * 
	 */
	public int [] binarySearchRange(int A[], int target) {
		if (null==A || 0==A.length) return null;
		int result[] = new int[2];
		result[0]=-1;
		result[1]=-1;
		binarySearch(A,0,A.length-1,target,result);
		return result;
	}
	public void binarySearch(int A[], int left, int right, int target,int result[]) {
		if (left>right) {
			return;
		}
		if (A[left]==A[right] && A[left]==target) {
			result[0]=left;
			result[1]=right;
			return;
		}
		int mid = left + (right-left)/2;
		if (A[mid]>target) {
			binarySearch(A,left,mid-1,target,result);
		} else if (A[mid]<target) {
			binarySearch(A,mid+1,right,target,result);
		} else {  // find mid is target, find repeated elements of left and right of mide
			result[0]=mid;
			result[1]=mid;
			int i = mid;
			while (i>left && A[i]==A[i-1]) {
				i--;
				result[0]=i;
			}
			i = mid ;
			while (i<right && A[i]==A[i+1] ) {
				i++;
				result[1]=i;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {5, 7, 7, 8, 8, 10};
		BinarySearchRange ref = new BinarySearchRange();
		int result[] = ref.binarySearchRange(A, 8);
		System.out.println(Arrays.toString(result));
		
		int B[] = {5, 5, 7, 7, 8, 8, 10};
		 
		result = ref.binarySearchRange(B, 5);
		System.out.println(Arrays.toString(result));
	}

}
