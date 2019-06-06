package com.code.review.matrix.middle.dynamic.program;

import java.util.Arrays;

public class BestTimeBuySellStock {
	/**
	 * 
	 * @param args LeetCode – Best Time to Buy and Sell Stock (Java)
 

		Say you have an array for which the ith element is the price of a given stock on day i.
		
		If you were only permitted to complete at most one transaction (ie, buy one and sell 
		one share of the stock), design an algorithm to find the maximum profit.
		
		Java Solution
		
		Instead of keeping track of largest element in the array, we track the maximum profit so far.
	 */
	public static int maxProfit(int[] prices) {
	    if(prices==null||prices.length<=1)
	        return 0;
	 
	    int min=prices[0]; // min so far
	    int result=0;
	    Arrays.sort(prices);
	    for(int i=1; i<prices.length; i++){
	    	min = Math.min(min, prices[i]);
	        result = Math.max(result, prices[i]-min);
	        
	    }
	    System.out.println("buy min="+min);
	    return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int price[] = {10,14,17,12,9,7,8,8,18,6};
		int profit = maxProfit(price);
		System.out.println(profit);
	}

}
