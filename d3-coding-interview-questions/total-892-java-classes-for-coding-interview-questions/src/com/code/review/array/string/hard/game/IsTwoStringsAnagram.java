package com.code.review.array.string.hard.game;

public class IsTwoStringsAnagram {
	// new way for anagram no need extra memory
	public static String convertAsscii(String s) {		
		if (s==null || s.length()==0) return null;
		s = s.toLowerCase().replace(" ", "");		 
		int sum = 0;
		for (int i=0; i<s.length();i++) {
			sum+=(int) s.charAt(i);
		}
		return String.valueOf(sum);
	}
	// O(nx2)
	public static boolean isAnagramByChars(String s1, String s2) {
		if (null== s1 || null == s2 ) return false;
		if (s1.length()!=s2.length()) {
			return false;
		}
		return convertAsscii(s1).equals(convertAsscii(s2));
	}
	//O(n+256)
	public static boolean isAnagram(String s, String t) {
	    if(s==null || t==null)
	        return false;
	 
	    if(s.length()!=t.length())
	        return false;
	 
	    int[] arr = new int[256];
	    for(int i=0; i<s.length(); i++){
	        arr[s.charAt(i)]++;
	        arr[t.charAt(i)]--;
	    }
	 
	    for(int i: arr){
	        if(i!=0)
	            return false;
	    }
	 
	    return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isAnagramByChars("ABC%D","DBAC%"));
		System.out.println(isAnagram("ABC%D","DBAC%"));
	}

}
