package com.code.review.aaa.facebook.code.lab;

import java.util.HashSet;
import java.util.Set;

public class ColorfulNum {
	/**
	 * Code 	For Given Number N find if its COLORFUL number or not
		Return 0/1
		COLORFUL number:
		A number can be broken into different contiguous sub-subsequence parts. 
		Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245. 
		And this number is a COLORFUL number, since product of every digit of a contiguous 
		subsequence is different
		Example:
		N = 23
		2 3 23
		2 -> 2
		3 -> 3
		23 -> 6
		this number is a COLORFUL number since product of every digit of a sub-sequence are different. 
		
		Output : 1

	 * My Approach:
	 * (1) Create product , input string, output product by multiplying all digits in String
	 * (2) create two points, range = 1 to length, start from 0 to len-length
	 * (3) convert given number to a string, using substring to pick up combination of data
	 */
	public static int getProduct(String s) {
		if (s==null || s.length()==0) {
			return -1;
		}
		if (s.length()==1) {
			return Integer.valueOf(s);
		}
		int prod =1;
		int d = Integer.valueOf(s);
		 
		while (d>0) {
			int digit = d % 10;
			if (digit==0) return 0;
			prod *=digit;
			d =d/10;
 		}
		return prod;
	}
	public static boolean isColorfulNum(int num) {
		if (num<10) return true;
		String str = String.valueOf(num);
		int len = str.length();
		Set<Integer> set = new HashSet<Integer>();
		for (int range = 1; range<len; range++) {
			for (int start=0;start<len-range+1;start++) {
				String dlta = str.substring(start, start+range);
				//System.out.println(dlta);
				int prod = getProduct(dlta);
				if (prod==-1) return false;
				if (set.contains(prod)) {
					return false;
				} else {
					set.add(prod);
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(isColorfulNum(3245));
		System.out.println(isColorfulNum(32456));
		System.out.println(isColorfulNum(1233));
		System.out.println(getProduct("32"));
		System.out.println(getProduct("324"));
		System.out.println(getProduct("320"));
		System.out.println(getProduct("3244"));
	}

}
