package com.code.review.aaa.career.cup.array.string;

public class CatDog {
	/**
	 * Return true if the string "cat" and "dog" appear the same number of times in the given 
	 * string.

		catDog("catdog") → true
		catDog("catcat") → false
		catDog("1cat1cadodog") → true
	 *  my approach
	 *  divide sub problem: build a sub routing to find count of "cat" or "dog", comparing counts 
	 *  (1) create a subroutine, parameter is source string s and pattern string p, condition check
	 *  (2) slen = s.length() and plen=p.length(), count=0
	 *  (3) i=0 and <=slen-plen, check if s.substring(i,i+plen) equal p then count++; i+=plen else i++
	 *  (4) is main program return findCount(s,"cat") == findCount(s,"dog")
	 */
	
	public static int findCount(String s, String p) {
		if (null==s || null==p || 0== s.length() || s.length()<p.length()) {
			return 0;
		}
		int plen = p.length();
		int slen = s.length();
		int count =0;
		int i=0;
		while (i<=slen-plen) {
			if (s.substring(i,i+plen).equals(p)) {
				count++;
				i+=plen;
			} else {
				i++;
			}
		}
		return count;
	}
	public static boolean catdog(String s) {
		
		int count1=findCount(s, "cat");
		int count2=findCount(s, "dog");
		if (count1==0 && count2==0) return false;
		return count1==count2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(catdog("catdog"));
		System.out.println(catdog("catcat"));
		System.out.println(catdog("1cat1cadodog"));
	}

}
