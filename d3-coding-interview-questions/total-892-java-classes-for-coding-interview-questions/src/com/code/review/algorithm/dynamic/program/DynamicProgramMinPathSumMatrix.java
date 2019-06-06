package com.code.review.algorithm.dynamic.program;

public class DynamicProgramMinPathSumMatrix {
	/**
	  *   
		Given a m x n grid filled with non-negative numbers, find a path from top 
		left to bottom right which minimizes the sum of all numbers along its path.
	  */
	 public static int minPathSum(int[][] grid) {
		 
		        if (null==grid || (grid.length==0 && grid[0].length==0)) {
		            return 0;
		        }
		        int rows = grid.length;
		        int cols = grid[0].length;
		        int dp[][] = new int[rows+1][cols+1];
		        dp[0][0] = grid[0][0];
		        for (int i=1;i<rows;i++) {  // first column initialize
		            dp[i][0] = dp[i-1][0]+grid[i][0];
		        }
		        for (int j=1;j<cols;j++) {  // first row initialize
		            dp[0][j] = dp[0][j-1]+grid[0][j];
		        }
		        
		        for (int i=1;i<rows;i++) {
		            for (int j=1; j<cols;j++) {
		                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1]) +grid[i][j];
		                 
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
		 
		int grid2 [][]= {
		  {20, 29, 84, 4, 32, 60, 86, 8, 7, 37},
		  {77, 69, 85, 83, 81, 78, 22, 45, 43, 63},
		  {60, 21, 0, 94, 59, 88, 9, 54, 30, 80},
		  {40, 78, 52, 58, 26, 84, 47, 0, 24, 60},
		  {40, 17, 69, 5, 38, 5, 75, 59, 35, 26},
		  {64, 41, 85, 22, 44, 25, 3, 63, 33, 13},
		  {2, 21, 39, 51, 75, 70, 76, 57, 56, 22},
		  {31, 45, 47, 100, 65, 10, 94, 96, 81, 14}
		};
		 result = minPathSum(grid2);
		  
		 System.out.println("Result is "+result);
		 
	}

}
