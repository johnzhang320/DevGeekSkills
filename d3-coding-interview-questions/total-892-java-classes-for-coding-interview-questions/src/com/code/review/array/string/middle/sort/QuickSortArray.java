package com.code.review.array.string.middle.sort;

import java.util.Arrays;

public class QuickSortArray {
	public static void quickSort(int[] arr, int low, int high) {
		if (arr == null || arr.length == 0) {
			return;
		}
		if (low >= high) {
			return;
		}
		int middle = low + (high-low)/2;
		int pivot = arr[middle];
		int lowIndex = low;
		int highIndex = high;
		while (lowIndex <= highIndex) {
			while (arr[lowIndex] < pivot) {
				lowIndex++;
			}
			while (arr[highIndex] > pivot) {
				highIndex--;
			}
			if (lowIndex <= highIndex) {
				int tmp = arr[highIndex];
				arr[highIndex] = arr[lowIndex];
				arr[lowIndex] = tmp;
				lowIndex++;
				highIndex--;
			}
 		}
		if (low < highIndex) {
			quickSort(arr,low, highIndex);
		}
		if (high > lowIndex) {
			quickSort(arr,lowIndex,high);
		}
	}
	
	/**
	 *   (1)   create a partition function, calculate mid = low +(high-low)/2 , pivot = A[mid]
	 *      keep A[low++] >=A[mid] and keep A[high--] <=A[mid]
	 *      if low<=high, A[low] swap A[high] , low++;high--
	 *      if low>=high return low
	 *    (2) in main sort function call partition => index;
	 *      if low<index-1 recursive call sort(A, low ,index-1) // high = mid
	 *      if (index<high) call sort(A,index,high)
	 */
	public static int partition(int A[],int low,int high) {
		int mid = low+(high-low)/2;
		while(low<=high) {
			// move left side to find element is greater than A[mid]
			while (A[low]<A[mid]) {
				low++;
			} 
			while (A[high]>A[mid]) {
				high--;
			} 
			if (low<=high) {
				int tmp = A[high];
				A[high] = A[low];
				A[low]=tmp;
				low++;
				high--;
			}
		}
		return low;
	}
	public static void qSort(int A[], int low, int high) {
		int index = partition(A,low,high);
		if (low<index-1) {  // index-1 is high , at this moment high <low from partition return
			qSort(A,low,index-1);
		}
		if (index<high) {    // index is low at partition
			qSort(A,index,high);
		}
	}
	/**
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {7,1,5,3,2,0,6,10,4,8,15,9};
		qSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
		
	}
}
