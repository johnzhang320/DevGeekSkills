package com.code.review.array.string.easy.anagram;

import java.util.HashMap;
import java.util.Map;

public class IsAnagramMap {
	// O(n) Solution, using HashMap<Character, count> to save str1 and loop str2 to check if exists
	// once found, reduce character count
	public static boolean isAnagramMap(String s1, String s2) {
		if (null== s1 || null == s2 ) return false;
		if (s1.length()!=s2.length()) {
			return false;
		}
		 System.out.println(s1+","+s2);
		/* Ask interview if he cares case sensitive 
		 s1 = s1.toLowerCase(); 
		 s2 = s2.toLowerCase();	
		*/
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i=0;i<s1.length();i++) {
			char key = s1.charAt(i);
			if (map.containsKey(key)) {
				 map.put(key,map.get(key)+1);
			} else {
				map.put(key, 1);
			}
		}
		for (int i=0;i<s2.length();i++) {
			char key = s2.charAt(i);
			if (!map.containsKey(key)) {
				return false;
			} else {
				if (map.get(key)>0) {
					map.put(key, map.get(key)-1);
				} else {
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 ="abcd";
		String s2 = "dabc";
		 
		
		System.out.println("-------------IsAnagramMap------------");
		System.out.println(isAnagramMap(s1,s2));
		System.out.println(isAnagramMap("abb","bba"));
		System.out.println(isAnagramMap("bbc","bba"));
		System.out.println(isAnagramMap("bcb","bba"));
		System.out.println(isAnagramMap("cbb","bba"));
	}

}
