package com.code.review.algorithm.dynamic.program;

import java.util.ArrayList;

public class TriangleMinPathSum {
	/**
	 * 
	 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent
	 *  numbers on the row below.

		For example, given the following triangle
		
		[
		     [2],
		    [3,4],
		   [6,5,7],
		  [4,1,8,3]
		]
		
		The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

	    Note:
	    Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows 
	    in the triangle.

	 * 
	 */
	public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
	    if (null == a || a.size() ==0) return 0;
	    int [] total = new int[a.size()];   // every level min value
	    int row = a.size() - 1;
	    // calculation bottom total value
	    for (int i = 0; i< a.get(row).size();i++ ) {
	        total[i] = a.get(row).get(i);
	    }
	   // iterate from last second row
	    for (int i=a.size()-2; i>=0; i--) {
	        for (int j = 0 ; j<a.get(i+1).size()-1; j++) {
	            total[j] = a.get(i).get(j)+Math.min(total[j], total[j+1]);
	        }
	    }
	    return total[0];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
