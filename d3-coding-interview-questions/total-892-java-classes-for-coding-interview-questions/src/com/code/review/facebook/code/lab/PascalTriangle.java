package com.code.review.facebook.code.lab;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
	/**
	 *Given an index k, return the kth row of the Pascal’s triangle.

	Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.
	
	Example:
	
	Input : k = 3
	
	Return : [1,3,3,1]

    NOTE : k is 0 based. k = 0, corresponds to the row [1].

	Note:Could you optimize your algorithm to use only O(k) extra space?


	 * Two loop: first loop row, second loop is prev[j-1]+prev[j]
	 * before second loop, add 1 and after second loop add 1 
	 */
	public static List<Integer> pascalTriangleII(int k) {
		 
		List<Integer> prev = new ArrayList<Integer>();
		int row = 1;
		prev.add(1);
		if (k==0) {
			return prev;
		}
		List<Integer> curr = new ArrayList<Integer>();
		while (row<=k) {
			curr = new ArrayList<Integer>();
			// add 1 before internal loop
			curr.add(1);
			for (int i=1;i<row;i++) {
				curr.add(prev.get(i-1)+prev.get(i));
			}
			// after loop add 1
			curr.add(1);
			prev = curr;
		 
			if (row == k) {
				return curr;
			}
			row++;
		}
		 
		return curr;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> result = pascalTriangleII(3);
		for (Integer s: result) {
			System.out.print(s+" ");
		}
	}

}
