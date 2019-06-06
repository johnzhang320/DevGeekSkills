package com.code.review.array.string.easy.largest;

import java.util.ArrayList;
import java.util.List;

public class LargestThreeProduct {
/*
 * 628. Maximum Product of Three Numbers
Easy

 

Share
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24
 

Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

 */
	 /* 
     *  Reverse sort the A[] , check A[n-1] and A[n-2] <0 , then A[0] *A[n-1]*A[n-2] > A[0]*A[1]*A[2]
     *  My Solution leetcode passed !!!!!
     */
    public int maximumProduct(int[] A) {
        if (null == A || 0==A.length || A.length<3) return 0;
        int n = A.length;
        List<Integer> list =new ArrayList<Integer>();
        for (Integer i: A) list.add(i);
        list.sort((o1,o2)->o2-o1); //reversed
        int   neg = list.get(n-1)*list.get(n-2)*list.get(0);
        int  pos = list.get(0)*list.get(1)*list.get(2);
        if (neg > pos) {
            return neg;
        }
        return pos;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
