package com.code.review.aaa.google;

public class LC418SentenceScreenFitting {
	 /**
     * (1) Basic idea: find total length(start) which can contain all words and space
     * (2) find length of words + one space 
       (3) totalLength(start)/wordNumber 
       (4) detail: start+=cols, if end of line is space , start++;
       (5) not space, start--
     */
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
