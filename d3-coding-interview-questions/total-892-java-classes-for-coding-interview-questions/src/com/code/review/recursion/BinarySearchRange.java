package com.code.review.recursion;

import java.util.Arrays;



/**
 * LeetCode – Search for a Range (Java)

	Given a sorted array of integers, find the starting and ending position of a given target value. 
	Your algorithm's runtime complexity must be in the order of O(log n). If the target is not found 
	in the array, return [-1, -1]. For example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
    Solution
    First Step binary search mid target value , this time we use recursively binary search
    then go left and go right side to process the duplicate one 
    
    Using recursion to adjust low and high based on mid
    i = mid , using while to extends if (A[i]==A[i-1] or A[i]==A[i+1]
 */
public class BinarySearchRange {
	public void binarySearch(int A[], int low, int high, int target,int result[]) {
		if (low>high) {
			return;
		}
		if (A[low]==A[high] && A[low]==target) {
			result[0]=low;
			result[1]=high;
			return;
		}
		int mid = low+(high-low)/2;
		if (A[mid]>target) {
			binarySearch(A, low, mid-1, target,result);
		} else if (A[mid]<target) {
			binarySearch(A, mid+1, high, target,result);
		} else { // find target
			result[0]=mid;
			result[1]=mid;
			int i=mid;
			while (i>low && A[i]==A[i-1]) {
				i--;
				result[0]=i;
				
			}
			i = mid;
			System.out.println("mid="+mid);
			while(i<high && A[i]==A[i+1]) {
				i++;
				result[1]=i;
				 
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {5, 7, 7,7, 8, 8, 10};
		BinarySearchRange ref = new BinarySearchRange();
		int result[]=new int [2];
		 ref.binarySearch(A, 0, A.length-1, 7,result);
		System.out.println(Arrays.toString(result));
		
		int B[] = {5, 5, 7, 7, 8, 8, 10,10,10};
		 
		 ref.binarySearch(B, 0, B.length-1, 10,result);
		System.out.println(Arrays.toString(result));
	}
}
