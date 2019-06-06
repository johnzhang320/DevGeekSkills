package com.code.review.array.string.hard.game;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
/**
 *  LeetCode – Pascal’s Triangle (Java)
 

	Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5, 
	the result should be:
	
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
	]

 * 
 */
	public static List<List<Integer>> PascalTriangle(int totalRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>> ();
		List<Integer> prev = new ArrayList<Integer>();
		int row=1;
		prev.add(1);
		result.add(prev);
		while (row<totalRows) {
			List<Integer> curr = new ArrayList<Integer>();
			curr.add(1);
			for (int i=1; i<=row-1;i++) {
				curr.add(prev.get(i-1)+prev.get(i));
			}
			curr.add(1);
			prev = curr;
			result.add(curr);
			row++;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = PascalTriangle(6);
		System.out.println("{\n");
		for (List<Integer> list: result) {
			System.out.print("\n{");
			for (Integer i: list) {
				 System.out.print(i+" ");
			}
			System.out.println("}");
		}
		System.out.println("\n}");
	}

}
