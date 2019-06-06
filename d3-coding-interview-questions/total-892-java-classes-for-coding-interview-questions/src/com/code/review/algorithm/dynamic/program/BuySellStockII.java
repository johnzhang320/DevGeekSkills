package com.code.review.algorithm.dynamic.program;

import java.util.Arrays;
import java.util.List;

/*
 * 122. Best Time to Buy and Sell Stock II
 
	Share
	Say you have an array for which the ith element is the price of a given stock on day i.
	
	Design an algorithm to find the maximum profit. You may complete as many transactions as you like
    (i.e., buy one and sell one share of the stock multiple times).
	
	Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
	
	Example 1:
	
	Input: [7,1,5,3,6,4]
	Output: 7
	Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
	             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
	Example 2:
	
	Input: [1,2,3,4,5]
	Output: 4
	Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
	             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
	             engaging multiple transactions at the same time. You must sell before buying again.
	Example 3:
	
	Input: [7,6,4,3,1]
	Output: 0
	Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
/*
 *  buy = A[0] 
 *  if {A[i]>A[j-1])  diff = A[i] - buy
 *  else {
 *     sum +diff;
 *     diff = 0;
 *     buy = A[i];
 *   
 */
public class BuySellStockII {
	public static int buySellII(int A[]) {
		if (null==A || 0==A.length)return 0;
		int buy = A[0];
		int sum=0;
		int diff=0;
		for (int i=1;i<A.length;i++) {
			if (A[i]>A[i-1]) {
				diff = A[i] - buy;
			} else {
				sum+=diff;
				diff = 0;
				buy = A[i];
			}
		}
		if (diff>0) sum=diff;
		System.out.println("Profit="+sum);
		return sum;
	}
	
	/*
	 *  Above solution cauld not pass the leetcode test
	 *  But Dynamic Program solution I think out passed
	 *  define dp as different between diff = A[i]-A[i-1] , dp[0] = 0 
	 *  if diff>0 , dp[i] = dp[i-1]+diff (bottom up dp) else dp[i]=dp[i-1] keep original diff
	 *  diff>0, A[]={ 1,2,7,4,5,7} , dp[0]=0, dp[1] = dp[0]+A[1]-A[0] = 1, dp[2] = dp[1]+A[2]-A[1] = 1+5=6 
	 *  dp[3] =dp[2] = 6, dp[4] = dp[3]+A[4]-A[3] =6+ 5-4=7 , dp[5] = dp[4]+A[5]-A[6] = 7+7-5=9
	 *  Below solution is passed leetCode testing!!!
	 */
	public static int buySellIIDP(int A[]) {
		if (null==A || 0==A.length)return 0;
		 int n = A.length;
	        int dp[] = new int[n+1];
	        dp[0] = 0;
	        for (int i=1; i<n; i++) {
	            int diff = A[i] - A[i-1];
	            if (diff>0) {
	                dp[i] = dp[i-1]+diff;
	            } else {
	                dp[i] = dp[i-1];
	            }
	        }
	        System.out.println("Passed leet code test profit="+dp[n-1]);
	        return dp[n-1];
	}
	public static int buySellIIDP2(int A[]) {
		if (null==A || 0==A.length)return 0;
		 int n = A.length;
	        int dp[] = new int[n+1];
	        dp[0] = 0;
	        for (int i=1; i<n; i++) {
	            int diff = A[i] - A[i-1];
	            dp[i] =Math.max(dp[i-1], dp[i-1]+diff);
	           
	        }
	        System.out.println("Passed leet code test profit 2="+dp[n-1]);
	        return dp[n-1];
	}
	public static void main(String[] args) {
		int A[] = {7,1,5,3,6,4};
		buySellII(A);
		buySellIIDP2(A);
		int A2[] = {1,2,3,4,5};
		buySellII(A2);
		buySellIIDP2(A2);
		int A3[] = {7,6,4,3,1};
		buySellII(A3);
		buySellIIDP2(A3);
		int A4[]={ 1,2,7,4,5,7} ;
		buySellIIDP(A4);
		 
		buySellIIDP2(A4);
	
		/*List<Integer> list = Arrays.asList(new Integer[]{1,2,7,4,5,7});
		list.sort((o1,o2)->o2-o1);
		list.forEach(x->System.out.print(x+" "));*/
	}
}
