package com.code.review.array.string.easy.duplicate;

import java.util.HashMap;
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
	
	public static boolean TwoDiffIsTargetNotSortedPositiveArray(int A[], int k) {
		if (null==A || 0==A.length || 1==A.length) return false;
		if (A.length==2) return Math.abs(A[1]-A[0]) == k;
		Set<Integer> set =  new HashSet<Integer>();
		for (Integer o: A) {
			if (!set.contains(o)) { 
			// O(1) for HashSet search
				if ((o-k)>0 ) {
					set.add(o-k);
					//System.out.println("o="+o+",k="+k);
				} else {
					set.add(o+k);
				}
			} else { // find current o = k-prev o
				return true;
			}
		}
		return false;
	}
	
	public static int[] TwoDiffIsSumTarget(int A[], int k) {
		int result[] = new int[2];
		if (null==A || 0==A.length || 1==A.length) return result;
		if (A.length==2) {
			if (Math.abs(A[1]-A[0]) == k) {
				result[0]=A[0];
				result[1]=A[1];
			}
		}
		Map<Integer,Integer> map =  new HashMap<Integer,Integer>();
		for (Integer o: A) {
			if (!map.containsKey(o)) { 
			// O(1) for HashMap search
				if ((o-k)>0 ) {
					map.put((o-k), o);
					//System.out.println("o="+o+",k="+k);
				} else {
					map.put((o+k),o);
				}
			} else { 
				result[0]=map.get(o);
				result[1]=o;
				return result;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] ={1,3,5};
		int k=4;
		System.out.println("Sorted:"+TwoDiffIsTarget(A, k) );
		int A1[] ={1,3,5,20,32};
		k=12;
		System.out.println("Sorted:"+TwoDiffIsTarget(A1, k) );
		int A2[] ={1,3,5,22,32};
		k=121;
		System.out.println("Sorted:"+TwoDiffIsTarget(A2, k) );
		
		
		int A3[] ={4,1,3,5,4,2};
		k=4;
		System.out.println("Not Sorted:"+TwoDiffIsTargetNotSortedPositiveArray(A3, k) );
		
		int A4[] ={5,3,1,22,32};
		k=4;
		System.out.println("Not Sorted:"+TwoDiffIsTargetNotSortedPositiveArray(A4, k) );
		
		int result[] = TwoDiffIsSumTarget(A3,k);
		
		System.out.println("Not Sorted and number :"+result[0]+",number:"+result[1]+", differece is "+k );
		
	 
		 
		result = TwoDiffIsSumTarget(A4,k);
		
		System.out.println("Not Sorted and number :"+result[0]+",number:"+result[1]+", differece is "+k );
	
		
	}

}
