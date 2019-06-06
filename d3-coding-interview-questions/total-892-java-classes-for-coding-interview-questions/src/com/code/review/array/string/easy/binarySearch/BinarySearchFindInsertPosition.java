package com.code.review.array.string.easy.binarySearch;

 
public class BinarySearchFindInsertPosition {
	/**
	 * Given a sorted array and a target value, return the index if the target is found.
	 *  If not, return the index where it would be if it were inserted in order. 
	 *  You may assume no duplicates in the array.
 	 *	Here are few examples.
 	 *	[1,3,5,6], 5 -> 2
	 *	[1,3,5,6], 2 -> 1
	 *	[1,3,5,6], 7 -> 4
	 *	[1,3,5,6], 0 -> 0
 	 * 
 	 * Analysis
 	 *  Apply binary search to find insert position
 	 *  check index if low index < high index as while loop criteria
 	 *  mid =low + (high-low)/2; 
 	 *  check if (arr[mid] > target) low=mid+1; else { high = mid }    
	 * @param args
	 */
	public static int binarySearchFindIndexOfTargetInsert(int [] arr, int target) {
		int low=0;
		int high = arr.length;
		int mid = 0;
		while (low < high) {
			mid = low+(high - low)/2;
			if (arr[mid]>target) {
				high = mid-1;
			} else if (arr[mid]<target){
				low = mid+1;
			} else {
				return mid;
			}
		}
		return high;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,2,3,5,6,7,9,10,12};
		int target[] = {2,3,4,5, 8,13};
	//	ArrayUtil.display(arr);
		for (int i=0; i<target.length;i++) {
			System.out.println("insert target "+target[i]+"  index === "+binarySearchFindIndexOfTargetInsert(arr, target[i]));
		}
	}

}
