package com.code.review.aaa.career.cup.array.string;

import java.util.ArrayList;
import java.util.List;

public class ConvertZigZag {
	/**
	 LeetCode – ZigZag Conversion (Java)


		The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
		(you may want to display this pattern in a fixed font for better legibility)
		
		P   A   H   N
		A P L S I I G
		Y   I   R
		
		And then read line by line: "PAHNAPLSIIGYIR"
		Write the a method convert("PAYPALISHIRING", 3) which returns "PAHNAPLSIIGYIR".
		
		Analysis
		Row =0 ; pick up char of string begin with 0, step 4
		Row =1 ; pick up char of string begin with 1, step 2 
		Row =2 ; pick up char of string begin with 2, step 4
		even line, steps = 4, odd line 2; 
		      
	 */
	/**
	 *  Implementation:
	 *  (1) Parameters: s is string, lines is total rows
	 *  (2) define int row is current row number which is also begin point to start zigzag at each line
	 *  (3) check if row%2==0 then step=4, row%2!=0 then step 2;
	 *  (4) List<List<String>> result, if step=4, fill 3 space between Char, if step = 2 fill one space between char
	 *  (5) External iteration for row, internal iteration for i=row to len   
	 * 
	 */
	public static List<List<String>> ConvertZigZag(String s, int lines) {
		List<List<String>> result = new ArrayList<List<String>>();
		int row=0;
		int step;
		String space;
		while (row<lines) {
			if (row%2==0) {
				step = 4;
				space="   ";
			} else {
				step =2;
				space=" ";
			}
			List<String> lineResult = new ArrayList<String>();
			for (int i=row; i<s.length(); i+=step) {
				lineResult.add(s.charAt(i)+space);
			}
			result.add(lineResult);			
			row++;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s ="PAYPALISHIRING";
		int lines=3;
		List<List<String>> result = ConvertZigZag(s, lines);
		for (List<String> rs : result) {
			rs.forEach((n)->System.out.print(n));
			System.out.println(" ");
		}
	}

}
