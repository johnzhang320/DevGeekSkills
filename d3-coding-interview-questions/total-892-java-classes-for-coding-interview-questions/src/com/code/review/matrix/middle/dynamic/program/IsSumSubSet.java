package com.code.review.matrix.middle.dynamic.program;

public class IsSumSubSet {
	/**
	 * 
		Dynamic Programming | Set 25 (Subset Sum Problem)
		
		Given a set of non-negative integers, and a value sum, determine if there is a subset of the 
		given set with sum equal to given sum.
		
		Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
		Output:  True  //There is a subset (4, 5) with sum 9.

	 *  We can solve the problem in Pseudo-polynomial time using Dynamic programming. We create a 
	 *  boolean 2D table subset[][] and fill it in bottom up manner. The value of subset[i][j] 
	 *  will be true if there is a subset of set[0..j-1] with sum equal to i., otherwise false.
	 *  Finally, we return subset[sum][n]
	 *  
	 *  formula
	 *  (1) dp[][] = new boolean[sum+1][n+1]   // sum is target sum and n is size of array
	 *  (2) dp[i][0]=false // means if sum is not 0 and set is empty (n=0) , then answer is false
	 *  (3) dp[0][n]=true  // means if sum is 0 and set is not empty then answer is true
	 *  (4) in i to sum and j to n loop 
	 *      if i>=set[j-1]  // sum> element of array
	 *          dp[i][j] =dp[i][j] || dp[i-set[j-1]][j-1]]
	 *      else     
	 *          dp[i][j] = dp[i][j-1]
	 *  (5) return dp[sum][n]       
	 */
	public static boolean isSubSetSum(int set[], int sum) {
		if (null==set || 0==set.length) return false;
		int n=set.length;
				
		boolean dp[][] = new boolean[sum+1][n+1];
		for (int j=0;j<=n;j++) {
			dp[0][j]=true;
		}
		for (int i=1;i<=sum;i++) {
			dp[i][0]=false;
		}
		for (int i=1;i<=sum;i++) {
			for (int j=1;j<=n;j++) {
				dp[i][j] = dp[i][j-1];
				if (i>=set[j-1])  
					dp[i][j] = dp[i][j] || dp[i-set[j-1]][j-1];
				 
			}
		}
		System.out.println("sum="+sum+", is Sub Set ? "+ dp[sum][n]);
		return dp[sum][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int set[] = {3, 34, 4, 12, 5, 2}, sum = 9;
		 isSubSetSum(set, sum); 
		 sum = 11;
		 isSubSetSum(set, sum); 
		 
		 sum = 110;
		 isSubSetSum(set, sum); 
	}
	

}
