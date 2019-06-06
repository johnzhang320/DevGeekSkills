package com.code.review.array.string.easy.sum;

import java.util.Arrays;

public class ThreeSumCloset {
/**
  LeetCode – 3Sum Closest (Java)
 

	Given an array S of n integers, find three integers in S such that the sum is 
	closest to a given number, target. Return the sum of the three integers. You
	 may assume that each input would have exactly one solution.
	
	For example, given array S = {-1 2 1 -4}, and target = 1. 
	The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * @param args
 */public static int threeSumClosest(int[] nums, int target) {
	    int min = Integer.MAX_VALUE;
		int result = 0;
	 
		Arrays.sort(nums);
	 
		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				int diff = Math.abs(sum - target);
	 
				if(diff == 0) return sum;
	 
				if (diff < min) {
					min = diff;
					result = sum;
				}
				if (sum <= target) {
					j++;
				} else {
					k--;
				}
			}
		}
	 
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {4,6,10,-1,3};
		int target = 21;
		
		int result = threeSumClosest(arr, target);
		
		System.out.println(result);
	}

}
