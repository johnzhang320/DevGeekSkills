package com.code.review.array.string.easy.move.zero;

import java.util.ArrayList;
import java.util.List;

import com.code.utils.BST.Utils;

public class MoveZeroToHead {
	/**
	 * Interview question
	 * Move zero to head of array, keep original array order. Using O(1) space
	 * A[] = {0,1,0,2,0,9,7,0,10,9}
	 * output 
	 * {0,0,0,0,1,2,9,7,10,9}
	 * 
	 * each loop swap 0,N to N,0 , where N is non zero data
	 * 
	 * 
	 * 
	 * @param args
	 */
	//O(N^2)
	public static void moveZeroToBegin(int A[]) {
		int len = A.length;
		for (int i = 0; i < len; i++) {
			
			for (int j=1; j<len; j++) {
			   if (A[j-1]>0 && A[j]==0) {
				   int tmp = A[j-1];
				   A[j-1] = A[i];
				   A[i] = tmp;
			   }
				 
			}
		}
	}
	// O(N) very safe
	public static List<Integer> moveZeroToHead(int A[]) {
		List<Integer> zero = new ArrayList<Integer>();
		List<Integer> nonZero = new ArrayList<Integer>();
		for (int i=0; i<A.length;i++) {
			if (A[i]>0) nonZero.add(A[i]);
			if (A[i]==0) zero.add(A[i]);
		}
		zero.addAll(nonZero);
		
 		return zero;
	}
	/**
	 *  move zero to right end, number move to left
	 *  
	 *  O(n) runtime and O(n) space
	 *  
	 *  Solution 1:
	 *  create extra B[A.length], k=0, i=0 to A.length-1, if A[i] != 0 B[k++]=A[0], output B , one pass, extra O(n) space
	 *  
	 *  Solution 2: 
	 *  
	 *  i=0 to A.length-1, if A[k]!=0 && A[i]==0 , A[i] swap A[k], k++
	 *  
	 * @param args
	 */
	public static int [] moveZero3(int A[]) {
		int k=A.length-1;
		for (int i=A.length-1; i>=0;i--) {
			 if (A[k]!=0 && A[i]==0) {
				int tmp = A[i];
				A[i]=A[k];
				A[k]=tmp;
				k--; 
			}
			if (A[k]==0) {
				k--;
			}
		}
		return A;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] =  {0,1,0,8,0,9,7,0,10,0,12,0};
		moveZeroToBegin(A);
		Utils.print(A);
		System.out.println(moveZeroToHead(A).toString());
		
		int B[] =  {0,1,0,8,0,9,7,0,10,0,12,0};
		moveZero3(B);
		for (Integer i:B) {
			System.out.print(i+" ");
		}
	}

}
