package com.code.review.array.string.easy.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
	/**
	 *  LeetCode – Group Anagrams (Java)
 

		Given an array of strings, return all groups of strings that are anagrams.
		
		Analysis
		
		    An anagram is a type of word play, the result of rearranging the letters of a word or phrase to 
		    produce a new word or phrase, using all the original letters exactly once; for example, Torchwood 
		    can be rearranged into Doctor Who.
		
		Create Map<word, ArrayList<String> as group, check if Anagram using a subroutine 
	 * @param args
	 */
	public static List<List<String>> groupAnagram(String strs[]) {
		List<List<String>> result = new ArrayList<List<String>>();
	 
		Map<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>> ();
		for (String str: strs) {
			String key = convertAsscii(str);  // Anagram asscii code is same
			if (map.containsKey(key)) {
				ArrayList<String> al = map.get(key);
				al.add(str);
				map.put(key, al);
			} else { // first word
				ArrayList<String> al = new ArrayList<String>();
				al.add(str);
				map.put(key, al);
			}
		}
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			ArrayList<String> list = map.get(key);
			if (list.size()>1) { // exists group of anagram which is at least two elements
				result.add(list);
			}
		}
		return result;
	}
	// new way for anagram
	public static String convertAsscii(String s) {
		
		if (s==null || s.length()==0) return null;
		s = s.toLowerCase().replace(" ", "");
		char sc[] = s.toCharArray();
		int sum = 0;
		for (char c: sc) {
			sum+=(int) c;
		}
		return String.valueOf(sum);
	}
	
	public static boolean isAnagramMap(String s1, String s2) {
		if (null== s1 || null == s2 ) return false;
		if (s1.length()!=s2.length()) {
			return false;
		}
		return convertAsscii(s1).equals(convertAsscii(s2));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(convertAsscii("ADC%G"));
		System.out.println(convertAsscii("%ADGC"));
		
		String str[] = {"How are you","you are how","alone","unity","iunty","new","wen","Torchwood","Doctor Who","wood torch"};
		List<List<String>> result = groupAnagram(str);
		for (List<String> list:result) {
			System.out.println("");
			for(String s:list) {
				System.out.print(s+", ");
			}
		}
	}

}
