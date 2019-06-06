package com.code.review.array.string.apple.questions;

public class WithoutString {
	/**
	 * Given two strings, base and remove, return a version of the base string where all instances of the remove 
	 * string have been removed (not case sensitive). You may assume that the remove string is length 1 or more. 
	 * Remove only non-overlapping instances, so with "xxx" removing "xx" leaves "x".

		System.out.println(withoutString("Hello there", "llo") → "He there"
		System.out.println(withoutString("Hello there", "e") → "Hllo thr"
		System.out.println(withoutString("Hello there", "x") → "Hello there"
	 * @param args
	 */
	public static String withoutString(String base, String remove) {
	    if (base==null || base.length()<remove.length() ) return base;
	    int rLen = remove.length();
	    int bLen = base.length();
	    StringBuffer result = new StringBuffer();
	    int i=0;
	    while(i<=bLen-rLen) {
	        if (base.substring(i,i+rLen).equalsIgnoreCase(remove)) {
	             i+=rLen;
	        } else {
	           result.append(base.charAt(i));
	           i++;
	        }
	    }
	    while (i<bLen) {
	        result.append(base.charAt(i));
	        i++;
	    }
	    return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 	System.out.println(withoutString("Hello there", "llo")); // → "He there"
		System.out.println(withoutString("Hello there", "e")); // → "Hllo thr"
		System.out.println(withoutString("Hello there", "x")); // → "Hello there"
	}

}
