package com.code.review.array.string.easy.largest;

public class LongestCommonPrefix {
	/**
	 * Problem

		Write a function to find the longest common prefix string amongst an array of strings.
		
		Analysis
		
		To solve this problem, we need to find the two loop conditions. One is the length of 
		the shortest string. The other is iteration from each element tail to begin over every
		element of the string array. 
	 * @param args
	 */
	public static String LongestCommonPrefix(String str[]) {
		 
		String minString="";
		int minLen = Integer.MAX_VALUE;
		for (String s: str) {
			if (minLen>s.length()) {  // find string with minimum length within array, this is possible shortest string prefix
				minString =s;
				minLen=s.length();
			}
		}
		int p=minLen;  // compare shortest string of array with other elements char by char, if not match, shift left
		 
		for (String s: str) {
			if (s.length()==0) return "";
			while (!s.substring(0,p).equalsIgnoreCase(minString.substring(0,p))) { 
				p--;
			}
			if (p<=0) return "";
 		}
		return minString.substring(0,p);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arr[] = {"HelloWorldTiffanyComeOver", "HelloWorldTiffanyCome",
						"HelloWorldATiffany", "HelloWorldTiffUSA","HelloWorldTiffHEHEHE" };
			 	                  	 
		String arr2[] = {"aac","abcd"};
		String result= LongestCommonPrefix(arr); 
	 	System.out.println(result);	  
	 	String result2= LongestCommonPrefix(arr2); 
	 	System.out.println(result2);	  
	}

}
