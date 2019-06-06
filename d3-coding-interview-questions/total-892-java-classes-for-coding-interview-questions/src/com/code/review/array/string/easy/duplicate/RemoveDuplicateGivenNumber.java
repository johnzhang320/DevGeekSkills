package com.code.review.array.string.easy.duplicate;

import java.util.Arrays;

import com.code.utils.BST.Utils;

public class RemoveDuplicateGivenNumber {
	/**
	 * Remove Element

		Given an array and a value, remove all the instances of that value in the array.
		Also return the number of elements left in the array after the operation.
		It does not matter what is left beyond the expected length.
		
		    Example:
		    If array A is [4, 1, 1, 2, 1, 3]
		    and value elem is 1,
		    then new length is 3, and A is now [4, 2, 3]
		
		Try to do it in less than linear additional space complexity.
	 * @param args
	 */
	public static int[] removeDuplicatedGivenNumber(int A[], int k) {
		if (null==A || A.length==0) return null;
		int tail=0;
		for (int i=0; i<A.length;i++) {
			if (A[i]!=k) {
				A[tail++]=A[i];
			} 
		}
		return Arrays.copyOf(A, tail);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {4, 1, 1, 2, 1, 3};
		Utils.print(removeDuplicatedGivenNumber(A,1));
	}

}
