package com.code.review.array.string.easy.number.calculate;

public class AddingBinary {
/**
 *   

	Given two binary strings, return their sum (also a binary string).
	
	For example, a = "11", b = "1", the return is "100".
 * 
 */
	public static String AddingBinary(String a, String b) {
		if (null==a || 0==a.length()) {
			return b;
		}
		if (null==b || 0==b.length()) {
			return a;
		}
		int la = a.length()-1;
		int lb = b.length()-1;
		int carry=0;
		StringBuffer sb = new StringBuffer();
		while (la>=0 || lb>=0) {
			int va=0;
			int vb=0;
			if (la>=0) {
				va = (a.charAt(la)=='0' ? 0:1);
				la--;
			}
			if (lb>=0) {
				vb = (b.charAt(lb)=='0' ? 0:1);
				lb--;
			}
			int value = va+vb+carry;
			if (value>=2) {
				sb.append(String.valueOf(value-2));
				carry=1;
			} else {
				sb.append(String.valueOf(value));
				carry=0;
			}
			
		}
		if (carry==1) {
			sb.append("1");
		}
		String result = sb.reverse().toString();
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="1101";
		String b="1110";
		System.out.println( AddingBinary(a,  b));
		
	}

}
