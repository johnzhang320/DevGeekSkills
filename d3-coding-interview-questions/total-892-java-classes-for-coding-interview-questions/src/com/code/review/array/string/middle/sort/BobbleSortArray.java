package com.code.review.array.string.middle.sort;

import java.util.Arrays;

// j-1 and j exchange if arr[j-1] > arr[i], O(n^2)
public class BobbleSortArray {
	public static int[] bobbleSort(int [] arr) {
		int len = arr.length;
		for (int i = 0; i<len; i++) {
			for (int j = 1; j < len; j++) {
				if (arr[j-1]>arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr)); 
		return arr;
	}
	
	public static int countbobbleSort(int [] popularity) {
		int len = popularity.length;
		int count=0;
		for (int i = 0; i<len; i++) {
			for (int j = 1; j <len; j++) {
				if (popularity[j-1]>popularity[j]) {
					int temp = popularity[j];
					popularity[j] = popularity[j-1];
					popularity[j-1] = temp;
					count++;
				}
			}
		}
	 
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr[] = {7,1,5,3,2,0,6};
		bobbleSort(arr);
		int arr2[] = {3,1,2,4};
		System.out.println(countbobbleSort(arr2));
	}

}
