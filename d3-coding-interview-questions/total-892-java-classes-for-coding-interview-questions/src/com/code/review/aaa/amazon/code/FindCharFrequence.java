package com.code.review.aaa.amazon.code;
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
	
	Thanks
	My Approach:
	Obviously this is sorted array, we apply firstChar and lastChar subroutine
	and in each sub code , we use binary search , once find data, firstChar find A[mid-1] ! = k
	or k!=A[mid+1]
 *
 */

public class FindCharFrequence {
	public int firstChar(int A[], int k) {
		if (null==A || 0==A.length) return 0;
		int low = 0;
		int high = A.length-1;
		while(low<=high) {
			int mid = low+(high-low)/2;
			if (k<A[mid]) {
				high = mid-1;
			} else if (k>A[mid]) {
				low=mid+1;
			} else { // find k, first A[mid] is k			 
				if (mid>0 && k!=A[mid-1]) {
					return mid;
				}
				high=mid-1;
			}
			
		}
		return 0;
	}
	public int lastChar(int A[], int k) {
		if (null==A || 0==A.length) return 0;
		int low = 0;
		int high = A.length-1;
		while(low<=high) {
			int mid = low+(high-low)/2;
			if (k<A[mid]) {
				high = mid-1;
			} else if (k>A[mid]) {
				low=mid+1;
			} else { // find k, first A[mid] is k
				if (mid+1<A.length && k!=A[mid+1]) {
					return mid;
				}
				low=mid+1;
			}
			
		}
		return 0;
	}
	public int getFrequence(int A[], int k) {
		return lastChar(A,k)-firstChar(A,k)+1;
	}
	public static void main(String args[]) {
		int A[] ={1,2,2,3,4,5,5,5,2};
		FindCharFrequence ref = new FindCharFrequence();
		int k=5;
		
		System.out.println("k="+k+",frequence="+ref.getFrequence(A, k));
		
		int B[] = {1,1,1,1};
		
		k=1;
			
		System.out.println("k="+k+",frequence="+ref.getFrequence(A, k));
	}
}
