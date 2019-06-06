package com.code.review.array.string.easy.rotate;

public class RotateArrayNoExtraMemoryBigOn {
/**
 * 
 * 	 amazon-interview-questions 
		
		Rotate a array by N. N can be smaller of greater than the array length.
		e.g {0,1,2,4,5,6,7} N =4 should return {5,6,7,4,0,1,2}. 
		No extra memory and O(n) computation time
		{0,1,2,4,5,6,7}  
 */
	/**
	 *  Suppose N is located at middle index
	 *  (1) define low index; low = prev index (2) of N and high index = length-1
	 *  (2) swap A[low] and A[high] ; low-- and high--, until low==0
	 *  Suppose N is 2, which is not at middle index return [7,6,5,4,2,1,0]
	 *  [0,1,2,4,5,6,7] low= 1, high=6,
	 *   
	 *  step 1 [7,1,2,4,5,6,0] low =0, high =6
	 *  step 2 [7,6,2,4,5,1,0] low =1, high =7  if (A[low] < A[high] 
	 *  step 3 [7,6,5,4,2,1,0] low= 2,  high=5  swap
	 *   
	 *   
	 *  Suppose N is 5, which is not at middle index return [6,7,5,0,1,2,4]
	 *  [0,1,2,4,5,6,7] low= 3, high=6, 
	 *  step 1 [ 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
