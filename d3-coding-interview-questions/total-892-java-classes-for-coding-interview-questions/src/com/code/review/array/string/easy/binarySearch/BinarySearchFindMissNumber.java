package com.code.review.array.string.easy.binarySearch;

import java.util.Arrays;

public class BinarySearchFindMissNumber {
	/**
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
	 *  find the one that is missing from the array. For example, 
	 *  given nums = [0, 1, 3] return 2.
	 *  
	 *  Analysis:
	 *  Low=0; high=arr.length, if 0 ~ high , all elements exist, before low==high, all elements
	 *  will be scanned, if some element missed and low> high , high data must be missed one
	 *  O(logN)
	 * @param args
	 */
	
	public static int binarySearchFindMissNumber(int arr[]) {
		//Arrays.sort(arr);
		int low = arr[0];
		int high = arr.length-1;
		int mid =0;
		while (low<high) {
			mid = low+(high-low)/2;
			if (arr[mid] > mid) {
				high = mid-1;
			} else {
				low = mid+1;
			}   
		}
		return high;
	}
	
	/**
	 *  Same question recursion solution
	 *  same condition as iteration, high = mid and low = mid+1
	 */
	 public static int binarySearch(int arr[],int low,int high) {
		 int mid = (low+high)/2;
		 if (low<high) {			
			 if (arr[mid]>mid) {
				 return binarySearch(arr,low,mid);
			 } else {
				 return binarySearch(arr,mid+1,high);
			 }
		 } else {
			 return high;
		 }
 	 }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {0,1,2,3,4,5,6,7,8,9,10,12,13,14};
		System.out.println(binarySearchFindMissNumber(arr));
		System.out.println(binarySearch(arr,0,arr.length-1));
	}

}
