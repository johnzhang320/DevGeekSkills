package com.code.review.array.string.hard.zigzag;
 
public  class ConvertZigZag {
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
	public static String convert(String str, int lineNumber) {
		int len = str.length();
		StringBuffer sb = new StringBuffer();
		for (int row=0; row<lineNumber; row++) {
			int j = row;
			while (j<len) {
				sb.append(str.charAt(j));
				if (row%2==0) {
					j += 4;
				} else {
					j +=2;
				}
				
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "PAYPALISHIRING";
		System.out.println("----------ZigZag----------");
		ConvertZigZag ref = new ConvertZigZag();
		System.out.println(ref.convert(str, 3));
	}

}
