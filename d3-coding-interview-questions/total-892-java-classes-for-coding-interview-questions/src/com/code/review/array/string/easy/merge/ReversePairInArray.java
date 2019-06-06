package com.code.review.array.string.easy.merge;
 
public  class ReversePairInArray {
	/**
	 * If an element at left side is greater than another element at right side, they form a reversed pair
	 * in an array. How do you get a count of reversed pairs
	 * e.g in Array {7,5,6,4}, which are {7,5} , 7,6,{7,4},{5,4} and {6,4}
	 * Solution 1 brutal force: two loop can each element from reminding others O(n^2)
	 * @param args
	 */
	// O(n^2)
	public static int ReversePairInArray(int arr[]) {
		int count=0;
		if (null==arr || arr.length<=1) {
			return 0;
		}
		for (int i=0; i<arr.length;i++) {
			for (int j=i+1; j<arr.length; j++) {
				if (arr[i]>arr[j]) {
					count++;
				}
			}
		}
		return count;
	}
	
	// O(nLogn)
	/**
	 *  Apply Merge Sort. divide and conquer, after divided by single element arrays, we count 
	 *  reverse pair which elements at left side in sub arrary is greater than element at right
	 *  side while we merge sub array to sorted array and merge between sub arrary  
	 *  
	 */
	public int countReversePairs(int arr[]) {
		 
		return countReversePairs(arr, 0, arr.length-1);
	}
	public int countReversePairs(int arr[], int low, int high) {
		if (low>= high) {
			return 0;
		}
		int mid = low + (high-low)/2;
		int left = countReversePairs(arr,low,mid);   // left side element
		int right = countReversePairs(arr,mid+1,high); // right side element
		int between = merge(arr,low, high,mid);
		return left+right+between;
	}
	public int merge(int arr[], int low , int high, int mid) {
		int buffer[] = new int[arr.length];
		int i= mid;   // merge from last position of left side
		int j = high; // merge from last position of right side 
		int k = high;
		int count = 0;
		while (i>=low && j>=mid+1) {
			if (arr[i]>arr[j]) { // left side element > right side element
				buffer[k--] = arr[i--]; // place larger one to merged array, 
				count+= (j-mid);              // left side < right side, reversed pair
			} else {
				buffer[k--] = arr[j--];
			}
		}
		while (i>=low) buffer[k--] = arr[i--];
		while (j>=mid+1) buffer[k--] = arr[j--];
		
		// copy buffer to arr
		for (i=low ; i <=high; i++ ) {
			arr[i] = buffer[i];
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {7,5,6,4};
		System.out.println("----------Brutal Force----------");
		System.out.println(ReversePairInArray(arr));
		System.out.println("----------Divide & Conquer-------");
		ReversePairInArray ref = new ReversePairInArray();
		System.out.println(ref.countReversePairs(arr));
	}

}
