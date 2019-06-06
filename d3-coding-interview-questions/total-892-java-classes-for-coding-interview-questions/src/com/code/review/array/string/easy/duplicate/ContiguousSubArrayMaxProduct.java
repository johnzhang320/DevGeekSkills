package com.code.review.array.string.easy.duplicate;

public class ContiguousSubArrayMaxProduct {
	/**
	 * Find the contiguous sub array within an array (containing at least one number) which has the largest
	 *  product.
		Return an integer corresponding to the maximum product possible.
		
		Example :
		
		Input : [2, 3, -2, 4]
		Return : 6 
		
		Possible with [2, 3]
		Analysis:
		we have three level product:currProd, max Product, accumulated max product 
		
	 *  My Approach
	 *  (1) currProd =1 and maxProd=min and accumulateMaxProd = min
	 *  (2) currProd *=A[i];
	 *  (3) accumulatedMaxProd and maxProd compare, larger one save to accumulatedMaxProd
	 *  (4) if currProd >= maxProd then maxProd =currProd else currProd=1 and maxProd=min
	 */
	public static int contiguousSubArrayMaxProduct(int A[]) {
		if (null == A || A.length==0) return -1;
	 
		int maxProd = Integer.MIN_VALUE;
		int accumulatedMaxProd = Integer.MIN_VALUE;
		int currProd=1;
		for (int i=0; i<A.length; i++) {
			currProd *=A[i];
			
			accumulatedMaxProd = Math.max(accumulatedMaxProd,maxProd);  // larger pool 
			if (currProd>=maxProd) {
				maxProd = currProd;            // small pool
			} else {
				currProd=1;		
				maxProd=Integer.MIN_VALUE;
			}
		}
		accumulatedMaxProd = Math.max(accumulatedMaxProd, maxProd);   // result in large pool
		return accumulatedMaxProd;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {2, 3, -2, 4,2};
		System.out.println(contiguousSubArrayMaxProduct(A));
		int A2[] = {2, 3, -2, 4, 2, -4, 5,2};
		System.out.println(contiguousSubArrayMaxProduct(A2));
	}

}
