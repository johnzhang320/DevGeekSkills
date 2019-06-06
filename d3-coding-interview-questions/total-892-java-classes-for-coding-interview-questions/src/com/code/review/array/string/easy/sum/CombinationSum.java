package com.code.review.array.string.easy.sum;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	/** 
	 * LeetCode – Combination Sum II (Java)
 

		Given a collection of candidate numbers (C) and a target number (T), find all unique combinations 
		in C where the candidate numbers sums to T. Each number in C may only be used ONCE in the 
		combination.
		
		Note:
		1) All numbers (including target) will be positive integers.
		2) Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
		   (ie, a1 ≤ a2 ≤ … ≤ ak).
		3) The solution set must not contain duplicate combinations.
		
 	 * give sum subarray from array is typical backtracking using recursion
 	 * arr is not sorted
 	 * int candidates[] = {1, 4, 2, 15, 12, 6, 3,8};   	 
	 * int target = 18;
 	 */
 	public List<List<Integer>> combinationSum(int arr[], int target) {
 		List<List<Integer>> result = new ArrayList<List<Integer>>();
 		List<Integer> curr = new ArrayList<Integer>();
 		
 		//helper(result,curr,0,target,arr);
 		recursionHelper(result,curr,0,target,arr);
 		return result;
 	}
	
 	/**
 	 * helper major idea different between target-arr[i] as new target of recursion call and each time maintain current list 
 	 * until target is zero to save current list to result list or less than zero, both return from recursion, any time return 
 	 * remove last added element from current list, each time to call recursion , start = i+1; because target==0 and target<0 return
 	 * then target > 0 , recursion would not return, therefore start=i+1, means current list is not enough to be summed to original 
 	 * target, ignore already added elements and catch more element of arr
 	 *   
 	 * @param result
 	 * @param curr
 	 * @param start
 	 * @param target
 	 * @param arr
 	 */
 	public void helper(List<List<Integer>> result, List<Integer> curr, int start, int target, int[] arr) {
 		if (target == 0) {
 			result.add(new ArrayList<Integer> (curr));
 			return;
 		}
 		if (target<0) {
 			return ;
 		}
 		int prev = -1;
 		for (int i=start; i<arr.length;i++) {
 			//if (prev != arr[i]) {
 				curr.add(arr[i]);
 				helper(result,curr,i+1,target - arr[i], arr);
 				curr.remove(curr.size()-1);
 			//}
 			prev = arr[i];
 		}
 	}
 	
 	/**
 	 * 	 
 	 * helper major idea different between target-arr[i] as new target of recursion call and each time maintain current list 
 	 * until target is zero to save current list to result list or less than zero, both return from recursion, any time return 
 	 * remove last added element from current list, each time to call recursion , start = i+1; because target==0 and target<0 return
 	 * then target > 0 , recursion would not return, therefore start=i+1, means current list is not enough to be summed to original 
 	 * target, ignore already added elements and catch more element of arr
 	 *   
 	 * @param args
 	 */
    public void recursionHelper(List<List<Integer>> results, List<Integer> currentSumList,int startIndex, int target, int A[]) {
    	// return condition one, target<0 , recursion return
    	if (target<0) {
    		return;
    	}
    	// return condition two, target==0, pick up one combination , recursion return
    	if (target==0) {
    		results.add(new ArrayList<Integer>(currentSumList));
    		 
    		return;
    	}
    	
    	int prev=-1;
    	// target>0 , means keep call recursion , i=start to end
    	for (int i=startIndex;i<A.length;i++) {
    		// skip repeated element
    		if (prev!=A[i]) {
    			currentSumList.add(A[i]);  // add current element to currrent
    			recursionHelper(results,            // results set
    					        currentSumList,     // current list added element 
    					        i+1,                // next call skip current element of A[i] 
    					        target-A[i],        // because target>0 therefore minus current element
    					        A                   // original Array keep not change
    					        );
    		    // return here means target ==0 or <0 , remove current element from current sum list
    			currentSumList.remove(currentSumList.size()-1);
    		}
    		prev = A[i];
    	}
    }
    public List<Integer> combinationSumLongestSubArray(int arr[], int target) {
 		List<List<Integer>> result = new ArrayList<List<Integer>>();
 		List<Integer> curr = new ArrayList<Integer>();
 		
 		//helper(result,curr,0,target,arr);
 		recursionHelper(result,curr,0,target,arr);
 		int max = Integer.MIN_VALUE;
 		List<Integer> maxList=null;
 		for (int i=0; i<result.size();i++) {
 			curr = result.get(i);
 			if (max<curr.size()) {
 				max = curr.size();
 				maxList = curr;
 			}
 			 
 		}
 		return maxList;
 	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int candidates[] =  {1, 4, 2, 5, 7, 6, 3,8};   	 
	 	int target = 7;
	 	CombinationSum ref = new CombinationSum();
	 	List<List<Integer>> result = ref.combinationSum(candidates, target); 
	 	  System.out.println("----------------Recursive----------------");
	    for (List<Integer> res: result) {
	    	System.out.println(res.toString());
	    }
	    List<Integer> maxList = ref.combinationSumLongestSubArray(candidates, target);
	    System.out.print("\n----------\n[");
	    maxList.forEach(x->System.out.print(x+" "));
	    System.out.println("]\n");
	}

}
