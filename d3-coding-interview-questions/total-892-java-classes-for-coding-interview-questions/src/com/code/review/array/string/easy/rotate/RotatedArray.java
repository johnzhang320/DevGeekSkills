package com.code.review.array.string.easy.rotate;

public class RotatedArray {
	 /**
	  *  Rotation of Array
	  *  Rotate an array of n elements to the right by k steps.

		For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
		How many different ways do you know to solve this problem?
		
		Solution 1 - Intermediate Array
		
		In a straightforward way, we can create a new array and then copy elements to the new array. 
		Then change the original array by using System.arraycopy().
	  */
	 public static int [] rotationArray(int arr[], int k) {
		 int result[] = new int[arr.length];
		 int len = arr.length;
		 if (k>len) k = k%len;
		 for (int i=0;i<k;i++) {
			 result[i] = arr[len-k+i];
		 }
		 int j=0;
		 for (int i=k;i<len;i++) {
			 result[i] = arr[j++];
		 }
		 
		 return result;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {1,2,3,4,5,6,7};
		int result[] = rotationArray(A, 3);
		for (Integer i:result)
		System.out.print(i+" ");
	}

}
