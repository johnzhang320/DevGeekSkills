package com.code.review.aaa.facebook.code.lab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SumContiguousNonNegativeSubarray {
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
	// O(n) solution for finding smallest subarray with sum
	// greater than x
	 
	 
	    // Returns length of smallest subarray with sum greater than x.
	    // If there is no subarray with given sum, then returns n+1
	    public static int smallestSubWithSum(int arr[], int x) 
	    {
	    	int len = arr.length;
	        // Initialize current sum and minimum length
	        int curr_sum = 0, min_len = len + 1;
	       
	       
	        // Initialize starting and ending indexes
	        int start = 0, end = 0;
	        while (end < len) 
	        {
	            // Keep addileng array elements while current sum
	            // is smaller than x
	            while (curr_sum <= x && end < len) {
	                curr_sum += arr[end];
	             
	                end++;
	            }
	 
	            // If current sum becomes greater than x.
	            while (curr_sum > x && start < len) 
	            {
	                // Update minimum length if needed
	                if (end - start < min_len) {
	                    min_len = end - start;
	                    
	                }
	                // remove starting elements
	                curr_sum -= arr[start];
	                
	                start++;
	            }
	        }
	        if (min_len == len+1)
		           System.out.println("Not Possible");
		        else {
		           System.out.println("min_len="+min_len);
		        
		          // list.forEach((i)->System.out.print(i+" "));
		           System.out.println("\n ");
		        }   
	        return min_len;
	    }
	 
	    // Driver program to test above functions
	    public static void main(String[] args)
	    {
	        int arr1[] = {1, 4, 45, 6, 10, 19};
	        int x = 51;
	        int n1 = arr1.length;
	         smallestSubWithSum(arr1, x);
	        
	 
	        int arr2[] = {1, 10, 5, 2, 7};
	        int n2 = arr2.length;
	        x = 9;
	        int res2 = smallestSubWithSum(arr2,  x);
	      
	 
	        int arr3[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
	        int n3 = arr3.length;
	        x = 280;
	        int res3 = smallestSubWithSum(arr3, x);
	         
	    }
	 
	 

}
