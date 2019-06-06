package com.code.review.array.string.middle.sort;

import java.util.Arrays;

public class BobbleDescSortArray {
	public static int[] descBobbleSort(int [] popularity) {
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
		System.out.println(Arrays.toString(popularity)); 
		System.out.println("Swap count="+count);
		return popularity;
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr2[] = {3,1,2,5,7,4,8,6,10,9};
		
		int arr3[] = {4,3,1,2};
		descBobbleSort(arr2); 
		descBobbleSort(arr3);
	}

}
