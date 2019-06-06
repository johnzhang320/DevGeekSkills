package com.code.review.aaa.career.cup.array.string;
/**
 * 
 *  bloomberg-lp-interview-questions
 

	* Given an unsorted integer array, place all zeros to the end of the array without changing the 
	* sequence of non-zero
	* elements. (i.e. [1,3,0,8,12, 0, 4, 0,7] --> [1,3,8,12,4,7,0,0,0])
 *  (1) slow = 0 and fast=0 two pointer
 *  (2) in fast<A.length loop if (A[fast]==0 fast++
 *  (3) if (A[fast]!=0 A[slow] = A[fast] , slow++, fast++
 *  (4) in slow<A.length loop A[slow]=0 slow++
 *  O(n)
 */
 
 
public class MoveZeroToEnd {
    public static int[] moveZeroToEnd(int A[]) {
    	int slow=0;
    	int fast =0;
    	while (fast<A.length) {
    		if (A[fast]==0) {
    			fast++;
    		} else {
    			A[slow] = A[fast];
    			slow++;
    			fast++;
    		}
    	}
    	while (slow<A.length) {
    		A[slow]=0;
    		slow++;
    	}
    	return A;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,3,0,8,12, 0, 4, 0,7};
		int A2[] = moveZeroToEnd(A);
		for (Integer a: A2) System.out.print(a+" ");
	}

}
