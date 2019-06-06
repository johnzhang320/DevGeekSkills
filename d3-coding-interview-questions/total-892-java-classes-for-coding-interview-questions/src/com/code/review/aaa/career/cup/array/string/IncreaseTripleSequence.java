package com.code.review.aaa.career.cup.array.string;

import java.util.HashSet;
import java.util.Set;

public class IncreaseTripleSequence {
	/**
	 * /**
	 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in 
	 * the array.

		Examples:
		Given [1, 2, 3, 4, 5],
		return true.
		
		Given [4, 5, 1, 2, 3],
		return true.
		
		Given [5, 4, 3, 2, 1],
		return false.
		
	 *  (1) define start=0, HashSet<Integer>, count=0, ;
	 *  (2) add array into set
	 *  (3) i = 0 to A.length-1, find A[i]-1 is NOT in set, start = A[i]
	 *  (4) count=0; while(set.contains(start)) count++ if count>=3 return true start++;
	 *  
	 *  
	 */
	public static boolean findTripleSequence(int A[]) {
		if (null==A || 0==A.length) return false;
		int count=0;
		int start=0;
		Set<Integer> set =new HashSet<Integer>();
		for (Integer i: A) set.add(i);
		for (int i=0; i<A.length;i++) {
			if (set.contains(A[i]-1)) {
				start = A[i];
				count=0;
				while (set.contains(start)) {
					start++;
					count++;
					if (count>=3) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {1,2,3,4,5,3};
		int A2[] = {6,5,4,3,2,1};
		int A3[] = {5,6,7,1,2};
		System.out.println(findTripleSequence(A1));
		System.out.println(findTripleSequence(A2));
		System.out.println(findTripleSequence(A3));
	}

}
