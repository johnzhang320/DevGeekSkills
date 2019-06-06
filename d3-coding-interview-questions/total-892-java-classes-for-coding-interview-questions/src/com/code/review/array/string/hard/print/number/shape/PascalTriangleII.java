package com.code.review.array.string.hard.print.number.shape;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleII {
	/**
	 * Given numRows, generate the first numRows of Pascal’s triangle.

		Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.
		
		Example:
		
		Given numRows = 5,
		
		Return
		
		[
		     [1],
		     [1,1],
		     [1,2,1],
		     [1,3,3,1],
		     [1,4,6,4,1]
		]


	 * Two loop: first loop row, second loop is prev[j-1]+prev[j]
	 * before second loop, add 1 and after second loop add 1 
	 */
	public static List<List<Integer>> pascalTriangle(int totalRow) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> prev = new ArrayList<Integer>();
		int row = 1;
		prev.add(1);
		result.add(prev);
		
		while (row<totalRow) {
			List<Integer> curr = new ArrayList<Integer>();
			// add 1 before internal loop
			curr.add(1);
			for (int i=1;i<row;i++) {
				curr.add(prev.get(i-1)+prev.get(i));
			}
			// after loop add 1
			curr.add(1);
			prev = curr;
			result.add(curr);
			row++;
		}
		System.out.println("[");
			for (List<Integer> list: result) {
				System.out.print("[ ");
				for (Integer i: list) {
					 System.out.print(i+" ");
				}
				System.out.print("]\n");
			}
		System.out.println("]");
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = pascalTriangle(5);
	}

}
