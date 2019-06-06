package com.code.review.aaa.career.cup.algorithm;

public class FindMinDiffTwoSubsets {
	/**
	 *  
	 * 
		Partition a set into two subsets such that the difference of subset sums is minimum
		
		Given a set of integers, the task is to divide it into two sets S1 and S2 such that the
		absolute difference between their sums is minimum.
		
		If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 
		must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
		
		Example:
		
		Input:  arr[] = {1, 6, 11, 5} 
		Output: 1
		Explanation:
		Subset1 = {1, 5, 6}, sum of Subset1 = 12 
		Subset2 = {11}, sum of Subset2 = 11		
		Recursive Solution
		The recursive approach is to generate all possible sums from all the values of array and to 
		check which solution is the most optimal one.
		To generate sums we either include the i’th item in set 1 or don’t include, i.e., 
		include in set 2.
		
		
	 */
	// Function to find the minimum sum
	public int findMinSummary(int A[],int i,  int sumCalculated, int sumTotal) {
		 // If we have reached last element.  Sum of one
	    // subset is sumCalculated, sum of other subset is
	    // sumTotal-sumCalculated.  Return absolute difference
	    // of two sums.
	    if (i==0)
	        return Math.abs((sumTotal-sumCalculated) - sumCalculated);
	 
	 
	    // For every item A[i], we have two choices
	    // (1) We do not include it first set
	    // (2) We include it in first set
	    // We return minimum of two choices
	    return Math.min(findMinSummary(A, i-1, sumCalculated+A[i-1], sumTotal),
	               findMinSummary(A, i-1, sumCalculated, sumTotal));
	}
	// Returns minimum possible difference between sums
	// of two subsets
	int findMin(int A[])
	{
		int n = A.length;
	    // Compute total sum of elements
	    int sumTotal = 0;
	    for (int i=0; i<n; i++)
	        sumTotal += A[i];
	 
	    // Compute result using recursive function
	    return findMinSummary(A, n, 0, sumTotal);
	}
	public static void main(String[] args) {
		FindMinDiffTwoSubsets ref = new FindMinDiffTwoSubsets();
		 int A[] = {3, 1, 4, 2, 2, 1};
		 System.out.println(ref.findMin(A));
	}

}
