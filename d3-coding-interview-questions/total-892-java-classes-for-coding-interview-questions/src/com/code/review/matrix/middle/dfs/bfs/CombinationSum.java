package com.code.review.matrix.middle.dfs.bfs;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	/** 
	 * LeetCode – Combination Sum II (Java)
 

		Given a collection of candidate numbers (C) and a target number (T), find all unique combinations 
		in C where the candidate numbers sums to T. Each number in C may only be used ONCE in the 
		combination.
		
		Note:
		1) All numbers (including target) will be positive integers.
		2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
		   (ie, a1 ≤ a2 ≤ … ≤ ak).
		3) The solution set must not contain duplicate combinations.
		
 	 * give sum subarray from array is typical backtracking using recursion
 	 * arr is not sorted
 	 * int candidates[] = {1, 4, 2, 15, 12, 6, 3,8};   	 
	 * int target = 18;
 	 */
 	public List<List<Integer>> combinationSum(int arr[], int target) {
 		List<List<Integer>> result = new ArrayList<List<Integer>>();
 		List<Integer> curr = new ArrayList<Integer>();
 		
 		helper(result,curr,0,target,arr);
 		return result;
 	}
 	public void helper(List<List<Integer>> result, List<Integer> curr, int start, int target, int[] arr) {
 		if (target == 0) {
 			result.add(new ArrayList<Integer> (curr));
 			return;
 		}
 		if (target<0) {
 			return ;
 		}
 		int prev = -1;
 		for (int i=start; i<arr.length;i++) {
 			if (prev != arr[i]) {
 				curr.add(arr[i]);
 				helper(result,curr,i+1,target - arr[i], arr);
 				curr.remove(curr.size()-1);
 			}
 			prev = arr[i];
 		}
 	}
 	
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int candidates[] = {1, 4, 2, 15, 12, 6, 3,8};   	 
	 	int target = 18;
	 	CombinationSum ref = new CombinationSum();
	 	List<List<Integer>> result = ref.combinationSum(candidates, target); 
	 	  System.out.println("----------------Recursive----------------");
	    for (List<Integer> res: result) {
	    	System.out.print("\n[");
	    	for (Integer i:res) {
	    		System.out.print(i+" ");
	    	}
	    	System.out.println("]\n");
	    }
	  
	}

}
