package com.code.review.array.string.easy.rotate;

public class FindMinimunInRotaiedSortedArray {
	/**
	    *  LeetCode – Find Minimum in Rotated Sorted Array (Java) 

			Suppose a sorted array is rotated at some pivot unknown to you beforehand. 
			(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
			
			Find the minimum element.You may assume no duplicate exists in the array.
			Analysis

			This problem is a binary search and the key is breaking the array to two parts, so that 
			we only need to work on half of the array each time.
			
			If we pick the middle element, we can compare the middle element with the leftmost (or 
			rightmost) element. If the middle element is less than leftmost, the left half should be selected; 
			if the middle element is greater than the leftmost (or rightmost), the right half should be selected. 
			Using recursion or iteration, this problem can be solved in time log(n).
			
			In addition, in any rotated sorted array, the rightmost element should be less than the left-most element, 
			otherwise, the sorted array is not rotated and we can simply pick the leftmost element as the minimum. 
			
			My Implementation
			As 4 5 6 7 0 1 2, first arr[mid] = 7, arr[right] = 2, therefore left = mid+1 
			If 5,6,7,0,1,2,3,4, arr[mid] = 1, 
			while (left < right)
	    */
	     public int FindMinimunInRotaiedSortedArray(int arr[]) {
	    	 if (null==arr ||  arr.length==0) {
	    		 return -1;
	    	 }
	    	 if (arr.length==1) {
	    		 return arr[0];
	    	 }
	    	 int left = 0;    	 
	    	 int right = arr.length-1;
	    	 if (arr[left]<arr[right]) { // not rotated
	    		 return arr[right/2];
	    	 }
	    	 while (left <= right) {
	    		if (right-left == 1) {
	    			 return arr[right];
	    		 }
	    		 int mid = left + (right-left)/2;
	    		 if (arr[mid] > arr[right]) {
	    			 left = mid;
	    		 } else {
	    			 right = mid;
	    		 }
	    	 }
	    	 return arr[left];
	     }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
