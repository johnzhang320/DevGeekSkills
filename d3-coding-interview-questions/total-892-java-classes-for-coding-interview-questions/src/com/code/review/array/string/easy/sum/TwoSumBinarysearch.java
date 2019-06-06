package com.code.review.array.string.easy.sum;

import java.util.Arrays;

public class TwoSumBinarysearch {
	/**
	 *  Sorted array, find two element sum is target
	 *  typical binary search O(logN)
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int[] TwoSumBinarysearch(int arr[] , int target) {
		int low = 0;
		int high = arr.length-1;
		int result[] = new int[2];
		//Arrays.sort(arr);
		while(low<high) {
			 
			if (target < arr[low]+arr[high]) {
				high--;
			} else if (target>arr[low]+arr[high]){
				low++;
			} else {
				result[0] = arr[low];
				result[1] = arr[high];
				break;
			}
		}
		return result;
	}
	// Log(N)
	// BinarySearch from two ends of array 
	public static int[] find2ElemsOfSortedTarget(int A[], int x) {
		int low=0;
		int high = A.length-1;
		int result[] = new int[2];
		while (low<high) {
			
			if (x>A[low]+A[high]) {
				low++;
			} if (x<A[low]+A[high]) {
				high--;
			} else  {
 				result[0] = A[low];
				result[1] = A[high];
				break;
			}
			
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {2,3,4,6,7,10,11,14,15,18,19,21,22,25,31,35,38,41,43,55,62};
		int target = 46;
		//int result[] =  TwoSumBinarysearch(arr, target);
		
		//System.out.println("result[0]="+result[0]+",result[1]="+result[1]);
		
		int A[] = {2,3,4,6,7,10,11,14,15,18,19,21,22,25,31,35,38,43,41,55,62};
		 
		int result1[] =  find2ElemsOfSortedTarget(A, 46);
		
		System.out.println("result1[0]="+result1[0]+",result1[1]="+result1[1]);
	}

}
