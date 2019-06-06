package com.code.review.array.string.easy.binarySearch;

public class BinarySearchTwoElemIsTarget {
	/**
	 * 
		Given a sorted array of integers, find the starting and ending position of a given
		target value. Your algorithm's runtime complexity must be in the order of O(log n). 
		If the target is not found in the array, return [-1, -1]. For example, 
		given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
		
		Analysis
		Use binary Search find first element, then check mid left or right equal to arr[mid] 
	 * @param args
	 */
	public static int[] binarySearchTwoElemIsTarget(int arr[], int target) {
		int low=0;
		int high = arr.length-1;
		int [] result = new int[2];
		result[0]=-1;
		result[1]=-1;
		 
		int mid=0;
		while (low<high) {
			mid = low+(high-low)/2;
			if (target<arr[mid]) {
				high = mid-1;
			} else if (target>arr[mid]) {
				low = mid+1;
			} else {
				result[0] = arr[mid];
				if (mid>0 && arr[mid] == arr[mid-1] ) {
					result[0] = mid-1;
					result[1] = mid;
				} else if (mid>=0 && mid<arr.length-1 && arr[mid]==arr[mid+1]){
					result[0] = mid;
					result[1] = mid+1;
				} 
				 
				break;
			}
		}
		System.out.println("mid="+mid);
		 
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] ={5, 7, 7, 8, 8, 10};
		int result[] = binarySearchTwoElemIsTarget(arr,7);
		System.out.println("result[0]="+result[0]+",result[1]="+result[1]);
		
		 
		
		int result2[] = binarySearchTwoElemIsTarget(arr,9);
		System.out.println("result[0]="+result2[0]+",result[1]="+result2[1]);
		
		int arr2[] ={5, 5, 7, 7, 8, 8, 10};
		
		int result3[] = binarySearchTwoElemIsTarget(arr2,5);
		System.out.println("result[0]="+result3[0]+",result[1]="+result3[1]);
		
		int result4[] = binarySearchTwoElemIsTarget(arr,5);
		System.out.println("result[0]="+result4[0]+",result[1]="+result4[1]);
		
	}

}
