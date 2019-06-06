package com.code.review.aaa.career.cup.array.string;

import java.util.Arrays;

public class FindIndexOfMaxValueOnePass {
	/**
	 *  facebook-interview-questions
 
		You have a array with integers:
		
		[ 1, -2, 0, 6, 2, -4, 6, 6 ]
		
		You need to write a function which will evenly return indexes of a max value in the array.
		In the example below max value is 6, and its positions are 3, 6 and 7. So each run function 
		should return random index from the set.
		
		Try to implement with O(n) for computation and memory.
		Try to reduce memory complexity to O(1).
	 *  my approach for O(n) computation and O(1) space
	 *  Analysis:
	 *  We dynamically put max into left size of original array, if current element > previous Max, overwrite 
	 *  equal append to left side
	 *  (1) In an iteration, create two pointer, p1 and i, p1 store the of index current max data in array,  
	 *  (2) if max == A[i], A[++p1] = i, if A[i]>max, max = A[i],p1=0, A[p1] = i, p1++;
	 *  (3) return A[0] ~ A[p1]
	 */
	public static int[] findIndexOfMaxOnePass(int A[]) {
		if (null==A || 0==A.length) return null;
		int p1 = 0;
		int max = Integer.MIN_VALUE;
		for (int i=0;i<A.length;i++) {
			if (A[i]>max) {
				p1=0;
				A[p1] = i;
				max = A[i];				
			} else if (A[i] == max) {
				p1++;
				A[p1] = i;
			}
		}
		return Arrays.copyOf(A, p1+1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = 	{ 1, -2, 0, 6, 2, -4, 6, 6 };
		int result[] = findIndexOfMaxOnePass(A);
		for (Integer i: result) {
			System.out.print(i+",");
		}
	}

}
