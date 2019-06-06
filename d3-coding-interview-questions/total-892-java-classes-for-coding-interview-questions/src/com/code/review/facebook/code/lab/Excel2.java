package com.code.review.facebook.code.lab;

public class Excel2 {
	/**
	 * 
	 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

		For example:
		
		    1 -> A
		    2 -> B
		    3 -> C
		    ...
		    26 -> Z
		    27 -> AA
		    28 -> AB 
		My Approach:
		(1) create S contains 26 letters
		(2) pass num<=26 fetch from s.charAt(num-1)
		(2) if >26, mod = num%26 , div = num/26, create div number of A, plus mod-1 letter    
	 */
	public static  String getExcelTitle(int num) {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		if (num<=26) {
			return Character.toString(s.charAt(num-1));
		} else {
			int mod = num%26;
			int div = num/26;
			StringBuffer sb = new StringBuffer();
			for (int i=0;i<div;i++) {
				sb.append("A");
			}
			if (mod>0) {
				sb.append(Character.toString(s.charAt(mod-1)));
			}
			return sb.toString();
		}
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getExcelTitle(2));
		System.out.println(getExcelTitle(27));
		System.out.println(getExcelTitle(29));
		System.out.println(getExcelTitle(63));
		System.out.println(getExcelTitle(89));
		System.out.println(getExcelTitle(120));
	}

}
