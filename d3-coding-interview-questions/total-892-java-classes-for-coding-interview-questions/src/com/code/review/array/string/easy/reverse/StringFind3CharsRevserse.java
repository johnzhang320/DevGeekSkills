package com.code.review.array.string.easy.reverse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringFind3CharsRevserse {
	/**
	 *  amazon-interview-questions 

		Find the substring of length 3 which is present in the reverse order from the string.
		Ex: if the string is abcdcba (cba is the reverse of abc) so we should return cba.
		And was asked to improve upon the complexity.
	 * 
	 */
	/**
	 *  input abchowareyoucbauoyhcb
	 *  output cba , uoy, hcb
	 *  (1) create a HashSet<String> set and create reverse subroutine
	 *  (2) i = 0 to n-3; subs =s.subString(i,i+3), check if subs exists in set,
	 *  (3) if not exists in set, reverse subs and store in set
	 *  (4) if exists in set, add subs into result array list
	 *  (5) O(n) computation
	 */
	public static String reverse(String s) {
		char A[] = s.toCharArray();
		char temp = A[0];
		A[0] = A[2];
		A[2] = temp;
		return new String(A);
	}
	public static List<String> StringFind3CharsReverse(String s) {
		List<String> result = new ArrayList<String>();
		if (null==s || 0==s.length()) return result;
		Set<String> set = new HashSet<String>();
		for (int i=0; i<=s.length()-3;i++) {
			String subs = s.substring(i,i+3);
			if (!set.contains(subs)) {  // not found subs in Set
				subs = reverse(subs);
				set.add(subs);
			} else {  // found subs in set , output subs
				result.add(subs);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abchowareyoucbauoyhcb";
		List<String> result = StringFind3CharsReverse(s);
		result.forEach((n)->System.out.print(n+","));
	}

}
