package com.code.review.array.string.easy.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonDataUnsortedArrays {
	/**
	 * 	
	 *  Case 1: Two unsorted arrays , elements in array itself has no repeated
	 *  Two unsorted arrays ,find the same data elements among two arrays
	 *  implementation:
	 *  (1) add first array to HashSet
	 *  (2) loop second array, curr exists in set, add to result
	 */
	public static int[] CommonDataUnsortedArrays (int [] arr1, int [] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		int i=0,j=0;
		Set<Integer> set = new HashSet<Integer>();
		int result[] = new int[len1+len2+1];
		for (Integer a: arr2) {
			set.add(a);
		}
		for (Integer p: arr1) {
			if (set.contains(p)) {
				result[i++] = p;
			}
		}
		return Arrays.copyOf(result,i);
	}
	/**
	 * 	
	 *  Case 2: Two unsorted arrays , elements in array itself could be repeated
	 *  Two unsorted arrays ,find the same data elements among two arrays
	 *  implementation:
	 *  Create two set<Integer> , add two arrays in set separately
	 *  Loop set1 and find element in set2 
	 *  then  list element in map when count == 1;
	 */
	public static int[] CommonUnsortedArraysRepeated(int arr1[], int arr2[]) {
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		for (Integer i: arr1) set1.add(i);
		for (Integer i: arr2) set2.add(i);
		int result[] = new int[arr1.length+arr2.length];
		int count=0;
		for (Integer i: set1) {
			if (set2.contains(i)) {
				result[count++] = i;
			}
		}
		return Arrays.copyOf(result, count);
	}
	
	public static List<Integer> intersectsInToUnsortedArrays(int A[], int B[]) {
		List<Integer> result = new ArrayList<Integer>();
		if (null==A || 0==A.length || null==B || 0==B.length ) return result;
		Set<Integer> set = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		for (Integer i : A) set.add(i);
		for (Integer i : B) set2.add(i);
		for (Integer b: set2) {
			if (set.contains(b)) {
				result.add(b);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int a[]={2,4,9,8,10,12,15,16,30,33,40};		
		 int b[]={8,4,3,2,5,6,0,10,20,31};
		 int result[] = CommonDataUnsortedArrays (a, b);		 
		 
		 int res[] = {4,8,2,10};
		 
		 System.out.println(Arrays.toString(result));
		 
		 int a2[]={2,4,8,9,10,10,12,15,16,30,33,4,40};		
		 int b2[]={8,4,3,2,5,6,0,10,20,8,31};
		 int result2[] =  CommonUnsortedArraysRepeated (a2, b2);		 	 
		 
		 
		 System.out.println(Arrays.toString(result2));
		 
		 int a3[]={2,4,8,9,10,10,12,12,10,15,16,30,33,4,40};		
		 int b3[]={8,4,3,2,5,6,0,5,10,20,8,31,10};
		 System.out.println(intersectsInToUnsortedArrays(a3, b3).toString());		
	}

}
