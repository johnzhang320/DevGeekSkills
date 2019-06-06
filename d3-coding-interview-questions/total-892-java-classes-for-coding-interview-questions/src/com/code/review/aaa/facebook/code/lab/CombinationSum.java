package com.code.review.aaa.facebook.code.lab;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	/**
	 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the 
	 * candidate numbers sums to T.

		The same repeated number may be chosen from C unlimited number of times.
		
		    Note:
		
		        All numbers (including target) will be positive integers.
		        Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
		        The combinations themselves must be sorted in ascending order.
		        CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR … (a1 = b1 AND a2 = b2 AND … 
		        ai = bi AND ai+1 > bi+1)
		        The solution set must not contain duplicate combinations.
		
		Example,
		Given candidate set 2,2,3,6,7 and target 7,
		A solution set is:
		
		[2, 2, 3]
		[7]
		
		    Warning : DO NOT USE LIBRARY FUNCTION FOR GENERATING COMBINATIONS.
		    Example : itertools.combinations in python.
		    If you do, we will disqualify your submission retroactively and give you penalty points.

	 * Typical Combination Sum, key point is start+1
	 * (1) void dfs(ArrayList<ArrayList<Integer>> result, int start,int sum, int arr[], ArrayList<Integer> curr);
	 * (2) start = start+1 to avoid to same element duplicated applied
	 * (3) sum = target-arr[i]
	 * (4) if target<0 return
	 * (5) if target==0 add curr to result , return
	 * (6) before for loop , prev = -1, if prev!=arr[i] call dfs
	 * (7) before call dfs , curr.add(arr[i]) and after dfs calling,   curr.add(curr.size()-1)
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum(int arr[], int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> curr = new ArrayList<Integer>();
		dfs(result, 0, target, arr, curr);
		return result;
	}
	public static void dfs(ArrayList<ArrayList<Integer>> result, int start,int target, int arr[], ArrayList<Integer> curr) {
		if (target==0) {
			ArrayList<Integer> tmp = new ArrayList<Integer>(curr);
			result.add(tmp);
			return;
		}
		if (target<0) { // combination is not reaching to target
			return;
		}
		int prev = -1;
		for (int i=start; i<arr.length; i++) {
			if (prev!=arr[i]) {				
				curr.add(arr[i]);
				dfs(result,start+1, target-arr[i], arr, curr);
				curr.remove(curr.size()-1);
			}
			prev = arr[i];
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {2,2,3,6,7};
		int target = 7;
		ArrayList<ArrayList<Integer>> result =combinationSum(arr, target);
		  for (List<Integer> res: result) {
		    	System.out.print("\n[");
		    	for (Integer i:res) {
		    		System.out.print(i+" ");
		    	}
		    	System.out.println("]\n");
		    }
	}

}
