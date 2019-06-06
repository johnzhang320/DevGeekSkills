package com.code.review.array.string.easy.prefix;

public class LongestCommonPrefix {
	/**
	   * LeetCode � Longest Common Prefix (Java) 
� 
		Problem
		Write a function to find the longest common prefix string amongst an array of strings.
		Analysis
		To solve this problem, we need to find the two loop conditions. One is the length 
		of the shortest string. The other is iteration over every element of the string array. 
		My Implementation
		First loop find shortest string
		Second loop using shortStr.subString(0,p) == element.substring(0,p) if not match p-- compare again
		until matched, otherwise return null;
	   */
	  public static String FindLongestCommonPrefix(String arr[]) {
		  String minElem=arr[0];
		  for (String s:arr) {
			  if (s.length()<minElem.length() )
				  minElem = s;
		  }
		  int p = minElem.length();
		  if (p==0) return "";
		  for (String s: arr) {			  
			  while (!s.substring(0, p).equalsIgnoreCase(minElem.substring(0,p))) {
				  if (p>0) {
					  p--;
				  } else {
					  return "";
				  }
			  }
		  }
		  return minElem.substring(0,p);
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  String arr[] = {"aa","ab"};
		  String result = FindLongestCommonPrefix(arr); 
		  System.out.println(result);	
		  
		  String arr2[] = {"aacrgs","aacb",""};
		  String result2 = FindLongestCommonPrefix(arr2); 
		  System.out.println(result2);	
		  
		  String arr3[] = {"aacrgs","aacb","aacsweqwe"};
		  String result3 = FindLongestCommonPrefix(arr3); 
		  System.out.println(result3);	
	}

}
