package com.code.review.array.string.easy.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumII {
 /**
  *  Sum III 
  *     Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
		Find all unique triplets in the array which gives the sum of zero.
		
		Note:
		
		The solution set must not contain duplicate triplets.
  *  Given array nums = [-1, 0, 1, 2, -1, -4],

	A solution set is:
	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]
     /**
     *  Solution sum = 0
     *. (1) Sort A[]
     *  (2) i =0 to A.length-2
     *  (3) low = i+1, high = A.length-1, while low < high
     *. (4) check if (A[i]+A[low]+A[high]==0) add three of them to listb and add listb
            to listA
            remove duplication A[low]=A[low-1] and A[high]=A[high+1]
        (5) if three of them <0  low++ and >0 high--    
     */
    public static List<List<Integer>> threeSumII(int[] A) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (null==A || A.length<3) return result;
        Arrays.sort(A);
        for (int i=0;i<A.length-2;i++) {
        
            if (i > 0 && A[i] == A[i - 1]) continue;
            int low = i+1;
            int high = A.length-1;
            while (low<high) {
                if (A[i]+A[low]+A[high]==0) {
                    List<Integer> re = new ArrayList<Integer>();
                    re.add(A[i]);
                    re.add(A[low]);
                    re.add(A[high]);
                    result.add(re);
                    low++;   // equal post processing
                    high--;
                        
                    // remove duplication
                    while (low<high && A[low]==A[low-1]) low++;
                    while (low<high && A[high]==A[high+1]) high--;
                    
                } else if(A[i]+A[low]+A[high]<0) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return result;
    }
 	
 	
 	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int candidates[] = {-2,0,1,1,2};   	 
	  
	 	 
	 	List<List<Integer>> result = threeSumII(candidates); 
	    for (List<Integer> res: result) {
	    	System.out.print("[");
	    	for (Integer i:res) {
	    		System.out.print(i+" ");
	    	}
	    	System.out.print("]\n");
	    }
	}

}
