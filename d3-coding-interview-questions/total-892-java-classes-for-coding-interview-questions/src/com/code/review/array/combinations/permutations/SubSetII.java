package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetII {
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null)
			return null;
	    int len = nums.length;
		Arrays.sort(nums);
	 
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<List<Integer>> prev = new ArrayList<List<Integer>>();
	 
		for (int i = len-1; i >= 0; i--) {
	 
			//get existing sets
			if (i == len - 1 || nums[i] != nums[i + 1] || prev.size() == 0) {
				prev = new ArrayList<List<Integer>>();
				for (int j = 0; j < result.size(); j++) {
					prev.add(new ArrayList<Integer>(result.get(j)));
				}
			}
	 
			//add current number to each element of the set
			for (List<Integer> temp : prev) {
				temp.add(0, nums[i]);
			}
	 
			//add each single number as a set, only if current element is different with previous
			if (i == len - 1 || nums[i] != nums[i + 1]) {
				List<Integer> temp = new ArrayList<Integer>();
				temp.add(nums[i]);
				prev.add(temp);
			}
	 
			//add all set created in this iteration
			for (List<Integer> temp : prev) {
				result.add(new ArrayList<Integer>(temp));
			}
		}
	 
		//add empty set
		result.add(new ArrayList<Integer>());
        return result;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[]={1,2,3};
		List<List<Integer>> result = subsetsWithDup(nums);
		for (List<Integer> list : result) {
			System.out.println(list.toString());
		}
	}

}
