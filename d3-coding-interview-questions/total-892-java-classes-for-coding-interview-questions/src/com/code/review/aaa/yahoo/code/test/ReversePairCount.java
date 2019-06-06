package com.code.review.aaa.yahoo.code.test;

public class ReversePairCount {
	/**
	 * Q2:
		For an array A, if i < j, and A[i] > A[j], then (A[i], A[j]) is called a reverse pair. 
		Given an array A, find the count of reverse pairs in A.
		Example:
		Given A = {10, 12, 8, 11, 14}, (10, 8), (12, 8), (12, 11) are reverse pairs. Return 3.

	 *  My Solution 1, Naive Solution:
	 *  Using two loop O(N^2)
	 */
	 public static int fetchPair(int A[]) {
	      if (null==A || 0==A.length) return 0;
	      int count =0;
	      for (int i=0; i<A.length; i++) {
	          int curr = A[i];
	          for (int j = i+1; j<A.length; j++) {
	              if (curr > A[j]) {
	                  count++;
	              }
	          }
	      }
	      return count;
	}
	/**
	 *  Using Merge Sort, when we do merge, we need compare A[low]<=A[high] else A[low]>A[high]
	 *  then we count A[low]>A[high] 
	 * 
	 */
	 public int reversePair(int A[]) {
		 return mergeSort(A, 0, A.length-1);
	 }
	 private int mergeSort(int A[], int low, int high) {
		 if (low>=high) return 0;
		 int mid = low+(high-low)/2;
		 int sum=0;
		 sum += mergeSort(A, low, mid);
		 sum += mergeSort(A,mid+1, high);
		 sum +=merge(A, low, mid, high);
			 
		 return sum;
	 }
	 private int merge(int A[], int low, int mid, int high) {
		 int temp[] = new int[A.length];
		 int left = low;
		 int right = mid+1;
		 int index = low;
		 int sum = 0;
		 while (left<=mid && right<=high) {
			 if (A[left] <= A[right]) {
				 temp[index++] = A[left++];
			 } else {
				 temp[index++] = A[right++];
				 sum +=mid-left+1;
			 }
		 }
		 while (left<=mid) {
			 temp[index++]=A[left++];
		 }
		 while (right<=high) {
			 temp[index++]=A[right++];
		 }
		 for (int i = low; i<=high;i++) {
			 A[i] = temp[i];
		 }
		 return sum;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {10, 12, 8, 11, 14};
		System.out.println(fetchPair(A));
		ReversePairCount ref = new ReversePairCount();
		System.out.println(ref.reversePair(A));
	}

}
