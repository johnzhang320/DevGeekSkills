package com.code.review.array.string.easy.isMatch;

public class Wildcard {
	/**
	 * Implement wildcard pattern matching with support for '?' and '*'.
 
		To understand this solution, you can use s="aab" and p="*ab".
	 * @param args
	 */
	public boolean isMatch(String s, String p) {
		if (null== s || s.length()==0) return false;
		int i = 0;
		int j = 0;
		int starIndex = -1;
		int iIndex = -1;
	 
		while (i < s.length()) {
			if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				++i;
				++j;
			} else if (j < p.length() && p.charAt(j) == '*') {
				starIndex = j;		
				iIndex = i;
				j++;
			} else if (starIndex != -1) {
				j = starIndex + 1;
				i = iIndex+1;
				iIndex++;
			} else {
				return false;
			}
		}
	 
		while (j < p.length() && p.charAt(j) == '*') {
			++j;
		}
	 
		return j == p.length();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wildcard ref = new Wildcard();
		String s = "aab";
		String p = "a*";
		System.out.println(ref.isMatch(s, p));
	}

}
