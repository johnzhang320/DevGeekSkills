package com.code.review.algorithm.dynamic.program;

import java.util.ArrayList;
import java.util.Arrays;

public class CoinChange {
	/**
	 *  Coins = 1,5,6,8 cents
	 *  we want to find total 11 cents
	 *  based on my note from video from Tushur Roy
	 *  (1) we create coin[] = {1,5,6,8}
	 *  (2) we create dp[][] = int[4][12] dp[0][j] = 0 to 11, dp[i][0]=0 
	 *  (3) if j>=coins[i] then take minimum of either above line element or back to coins[i] plus one
	 *  (4) else j<coins[i] then dp[i][j] = above element of dp
	 *  (5) i =1 ; i<=rows and j=1 ; j<=cols
	 *  if (j>=coins[i-1]) 
	 *      dp[i][j] = min (dp[i-1][j], 1+dp[i][j-coins[i-1]])
	 *  else 
	 *     dp[i][j] = dp[i-1][j]    
	 */
	public static int coinChange(int coins[], int total) {
		
		int rows = coins.length;
		int cols = total;
		int dp[][] = new int[rows+1][cols+1];
		for (int i=0;i<=rows;i++) {
			dp[i][0]=0;
		}
		for (int j=0;j<=cols;j++) {
			dp[0][j]=j;
		}
		for (int i=1;i<=rows;i++) {
			for (int j=1;j<=cols;j++) {
				if (j>=coins[i-1]) {
					dp[i][j] = Math.min (dp[i-1][j], 1+dp[i][j-coins[i-1]]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		for (int i=0;i<=rows;i++) {
			
			for (int j=0;j<=cols;j++) {
				 
			   System.out.print(dp[i][j]+" ");
				 
			}
			System.out.println(" ");
		}
		return dp[rows][cols];
	}
	/*
	 *  for example f(11) can be f(11-1)+1 one cents coin, f(11-5)+1 five cent coin, f(11-6) + 1 six cent coin and fz(11-8)+ 1 eight cent coin 
	 *  find all min
	 *  so change 11 to x , we have formula f(x) = min (f(x-1), f(x-5), f(x-6), f(x-8))+1
	 *  coin[] = { 1,5,6,8 }
	 *  f[0] =1, f[1]=f[0]+1,f[2]=f[1]+1,f[3]=f[2]+1,f[4]=f[3]+1,f(5)=1,f(6)=1,f(7)=f(6)+1,f(8) =1
	 */
	public static int findCoin(int [] coin, int value) {
		for (Integer i : coin) {
			if (i==value) return i;
		}
		return -1;
	}
	 
	public static int findCoinNumber(int coin[], int k) {
		if (null==coin || coin.length==0 || k==0) return 0;
		int n = coin.length;
		int dp[] = new int [k+1];
		for (int i=0; i<k;i++) dp[i]=-1;
		dp[0] = 0;
		Arrays.sort(coin);
	  if (n==1 && k!=coin[0] && coin[0]!=1 ) {
            if (k>coin[0] && k%coin[0]!=0) {
                return -1;
            }
            if (k<coin[0]) return -1;
        }
		if (n==1 && k!=coin[0]) return -1; 
		for (int i=1; i<=coin[n-1];i++) {
			if (findCoin(coin,i)==-1) {
				dp[i]=dp[i-1]+coin[0];
			} else {
				dp[i]=1;
			}
			if (i==k ) {
				
				return dp[i];
			}
		}
		for (int i=coin[n-1]+1; i<=k; i++) {
			dp[i] = dp[i-coin[0]]+1;
			for (int j=1; j<coin.length;j++) {
				dp[i] =Math.min(dp[i], dp[i-coin[j]]+1);
			}
			 
		}
		
		return dp[k];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int coins[] = {1,5,6,8};
		int total = 11;
		
		System.out.println("1 Need coins ="+coinChange(coins, total)); 
		
		System.out.println("Find coin number ="+findCoinNumber(coins, 11)); 
		
		System.out.println("Find coin number ="+findCoinNumber(coins, 15)); 
		
		System.out.println("Find coin number ="+findCoinNumber(coins, 16)); 
		
		System.out.println("Find coin number ="+findCoinNumber(coins, 19)); 
		
		int [] coin = {2};
		System.out.println("Find coin number ="+findCoinNumber(coin, 3)); 
		int [] coin4 = {3};
		System.out.println("Find coin number ="+findCoinNumber(coin4, 1)); 

		int [] coin2 = {186,419,83,408};
		System.out.println("Find coin number ="+findCoinNumber(coin2, 6249)); 
		
		int [] coin3 = {216,94,15,86};
		
		System.out.println("Find coin number ="+findCoinNumber(coin3, 5372)); 
		
	}
	// lintCode standard answer
	/*
	Thoughts:
	Last step: which coin can make amount? enumerate it
	dp[i]: min # of coins to make i amount.
	dp[i] = Math.min(dp[i - coinValueA], dp[i - coinValueB], dp[i - coinValueC], ...) + 1 
	init: dp[0] = 0. No value, no coins. 
	If dp[i] not found, set it to -1
	passed Leet code test
	*/
	class Solution {
	    public int coinChange(int[] coins, int amount) {
	        if (coins == null || coins.length == 0) {
	            return -1;
	        }
	        int[] dp = new int[amount + 1];
	        dp[0] = 0;
	        for (int i = 1; i <= amount; i++) {
	            dp[i] = Integer.MAX_VALUE;
	            for (int coin : coins) {
	                if (i >= coin && dp[i - coin] != -1 && coin != 0) {
	                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
	                }
	            }
	            if (dp[i] == Integer.MAX_VALUE) {
	                dp[i] = -1;
	            }
	        }
	        return dp[amount];
	    }
	}
}
