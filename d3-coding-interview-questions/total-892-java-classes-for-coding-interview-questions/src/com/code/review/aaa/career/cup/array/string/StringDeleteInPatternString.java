package com.code.review.aaa.career.cup.array.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringDeleteInPatternString {
	/**
	 *  amazon-interview-questions

		write a function strRemove(char *source, char *remove )
		This function will delete all the chars that exist in string remove from array 
		source, number of iteration should be only 1. Make the searching efficient.
		Example
		("amazon development center", "aenr")
		"mzo dvlpmt ct".
		Criteria - First parameter should be modified , no need to create an extra string. 
	 *  
	 */
	/**
	 * Java code using char A[] and char remove[]
	 * (1) add remove string chars into HashSet
	 * (2) create p index as result section index
	 * (3) if A[i] is not in Hashset, then A[p] = A[i]; p++;
	 * (4) return new String(Arrays.copyOf(A,p)); or A[p} to A[len] ='0'; means C++ *p='\n';
	 * O(n)
	 * 
	 */
	public static String StringDeleteByPattern(char A[], char pattern[]) {
		if (null==A || 0==A.length || null==pattern || A.length < pattern.length) return null;
		Set<Character> set = new HashSet<Character>();
		for (Character c: pattern) set.add(c);
		int p = 0;
		for (int i=0; i< A.length;i++) {
			if (!set.contains(A[i])) {
				A[p++] = A[i];
			}
		}
		return new String(Arrays.copyOf(A,p));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		System.out.println(StringDeleteByPattern("amazon development center".toCharArray(), "aenr".toCharArray()));
	}

}
