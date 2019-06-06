package com.code.review.aaa.career.cup.array.string;

public class FindRepeatTimeLessThanBigONSortedArray {
	/**
	 * 
	 *  amazon-interview-questions
 

		Find the frequency of a number in array in less than bigo n time
		Array 1,2,2,3,4,5,5,5,2
		Input 5
		Output 3
		Array 1,1,1,1,
		Input 1
		Output 4
		
		Keep in mind less than bigo n

	 * 
	 */
	
	/**
	 * This array is sorted, so we can use binary search , find repeated time in O(log(n))
	 * (1) binary search input x begin index and last index
	 * (2) when A[mid] == x, check if mid>0 and A[mid-1] != x during begin index search
	 * (3) when A[mid] == x, check if mid<A.length and A[mid+1] != x during end index search
	 * (4) end index - begin index is repeated time
	 */
	public static int beginIndex(int A[], int x) {
		int low = 0;
		int high = A.length-1;
		while (low<high) {
			int mid = low + (high-low)/2;
			if (x<A[mid]) {
				high = mid-1;
			} else if (x>A[mid]) {
				low = mid+1;
			} else {  // match
				while (mid>0 && A[mid-1]==x) {
					mid--;
				}
				return mid;
			}
		}
		return low;
	}
	public static int endIndex(int A[], int x) {
		int low = 0;
		int high = A.length-1;
		while (low<high) {
			int mid = low + (high-low)/2;
			if (x<A[mid]) {
				high = mid-1;
			} else if (x>A[mid]) {
				low = mid+1;
			} else {  // match
				while (mid<A.length-1 && A[mid+1]==x) {
					mid++;
				}
				return mid;
			}
		}
		return low;
	}
	public static int findRepeatedTime(int A[], int x) {
		if (null==A || 0==A.length) return 0;
		return endIndex(A,x)- beginIndex(A,x)+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,2,2,3,4,5,5,5};
		int x = 5;
		System.out.println(findRepeatedTime(A,  x));
	}

}
