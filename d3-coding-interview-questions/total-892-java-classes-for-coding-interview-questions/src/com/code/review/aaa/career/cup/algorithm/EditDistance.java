package com.code.review.aaa.career.cup.algorithm;

public class EditDistance {
	/**
	 * Input:   str1 = "geek", str2 = "gesek"
		Output:  1
		We can convert str1 into str2 by inserting a 's'.
		
		Input:   str1 = "cat", str2 = "cut"
		Output:  1
		We can convert str1 into str2 by replacing 'a' with 'u'.
		
		Input:   str1 = "sunday", str2 = "saturday"
		Output:  3
		Last three and first characters are same.  We basically
		need to convert "un" to "atur".  This can be done using
		below three operations. 
		Replace 'n' with 'r', insert t, insert a
		
		Edit Distance
		Dynamic Programming formula
		dp[0][j] = j
		dp[i][0] = i;
		i = 1 to m
		j = 1 to n
		if str1[i] = str2[j]
		   dp[i][j] = dp[i-1][j-1]   // diagonal value
		else 
		   dp[i][j] = min (dp[i][j-1],  // insert
		                   dp[i-1][i],   // delete 
		                   dp[i-1][[j-1])+1 // replace 
		
	 *  Implementation
	 *  (1) implement min function compare x, y, z to get min
	 *  (2) implement formula using recusive way  
	 */
	public int min(int x,int y, int z) {
		int min = Math.min(x, y);
		return Math.min(min, z);
	}
	// recursive way
	public int editDistance(String str1, String str2,int i, int j) {
		if (i==0) return j;
		if (j==0) return i;
		// if str1 == str2 // If last characters of two strings are same, nothing
	    // much to do. Ignore last characters and get count for
	    // remaining strings.
		if (str1.charAt(i-1) == str2.charAt(j-1)) {
			return editDistance(str1,str2,i-1,j-1);
		}
		// If last characters are not same, consider all three
	    // operations on last character of first string, recursively
	    // compute minimum cost for all three operations and take
	    // minimum of three values.
		return 1 + min(editDistance(str1,str2,i,j-1), 
				       editDistance(str1,str2,i-1,j),
				       editDistance(str1,str2,i-1,j-1)
				   );
	}
	 
	// Iterative way
		public int editDistanceIterative(String word1, String word2) {
			if (word1==null || word1.length()==0 || word2==null || word2.length()==0) return 0;
			int m = word1.length();
			int n = word2.length();
			int dp[][] = new int[m+1][n+1];
			// row[0] initilize
			for (int j=0;j<=n; j++) {
				dp[0][j]=j;
			}
			// col[0] initialize
			for (int i=0;i<=m;i++) {
				dp[i][0] = i;
			}
			for (int i=0;i<m; i++) {
				for (int j=0;j<n; j++) {
					if (word1.charAt(i) == word2.charAt(j)) {
						dp[i+1][j+1] = dp[i][j];
					} else {
						dp[i+1][j+1] = Math.min(
								  Math.min(dp[i][j+1],
								   dp[i+1][j]),
								   dp[i][j]
								   )+1;
					}
					
				}
			}
			return dp[m][n];
		}
	public static void main(String args[])
    {
        String str1 = "horse";
        String str2 = "ors";
        EditDistance ref = new EditDistance();
        System.out.println( "recursively way ="+ref.editDistance( str1 , str2 , str1.length(), str2.length()) );
        System.out.println( "itreative way ="+ref.editDistanceIterative( str1 , str2));

    }

}
