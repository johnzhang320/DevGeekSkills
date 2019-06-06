package com.code.review.array.string.easy.binarySearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.code.utils.LinkedList.Utils;

public class BinarySearchFindMissData1ToN {
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
	// using binary search we have to start with 0,1,2,....n
	// this is not good for linear O(n) requirement 
	public static int binarySearchFindMissNumber(int arr[]) {
		//Arrays.sort(arr);
		int low = 0;
		int high = arr.length-1;
		int mid =0;
		while (low<high) {
			mid = low+(high-low)/2;
			if (arr[mid] > mid) {
				high = mid;
			} else {
				low = mid+1;
			}   
		}
		return high;
	}
	
	// O(n) runtime
	public static int findByMath(int[] arr) {
		if (null==arr || 0==arr.length) return 0;
		
		int n=arr[arr.length-1];
		int should = n*(n+1)/2;
		// actual sum
		int sum = 0;
		for (int i=0;i<arr.length;i++) {
			sum+=arr[i];
		}
		return should - sum;
	}
	// O(n) runtime
	public static int[] findMissDupMath(int[] arr) {
		if (null==arr || 0==arr.length) return null;
		Set<Integer> set =new HashSet<Integer>();
		
		int n=arr[arr.length-1];
		
		int should = n*(n+1)/2;
		// actual sum
		int sum = 0;
		int dup = 0;
		for (int i=0;i<arr.length;i++) {
			sum+=arr[i];
			 
			if (set.contains(arr[i])) {				
			 
				dup = arr[i];
			} else {
				set.add(arr[i]);
			}
		}
		int result[] = new int[2];
		result[0] = should - sum+dup;
		result[1] = dup;
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {0,1,2,3,4,5,6,8,9,10,11,12,13,14};
		System.out.println(binarySearchFindMissNumber(arr));
		
		int arr2[] = {1,2,3,4,5,6,8,9,10,11,12,13,14};
		System.out.println(findByMath(arr2));
		
		int arr3[] = {1,2,6,3,4,5,6,8,9,10,11,12,13,14};
		Utils.print(findMissDupMath(arr3));
		
	}

}
