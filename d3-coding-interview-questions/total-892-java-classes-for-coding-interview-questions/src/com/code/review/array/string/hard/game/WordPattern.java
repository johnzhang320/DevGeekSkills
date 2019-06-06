package com.code.review.array.string.hard.game;

import java.util.HashMap;

public class WordPattern {
	/**
	 *  LeetCode – Word Pattern (Java)
  
		Given a pattern and a string str, find if str follows the same pattern. 
		Here follow means a full match, such that there is a bijection between a 
		letter in pattern and a non-empty word in str.
	 * @param args
	 */
	public static boolean wordPattern(String pattern, String str) {
	    String[] arr = str.split(" ");  
	 
	    //prevent out of boundary problem
	    if(arr.length != pattern.length())
	        return false;
	 
	    HashMap<Character, String> map = new HashMap<Character, String>();
	    for(int i=0; i<pattern.length(); i++){
	        char c = pattern.charAt(i);
	        if(map.containsKey(c)){
	            String value = map.get(c);
	            if(!value.equals(arr[i])){
	                return false;
	            }
	        }else if (map.containsValue(arr[i])){
	            return false;
	        }
	        map.put(c, arr[i]);
	    }
	 
	    return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pattern="Hello world";
		String str = "Hello world";
		String str1 = "Hello w0rld";
		System.out.println(wordPattern(pattern,str));
		System.out.println(wordPattern(pattern,str1));
	}

}
