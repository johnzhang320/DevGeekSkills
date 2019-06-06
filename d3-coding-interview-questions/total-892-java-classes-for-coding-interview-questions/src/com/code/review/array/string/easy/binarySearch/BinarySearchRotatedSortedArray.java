package com.code.review.array.string.easy.binarySearch;

public class BinarySearchRotatedSortedArray {
/**
*  LeetCode � Find Minimum in Rotated Sorted Array (Java) 

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
	while (left < right)
    */
	public static int BinarySearchRotatedSortedArray(int arr[], int target) {
		if (null==arr || arr.length==0) return -1;
		if (arr.length==1 ) {
			if (arr[0]!=target) {
				return -1;
				
 			} else {
 				return 0;
 			}
 		}
        if (arr.length==2 ) {
			if (arr[0]==target) {
				return 0;
				
 			} else if (arr[1]==target) {
 				return 1;
 			}
 		}
		// check if rotated
		int len = arr.length;
		int low = 0;
		int high = len-1;
        int prev=-1;
		while (low<=high) {
			int mid = low + (high-low)/2;
			if (arr[mid]==target) {
				return mid;
			}
			// {1,-2,3,10,-4,7,2,-5] , arr[low]<arr[mid] means from low to mid is ascend order
			// following ascend order array binary search target < arr[mid] high=high-1; target > arr[mid] low=mid+1
			// only point is we must check target > arr[low] 
			if (arr[low]<arr[mid]) {
				if (target<arr[mid] && target>arr[low]) { // target located left hand side of middle
					if (mid !=prev) high = mid; else high=mid-1;
				} else {
                    if (mid !=prev) low = mid; else low=mid+1;
					 
				}
			} else { // arr[low] > arr[mid] means mid is rotated zone, target > arr[mid] low=mid+1 otherwise high=mid-1
				if (target>arr[mid] && target < arr[high]) {
					 if (mid !=prev) low = mid; else low=mid+1;					
				} else {
					if (mid !=prev) high = mid; else high=mid-1;
				}
 			}
            prev = mid;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {4, 5, 6, 7, 0, 1 ,2, 3 };
		int target =2;
		System.out.println(BinarySearchRotatedSortedArray(arr, target));
		target = 6;
		System.out.println(BinarySearchRotatedSortedArray(arr, target));
		int arr2[] = {0,1,2,3,4, 5, 6, 7};
		target =2;
		System.out.println(BinarySearchRotatedSortedArray(arr2, target));
		target = 6;
		System.out.println(BinarySearchRotatedSortedArray(arr2, target));
		int arr3[] ={2};
		target =2;
		System.out.println(BinarySearchRotatedSortedArray(arr3, target));
		 
	}

}
