package com.code.review.aaa.facebook.code.lab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindMissAndDup1ToN {
	/**
	 * You are given a read only array of n integers from 1 to n.

		Each integer appears exactly once except A which appears twice and B which is missing.
		
		Return A and B.
		
		Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
		
		Note that in your output A should precede B.
		
		Example:
		
		Input:[3 1 2 5 3] 
		
		Output:[3, 4] 
		
		A = 3, B = 4


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
	    result.add(should - sum + dup);
	   
	    return result;
	}
	public static int binarySearchFindMissNumber(int arr[]) {
		//0,1,2,3,....N
		int low = 0;
		int high = arr.length-1;
		int mid =0;
		while (low<=high) {
			mid = low+(high-low)/2;			
			if (arr[mid] > mid+1) {
				high = mid-1;
				//System.out.println("greater than arr[mid]="+arr[mid]+",mid="+mid+",low="+low+",high="+high);
			} else {
			//	System.out.println("less than arr[mid]="+arr[mid]+",mid="+mid+",low="+low+",high="+high);
				low = mid+1;
			}   
		}
		return low+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,2,3,6,4,5,6,8,9,10,11,12,13,14};
		List<Integer> a = new ArrayList<Integer>();
		for (Integer i:A) {
			a.add(i);
		}
		ArrayList<Integer> result =repeatedNumber( a);
		for (Integer i: result) {
			System.out.print(i+ " ");
		}
		System.out.println(" " );
		int A2[] = {1,2,3,4,5,6,8,9,10,11,12,13,14};
		int miss = binarySearchFindMissNumber(A2);
		System.out.println("\nmiss="+miss);
	}

}
