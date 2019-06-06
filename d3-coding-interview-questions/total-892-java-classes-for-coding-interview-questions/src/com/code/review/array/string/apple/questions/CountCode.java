package com.code.review.array.string.apple.questions;

public class CountCode {
	/**
	 * Return the number of times that the string "code" appears anywhere in the given string, 
	 * except we'll accept any letter for the 'd', so "cope" and "cooe" count.

		countCode("aaacodebbb") → 1
		countCode("codexxcode") → 2
		countCode("cozexxcope") → 2
	 * @param args
	 */
	public int countCode(String str) {
	    if (str==null || str.length()<4)  return 0;
	    int matchCount =0;
	    for (int i=0; i<=str.length()-4;i++) {
	        String src = str.substring(i,i+4);
	        if (isMatch(src)) {
	           matchCount++;
	        }
	    }
	    return matchCount;
	}

	public boolean isMatch(String src) {
	   if (src==null || src.length()!=4)  return false;
	   String code = "code";
	   for (int i=0; i<4; i++ ) {
	       if (i!=2) {
	          if (src.charAt(i) != code.charAt(i)) {
	               return false;
	          }  
	       } 
	   }
	   return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
