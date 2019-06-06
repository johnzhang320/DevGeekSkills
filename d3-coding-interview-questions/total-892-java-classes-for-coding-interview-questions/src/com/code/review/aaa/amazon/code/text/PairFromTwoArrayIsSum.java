package com.code.review.aaa.amazon.code.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairFromTwoArrayIsSum {
	/**
	 *  amazon-interview-questions
 		
 
		A = {1,2,4,-1,5,7,9,....}
		B = {3, 6, 3, 4, 0 .......}
		n = 5 -> pairs whose sum is n
		
		Output = (1,4),(-1,6) (5,0)....
		My Approach 
		place A into set<sum-A[i],A[i]>, check if map.containsKey(B[i]) return 
	 * 
	 */
	private class pair {
		int val1;
		int val2;
		public pair(int val1, int val2) {
			super();
			this.val1 = val1;
			this.val2 = val2;
		}
		@Override
		public String toString() {
			return "pair [val1=" + val1 + ", val2=" + val2 + "]";
		}
		
	}
	
	public List<pair> findPairFromTwoArraysIsSum(int A[],int B[], int sum) {
		List<pair> result = new ArrayList<pair>();
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		if (null==A || 0==A.length) {
			return result;
		}
		if (null==B || 0==B.length) {
			return result;
		}
		for (int i=0; i<A.length;i++) {
			 map.put(sum-A[i], A[i]);
		}
		for (int i=0; i<B.length;i++) {
			if (map.containsKey(B[i])) {
				pair p = new pair(map.get(B[i]),B[i]) ;
				result.add(p);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
