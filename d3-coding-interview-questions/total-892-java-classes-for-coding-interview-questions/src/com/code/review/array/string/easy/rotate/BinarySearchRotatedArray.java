package com.code.review.array.string.easy.rotate;

public class BinarySearchRotatedArray {
	/**
	 * Search in a Rotation of an Array

		Question: When some elements at the beginning of an array are moved to the end, it gets a rotation
		 of the original array. Please implement a function to search a number in a rotation of an 
		 increasingly sorted array. Assume there are no duplicated numbers in the array.
		For example, array {3, 4, 5, 1, 2} is a rotation of array {1, 2, 3, 4, 5}. If the target number 
		to be searched is 4, the index of the number 4 in the rotation 1 should be returned. 
		If the target number to be searched is 6, -1 should be returned because the number does not exist 
		in the rotated array.
		Analysis: Binary search is suitable for sorted arrays. Let us try to utilize it on a rotation of a 
		sorted array. Notice that a rotation of a sorted array can be partitioned into two sorted sub-arrays,
		 and numbers in the first sub-array are greater than numbers in the second one.
		Two pointers P1 and P2 are utilized. P1 references to the first element in the array, and P2 
		references to the last element. According to the rotation rule, the first element should be greater
		 than the last one.
		The algorithm always compares the number in middle with numbers pointed by P1 and P2 during binary 
		search. If the middle number is in the first increasingly sorted sub-array, it is greater than the 
		number pointed by P1.
		If the value of target number to be search is between the number pointed by P1 and the middle number, 
		we then search the target number in the first half sub-array. In such a case the first half sub-array 
		is in the first increasing sub-array, we could utilize the binary search algorithm. For example, 
		if we search the number 4 in a rotation {3, 4, 5, 1, 2}, we could search the target number 4 in the 
		sub-array {3, 4, 5} because 4 is between the first number 3 and the middle number 5.
		If the value of target number is not between the number pointed by P1 and the middle number, 
		we search the target in the second half sub-array. Notice that the second half sub-array also
		 contains two increasing sub-array and itself is also a rotation, so we could search recursively 
		 with the same strategy. For example, if we search the number 1 in a rotation {3, 4, 5, 1, 2}, 
		 we could search the target number 1 in the sub-array {5, 1, 2} recursively.
		The analysis above is for two cases when the middle number is in the first increasing sub-array. 
		Please analyze the other two cases when the middle number is in the second increasing sub-array 
		yourself, when the middle number is less than the number pointed by P2. 
	 * @param args
	 */
	 int searchInRotation(int numbers[], int length, int k)
	 {
	     if(numbers == null || length <= 0)
	         return -1;
	    
	     return searchInRotation(numbers, k, 0, length - 1);
	 }
	 int searchInRotation(int numbers[], int k, int start, int end)
	 {
	     if(start > end)
	         return -1;
	        
	     int middle = start + (end - start) / 2;
	     if(numbers[middle] == k)
	         return middle;
	    
	     // the middle number is in the first increasing sub-array
	     if(numbers[middle] >= numbers[start])
	     {
	         if(k >= numbers[start] && k < numbers[middle])
	             return binarySearch(numbers, k, start, middle - 1);
	         return searchInRotation(numbers, k, middle + 1, end);
	     }
	     // the middle number is in the second increasing sub-array
	     else if(numbers[middle] <= numbers[end])
	     {
	         if(k > numbers[middle] && k <= numbers[end])
	             return binarySearch(numbers, k, middle + 1, end);
	         return searchInRotation(numbers, k, start, middle - 1);
	     }
	    
	     // It should never reach here if the input is valid
	     return -1;
	 }
	 int binarySearch(int arr[], int target, int low, int high ) {
		     if (low>high) return -1;
		    
			 int mid = (low+high)/2;
			 if (arr[mid]==target) return mid;
			 if (arr[mid]<target) {
				return  binarySearch(arr,target, mid+1, high);
			 }  
			 return binarySearch(arr,target,low,mid-1);
 	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]={2,3,5,6,7,8,9};
		 BinarySearchRotatedArray ref =new  BinarySearchRotatedArray();
		 System.out.println(ref.binarySearch(arr, 7, 0, arr.length-1));
		int rotate[] = {7,8,9,2,3,5,6};
		
		 System.out.println(ref.searchInRotation(arr, 9, arr.length-1));
	}

}
