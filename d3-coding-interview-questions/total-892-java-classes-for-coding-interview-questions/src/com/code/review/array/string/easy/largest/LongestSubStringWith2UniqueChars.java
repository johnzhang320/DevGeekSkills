 package com.code.review.array.string.easy.largest;

import java.util.HashMap;
import java.util.Map;

import com.code.review.utils.Utils;

public class LongestSubStringWith2UniqueChars {
	/**
	 * This is a problem asked by Google.

		Given a string, find the longest substring that contains only two unique characters. 
		For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique 
		character is "bcbbbbcccb".
		
		1. Longest Substring Which Contains 2 Unique Characters
		Solution:
		Using HashMap tracks 2 different characters  by map.size(); max = max(max, i-start)
	 * @param str
	 * @return
	 */
	public static int LongestSubStringWith2UniqueChars(String str) {
		int max = Integer.MIN_VALUE;
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		int start=0;
		for (int i=0; i< str.length(); i++) {
			char c = str.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
			if (map.size()>2) {
				max = Math.max(max, i-start);
				
				while (map.size()>2) {
					char curr = str.charAt(start);  // make map.size()<=2
					if (map.containsKey(curr)) {						 
						if (map.get(curr)>1) {
							map.put(curr, map.get(curr)-1);
							 
						} else {
							map.remove(curr);
							 
						}
						start++;
					}
				}
			}
			
		}
		max = Math.max(max, str.length()-start);
		return max;
	}
	/**
	 * 2 Longest Substring Which Contains k Unique Characters
	 * Given "abcadcacacaca" and 3, it returns "cadcacacaca". return 11
	 * @param args
	 */
	public static int LongestSubStringWithKUniqueChars(String str, int k) {
		int max = Integer.MIN_VALUE;
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		int start=0;
		for (int i=0; i< str.length(); i++) {
			char c = str.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
			if (map.size()>k) {
				max = Math.max(max, i-start);
				
				while (map.size()>k) {
					char curr = str.charAt(start);  // make map.size()<=2
					if (map.containsKey(curr)) {						 
						if (map.get(curr)>1) {
							map.put(curr, map.get(curr)-1);
							 
						} else {
							map.remove(curr);
							 
						}
						start++;
					}
				}
			}
			
		}
		max = Math.max(max, str.length()-start);
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcbbbbcccbdddadacb";
		int result = LongestSubStringWith2UniqueChars(str); 
		System.out.println(result);
		String str2 = "abcadcacacaca";
		int k=3;
		int result2 = LongestSubStringWithKUniqueChars(str2,k); 
		System.out.println(result2);
	}

}
