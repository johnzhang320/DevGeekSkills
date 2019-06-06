package com.code.review.aaa.vmware.code;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordGroupCount {
    /**
     *  words in String can be delimited by space or any other char
     *  count each same words in group  
     *  
     *  This is kafka    training course, this is   spark project course, how many course you already take? I did not    take any course.
     *  
     *  
     *  count each words
     *  
     *  (1) create lrtrim function using pattern string " ,.?-" etc
     *  (2) split word by one space only , then call lrTrim function then put it into map<String,Integer>
     *  
     *    
     */
	
	public static String lrTrim(String s, String pattern) {
		int i=0,j=s.length();
		for (i=0;i<s.length();i++) { // remove from left
			char c = s.charAt(i);
			if (pattern.indexOf(c)==-1) {
			    break;	
			}
			
		}
		if (i==s.length()) return " ";
		for (j=s.length()-1;j>=0;j--) {
			if (pattern.indexOf(s.charAt(j))==-1) {
			    break;	
			}
		}
		
		return s.substring(i,j+1);
	}
	
	public static Map<String,Integer> wordCount(String s,String pattern) {
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		String [] wordList = s.split(" ");
		System.out.println(wordList.toString());
		for (int i = 0 ; i< wordList.length;i++) {
			
			String word = lrTrim(wordList[i],pattern);
			if (map.containsKey(word)) {
				map.put(word,map.get(word)+1);
			} else {
				map.put(word,1);
			}
		
		}
		return map;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lrTrim(" ,take, !", " ,!") );
		String s = "     * This is kafka    training course, this is   spark project course, how many course you already take? I did not    take any course.";
        System.out.println(wordCount(s," ,.!*-=+"));
	}

}
