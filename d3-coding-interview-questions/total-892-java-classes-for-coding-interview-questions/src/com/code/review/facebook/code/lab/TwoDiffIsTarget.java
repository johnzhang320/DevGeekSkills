package com.code.review.facebook.code.lab;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoDiffIsTarget {
/**
 * Given an array ‘A’ of sorted integers and another non negative integer k, find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j.

	    Example:
	
	    Input :
	
	    A : [1 3 5] 
	    k : 4
	
	    Output : YES
	
	    as 5 - 1 = 4
	
	Return 0 / 1 ( 0 for false, 1 for true ) for this problem
	
	Try doing this in less than linear space complexity.
	My Approach
	(1) Create HashSet<Integer>
	(2) first Add A[i]+k, second time if A[j] = k+A[i] , means k = A[j]-A[i], j!=i because Sorted
	(3) O(n) is worest case
 * @param args
 */
	public static boolean TwoDiffIsTarget(int A[], int k) {
		if (null==A || 0==A.length || 1==A.length) return false;
		if (A.length==2) return Math.abs(A[1]-A[0]) == k;
		Set<Integer> set =  new HashSet<Integer>();
		for (Integer o: A) {
			if (!set.contains(o)) { 
			// O(1) for HashSet search
				set.add(k+o);
			} else { // find current o = k-prev o
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] ={1,3,5};
		int k=4;
		System.out.println(TwoDiffIsTarget(A, k) );
		int A1[] ={1,3,5,20,32};
		k=12;
		System.out.println(TwoDiffIsTarget(A1, k) );
		int A2[] ={1,3,5,22,32};
		k=121;
		System.out.println(TwoDiffIsTarget(A2, k) );
	}

}
