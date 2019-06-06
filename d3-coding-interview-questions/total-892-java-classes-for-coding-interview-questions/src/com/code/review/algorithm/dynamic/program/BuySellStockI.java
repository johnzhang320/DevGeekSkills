package com.code.review.algorithm.dynamic.program;

public class BuySellStockI {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.

		If you were only permitted to complete at most one transaction (i.e., buy one and sell one
		 share of the stock), design an algorithm to find the maximum profit.
		
		Note that you cannot sell a stock before you buy one.
		
		Example 1:
		
		Input: [7,1,5,3,6,4]
		Output: 5
		Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
		             Not 7-1 = 6, as selling price needs to be larger than buying price.
		Example 2:
		
		Input: [7,6,4,3,1]
		Output: 0
		Explanation: In this case, no transaction is done, i.e. max profit = 0.
	 */
	/**
	 * Buy must happen before sell
	 * Therefore we 
	 * from end-1 to 0 find minimum before this element , different save to maxprofit
	 * 
	 * 
	 * @param A
	 * @return
	 */
	public static int getMaxProfile(int A[]) {
		if (null==A || 0==A.length) return 0;
		int maxProfit=0;
		int buy=0,sell=0;
		for (int i=A.length-1; i>=0;i--) {
		    
			int curr = A[i];
			for (int j=i-1;j>=0;j--) {
				if (curr>A[j]) {
					curr = A[j];
				}
			}
			if (A[i]-curr>maxProfit) {
				buy = curr;
				sell =A[i];
				maxProfit = A[i]-curr;
			}
			 
		}
		System.out.println("maxProfit="+maxProfit+", buy at "+buy +", sell at "+sell);
		return maxProfit;
	}
	/**
	 * min=price[0]
	 * Calculte min after compare result < prices[i]-min , min = min(min, prices[i]), i+1 use i min
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
	    if(prices==null||prices.length<=1)
	        return 0;
	 
	    int min=prices[0]; // min so far
	    int result=0;
	    int buy=0,sell=0;
	    for(int i=1; i<prices.length; i++){
	    	// ensure alway use the elment min before current element as buy price and current price as sell
	    	// min is front mininum value 
	    	// when we calculate a prices[i] , the min over price[0] to price[i-1] already ready for calculated  
	    	if (result<(prices[i]-min)) {
	    		buy=min;
	    		sell = prices[i];
	    		result = prices[i]-min;
	    	}
	      
	        min = Math.min(min, prices[i]);
	    }
	    System.out.println("leet code maxProfit="+result+", buy at "+buy +", sell at "+sell);
	    return result;
	}
	/**
	 * Using dp[0] = prices[0]
	 * i=1 to n
	 * dp[i] present min value before prices[i], dp[i] = min (dp[i-1], prices[i-1]) , using prices[i-1] to find min before prices[i]
	 * result = max(result, prices[i] - dp[i])
	 * 
	 * @param args
	 */
	 public static int maxProfitdp(int[] prices) {
	        if (prices == null || prices.length == 0) {
	            return 0;
	        }
	        int n = prices.length;
	        int[] dp = new int[n + 1]; // min value up to first i items
	        dp[0] =prices[0]; //Integer.MAX_VALUE;
	        int profit = 0;
	        int buy=0, sell=0;
	        for (int i = 1; i < n; i++) {
	        	// dp represent the min before prices[i] because dp[i-1] when i=1 . it is prices[0]
	            dp[i] = Math.min(dp[i - 1], prices[i]);
	            if (profit < prices[i]-dp[i]) {   // find buy and sell
	            	buy = dp[i];
	            	sell= prices[i];
	            	profit = prices[i]-dp[i];
	            }
	            //profit = Math.max(profit, prices[i] - dp[i]);
	        }
	        System.out.println("Dynamic Program maxProfit="+profit+", buy="+buy+", sell="+sell);
	        return profit;
	    }
	public static void main(String[] args) {
		int A[] = {20,3,100,2,96,8,99,11};
		getMaxProfile(A);
		maxProfit(A);
	    maxProfitdp(A);
		int A1[] = {40,42,45,43,66,55,57,69,62,60};
		getMaxProfile(A1);
		maxProfitdp(A1);
		maxProfit(A1);
		int A2[] = {10,12,20,2,4,7,1,2,19,8,2,1};
		getMaxProfile(A2);
		maxProfit(A2);
		maxProfitdp(A2);
	}
}
