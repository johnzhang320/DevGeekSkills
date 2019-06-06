package com.code.review.array.string.easy.binarySearch;

 

public class BinarySearchMinRotatedSortedArray {
	/**
	    *  LeetCode � Find Minimum in Rotated Sorted Array (Java) 

			Suppose a sorted array is rotated at some pivot unknown to you beforehand. 
			(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
			
			Find the minimum element.You may assume no duplicate exists in the array.
			Analysis

			This problem is a binary search and the key is breaking the array to two parts, so that 
			we only need to work on half of the array each time.
			
			If we pick the middle element, we can compare the middle element with the lowmost (or 
			highmost) element. If the middle element is less than lowmost, the low half should be selected; 
			if the middle element is greater than the lowmost (or highmost), the high half should be selected. 
			Using recursion or iteration, this problem can be solved in time log(n).
			
			In addition, in any rotated sorted array, the highmost element should be less than the low-most element, 
			otherwise, the sorted array is not rotated and we can simply pick the lowmost element as the minimum. 
			
			My Implementation
			As 4 5 6 7 0 1 2, first arr[mid] = 7, arr[high] = 2, therefore low = mid+1 
			while (low < high)
	    */
	/*
	 * tags: Array, Binary Search
time: log(n)
space: O(1)

#### Binary Search
- 关键点, 是找到 [mid]是在左边/还是右边的continous increasing subarray: 比较 `A[start] < A[mid]`
- 在两个section 里面分别讨论 target 的位置     
- 1. `nums[start] < nums[mid]`: start是从index=0开始的, 那就说明 `mid在前半段`
- `start<target<mid`: target 在这个section里面, end = mid;
- `target > mid`: start = mid;
- 2. `nums[start] > nums[mid]`: start是从index=0开始的, 那就说明 `mid在后半段`
- `mid < target < end`: start = mid;
- `target < mid`: end = mid;   

#### binary search break point, 然后继续binary search target
- 1. binay search break point     
- 2. binary search target      
- 注意等号，在判断target在前半段还是后半段：if (A[p1] <= target && target <= A[breakPoint])      

	 */
	     public static int FindMinimunInRotaiedSortedArray(int nums[],int target) {
	    	  if (nums == null || nums.length == 0) return -1;
	          int start = 0, end = nums.length - 1;
	          while (start + 1 < end) {
	              int mid = start + (end - start) / 2;
	              if (nums[mid] == target) return mid;
	              if (nums[start] < nums[mid]) { //Land in 1st continous section
	                  if (nums[start] <= target && target <= nums[mid]) { //target in 1st section?
	                      end = mid;
	                  } else {
	                      start = mid;
	                  }
	              } else { //Land in 2nd continous section
	                  if (nums[mid] <= target && target <= nums[end]) { //target in 2nd section?
	                      start = mid;
	                  } else {
	                      end = mid;
	                  }
	              }
	          }
	          if (nums[start] == target) return start;
	          if (nums[end] == target) return end;
	          return -1;
	     }
	/**
	 *  My understanding
	 *  (1) initially check if A[low] < A[high] , yes means no rotation, return A[low]
	 *  (2) before rotation element, A[mid] always greater than A[high], low = mid, do not use low=mid+1
	 *  (3) once A[mid] is at rotation element , A[mid] is always less than A[high], high = mid;   
	 * @param args
	 */
	 
	public static void main(String[] args) {
// TODO Auto-generated method stub
 	  	 	
	 	int arr[] = {5, 6, 7, 9, 10, 20, 21,100, 1, 2, 3 ,4 };   	 
	  
	  
	}

}
