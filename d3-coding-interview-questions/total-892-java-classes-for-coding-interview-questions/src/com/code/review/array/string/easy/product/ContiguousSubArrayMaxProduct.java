package com.code.review.array.string.easy.product;

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
	 
		int maxProd = Integer.MIN_VALUE;   //current round max prod
		int max = Integer.MIN_VALUE;       //history round max prod
		int currProd=1;
		for (int i=0; i<A.length; i++) {   // start with i=0;
			currProd *=A[i];
			
			max = Math.max(max,maxProd); // historic round max 
			if (currProd>=maxProd) {   // find current prod is current max prod
				maxProd = currProd;
			} else {   
				currProd=1;    // current prod is not larger than current max prod, we produce new current prod 		
				maxProd=Integer.MIN_VALUE;  // reset maxprod, skip  exam next A[i+1], ex,[2,3,-2,4,2) , -2 make currentProd <maxprod
				                            // forget -2, calculate new currentProd from 4
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
