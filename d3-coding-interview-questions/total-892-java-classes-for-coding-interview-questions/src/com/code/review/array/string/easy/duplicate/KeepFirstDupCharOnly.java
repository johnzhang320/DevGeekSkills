package com.code.review.array.string.easy.duplicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class KeepFirstDupCharOnly {
	/**
	 * Given String , keep only first duplicate character and remove following duplicated characters
	 * such as "google" , return "gole"
	 * solution: 
	 * add String to Map<Character, count>, scan each char in String, if count >1 , set it 0;    
	 * @param args
	 */
	// O(n) , if n <256 , better
	public static String KeepFirstDupCharOnly(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i=0; i< s.length(); i++) {
			char curr = s.charAt(i);
			if (map.containsKey(curr)) {
				map.put(curr, map.get(curr)+1);
			} else {
				map.put(curr, 1);
			}
		}
		StringBuffer result = new StringBuffer();
		for (int i=0; i< s.length(); i++) {
			char curr = s.charAt(i);
			if (map.containsKey(curr)) {
				if (map.get(curr)>0) {
					result.append(curr);
					if (map.get(curr)>1) {
						map.put(curr,0);
					}
				} 
				
			}
		}
		return result.toString();
	}
	/**
	 * Java has LinkedHashSet which can keep the original order and find O(1)
	 * make life much easy
	 * 
	 */
	public static String findNonDupString(String s) {
		LinkedHashSet<Character> set = new LinkedHashSet<Character>();
		for (int i = 0; i<s.length();i++) {
			char curr = s.charAt(i);
			if (!set.contains(curr)) {
				set.add(curr);
			}
			
		}
		
		return new String( set.toString());
	}
	/**
	 * Given String , keep only first duplicate character and remove following duplicated characters
	 * such as "google" , return "gole"
	 * such as "sreenuuu" return srenu
	 * No any extra memory will be allowed
	 * (1) prev = A[0];
	 * (2) if prev != curr, index++ else index not change
	 * (3)  Array copy from 0 to index
	 */
	public static char[] getNonRepeatChar(String s) {
		char A[] = s.toCharArray();
		int index=0;
		char prev = A[0];
		for (int i=0;i<A.length;i++) {
			if (prev!=A[i]) {
				index++;
				A[index]=A[i];
			}
			prev = A[i];
		}
		return Arrays.copyOf(A,index);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s= "google";
		System.out.println(KeepFirstDupCharOnly(s));
		String str = "sreenuuuteenaaage";
		System.out.println(getNonRepeatChar(str));
		 
	}

}
