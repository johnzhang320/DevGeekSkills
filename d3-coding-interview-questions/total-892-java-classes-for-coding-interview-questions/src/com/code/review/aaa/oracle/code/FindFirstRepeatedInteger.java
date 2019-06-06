package com.code.review.aaa.oracle.code;

import java.util.HashSet;
import java.util.Set;

public class FindFirstRepeatedInteger {
	/**
			 * 
		Find the first repeating element in an array of integers
		
		Given an array of integers, find the first repeating element in it. We need to find the element 
		that occurs more than once and whose index of first occurrence is smallest.
		
		Examples:
		
		Input:  arr[] = {10, 5, 3, 4, 3, 5, 6}
		Output: 5 [5 is the first element that repeats]
		
		Input:  arr[] = {6, 10, 5, 4, 9, 120, 4, 6, 10}
		Output: 6 [6 is the first element that repeats]

	 *  Solution one
	 *  place each element to HashSet from right hand side to left, if exist i=>minIndex; until to 0
	 *  return A[minIndex] O(n)
	 *  
	 *  Same logic we can found first duplicated char
	 */
	
	public static int findFirstIntegerFromArray(int A[]) {
		Set<Integer> set = new HashSet<Integer>();
		int minIndex=-1;
		for (int i=A.length-1;i>=0;i--) {
			if (set.contains(A[i])) {
				minIndex = i;
			} else {
				set.add(A[i]);
			}
		}
		if (minIndex!=-1) {
			System.out.println("found first repeated integer="+A[minIndex] );
			return A[minIndex];
		}
		else 
			System.out.println("Not found repeated integer");
		    return -1;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {6, 10, 5, 4, 9, 120, 4, 6, 5, 10};
		findFirstIntegerFromArray(A);
		int A1[] = {6, 10, 5, 4, 9, 120};
		findFirstIntegerFromArray(A1);
		
	}

}
