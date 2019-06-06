package com.code.review.aaa.facebook.code.lab;
 
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
		int step = lineNumber*2-2;
		   
		for (int row=0; row<lineNumber; row++) {
			// first & last rows
			if (row == 0 || row == lineNumber-1) {
				for (int j=row; j<str.length();j=j+step) {
					sb.append(str.charAt(j));
				}
			} else {
				int j = row;
				int step1 = 2 * (lineNumber-1-row);
				int step2 = step - step1;
				
				boolean flag=true;
				while (j<len) {
					sb.append(str.charAt(j));
					if (flag) {
						j += step1;
					} else {
						j +=step2;
					}
					flag = !flag;
					
				}
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "PAYPALISHIRING";
		System.out.println("----------ZigZag 1 ----------");
		ConvertZigZag ref = new ConvertZigZag();
		System.out.println(ref.convert(str, 3));
		System.out.println("----------ZigZag 2 ----------");
		System.out.println(ref.convert("ABCD", 2));
		
		str = "PAYPALISHIRINGFISHING";
		System.out.println("----------ZigZag 3 ----------");
	
		System.out.println(ref.convert(str, 4));
	}

}
