package com.code.review.aaa.career.cup.array.string;

public class FindElem1Occur {
	/**
	 * 
	 * Given an array of integers, every element appears twice except for one. Find that single one.

		Note: Your algorithm should have a linear runtime complexity. Could you implement it without
		using extra memory?
		
		Example :
		
		Input : [1 2 2 3 1]
		Output : 3

		My approach:
		use XOR each element, odd occurred element leave to result
	 */
	public static int findOneTimeElement(int A[]) {
		if (null==A || 0==A.length) return 0;
		int result=A[0];
		for (int i=1;i<A.length;i++) {
			result^=A[i];
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1, 2, 2, 3, 1};
		System.out.println(findOneTimeElement(A));
	}

}
