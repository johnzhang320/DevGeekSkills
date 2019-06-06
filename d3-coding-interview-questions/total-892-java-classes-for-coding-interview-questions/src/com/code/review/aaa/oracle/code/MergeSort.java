package com.code.review.aaa.oracle.code;

import com.code.review.array.string.middle.sort.MyMergeSort;

public class MergeSort {
	/**
	 *  (1) define class member variable array[] which is map of A[]
	 *      define tempArray [] = new int[A.length]
	 *      define len =A.length
	 *  (2) MergeSort(int A[])
	 *      DividedConquer(int low, int high)
	 *         recursively 
	 *         check low<high
	 *         DividedConquer(low,mid)
	 *         DividedConquer(mid+1, high)
	 *         DoMerge(low,mid,high)
	 *  (3) DoMerge(low,mid,high) 
	 *      1> i =low to high , tempArray[i] = array[i]
	 *      2> compare element at left side of mid and right side of mid, put smaller one to array[k++]          
	 *              
	 * 
	 */
	// call mSort(int A[]) initialize below variable
	private int tempArray[];
	private int array[];
	private int len;
	public void MergeSort(int A[]) {
		len = A.length;
		array = A;
		tempArray = new int[len];
		DividedConquer(0,len-1);
	}
	private void DividedConquer(int low,int high) {
		if (low<high) {
			int mid = low + (high-low)/2;
			DividedConquer(low,mid);
			DividedConquer(mid+1,high);
			DoMerge(low,mid,high);
		}
		
	}
	private void DoMerge(int low, int mid, int high) {
		// initialize from low to high to tempArray
		for (int i=low;i<=high;i++) {
			tempArray[i] = array[i];
		}
		int k=low;
		int i = low;
		int j = mid+1;
		
		while (i<=mid && j<=high) {
			if (tempArray[i]<=tempArray[j]) {
				array[k] = tempArray[i];
				i++;
			} else {
				array[k] = tempArray[j];
				j++;
			}
			k++;
		}
		while (i<=mid) {
			array[k] = tempArray[i];
			i++;
			k++;
		}
	}
	public static void main(String a[]){
		
		int[] inputArr = {45,23,11,89,77,98,4,28,65,43};
		MergeSort mms = new MergeSort();
		mms.MergeSort(inputArr);
		for(int i:inputArr){
			System.out.print(i);
			System.out.print(" ");
		}
	}

}
