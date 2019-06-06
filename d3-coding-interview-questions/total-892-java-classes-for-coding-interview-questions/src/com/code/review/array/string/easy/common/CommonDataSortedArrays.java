package com.code.review.array.string.easy.common;

import java.util.Arrays;

import com.code.review.utils.Utils;

public class CommonDataSortedArrays {
	/**
	 *  Case 2 
	 *  IN Two Sorted Arrays , find same data elements 
	 *  implementation. 
	 *  from 0 to len, check if arr1[i] < arr2[j], then i++ else j++ else (equal), adding to result  
	 *  
	 */
	public static int[] commonDataSortedArrays(int arr1[], int arr2[]) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		int result[] = new int[len1+len2+1];
		int i=0,j=0;
		int count=0;
		while (i<len1 && j<len2) {
			if (arr1[i]<arr2[j]) {
				i++;				
			} else if (arr1[i]>arr2[j]) {
				j++;
			} else {
				 
				result[count++] = arr1[i];
				i++;
				j++;
			}
		}
		return Arrays.copyOf(result,count);
	}
	public static void main(String[] args) {
		// TODO Auto-generated  
		 int a[]={2,4,8,9,10,12,15,16,30,33,40};		
		 int b[]={2,3,4,5,6,8,10,20,31};
		 int result[] = commonDataSortedArrays (a, b);	
		 for (Integer r:result)
		 System.out.print(r+" ");
	}

}
