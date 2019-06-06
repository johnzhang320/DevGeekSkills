package com.code.review.algorithm.dynamic.program;

public class DPBasicStudy {
/**
 *  Top to Bottom , Memoirization
 *  create a array to store middle sub problem and avoid calculate sub problem again
 *  This method need a lot of memory and slower than bottom to top, always use recursive calling
 */
	long lookup[] = new long[800];
	public long fib(int n) {
		if (lookup[n]==0) {  // not computer yet
			if (n<=1) {
				lookup[n]=n;
			} else {
				lookup[n] = fib(n-2)+fib(n-1);
			}
		}
		return lookup[n];
	}
	
	/**
	 *  Tabulation (Bottom Up): The tabulated program for a given problem builds a table in bottom 
	 *  up fashion and returns the last entry from table. For example, for the same Fibonacci number, 
	 *  we first calculate fib(0) then fib(1) then fib(2) then fib(3) and so on.
	 *  This is way most dp algorithm applied , always using iteration way
	 *  (1) build initialized value and compute further item after initialize value until target time n
	 */
	public long fib2(int n) {
		long f[] = new long [n+1];
		f[0] = 0;     // build initialize value for table from bottom
		f[1] = 1;     
		for (int i=2; i <=n; i++)  { // compute further item after initialize value until target time n
			f[i] =f[i-2]+f[i-1];
		}
		return f[n];
		
	}
	/**
	 *  Find max value from continuous subarray 
	 *  typical bottom top algorithm again
	 *  initial value dp[n] , 
	 *  dp[0] = A[0]
	 *  // compute further items
	 *  dp[i] = MATH.max(A[i], dp[i-1] + A[i])
	 *  // store current max dp[i] to max variable
	 *  max  = MATH.max(max,dp[i]);
	 * @param args
	 */
	public int getMaxSum(int A[]) {
		if (null==A || A.length==0) return 0;
		int n = A.length;
		int dp[] = new int[n+1];
		int max = Integer.MIN_VALUE;
		// initialize bottom of table
		dp[0] = A[0];
		for (int i=1;i<n;i++) {
			dp[i] = Math.max(A[i], A[i]+dp[i-1]);
			max = Math.max(max,dp[i]);
		}
		return max;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DPBasicStudy ref = new DPBasicStudy();
	   long ts = System.currentTimeMillis();
	   System.out.println(	ref.fib(168));
	   System.out.println("Momentory(Up To Bottom) take ms:"+(System.currentTimeMillis()-ts));
	   ts = System.currentTimeMillis();
	   System.out.println(	ref.fib2(168));
	   System.out.println("Tabulation (Bottom to up) take ms:"+(System.currentTimeMillis()-ts));
	   
	   int A1[] = {20, -19, 6, 9, 4};
		int A2[] = {10, -3, 4, -2, -1, 10};
		int A3[] = {20, -25, 6, 9, 8};
		int A4[] = {-2, -5, 6, -2, -3, 1, 5, -6};
		int A5[] = {-2, -3, 4, -1, -2, 1, 5, -13, 23};
		System.out.println(ref.getMaxSum(A1));
		System.out.println(ref.getMaxSum(A2));
		System.out.println(ref.getMaxSum(A3));
		System.out.println(ref.getMaxSum(A4));
		System.out.println(ref.getMaxSum(A5));
	}
}
