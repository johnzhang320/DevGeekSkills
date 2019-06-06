package com.code.review.array.string.easy.sum;

import java.util.Arrays;

public class TwoSumII {
	/**
	 * This problem is similar to Two Sum , find two elements in sorted array

		To solve this problem, we can use two points to scan the
		array from both sides. See Java solution below:
	 * @param args
	 */
	public static int [] twoSumII(int arr[], int target) {
		int i = 0;
		int j = arr.length-1;
		while (i<=j) {
			 int x = arr[i]+arr[j];
			 
			 if (x<target) {
				 i++;
			 } else if (x>target){
				 j--;
			 } else {
				 
				 return new int[] {i, j};  
			 }
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int arr[] = {1, 2, 3, 4, 7, 11, 15};
		  int k = 18;
		  System.out.println("for array "+Arrays.toString(arr)+" find k = "+k + " two sum is ");
		  System.out.println(Arrays.toString(twoSumII(arr,k))); 
	}

}
