package com.code.review.array.string.easy.largest;

public class FindSecondLargestFromArrary {
	/**
	 * The array is not sorted and find second largest element in O(n)  
	 * (1) 1 to n loop
	 * (2) if max<A[i] , save max to second-max and save A[i];
	 * (3) if n<=1, second-Max = Integer.Min_Value
	 * (4) if n=2, second-max = min(A[0],A[1])
	 * (5) if n>2, apple (2) algorithm  
	 */
	public static int findSecondLargest (int A[]) {
		if (null==A || A.length==0 || A.length == 1) return Integer.MIN_VALUE;
		if (A.length == 2) return Math.min(A[0], A[1]);
		int max = Integer.MIN_VALUE;
		int max2nd=Integer.MIN_VALUE;
		for (int i=0; i<A.length;i++) {
			if (A[i]>max) {
				max2nd = max;
				max = A[i];
			}
		}
		return max2nd;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A1[] = {2,4,6,1,-7,10,9,12,5,3};
		System.out.println(findSecondLargest(A1));
		int A2[] = {-2,-3};
		System.out.println(findSecondLargest(A2));
		
		int A3[] = {1};
		System.out.println(findSecondLargest(A3));
	}

}
