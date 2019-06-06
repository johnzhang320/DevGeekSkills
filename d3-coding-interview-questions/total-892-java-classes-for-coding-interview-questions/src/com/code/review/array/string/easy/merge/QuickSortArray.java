package com.code.review.array.string.easy.merge;

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {7,1,5,3,2,0,6};
		quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
		
	}
}
