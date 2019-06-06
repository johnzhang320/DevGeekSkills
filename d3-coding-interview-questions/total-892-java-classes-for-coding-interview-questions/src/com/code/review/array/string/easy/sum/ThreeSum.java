package com.code.review.array.string.easy.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
 /**
  *  Sum III 
  *  Problem:

	Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	Find all unique triplets in the array which gives the sum of zero.
	
	Note:
	Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
	The solution set must not contain duplicate triplets.
	
	 {1, 4, 2, 15, 12, 6, 3,8};
	 target = 18
	 result could be :
	 
	 	[1 2 15 ]
		[2 4 12 ]
		[4 6 8 ]
 	 
	Implementation: O(n^2)
	(1) first of all: sort the array by Arrays.sort(arr), using j<k loop
	(2) i=0 to len loop, if i==0 || arr[i]>arr[i-1] {
 
  */
	public static List<List<Integer>> ThreeSum(int arr[], int target) {
		if (null==arr || arr.length==0) return null;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(arr);
		for (int i=0; i<arr.length;i++) {
			if (i==0 || arr[i] > arr[i-1]) {
				
				int second = i+1;
				int third = arr.length-1;
				while (second<third) {
					if (arr[i]+arr[second]+arr[third]==target) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(arr[i]);
						list.add(arr[second]);
						list.add(arr[third]);
						result.add(list);
						second++;
						third--;
						// remove duplicated
						while (second<third && arr[second] == arr[second-1]) {
							second++;
						}
						while (second<third && arr[third] == arr[third-1]) {
							third--;
						}
					} else if (arr[i]+arr[second]+arr[third]<target) {
						second++;
					} else {
						third--;
					}
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int candidates[] = {1, 4, 2, 10, 12, 6, 3,8};   	 
	 	int target = 18;
	 	 
	 	List<List<Integer>> result = ThreeSum(candidates, target); 
	    for (List<Integer> res: result) {
	    	System.out.print("\n[");
	    	for (Integer i:res) {
	    		System.out.print(i+" ");
	    	}
	    	System.out.println("]\n");
	    }
	}

}
