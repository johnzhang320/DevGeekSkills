package com.code.review.array.string.easy.binarySearch;
//http://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form/
public class BinarySearchPositionSortedArray {
	/**
	 * find element position from sorted array is typical O(logN) binary search
	 * Algorithm: find mid = (high-low)/2, if elem[mid]>x,  (mid-low)/2, else (high-mid)/2 then call function recursively, if elem[mid]=x, return mid  
	 * @param args
	 */
	public static int search(int arr[],int x, int low, int high) {
		 
	    if (high>=low) {
	    	int mid = low + (high - low) /2;
	   
			if (arr[mid] > x) {
				return search(arr,x,low,mid);
			} 
			if (arr[mid] < x){
				return search(arr,x,mid+1,high);
			}  
			if (arr[mid] == x) {
				return mid;
			}
	    } 
		return -1;
	}
	/**
	 * Iterative Binary Search
	 * if arr[mid] > x , change high = m - 1 else low = m +1
	 * @param args
	 */
	public static int iterateSearch(int arr[], int x, int low, int high) {
		while (low <= high) {
			int mid = low + (high - low)/2;
			if (arr[mid] == x) return mid;
			if (arr[mid] > x ) {
				high = mid -1 ;
			} else {
				low = mid + 1;
			}
		}
		return -1;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int arr[] = {1, 3, 5, 7, 9, 11, 12, 14, 18};
		  int x = 14;
		  System.out.println(search(arr,x,0,arr.length-1));
		  x = 123;
		  System.out.println(search(arr,x,0,arr.length-1));
		  
		  x = 12;
		  System.out.println(iterateSearch(arr,x,0,arr.length-1));
		  
		  x = 9;
		  System.out.println(iterateSearch(arr,x,0,arr.length-1));
	}

}
