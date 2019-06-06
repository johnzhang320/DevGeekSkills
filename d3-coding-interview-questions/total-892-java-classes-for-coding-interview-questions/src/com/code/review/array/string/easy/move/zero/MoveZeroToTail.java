package com.code.review.array.string.easy.move.zero;

import java.util.ArrayList;
import java.util.List;

import com.code.utils.BST.Utils;

public class MoveZeroToTail {
	/**
	 * Interview question
	 * Move zero to tail of array, keep original array order. Using O(1) space
	 * A[] = {0,1,0,2,0,4,7,0,10,12}
	 * output 
	 * {1,2,4,7,10,12,0,0,0,0}
	 * 
	 * i index trace zero, j index trace non zero data, A[i]==0 && A[j]>0 
	 * A[i] = A[j] and A[j] = 0; if A[i]==0 then goto internal loop
	 * 
	 * @param args
	 */
	// O(n)
	public static void moveZeroToTail(int A[]) {
		if (null==A || A.length==0) return;
		int len = A.length;
		int k=0;
		
		for (int i=0;i<len;i++) {
			if (A[i]!=0) {
				A[k++]=A[i];
			}
		}
		for (int i=k;i<len;i++) {
			A[i]=0;
		}
	}
	// O(N) very safe
	public static List<Integer> moveZeroToTail2(int A[]) {
		List<Integer> zero = new ArrayList<Integer>();
		List<Integer> nonZero = new ArrayList<Integer>();
		for (int i=0; i<A.length;i++) {
			if (A[i]>0) nonZero.add(A[i]);
			if (A[i]==0) zero.add(A[i]);
		}
		nonZero.addAll(zero);
		
 		return nonZero;
	}
		 
	 
			
		 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] =  {0,1,0,2,0,4,7,0,10,12,0,13,15,0,22};
		moveZeroToTail(A);
		Utils.print(A);
		System.out.println(moveZeroToTail2(A).toString());
	}

}
