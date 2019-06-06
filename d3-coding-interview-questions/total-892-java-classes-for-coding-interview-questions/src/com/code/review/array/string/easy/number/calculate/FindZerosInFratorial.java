package com.code.review.array.string.easy.number.calculate;

public class FindZerosInFratorial {
	/**
	 * Given an integer n, return the number of trailing zeroes in n!.

		Note: Your solution should be in logarithmic time complexity.
		
		Example :
		
		n = 5
		n! = 120 
		Number of trailing zeros = 1
		So, return 1


	 * 
	 */
	public static int fractorial(int n) {
		if (n==0) return 1;
		int d = fractorial(n-1);
		return n*d;
		
	}
	public static int findZeros(int n) {
		int sum=0;
		while (n>0) {
			int mod = n%10;
			if (mod==0) {
				sum ++;
			}
			n/=10;
		}
		return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = fractorial(15);
		System.out.println(result);
		System.out.println(findZeros(result));
	}

}
