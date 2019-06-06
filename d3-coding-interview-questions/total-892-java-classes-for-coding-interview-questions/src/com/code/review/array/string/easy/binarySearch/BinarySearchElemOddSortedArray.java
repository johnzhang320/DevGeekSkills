package com.code.review.array.string.easy.binarySearch;
/**
 * Input: arr[] = {1, 1, 2, 2, 1, 1, 2, 2, 13, 1, 1, 40, 40, 13, 13}
Output: 13

Input: arr[] = {1, 1, 2, 2, 3, 3, 4, 4, 3, 600, 600, 4, 4}
Output: 3

 * @author jzhang
 *
 */
public class BinarySearchElemOddSortedArray {
	// A Binary Search based function to find the element
		// that appears only odd time
		public static void search(int arr[], int low, int high)
		{
		     // Base cases
		    if (low > high) {
		    	System.out.println("Not Found");
		        return;
		    }
		 
		    if (low==high)
		    {
		        System.out.println("The odd element is  "+ arr[low]);
		        return;
		    }
		 
		    // Find the middle point
		    int mid = (low + high) / 2;
		 
		    // If mid is even and element next to mid is
		    // same as mid, then output element lies on
		    // right side, else on left side
		    if (mid%2 == 0)
		    {
		        if (arr[mid] == arr[mid+1])
		            search(arr, mid+2, high);
		        else
		            search(arr, low, mid);
		    }
		    else  // If mid is odd, mid+1 only
		    {
		        if (arr[mid] == arr[mid-1])
		            search(arr, mid+1, high);
		        else
		            search(arr, low, mid-1);
		    }
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int arr1[] = {1, 1, 2, 2, 1, 1, 2, 2,  1, 1, 40, 40, 13,13,40};
			
		 search(arr1, 0, arr1.length-1);
	}

}
