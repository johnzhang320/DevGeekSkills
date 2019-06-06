package com.code.review.array.string.hard.game;

public class ContinguousSmallestSubarrayLargerThanSum {
	/**
	 * 
	 * 
		Smallest subarray with sum greater than a given value
		
		Given an array of integers and a number x, find the smallest subarray with sum greater than 
		the given value.
		
		Examples:
		arr[] = {1, 4, 45, 6, 0, 19}
		   x  =  51
		Output: 3
		Minimum length subarray is {4, 45, 6}
		
		arr[] = {1, 10, 5, 2, 7}
		   x  = 9
		Output: 1
		Minimum length subarray is {10}
		
		arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}
		    x = 280
		Output: 4
		Minimum length subarray is {100, 1, 0, 200}
		
		arr[] = {1, 2, 4}
		    x = 8
		Output : Not Possible
		Whole array sum is smaller than 8.

	 */
	/* O(n) solution for finding smallest subarray with sum
	 * greater than x
	 * (1) i=0 , i controlled, min_sum=0, cur_sum, len=A.length
	 * (2) in i while loop, smaller while loop one, i<len && cur_sum<sum, i++, 
	 * (3) in smaller while loop two, i<len && cur_sum>sum, if (cur_sum<min_sum) min_sum=cur_sum  
	 * 
	 */
	 
	// Returns length of smallest subarray with sum greater than x.
	// If there is no subarray with given sum, then returns n+1
    public static int smallestSubWithSum(int A[], int sum) {
         if (null==A || 0==A.length ) return 0;
         int i=0; 
         int len = A.length;
         int min_len=-1;
         int cur_sum=0;
         int start=0;
         while (i<len) {
        	 while (i<len && cur_sum<=sum) {
        		 cur_sum+=A[i];
        		 i++;
        	 }
        	 
        	 while (i<len && cur_sum>sum) {
        		  if (i-start>min_len) {
        			  min_len=i-start;
        		  }
        		  cur_sum-=A[start];
        		  start++;		  
        	 }
         }
         if (min_len!=-1) {
        	 System.out.println("min length="+min_len);
         } else {
        	 System.out.println("Not found");
         }
         return min_len;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[] = {1, 4, 45, 6, 10, 19};
        int x = 51;
        
        smallestSubWithSum(arr1, x);
        
 
        int arr2[] = {1, 10, 5, 2, 7};
        int n2 = arr2.length;
        x = 9;
        int res2 = smallestSubWithSum(arr2,  x);
	}

}
