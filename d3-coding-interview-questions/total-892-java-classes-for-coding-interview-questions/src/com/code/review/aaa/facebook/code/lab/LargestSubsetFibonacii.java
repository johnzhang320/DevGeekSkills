package com.code.review.aaa.facebook.code.lab;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LargestSubsetFibonacii {
	/**
	 * 
		Largest subset whose all elements are Fibonacci numbers
		
		Given an array with positive number the task is that we find largest subset from array that contain elements which are Fibonacci numbers.
		
		Asked in Facebook
		
		Examples:
		
		Input : arr[] = {1, 4, 3, 9, 10, 13, 7};
		Output : subset[] = {1, 3, 13}
		The output three numbers are Fibonacci
		numbers.
		
		Input  : arr[] = {0, 2, 8, 5, 2, 1, 4, 
		                  13, 23};
		Output : subset[] = {0, 2, 8, 5, 2, 1, 
		                    13, 23}

	 *  my Implementation
	 *  (1) find max number in A[]
	 *  (2) calculate all fibonacci from 0 to max, add to hashSet
	 *  (3) one pass to filter out all elements be fibonacci without repeated calculation of fibonacci
	 *  
	 */
	Set<Integer> set = new HashSet<Integer>();
	public void fibonacci(int n) {
		int fib0 = 0;
		int fib1 = 1;
		set.add(fib0);
		set.add(fib1);
		int fib;
		for (int i=2; i<=n; i++) {
		     fib = fib0+fib1;
		     set.add(fib);
		     fib0=fib1;
		     fib1= fib;
		     //System.out.print(fib+" ");    
		     
		}
	}
	public List<Integer> findFibonacciSet(int A[]) {
		List<Integer> result = new ArrayList<Integer>();
		int max = Integer.MIN_VALUE;
		for (Integer i:A) {
			if (i>max) {
				max= i;
			}
		}
		fibonacci(max);
		for (Integer i:A) {
			if (set.contains(i)) {
				result.add(i);
			}
		}
		System.out.println(" ");
		result.forEach((x)->System.out.print(x+" "));
		System.out.println(" ");
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LargestSubsetFibonacii ref = new LargestSubsetFibonacii();
		ref.fibonacci(13);
		int A[] = {1, 4, 3, 9, 10, 13, 7};
		ref.findFibonacciSet(A);
		int arr[] = {0, 2, 8, 5, 2, 1, 4,  13, 23};
		ref.findFibonacciSet(arr);
	}

}
