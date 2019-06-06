package com.code.review.array.string.easy.sum;

import java.util.ArrayList;
import java.util.List;

public class MinSubArrayISGivenSum {
	/**
	 * Given an array of n positive integers and a positive integer s, find the minimal length 
	 * of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
	 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal 
	 * length of 2 under the problem constraint.
	 * 
	 * Analysis
	 * Solution 1 
	 * Using combination sum solution, company smallest combination when we output
	 * define public List<Integer> result. company current comnbine set size< result.size()
	 * @param args
	 */
	static List<Integer> result = new ArrayList<Integer>();
	
	public static List<Integer> findMinimumBycombinationSum(int arr[], int target) {
 		
 		List<Integer> curr = new ArrayList<Integer>();
 		
 		helper(curr,0,target,arr);
 		return result;
 	}
 	public static void helper(List<Integer> curr, int start, int target, int[] arr) {
 		if (target == 0) {
 			if (result.size()==0 || curr.size()<result.size()) {
 				result = new ArrayList<Integer>(curr);    
 			} 
 			return;
 			
 		}
 		if (target<0) {
 			return ;
 		}
 		int prev = -1;
 		for (int i=start; i<arr.length;i++) {
 			if (prev != arr[i]) {
 				curr.add(arr[i]);
 				helper(curr,i+1,target - arr[i], arr);
 				curr.remove(curr.size()-1);
 			}
 			prev = arr[i];
 		}
 	}
 	/**
 	 * Iterator solution is wrong solution is two not adjacent elements is given sum
 	 * it is can not get minimum combination 
 	 * such as int arr[]={2,2,10,3,4,5,7,7,1};
		       int givenSum = 11;
		       10 and 1 is not adjacent elements which will be not get
 	 * @param arr
 	 * @param givenSum
 	 * @return
 	 */
	public static List<Integer> findMinArrayIsGivenSum(int arr[],int givenSum) {
		List<Integer> minList = new ArrayList<Integer>();
		for (int exIndex=0; exIndex<arr.length;exIndex++) {
			int trySum = 0;
			List<Integer> tryList = new ArrayList<Integer>();
			for(int inIndex=exIndex;inIndex<arr.length;inIndex++) {
				if (trySum<givenSum) {
					trySum+=arr[inIndex];
					tryList.add(arr[inIndex]);
				} 
				if (trySum==givenSum) {
					if (minList.size()==0 || minList.size()>tryList.size()) {
						minList = tryList;
						break;
					}  
				}
			}
		}
	
		return minList;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]={2,2,10,3,4,5,7,7,1};
		int givenSum = 11;
		List<Integer> list =findMinArrayIsGivenSum(arr,givenSum);
		System.out.println("Iterator solution can not find result\n");
		for (Integer i:list) {
			System.out.print(i+" ");
		}
		
		System.out.println("Combination Sum solution can find result");
		
		list =findMinimumBycombinationSum(arr,givenSum);
		 
		for (Integer i:list) {
			System.out.print(i+" ");
		}
	}

}
