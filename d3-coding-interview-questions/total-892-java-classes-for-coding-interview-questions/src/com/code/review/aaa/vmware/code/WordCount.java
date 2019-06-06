package com.code.review.aaa.vmware.code;

public class WordCount {
	/**
	 * this is a   test 
	 * space could be double or triple
	 * (1) create char variable prev which contain previous char
	 * (2) curr is current char
	 * (3) check if curr!=prev,
	 * (4) if prev char not match curr, then
	 * (5) check if p!=' ' and curr=' ', then count++;
	 * (6) prev = s.charAt(i);
	 * (7) after iteration finish, check if prev !=' '; then count++;
	 *  
	 */

	public static int word_count(String inputText) {
	    if (null==inputText || 0==inputText.length()) return 0;
	     
	    int count = 0;
	    char prev=inputText.charAt(0);
	    for (int i=1; i < inputText.length(); i++) {
	    	char c = inputText.charAt(i);
	    	if (c!=prev) {
	    		if (c==' ' && prev!=' ') {
	    			count++;
	    		} 
	    	}
	    	prev = inputText.charAt(i);
	    }
	    if (prev!=' ') {
	    	count++;
	    }
	     
	    return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "this is a   test           Hello World";
		System.out.println(word_count(s));
		s = "this is a test Hello World";
		System.out.println(word_count(s));
		
		
	}

}
