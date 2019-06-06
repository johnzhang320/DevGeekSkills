package com.code.review.algorithm.recursion;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
	Given an array of integers, find how many pairs in the array such that 
	their sum is bigger than a specific target number. Please return the number of pairs.
	Example
	numbers=[2, 7, 11, 15], target=24
	return 1
	Challenge
	Either of the following solutions are acceptable:
	O(1) Space, O(nlogn) Time
	Tags Expand 
	Two Pointers
	*/
	/**
	 * 
	 * Binary Search
	 * A[low]+A[high]>target
	 * count+=high-low;
	 * @author jianzhang
	 *
	 */
public class TwoGreaterSumInSortedArray {
	public static int TwoSum(int A[],int target) {
		if (null==A || A.length==0) {
			return 0;
		}
		int count=0;
		int low=0;
		int high=A.length-1;
		Arrays.sort(A);
		while (low<high) {
			if (A[low]+A[high]>target) {
				count++;
				high--;
			} else {
				low++;
			}
		}
		return count;
	}
	/**
	 *  Recursion
	 *  
	 */
	int count=0;
	public void TwoSumRecursion(int A[],int low ,int high, int target) {
		if (low>=high) {
			return;
		}
		if (A[low]+A[high]>target) {
			count++;
			TwoSumRecursion(A,low,high-1,target);
		} else  {
			TwoSumRecursion(A,low+1,high,target);
		}
		return;
	}
	public static void main(String [] args) {
		int A[] = {1,2,4,5,6,7,8,10,13};
		System.out.println(TwoSum(A,10));
		TwoGreaterSumInSortedArray f = new TwoGreaterSumInSortedArray();
		f.TwoSumRecursion(A,0 ,A.length-1, 10);
		System.out.println("Recursion count="+f.count);
		
	}
}
