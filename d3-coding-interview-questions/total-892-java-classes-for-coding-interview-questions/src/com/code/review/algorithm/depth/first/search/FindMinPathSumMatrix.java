package com.code.review.algorithm.depth.first.search;

public class FindMinPathSumMatrix {
	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all
	 *  numbers along its path.

		
	 *  
	 */
	/**
	 * A native solution would be depth-first search. It's time is too expensive and fails the online judgement.
	 * if i and j both in boundary at same time
	 * i+1 or j+1 dfs plus current grid[i][j]
	 * return r1 an r2 min one
	 *  
	 * @param args
	 */
	public int minPathSum(int[][] grid) {
		return dfs(0,0,grid);
	}
	public int dfs(int row, int col, int[][] grid) {
		int rows = grid.length-1;
		int cols = grid[0].length-1;
		if (row==rows && col == cols) return grid[row][col];
		if (col<cols && rows<rows) {
			// row + 1
			int r1 = grid[row][col]+dfs(row+1,col,grid);
			int r2 = grid[row][col]+dfs(row,col+1,grid);
			return Math.min(r1, r2);
		}
		if (row<rows) {
			return grid[row][col]+dfs(row+1,col,grid);
		}
		if (col<cols) {
			return grid[row][col]+dfs(row,col+1,grid);
		}
		return 0;
	}
	/**
	 *  Dynamic Programming
	 *  step 1 initialize dp[0][0] is top left grid
	 *  dp[0][0] = grid[0][0]
	 *  step 2 dp row boundary and col boundary
	 *  dp[0][j] = dp[0][j-1]+grid[0][j]
	 *  dp[i][0] = dp[i][0]+grid[0][j]
	 *  step 3 with loop i=1 to rows and j=1 to cols, check dp[i][j] 's up dp and left dp (up dp is dp[i-1][j] , left dp is dp[i][j-1]) ,
	 *  pick up smaller one dp plus grid[i][j] 
	 *  step 4 return dp[i-1][j-1]
	 *  Note dp is o to grid.length, one more than grid
	 */
	 public int minSumPathDP(int grid[][]) {
		 int rows = grid.length;
		 int cols = grid[0].length;
		 int dp[][] = new int[rows][cols];
		 //step 1
		 dp[0][0] = grid[0][0];
		 //step 2 
		 for (int i=1;i<rows;i++) dp[i][0]=dp[i-1][0]+grid[i][0];
		 for (int j=1;j<rows;j++) dp[0][j]=dp[0][j-1]+grid[0][j];
		 
		 // step 3
		 for (int i=1;i<rows;i++) {
			 for (int j=1; j<cols;j++) {
				 if (dp[i-1][j]<dp[i][j-1]) {  // pick up smaller one
					 dp[i][j] = dp[i-1][j]+grid[i][j];
				 } else {
					 dp[i][j] = dp[i][j-1]+grid[i][j];
				 }
			 }
		 }
		return dp[rows-1][cols-1];	
		 
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
