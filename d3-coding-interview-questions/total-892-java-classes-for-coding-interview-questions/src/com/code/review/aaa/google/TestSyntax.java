package com.code.review.aaa.google;

public class TestSyntax {
	 public int wordsTyping(String[] sentence, int rows, int cols) {
	        if (null==sentence || sentence.length==0) return 0;
	        StringBuffer buf = new StringBuffer();
	        for (String word: sentence) {
	            buf.append(word+" ");
	        }
	        String all = buf.toString();
	        int start=0;
	        int len = all.length();
	        for (int i=0;i< rows ;i++) {
	            start+=cols;
	            int pos = start % len;
	            if (all.charAt(pos)==' ') {
	                ++start;
	            } else {
	                while(start>0 && all.charAt((start-1) %len)!=' ') {
	                    --start;
	                }
	            }
	        }
	        return start/len;
	    }
	 public static void main(String []args) {
		 char w[]= {'h','e','l','l','o'};
		 System.out.println(new String(w));
	 }
}
