package com.code.review.aaa.career.cup.array.string;

public class WithoutString {
	/**
	 * Given two strings, base and remove, return a version of the base string where all instances of the remove 
	 * string have been removed (not case sensitive). You may assume that the remove string is length 1 or more. 
	 * Remove only non-overlapping instances, so with "xxx" removing "xx" leaves "x".

		System.out.println(withoutString("Hello there", "llo") → "He there"
		System.out.println(withoutString("Hello there", "e") → "Hllo thr"
		System.out.println(withoutString("Hello there", "x") → "Hello there"
	 *  My approach
	 *  find sub string in original string by substring(begin, end), i+=p.length() else i++;
	 *  if not find , add current char to buf
	 */
	 public static String removePatternStr(String s, String p) {
		 if (null==s || null==p || 0==s.length() || s.length()<p.length()) return s;
		 int slen =s.length();
		 int plen = p.length();
		 StringBuffer result = new StringBuffer();
		 int i=0;
		 while (i<=slen-plen) {
			 if (s.substring(i,i+plen).equalsIgnoreCase(p)) {
				 i+=plen;				 
			 } else {
				 result.append(s.charAt(i));
				 i++;
			 }
		 }
		 while (i<slen) {
			 result.append(s.charAt(i));
			 i++;
		 }
		 return result.toString();
	 }
	 public static void main(String arg[]) {
		 System.out.println( removePatternStr("Hello there", "llo"));
		 System.out.println( removePatternStr("Hello there", "e"));
		 System.out.println( removePatternStr("Hello there", "x"));
	 }
}
