package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	/**
	 * 
	 *  LeetCode � Combinations (Java)
 
		
		Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
		
		For example, if n = 4 and k = 2, a solution is:
		
		[
		  [2,4],
		  [3,4],
		  [2,3],
		  [1,2],
		  [1,3],
		  [1,4],
		]

	 */
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	 
		if (n <= 0 || n < k)
			return result;
	 
		ArrayList<Integer> item = new ArrayList<Integer>();
		dfs(n, k, 1, item, result); // because it need to begin from 1
	 
		return result;
	}
	 
	private void dfs(int n, int k, int start, ArrayList<Integer> item,ArrayList<ArrayList<Integer>> res) {
		if (item.size() == k) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
	 
		for (int i = start; i <= n; i++) {
			item.add(i);
			dfs(n, k, i + 1, item, res);
			item.remove(item.size() - 1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Combination ref = new Combination();
		ArrayList<ArrayList<Integer>> result = ref.combine(5, 3); 
	 	System.out.println("----------------Recursive----------------");
	    for (List<Integer> res: result) {
	    	System.out.print("[");
	    	for (Integer i:res) {
	    		System.out.print(i+" ");
	    	}
	    	System.out.println("]");
	    }
	}

}
