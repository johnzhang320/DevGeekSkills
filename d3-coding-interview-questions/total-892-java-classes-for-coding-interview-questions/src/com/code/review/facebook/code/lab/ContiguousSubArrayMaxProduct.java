package com.code.review.facebook.code.lab;

public class ContiguousSubArrayMaxProduct {
	/**
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest
	 *  product.
		Return an integer corresponding to the maximum product possible.
		
		Example :
		
		Input : [2, 3, -2, 4]
		Return : 6 
		
		Possible with [2, 3]
	 *  My Approach
	 *  i-start, product is max, if i-start product < max , current i+1 is start, check if i-start product >max  
	 */
	public static int contiguousSubArrayMaxProduct(int A[]) {
		if (null == A || A.length==0) return -1;
	 
		int maxProd = Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		int currProd=1;
		for (int i=0; i<A.length; i++) {
			currProd *=A[i];
			
			max = Math.max(max,maxProd);
			if (currProd>=maxProd) {
				maxProd = currProd;
			} else {
				currProd=1;		
				maxProd=Integer.MIN_VALUE;
			}
		}
		max = Math.max(max, maxProd);
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2, 3, -2, 4,2};
		System.out.println(contiguousSubArrayMaxProduct(A));
		int A2[] = {2, 3, -2, 4, 2, -4, 5,2};
		System.out.println(contiguousSubArrayMaxProduct(A2));
	}

}
