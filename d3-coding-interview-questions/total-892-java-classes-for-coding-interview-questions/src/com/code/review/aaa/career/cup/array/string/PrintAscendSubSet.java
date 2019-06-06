package com.code.review.aaa.career.cup.array.string;

import java.util.ArrayList;
import java.util.List;

public class PrintAscendSubSet {
	/**
	 * 
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
	 *  
	 *  Example should be 
	 *  
	 * If S = [1,3,2], the solution is:
		
		[
		[],
		[1],
		[1,3],		
		[1,2],
		[1,2,3],
		[3],
		[2],
		[2, 3]
		]
		
	 */
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
