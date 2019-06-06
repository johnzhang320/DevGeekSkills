package com.code.review.array.string.easy.duplicate;

import java.util.HashMap;
import java.util.Map;

public class DuplicateII {
	/**
	 *  A array and B array, return true only if A[i] == B[j] and abs(i-j) <k 
	 *  O(n) time to find the result.
	 * @param args
	 */
	public static boolean findDuplicateII(int A[], int B[], int k) {
		Map<Integer, Integer> map =new  HashMap<Integer,Integer>();
		for (int i=0; i<B.length; i++) {
			map.put(B[i], i);
		}
		for (int j=0; j<A.length;j++) {
			if (map.containsKey(A[j])) {
				int i = map.get(A[j]);
				if (Math.abs(i-j)<=k) {
					System.out.println("Two Element duplicated at element:"+A[j]);
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {3,1,4,5,6,9,10,11};
		int B[] = {2,12,7,8,11,19,120,111};
		int k=3;
		
		
		System.out.println(findDuplicateII(A, B,k) );
	}

}
