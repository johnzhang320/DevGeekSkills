package com.code.review.array.string.easy.sum;

public class ContiguousElemsIsSum {
/**
 * FaceBook interview question from careerCup.com
 * Given an array of positive integer and a target of X , find if there exists a contiguouts subarray with
 * sum = X
 * 
 * 
 * This is typical brutal force two loop loop we can solve this problem
 * 
 * @param args
 */
	public static boolean contiguousElemsIsSum(int arr[], int target) {
		boolean retVal=false;
		for (int i=0; i<arr.length;i++) {
			int trySum = 0;
			for (int j=i; j< arr.length;j++) {
				trySum += arr[j];
				if (trySum==target) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {3,1,5,16,13};
		int x = 8;
		System.out.println("x="+x+", result:"+contiguousElemsIsSum(arr, x));
		x=9;
		System.out.println("x="+x+", result:"+contiguousElemsIsSum(arr, x));
		x=29;
		System.out.println("x="+x+", result:"+contiguousElemsIsSum(arr, x));
		x=21;
		System.out.println("x="+x+", result:"+contiguousElemsIsSum(arr, x));
		
		x=211;
		System.out.println("x="+x+", result:"+contiguousElemsIsSum(arr, x));
	}

}
