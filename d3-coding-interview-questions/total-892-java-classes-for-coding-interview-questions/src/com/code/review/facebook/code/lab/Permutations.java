package com.code.review.facebook.code.lab;

import java.util.ArrayList;

public class Permutations {
	/**
	 * Given a collection of numbers, return all possible permutations.

		Example:
		
		[1,2,3] will have the following permutations:
		
		[1,2,3]
		[1,3,2]
		[2,1,3] 
		[2,3,1] 
		[3,1,2] 
		[3,2,1]
		
		    NOTE
		
		        No two entries in the permutation sequence should be the same.
		        For the purpose of this problem, assume that all the numbers in the collection are unique.
		
		    Warning : DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS.
		    Example : next_permutations in C++ / itertools.permutations in python.
		    If you do, we will disqualify your submission retroactively and give you penalty points.
	   
		
		We can get all permutations by the following steps:
		
		[1]
		[2, 1] -------------|  add 3 in different locations of [2,1]   
		                    |
		[1, 2] -------------|--            
		                    | |   
		[3, 2, 1] <-----|   | |  
		[2, 3, 1] <-----|<--| |          
		[2, 1, 3] <-----|     | 
		                      | add 3 in different locations of [1,2]   
 		[3, 1, 2] <-----|     |   
		[1, 3, 2] <-----|<----|
		[1, 2, 3] <-----|
		
		Using Depth First Search
		woid dfs(ArrayList<ArrayList<Integer>> result, int start, int num[]);
		condidtions
		(1) i = start to num.length, recursive call dfs(result, start+1, int num), before call swap(num,start,j) and after call swap(num,start,j
		(2) if start>=num.length add convert num to arrayList
		
	 *  
	 */
	public static ArrayList<ArrayList<Integer>> permutation(int num[]) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (null==num || 0==num.length) {
			return result;
		}
	
		dfs(result, 0, num);
		return result;
	}
	public static void dfs(ArrayList<ArrayList<Integer>> result, int start, int num[]) {
		if (start >= num.length) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (Integer i:num) {
				tmp.add(i);
			}
			result.add(tmp);
			return;
		}
		for (int i=start; i<num.length; i++) {
			swap(num,start,i);
			dfs(result, start+1, num);
			swap(num,start,i);
		}
	}
	private static void swap(int num[],int start, int j) {
		int tmp = num[start];
		num[start] = num[j];
		num[j] = tmp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num[] = {1,2,3};
		ArrayList<ArrayList<Integer>> result = permutation(num);
		 
		for (ArrayList<Integer> list:result) {
			System.out.print("\n[ ");
			for (Integer i: list) {
				System.out.print(i+" ");
			}
			System.out.print("]\n");
		}
	}

}
