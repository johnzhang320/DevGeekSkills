package com.code.review.array.string.easy.common;

public class ConvertStringToNumber {
	private static final String SAMPLE="-0123456789";
	public static Integer convertToNumber(String s) {
		if (null==s || s.length()==0) {
			System.out.println("Source String is null !");
			return null; 
		}
				
		StringBuffer buf = new StringBuffer();
		char c;
		for (int i=0; i<s.length();i++) {
			c = s.charAt(i);
			if (SAMPLE.indexOf(c)!=-1) {
				buf.append(c);
			} else {
				System.out.println("Source String contain non number character!");
				return null;
			}
		}
		Long ll=Long.valueOf(buf.toString());
		if (Integer.MAX_VALUE<ll) {
			System.out.println("Too large Integer "+ll);
			return null;
		}
		return Integer.valueOf(buf.toString());
		
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1= "242343223";
		String s2 = "23rtwt31314";
		String s3=null;
		String s4= "-999999";
		String s5= "999999999242343223";
		System.out.println(convertToNumber(s1));
		System.out.println(convertToNumber(s2));
		System.out.println(convertToNumber(s3));
		System.out.println(convertToNumber(s4));
		System.out.println(convertToNumber(s5));
	}

}
