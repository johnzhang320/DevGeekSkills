package com.code.review.aaa.career.cup.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class CoinChangeFromVideo {
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
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int coins[] = {1,5,6,8};
		int total = 11;
		
		System.out.println("1 Need coins ="+coinChange(coins, total)); 
		 
	}

}
