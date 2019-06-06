package com.code.review.algorithm.dynamic.program;

public class MinimumCostPath {
	/**
	 * 
	 * Given a cost matrix cost[][] and a position (m, n) in cost[][], write a function that returns cost 
	 * of minimum cost path to reach (m, n) from (0, 0). Each cell of the matrix represents a cost to 
	 * traverse through that cell. Total cost of a path to reach (m, n) is sum of all the costs on that
	 * path (including both source and destination). You can only traverse down, right and diagonally 
	 * lower cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), (i, j+1) and 
	 * (i+1, j+1) can be traversed
	 */
	/*
	 *  If initialize row [0] and col[0] with grip for dp, next dp means minimum dp[i-1][j-1], dp[i-1][j], dp[i][j-1] plus grip[i][j]
	 *  Formula 
	 *  Initialize dp[i][j] using original matrix grip[i][j]
	 *  (1) dp[0][j] = grid[0][j]
	 *  (2) dp[i][0] = grid[i][0]
	 *  (3) dp[i][j] = min( dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + grip[i][j]
	 */
	 public static int minPathSum(int[][] grid) {
	    if(grid == null || grid.length==0)
	        return 0;
	
	    int m = grid.length;
	    int n = grid[0].length;
	
	    int[][] dp = new int[m][n];
	    dp[0][0] = grid[0][0];    
	
	   // initialize top row
	    for(int i=1; i<n; i++){
	        dp[0][i] = dp[0][i-1] + grid[0][i];
	    }
	
	   // initialize left column
	    for(int j=1; j<m; j++){
	        dp[j][0] = dp[j-1][0] + grid[j][0];
	    }
	    
	    // fill up the dp table
	    for(int i=1; i<m; i++){
	        for(int j=1; j<n; j++){
	        	
	            if(dp[i-1][j] > dp[i][j-1]){
	                dp[i][j] = dp[i][j-1] + grid[i][j];
	            }else{
	                dp[i][j] = dp[i-1][j] + grid[i][j];
	            }
	            
	        }
	        
	        
	    }
	
	    return dp[m-1][n-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
