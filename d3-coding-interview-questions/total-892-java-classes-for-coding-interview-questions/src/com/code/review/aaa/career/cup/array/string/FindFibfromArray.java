package com.code.review.aaa.career.cup.array.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindFibfromArray {
	/**
	 * 
	 *  facebook-interview-questions
 

		From the input array, output a subset array with numbers part of a Fibonacci series.
		 input: [4,2,8,5,20,1,40,13,23]
		 output: [2,5,1,8,13]
		 
		my approach:
		(1) find max value from Array
		(2) calculate all fibonancci number from 0 to max value, put into HashSet
		(3) check if elements in hashset
		(4) Using simple iteration fibonancci program to calculate each fibonancci number
			 
		 
	 */
 
	public static List<Integer> getFibonancciFromArray(int A[]) {
		List<Integer> result = new ArrayList<Integer>();
		if (null== A || 0==A.length) return result;
		int max = Integer.MIN_VALUE;
		for (Integer n : A) {
			if(n>max) max = n;
		}
		Set<Integer> set = getFibonancciByMax(max);
		
		for (Integer n: A) {
			if (set.contains(n)) {
				result.add(n);
			}
		}
		return result;
	}
	
	public static Set<Integer> getFibonancciByMax(int max) {
		Set<Integer> set = new HashSet<Integer>();
		if (max==0 || max==1) {
			set.add(max);
			return set;
		}
		int fib1=1, fib2=1, fibonancci=1;
		set.add(fibonancci);
		while (fibonancci<=max) {			
			fibonancci = fib1+fib2;
			set.add(fibonancci);
			fib1 = fib2;
			fib2 = fibonancci;
			
		}
		return set;
	}
	public static void main(String arg[]) {
		int A[] = {4,2,8,5,20,1,40,13,23};
		List<Integer> result = getFibonancciFromArray(A);
		result.forEach((n)->System.out.print(n+","));
	}
}
