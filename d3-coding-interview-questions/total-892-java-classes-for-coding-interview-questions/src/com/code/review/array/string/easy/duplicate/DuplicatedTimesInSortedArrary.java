package com.code.review.array.string.easy.duplicate;

public class DuplicatedTimesInSortedArrary {
	/**
	 * Larry He
	 * in a sorted array, there is one number is repeated many times, find how many times does it repeat
	 * not accept O(n) and only accept O(logN) , it could be repeated n times
	 * for example {1,2,3,4,4,4,4,4,4,4,4,5,7,9,10,11,12}, given k=4, return 8;
	 * Solution:
	 * Using binary search
	 * find first occurrence index of repeated number 
	 * find last occurrence index of repeated number
	 * last - first = repeated occurrence
	 * O(logN)
	 */
	public int firstChar(int arr[], int k) {
		int low = 0;
		int high = arr.length-1;
		while (low<=high) {
			int mid = low+(high-low)/2;
			if (k<arr[mid]) {
				high = mid-1;
			} else if (k>arr[mid]) {
				low = mid+1;
			} else {
				if (mid>0 && k!=arr[mid-1]) { // 0 to mid
					return mid;
				}
				high = mid-1;
			}
		}
		return 0;
	}
	public int lastChar(int arr[], int k) {
		int low = 0;
		int high = arr.length-1;
		while (low<=high) {
			int mid = low+(high-low)/2;
			if (k<arr[mid]) {
				high = mid-1;
			} else if (k>arr[mid]) {
				low = mid+1;
			} else {
				if (mid<arr.length-1 && k!=arr[mid+1]) {   // mid to end-1
					return mid;
				}
				low = mid+1;
			}
		}
		return 0;
	}
	
	public void binarySearchRange(int A[], int low, int high, int target,int result[]) {
		if (low>high) {
			return;
		}
		if (A[low]==A[high] && A[low]==target) {
			result[0]=low;
			result[1]=high;
			return;
		}
		int mid = low+(high-low)/2;
		if (A[mid]>target) {
			binarySearchRange(A, low, mid-1, target,result);
		} else if (A[mid]<target) {
			binarySearchRange(A, mid+1, high, target,result);
		} else { // find target
			result[0]=mid;
			result[1]=mid;
			int i=mid;
			while (i>low && A[i]==A[i-1]) {
				i--;
				result[0]=i;
				
			}
			i = mid;
			System.out.println("mid="+mid);
			while(i<high && A[i]==A[i+1]) {
				i++;
				result[1]=i;
				 
			}
		}
	}
	public int getOccurrenceOfRepeatedChar(int arr[], int k) {
		int firstCharIndex = firstChar(arr, k);
		int lastCharIndex = lastChar(arr, k);
		System.out.println("firstCharIndex="+firstCharIndex+",lastCharIndex="+lastCharIndex);
		return lastCharIndex - firstCharIndex+1;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,2,3,4,4,4,4,4,4,4,4,5,7,9,10,11,11,11,12};
		int k = 4;
		DuplicatedTimesInSortedArrary ref = new DuplicatedTimesInSortedArrary();
		//System.out.println(ref.getOccurrenceOfRepeatedChar(arr, k) );
		int result[] = new int[2];
		ref.binarySearchRange(A, 0, A.length-1, 4, result);
		System.out.println("Index["+result[0]+","+result[1]+"]");
	}

}
