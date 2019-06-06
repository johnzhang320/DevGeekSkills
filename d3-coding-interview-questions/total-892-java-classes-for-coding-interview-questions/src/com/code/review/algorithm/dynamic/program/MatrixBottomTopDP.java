package com.code.review.algorithm.dynamic.program;
/**
 * int[][] grid = {
				 {1,1,2,1,2},
				 {4,9,7,2,4},
				 {5,6,3,1,7},
				 {5,6,2,1,3}
			 				
		 };
 *  calculate min path from left top to right bottom
 *  
 *  This is typical tabulation (Bottom to top) dp, that is we build initialize value: dp[0][j] as first row and dp[i][0] as first column
 *  then compute dp[i][j] =min(dp[i-1][j] , dp[i][j-1]) +grid[i][j]
 *  means take less one either from up row element or right column element plus current grid[i][j] 
 *  finally dp[rows-1][cols-1] is target one
 *
 */

public class MatrixBottomTopDP {
	public static int minPathSum(int [][] grid) {
		// build initial value from bottom
		if (null==grid || (grid.length==0 && grid[0].length==0)) {
            return 0;
        }
		int rows = grid.length;
		int cols = grid[0].length;
		int dp[][] = new int[rows+1][cols+1];
		// build initial from bottom
		dp[0][0] = grid[0][0];
		// build boundary values
		for (int i=1;i<rows;i++) {
			dp[i][0] = dp[i-1][0]+grid[i][0];
		}
		for (int j=1; j<cols;j++) {
			dp[0][j] = dp[0][j-1]+grid[0][j];
		}
		
		for (int i=1; i<rows ; i++) {
			for (int j=1;j<cols; j++) {
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])
						   + grid[i][j];
				
			}
		}
		return dp[rows-1][cols-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println("MinPathSum");
		 int[][] grid = {
				 {1,1,2,1,2},
				 {4,9,7,2,4},
				 {5,6,3,1,7},
				 {5,6,2,1,3}
			 				
		 };
		 // suppose 1+1+2+1+2+1+1+3=12
		 int result = minPathSum(grid);
		  
		 System.out.println("Result is "+result);
	}
}
