package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSet {
	/**
	 *     Given a set of distinct integers, S, return all possible subsets.

		Note: 1) Elements in a subset must be in non-descending order. 2) The solution set must not 
		contain duplicate subsets.
		
		For example, given S = [1,2,3], the method returns:
		[
		  [3],
		  [1],
		  [2],
		  [1,2,3],
		  [1,3],
		  [2,3],
		  [1,2],
		  []
		]
		
		Thoughts
		
		Given a set S of n distinct integers, there is a relation between Sn and Sn-1. The subset of 
		Sn-1 is the union of {subset of Sn-1} and {each element in Sn-1 + one more element}.
		 Therefore, a Java solution can be quickly formalized. 
	 * 
	 * 
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		if (nums == null)
			return null;
	 
		Arrays.sort(nums);
	 
		List<List<Integer>> result = new ArrayList<List<Integer>>();
	 
		for (int i = 0; i < nums.length; i++) {
			List<List<Integer>> temp = new ArrayList<List<Integer>>();
	 
			//get sets that are already in result
			for (List<Integer> a : result) {
				temp.add(new ArrayList<Integer>(a));
			}
	 
			//add nums[i] to existing sets
			for (List<Integer> a : temp) {
				a.add(nums[i]);
			}
	 
			//add nums[i] only as a set
			ArrayList<Integer> single = new ArrayList<Integer>();
			single.add(nums[i]);
			temp.add(single);
	 
			result.addAll(temp);
		}
	 
		//add empty set
		result.add(new ArrayList<Integer>());
	 
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[]={1,2,3};
		List<List<Integer>> result = subsets(nums);
		for (List<Integer> list : result) {
			System.out.println(list.toString());
		}
	}

}
