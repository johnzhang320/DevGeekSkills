package com.code.review.aaa.yahoo.code.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FindFirstUnrepeatedCharInOnePass {
	/**
	 *  yahoo-interview-questions 

		Find the first unrepeated character in a given string. 
		Solve this in a single pass.
	 * 
	 */
	public static char FindFirstUnrepeatedChar(String s) {
		if (null==s || 0==s.length()) {
			return '0';
		}
		Map<Character,Integer> map = new HashMap<Character,Integer>();
	 
		for (int i=0; i<s.length();i++) {
			char key = s.charAt(i);
			if (map.containsKey(key)) {
				int count = map.get(key);				 
				map.put(key, count+1);
				 
			} else {
				map.put(key, 1);
				 
			}
		}
		char result='0';
		for (int i=0; i<s.length();i++) {
			char curr = s.charAt(i);
			if (map.containsKey(curr)) {
				if (map.get(curr)==1) {
					result = curr;
					break;
				}
			}
		}
		return result;
	}
	
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "rrsfrwertwegbsdvews";
		System.out.println(FindFirstUnrepeatedChar(s) );
		
		//System.out.println(FindFirstUnrepeatedChar1Pass(s) );
	}

}
