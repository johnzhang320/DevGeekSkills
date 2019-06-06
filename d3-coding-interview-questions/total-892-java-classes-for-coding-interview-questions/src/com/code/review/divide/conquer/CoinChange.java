package com.code.review.divide.conquer;

public class CoinChange {
	/**
	 *  GeeksforGeeks
	 *  
	 *  Given a value N, if we want to make change for N cents, and we have infinite supply of 
	 *  each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The 
	 *  order of coins doesn’t matter.

		For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
		So output should be 4. For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, 
		{2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.

	 *  
	 */
	/**
	 * 
	 * 2) Overlapping Subproblems
		  Following is a simple recursive implementation of the Coin Change problem. The implementation 
		  simply follows the recursive structure mentioned above.
	 */
	// Returns the count of ways we can sum  S[0...m-1] coins to get sum n
	// recursively reduce the N to 0 via try S[m]
	static int countCoins(int A[],int m, int N) {
		if (N==0) {
			return 1;
		}
		if (N<0) {
			return 0;
		}
		if (m<=0 && N>=1) {
			return 0;
		}
		return countCoins(A,m-1,N) + countCoins(A,m,N-A[m-1]);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] ={1,2,3};
		System.out.println(countCoins(A,A.length,4));
	}

}
