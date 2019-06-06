package com.code.review.array.combinations.permutations;

import java.util.ArrayList;
import java.util.List;

public class FactorialCombination {
/**	Numbers can be regarded as product of its factors. For example,

	8 = 2 x 2 x 2;
	  = 2 x 4.
	Write a function that takes an integer n and return all possible combinations of its factors.

	Note:

	You may assume that n is always positive.
	Factors should be greater than 1 and less than n.
	Example 1:

	Input: 1
	Output: []
	Example 2:

	Input: 37
	Output:[]
	Example 3:

	Input: 12
	Output:
	[
	  [2, 6],
	  [2, 2, 3],
	  [3, 4]
	]
	Example 4:

	Input: 32
	Output:
	[
	  [2, 16],
	  [2, 2, 8],
	  [2, 2, 2, 4],
	  [2, 2, 2, 2, 2],
	  [2, 4, 4],
	  [4, 8]
	]
	 (1) Applied DFS to find factorial combination
	 (2) i = 0 to n, when i%2==0 to choose
	 (3) each_prod > n return as boundary condition
	 (4) each_prod == n return as check condition
	 LeetCode not Passed !!!!
	 
	*/
    public void dfs (int n, int each_prod, int start,List<List<Integer>> result, List<Integer> curr) {
        if (each_prod==n) {
           result.add(new ArrayList<Integer>(curr));
           return;
        }
        if (each_prod>n) {
            return ;
        }
        for (int i=start; i<=n/2;i++) {
        	if(i*each_prod>n)   // add this code section leetcode passed
                break;
        	if (n%i==0) {
	            curr.add(i);
	            dfs(n,i*each_prod,i,result,curr);
	            curr.remove(curr.size()-1);
        	}
        }
    }
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n==0 || n==1) return result;
        if (n%2!=0 && n%3!=0) return result; 
        List<Integer> curr = new ArrayList<Integer>();
        dfs(n,1,2,result,curr);
         
        return result;
        
    }
    public static void main(String[] args) {
    	FactorialCombination ref = new FactorialCombination();
    	int x = 32;
    	 List<List<Integer>> result =ref.getFactors(x);
    	System.out.println("Factorial Combination "+x+" is "+result.toString());	
    	
    	
    	
    	x = 8193;
   	    result =ref.getFactors(x);
   	   System.out.println("Factorial Combination "+x+" is "+result.toString());	
    }
}
