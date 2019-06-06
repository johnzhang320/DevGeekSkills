package com.code.review.array.string.easy.duplicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindMissAndDup1ToN {
	/**
	 * You are given a read only array of n integers from 1 to n.

		Each integer appears exactly once except A which appears twice and B which is missing.
		
		Return A and B.
		
		Note: Your algorithm should have a linear runtime complexity. Could you implement it without 
		using extra memory?
		
		Note that in your output A should precede B.
		
		Example:
		
		Input:[3 1 2 5 3] 
		
		Output:[3, 4] 
		
		A = 3, B = 4
	   (1) Natural data 1,2,3,....n , follow formula ssum = n*(n+1)/2
	   (2) add 1,2... to n is sum
	   (3) duplicate= (sum+missed)-n*(n+1)/2
	      then missed = n*(n+1)/2 - sum + duplicate
	   (4) we use hashset to find duplicate from given array, then we can find missed

	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> repeatedNumber(final List<Integer> a) {
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    if (null==a || 0==a.size()) return result;
	    int len = a.size();
	    
	    int should = len*(len+1)/2;
	    Set <Integer> set = new HashSet<Integer>();
	    int sum =0;
	    int dup=0;
	    for (int i=0; i<len; i++) {
	        int curr = a.get(i);
	        sum += curr;
	        if (set.contains(curr)) {
	            dup = curr;
	        } else {
	            set.add(curr);
	        }
	    }
	    result.add(dup);
	    System.out.println("should="+should+",sum="+sum);
	    result.add(should - sum + dup);
	   
	    return result;
	}
	
	public static int[] findDupMissedFromNaturalNumber(int A[]) {
		int result[] = new int[2];
		int n = A.length;
		int should = n*(n+1)/2;
		int dup=-1,missed,sum=0;
		Set<Integer> set = new HashSet<Integer>();
		for (int i=0;i<A.length;i++) {
			sum+=A[i];
			if (set.contains(A[i])) {
				dup = A[i];
			} else {
				set.add(A[i]);
			}
		}
		result[0] = dup;
		result[1] = n*(n+1)/2 + dup - sum;
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer arr[] = {3,1,2,3,5};
		ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(arr));
		
		System.out.println(repeatedNumber(arrayList).toString());
		
		int arr2[] = {3,1,2,3,4,5,6,8};
	 	int result[] =findDupMissedFromNaturalNumber(arr2);
		System.out.println("["+result[0]+","+result[1]+"]");
		
	}

}
