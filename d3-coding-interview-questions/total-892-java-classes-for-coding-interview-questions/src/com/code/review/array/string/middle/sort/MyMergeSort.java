package com.code.review.array.string.middle.sort;

public class MyMergeSort {
 
	/**
	 * Merge Sort algorithm
	 * (1) in sort main function, within low<high recursion sort(low,mid) and sort(mid,high), the call doSort(low,mid,high)
	 * (2) in doSort, i=low to high, tempMergArr[i] = A[i], while left<mid && right<high then swap left side of mid with right side 
	 */
	private int[] array;
	private int[] tempMergArr;
	private int length;
	
	public static void main(String a[]){
	
		int[] inputArr = {45,23,11,89,77,98,4,28,65,43};
		MyMergeSort mms = new MyMergeSort();
		mms.sort(inputArr);
		for(int i:inputArr){
			System.out.print(i);
			System.out.print(" ");
		}
	}
	
	public void sort(int inputArr[]) {
		this.array = inputArr;
		this.length = inputArr.length;
		this.tempMergArr = new int[length];
		doMergeSort(0, length - 1);
	}
	
	private void doMergeSort(int lowerIndex, int higherIndex) {
	
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			// Below step sorts the left side of the array
			doMergeSort(lowerIndex, middle);
			// Below step sorts the right side of the array
			doMergeSort(middle + 1, higherIndex);
			// Now merge both sides
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}
	
	private void mergeParts(int lowerIndex, int middle, int higherIndex) {
	
		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempMergArr[i] = array[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (tempMergArr[i] <= tempMergArr[j]) {
				array[k] = tempMergArr[i];
				i++;
			} else {
				array[k] = tempMergArr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) { // copy rest data to array
			array[k] = tempMergArr[i];
			k++;
			i++;
		}
	
	}
	
	
}
		 
