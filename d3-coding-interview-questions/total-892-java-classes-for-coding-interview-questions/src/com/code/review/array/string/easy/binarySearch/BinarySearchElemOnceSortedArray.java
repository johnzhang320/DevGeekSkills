package com.code.review.array.string.easy.binarySearch;

public class BinarySearchElemOnceSortedArray {
	/**
	 * Given a sorted array in which all elements appear twice (one after one) and one element 
	 * appears only once. Find that element in O(log n) complexity.
	 * Algorithm :
	 * An Efficient Solution can find the required element in O(Log n) time. The idea is to use Binary Search. 
	 * Below is an observation in input array.
	 *  All elements before the required have first occurrence at even index (0, 2, ..) and next occurrence at 
	 *  odd index (1, 3, …). And all elements after the required element have first occurrence at odd index and next occurrence at even index.
	 *  1) Find the middle index, say ‘mid’.
	 *  2) If ‘mid’ is even, then compare arr[mid] and arr[mid + 1]. If both are same, then the required element after ‘mid’ else before mid.
	 *  3) If ‘mid’ is odd, then compare arr[mid] and arr[mid – 1]. If both are same, then the required element after ‘mid’ else before mid.
	 * @param args
	 */
	// A Binary Search based function to find the element
	// that appears only once
	public static void search(int arr[], int low, int high)
	{
	     // Base cases
	    if (low > high)
	       return;
	 
	    if (low==high)
	    {
	        System.out.println("The once element is  "+ arr[low]);
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
	    else  // If mid is odd
	    {
	        if (arr[mid] == arr[mid-1]) 
	            search(arr, mid-2, high);
	        else
	            search(arr, low, mid-1);
	    }
	}
	
	public static int searchOnce(int arr[]) {
		int low = 0;
		int high = arr.length-1;
		while (low<=high) {
			if (low==high) {
				return arr[low];
			}
			int mid = low+(high-low)/2;
			if (mid%2==0) {  // even 
				if (arr[mid]==arr[mid+1]) {
					low=mid+2;
				} else {
					high = mid;
				}
				
			} else { // ood
				if (arr[mid] == arr[mid-1]) {
					low = low-2;
					
				} else {
					high = mid-1;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int arr[] = {1, 1, 2, 4, 4, 5, 5, 6, 6};
		
		 search(arr, 0, arr.length-1);
		 
		 System.out.println(searchOnce(arr));
		 
		 int arr1[] = {1, 1, 2, 2, 4, 5, 5, 6, 6};
			
		 search(arr1, 0, arr1.length-1);
		 
		 System.out.println(searchOnce(arr1));
		 /**
		  *  If element is 
		  */

	}
	
}
