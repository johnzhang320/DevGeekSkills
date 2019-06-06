package com.code.review.middle.matrix;

public class uniquePathsII {
	  /**
     *  dp[][] from first row and first col, if grid[i][j]==0 , dp[i][j]=0 else dp[i][j] = dp[i][j-1] or dp[i-1][i]
        if grid[0][0] ==1 and grid[m-1][n-1]==1 return 0
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        if (null==grid || grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0]==1 || grid[m-1][n-1]==1) return 0;
        
        int dp[][] = new int [m][n];
        dp[0][0]=1; // initialize blocked
        for (int i=1;i<m;i++) {
            if (grid[i][0]==1) {
               dp[i][0] =0; 
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int j=1;j<n;j++) {
            if (grid[0][j]==1) {
               dp[0][j] =0; 
            } else {
                dp[0][j] = dp[0][j-1];
            }
        }  
        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                if (grid[i][j]==1) {
                   dp[i][j] =0; 
                } else {
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }  
            }
        }
        return dp[m-1][n-1];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
