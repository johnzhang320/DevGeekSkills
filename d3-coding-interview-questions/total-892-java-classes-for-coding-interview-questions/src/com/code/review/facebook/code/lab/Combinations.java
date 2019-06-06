package com.code.review.facebook.code.lab;

import java.util.ArrayList;

public class Combinations {
	/**
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 2 3 ... n.

		Make sure the combinations are sorted.
		
		To elaborate,
		1. Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.
		2. Entries should be sorted within themselves.
		
		Example :
		If n = 4 and k = 2, a solution is:
		
		[
		  [1,2],
		  [1,3],
		  [1,4],
		  [2,3],
		  [2,4],
		  [3,4],
		]
		
		

	    Warning : DO NOT USE LIBRARY FUNCTION FOR GENERATING COMBINATIONS.
	    Example : itertools.combinations in python.
	    If you do, we will disqualify your submission retroactively and give you penalty points.

		
		My Approach
		Using Depth First Search
		woid dfs(ArrayList<ArrayList<Integer>> result, int start, int n, int k, ArrayList<Integer> curr);
		condidtions
		(1) i = start to n, recursive call dfs(result, i+1, n, k curr), before call curr.add(i) and after call curr.add(curr.size()-1)
		(2) if curr.size() == k add one of combination curr to result
		
	 *  
	 */
	public static ArrayList<ArrayList<Integer>> combinations(int n , int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> curr = new ArrayList<Integer>();
		if (n<=0 || n<k) {
			return result;
		}
		dfs(result, 1,  n,  k,  curr);
		return result;
	}
	public static void dfs(ArrayList<ArrayList<Integer>> result, int start, int n, int k, ArrayList<Integer> curr) {
		if (curr.size()==k) {
			ArrayList<Integer> tmp = new ArrayList<Integer>(curr);
			result.add(tmp);
			return;
		}
		for (int i=start; i<=n; i++) {
			curr.add(i);
			dfs(result, i+1,  n,  k,  curr);
			curr.remove(curr.size()-1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 4;
		int k = 2;
		ArrayList<ArrayList<Integer>> result = combinations( n , k);
		System.out.println("--------------n-4, k=2--------------------------");
		 
		for (ArrayList<Integer> list:result) {
			System.out.print("\n[ ");
			for (Integer i: list) {
				System.out.print(i+" ");
			}
			System.out.print("]\n");
		}
		System.out.println("--------------n-4, k=3--------------------------");
		 n = 4;
		 k = 3;
		result = combinations( n , k);
		for (ArrayList<Integer> list:result) {
			System.out.print("\n[ ");
			for (Integer i: list) {
				System.out.print(i+" ");
			}
			System.out.print("]\n");
		}
	}

}
