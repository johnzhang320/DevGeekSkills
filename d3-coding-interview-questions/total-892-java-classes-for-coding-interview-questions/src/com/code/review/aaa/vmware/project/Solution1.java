package com.code.review.aaa.vmware.project;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Solution1 extends TestCase {
/**
 *  Given an array of numbers find the 3rd of smalleest value in the array list
 *  eg {10,2,5,7,66,12,9,34}
 * @param args
 */
	private Solution1 ref = null;
	public void setUp() {
		ref = new Solution1();
	}
	// O(nLogn)
	public int find3rdsmallestData(int A[], int k) {
		if (null==A || 0==A.length) return -1;
		if (A.length<k) return -1;
		
		Set<Integer> set = new TreeSet<Integer>();
		for (Integer i : A) {
			set.add(i);
		}
		Iterator<Integer> itr = set.iterator();
		int i = 0;
		while (itr.hasNext() && i<k-1) {		
			itr.next();
			i++;
		}
		return 	itr.next();
		
	}
    // find smallest one first, record it and find second smallest one 
	public int find3rdSmallestOne(int A[]) {
		if (null==A || 0==A.length) return -1;
		if (A.length<3) return -1;
		int min1 = Integer.MAX_VALUE, min2= Integer.MAX_VALUE, min3= Integer.MAX_VALUE;
		int index[] = new int[3];
		for (int i = 0 ; i<A.length;i++) {
			if (A[i]<min1) {
				min1 = A[i];
				index[0] = i;
			}
		}
		for (int i = 0 ; i<A.length;i++) {
			if (A[i]< min2 && i!=index[0]) {
				min2 = A[i];
				index[1] = i;
			}
		}
		for (int i = 0 ; i<A.length;i++) {
			if (A[i]< min3 && i!=index[0] && i!=index[1]) {
				min3 = A[i];
				index[2] = i;
			}
		}
		return min3;
	}
	public void testCase1() {
		// TODO Auto-generated method stub
		int A[] = {10,2,5,7,66,12,9,34};
		int k=3;
		int result = ref.find3rdSmallestOne(A);
		Assert.assertEquals(7, result);
		System.out.println(result);
		
		int A2[] = {10,2,1};
		result =  ref.find3rdSmallestOne(A2);
		Assert.assertEquals(10, result);
		System.out.println(result);
		
	}

}
