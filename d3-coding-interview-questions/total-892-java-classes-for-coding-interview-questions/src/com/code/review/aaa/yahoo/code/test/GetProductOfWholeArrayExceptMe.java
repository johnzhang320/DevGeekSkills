package com.code.review.aaa.yahoo.code.test;

public class GetProductOfWholeArrayExceptMe {
	/**
	 * 
	 *  yahoo-interview-questions
 
		you have a array nums as input. For any i from 0 to length - 1. 
		should print product of whole array except nums[i]
		For example: nums = [2,3,1,4,3,2]
		output:
			72
			48
			144
			36
			48
			72
			My Approach 
			First , calculated total product frtotom all elements in Array
			i Loop, check if A[i] !=0 , totalProduct/A[i] = each result element 
	 */
	public static int[] getProductExceptMe(int A[]) {
		int totalProd = 1;
		for (Integer v:A) totalProd *=v;
		int result[] = new int[A.length];
		for (int i=0; i<A.length;i++) {
			if (A[i]!=0) {
				result[i] = totalProd/A[i];
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2,3,1,4,3,2};
		int result[] =  getProductExceptMe(A);
		for (Integer i: result) System.out.print(i+" ");
		
	}

}
