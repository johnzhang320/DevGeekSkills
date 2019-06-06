package com.code.review.array.combinations.permutations;
/**
 * 
 * 
	Dynamic Programming | Set 25 (Subset Sum Problem)
	
	Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set
	 with sum equal to given sum.
	
	Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
	Output:  True  //There is a subset (4, 5) with sum 9.

 *
 */
public class IsSumExistsInArray {
	public static boolean isSubsetSum(int set[], int n, int sum)
	{
	   // Base Cases
	   if (sum == 0)
	     return true;
	   if (n == 0 && sum != 0)
	     return false;
	 
	   // If last element is greater than sum, then ignore it
	   if (set[n-1] > sum)
	     return isSubsetSum(set, n-1, sum);
	 
	   /* else, check if sum can be obtained by any of the following
	      (a) including the last element
	      (b) excluding the last element   */
	   return isSubsetSum(set, n-1, sum) || 
	                        isSubsetSum(set, n-1, sum-set[n-1]);
	}
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int set[] = {3, 34, 4, 12, 5, 2};
		int sum = 9;
		 int n = set.length;
		  if (isSubsetSum(set, n, sum) == true)
		     System.out.println("Found a subset with given sum");
		  else
			  System.out.println("No subset with given sum");
		 
		  int set2[] = {3, 34, 14, 12, 5, 2};
		  int sum2 = 9;
		  n = set2.length;
		  if (isSubsetSum(set2, n, sum2) == true)
		     System.out.println("Found a subset with given sum");
		  else
		     System.out.println("No subset with given sum"); 
	}

}
