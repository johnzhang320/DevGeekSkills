package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AscSubArrays {
	/**
	 * Given a collection of integers that might contain duplicates, S, return all possible subsets.

		    Note:
		
		        Elements in a subset must be in non-descending order.
		        The solution set must not contain duplicate subsets.
		        The subsets must be sorted lexicographically.
		
		Example :
		If S = [1,2,2], the solution is:
		
		[
		[],
		[1],
		[1,2],
		[1,2,2],
		[2],
		[2, 2]
		]


	 *  (1) Two Loops
	 *  (2) external loop A[i] check if A[i]<=A[j], the add 
	 *  
	 */
	public static String AscSubarrays(int A[]) {
		if (null==A || 0==A.length) return null;
		StringBuffer result = new StringBuffer();
		result.append("[\n[]");
		for (int i=0; i<A.length;i++) {
			StringBuffer curr = new StringBuffer();
			curr.append("\n["+A[i]);
			result.append(curr+"]\n");
			String prev = curr.toString();
			for (int j=i+1;j<A.length;j++) {
				if (A[i]<=A[j]) {
					curr.append(","+A[j]);
				 
					if (prev.equals(curr.toString())) {
						break;
					} else {
						if (curr.length()>prev.length())
						result.append(curr+"]\n");
					
					}
				}
				prev = curr.toString();
			}
			
		}
		result.append("]\n");
		return result.toString();
	}
	public static String AscSubarraysList(int A[]) {
		if (null==A || 0==A.length) return null;
		List<String> result = new ArrayList<String>();
		result.add("[");
		result.add("[]");
		for (int i=0; i<A.length;i++) {
			StringBuffer curr = new StringBuffer();
			curr.append("["+A[i]);
			if (!result.contains("["+A[i]+"]")) {
				result.add(curr+"]");
			}
			
			for (int j=i+1;j<A.length;j++) {
				if (A[i]<=A[j]) {
					curr.append(","+A[j]);
 					if (!result.contains(curr+"]")) { 
						result.add(curr+"]");
 					}
				}
				 
			}
			
		}
		result.add("]");
		StringBuffer sb = new StringBuffer();
		for (String s: result) {
			sb.append(s+"\n");
		}
		return sb.toString();
	}
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
		int A[] = {1,2,2};
		String s = AscSubarrays(A);
		System.out.println(s);
		System.out.println("------------------------A1------------------");
		int A1[] = {1,2,3,3};
		s = AscSubarrays(A1);
		System.out.println(s);
		System.out.println("------------------------A1 HashSet------------------");
		 
		s = AscSubarraysList(A1);
		System.out.println(s);
	}

}
