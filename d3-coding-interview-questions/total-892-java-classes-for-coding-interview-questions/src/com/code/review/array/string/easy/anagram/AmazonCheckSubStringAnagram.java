package com.code.review.array.string.easy.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonCheckSubStringAnagram {
	/**
	 * if two strings are anagram to each other, their sorted sequence is the same
	 * Amazon question
	 * String str1 and str2, find each index, when str2 is in str1.substring(i,str2.length+i-1) is anagram
	 * 
	 */
	/**
	 *  (1) len1 = length of str1 and len2 = length of str2
	 *  (2) iterate i=0 to len1-len2
	 *  (3) check if str1.substring(i,i+len2) and str2 are anagram, match , put i to result arrayList 
	 *  
	 */
	public static List<Integer> AmazonCheckSubStringAnagram(String strlonger, String str2) {
		List<Integer> list = new ArrayList<Integer>();
		int len1 = strlonger.length();
		int len2 = str2.length();
		for (int i=0;i<len1-len2+1;i++) {
			if (isAnagram2(strlonger.substring(i,i+len2),str2)) {
				list.add(i);
			}
		}
		return list;
	}
	/**
	 * 
	 * @param str1
	 * @param str2
	 * 
	 * @return
	 */
	/** O(n) Solution,
	 (1) check if length of str1 and str2, if not match return false
	 (2) create HashMap<Character, count>, add str1 to map, if repeat , increase count
	 (3) iterate str2 , char c = str2.charAt(i), if c not found in map return false
	 (3) finish iteration, return true;
	 // once found, reduce character count
	 */
	 public static boolean isAnagram(String s1, String s2) {
			if (null== s1 || null == s2 ) return false;
			if (s1.length()!=s2.length()) {
				return false;
			}
			// System.out.println(s1+","+s2);
			//Ask interview if he cares case sensitive 
			 s1 = s1.toLowerCase(); 
			 s2 = s2.toLowerCase();	
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
	// if anagram , sum of two strings should be same
		public  static boolean isAnagram2(String s1, String s2) {
			if (s1==null || s2==null) return false;
			if (s1.length()!=s2.length()) return false;
			System.out.println("s1="+s1+",s2="+s2);
			if (valueInString(s1)!=valueInString(s2))  return false;
			return true;
		}
		public static int valueInString (String s) {
			if (null==s || s.length()==0) {
				return -1;
			}
			int sum=0;
			for (int i=0; i<s.length();i++) {
				sum+=(int)s.charAt(i);
			}
			System.out.println("s assum="+sum);
			return sum;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1="abbcbbaghkdbBab";
		String str2="bba";
		List<Integer> list = AmazonCheckSubStringAnagram(str1, str2);
		
		for (Integer i:list) {
			System.out.print(i+",");
		}
	}


}
