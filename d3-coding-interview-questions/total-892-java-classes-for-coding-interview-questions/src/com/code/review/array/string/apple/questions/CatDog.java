package com.code.review.array.string.apple.questions;

public class CatDog {
	/**
	 * Return true if the string "cat" and "dog" appear the same number of times in the given 
	 * string.

		catDog("catdog") → true
		catDog("catcat") → false
		catDog("1cat1cadodog") → true
	 * @param args
	 */
	public boolean catDog(String str) {
	    int dCount = timeCount(str,"dog");
	    int cCount = timeCount(str,"cat");
	    return dCount == cCount;
	    
	}

	public int timeCount(String str, String opt) {
	    if (str==null || str.length()<3) return 0;
	    int result=0;
	    for (int i=0; i<= str.length()-3;i++) {
	        String s = str.substring(i,i+3);
	        if (s.equals(opt)) {
	             result++;
	        }
	    }
	    return result;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CatDog ref = new CatDog();
		System.out.println(ref.catDog("catdog"));
		System.out.println(ref.catDog("catcat"));
		System.out.println(ref.catDog("1cat1cadodog"));
	}

}
