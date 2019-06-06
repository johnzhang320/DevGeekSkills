package com.code.review.array.string.easy.largest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MinSubstringWindow {
	  /**
	   *   LeetCode � Minimum Window Substring (Java)


		Given a string S and a string T, find the minimum window in S which will contain all the characters 
		in T in complexity O(n).
		
		For example, S = "ADOBECODEBANC", T = "ABC", Minimum window is "BANC".
		
		Java Solution
		My Implementation
		added T into a map<Character, count>
		start = 0;
		i =0; to len-1, 
		check if arr[i] in map and map.get(arr[i]) >0 , reduce 1
		until arr[i] all count==0;
		then start = i
	   */
	  private Map<Character, Integer> getTMap(String T) {
		  Map <Character, Integer> map = new HashMap<Character,Integer>();
		  for (int i=0;i<T.length();i++) {
			  char key = T.charAt(i);
			  if (map.containsKey(key)) { 
				  map.put(key, map.get(key)+1);
			  } else {
				  map.put(key, 1);
			  }
		  }
		  return map;
	  }
	  private int getMapCharacters(Map<Character,Integer> map) {
		  Iterator<Character> itr = map.keySet().iterator();
		  int count = 0;
		  while (itr.hasNext()) {
			  char key= itr.next();
			  count+=map.get(key);
		  }
		  return count;
	  }
	  /**
	   * (1) create Target String Map and count each characters in case the duplication is counted
	   * (2) i = 0 s.length-1, once find a character in target map, k = i to length to find others
	   * (3) once we find a character in Target Map , then count minus one, 
	   * (4) once all characters counted, check if min.length > current result length
	   *     result = current i to k+1 substring 
	   *  
	   * @param s
	   * @param T
	   * @return
	   */
	  public String MinimumWindowsSubString(String s, String T) {
		 Map<Character,Integer> map=getTMap(T);
		 
		 String result="";
		 String min = s;
		 for (int i=0;i<s.length(); i++) {
			 // suppose T character is able to be repeated
			 char key = s.charAt(i);
			 
			 if (map.containsKey(key)) {				 
				 for (int k=i;k<s.length();k++) {
					  key = s.charAt(k);
					  if (map.containsKey(key) && map.get(key)>0) {  // avoid remove map
						  
						 // System.out.println("key = "+ key+" in map ");  

						  map.put(key, map.get(key)-1);
					  }
					  if (getMapCharacters(map)==0) {  // check total count of characters in T map will be expired
						 
						  result = s.substring(i,k+1);   // 
						  
						  if (result.length()<min.length()) {
							  min = result;
						  }
						 // System.out.println("Map ==0 Result="+ result+",min = "+min);
						  map = getTMap(T);	  // initialize another map for T String
						  i = k;  // Ensure complexity still be O(n) even two level loop,  it will be ++ in external loop 
						  break;
					  }
				 }
			 }
		 }
		 return min;
	  }
	public static void main(String[] args) {
		MinSubstringWindow ref = new MinSubstringWindow();
		// TODO Auto-generated method stub
		String str="ADOBECODEBANC";
		String T ="ABC";
		String result = ref.MinimumWindowsSubString(str, T);
	 
		System.out.print(result);
	}

}
