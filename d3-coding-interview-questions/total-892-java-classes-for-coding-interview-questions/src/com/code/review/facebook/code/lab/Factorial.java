package com.code.review.facebook.code.lab;

public class Factorial {
	/**
	 * Given an integer n, return the number of trailing zeroes in n!.

		Note: Your solution should be in logarithmic time complexity.
		
		Example :
		
		n = 5
		n! = 120 
		Number of trailing zeros = 1
		So, return 1
		Examples:
		Input: n = 5
		Output: 1 
		Factorial of 5 is 20 which has one trailing 0.
		
		Input: n = 20
		Output: 4
		Factorial of 20 is 2432902008176640000 which has
		4 trailing zeroes.
		
		Input: n = 100
		Output: 24

	 *  
	 */
	public int findTrailingZeros(int  n)
	{
	    // Initialize result
	    int count = 0;
	 
	    // Keep dividing n by powers of 5 and update count
	    for (int i=5; n/i>=1; i *= 5)
	          count += n/i;
	 
	    return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
