package com.code.review.aaa.career.cup.array.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class FirstCharNonRepeatedOnePass {
    /**
     *  yahoo-interview-questions
 
	    Find the first unrepeated character in a given string. Solve this in a single pass.
	    
	    Solution one
	   (1) ch[256]=0, i=0 to s.length()-1 (ch[s.charAt(i)]++)
	   (2) i = 0; to s.length-1, ch[s.charAt(i)] == 1 return s.charAt(i)
	    
	    
	    My implement O(1), real one pass , but O(2n) space
     *  (1) Create HasMap<Character, count)
     *  (2) LinkedHashSet<Character>
     *  (3) in loop, if number exists in map and count>1 remove number from set if set exist
     *  (4) if not in map, add it to map and hashset 
     *  (5) return first one character in linkedHashSet
   
     */
	public static char firstCharNonRepeatedOnePass(String str) {
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		Set<Character> set = new LinkedHashSet<Character>();
		for (int i=0; i<str.length(); i++) {
			char key = str.charAt(i);
			if (map.containsKey(key)) {
				int count = map.get(key);
				if (count>=1) {
					if (set.contains(key)) {
						set.remove(key);
					}					
				} 
				map.put(key,count+1);
			} else {
				map.put(key,1);
				set.add(key);
			}
		}
		char first=set.iterator().next();
		 
		return first;
	}
	/**
	 *  Solution two 
	 *  (1) create HashMap
	 *  i = A.length-1 to 0, add map<Character,count>() , after that check character again and its count==1
	 *  if is 1, min = i;
	 *  return A[min] if min!=-1  O(n) time and space
	 * @param args
	 */
	 public static char firstChar(String s) {
		 if (null==s || 0==s.length()) return ' ';
		 int len = s.length();
		 int min = -1;
		 Map<Character,Integer> map = new HashMap<Character,Integer>();
		 for (int i=len-1; i>=0;i--) {
			 char key = s.charAt(i);
			 if (map.containsKey(key)) {
				 map.put(key,map.get(key)+1);
			 } else {
				 map.put(key, 1);
			 }
			 if (map.containsKey(key) && map.get(key)==1) {
				 min = i;
			 }
		 }
		 // prevent char at min located duplicated at last comparison
		 if (min!=-1) {
			 char key = s.charAt(min);
			 if (map.containsKey(key) && map.get(key)>1) {
				 min = -1;
			 }
		 }
		 
		 if (min!=-1) {			 
			 System.out.println("'"+s+"' has first non duplicated char: "+s.charAt(min));
			 return s.charAt(min);
		 } else {
			 System.out.println("'"+s+ "' all chars are duplicated !");
			 return ' ';
			 
		 }
	 }
	 
	// using HashSet<character>  
	public static char find1stNonDupChar(String s) {
		 if (null==s || 0==s.length()) return ' ';
		 int len = s.length();
		 int min = -1;
		 Set<Character> set = new HashSet<Character>();
		 for (int i=len-1; i>=0;i--) {
			 char key = s.charAt(i);
			 if (!set.contains(key)) {
				 set.add(key); 
 				 min = i;
			 }
		 }
		 if (min!=-1 && s.charAt(0)==s.charAt(min) && min>0) {
			 min = -1;
		 }
		 if (min!=-1) { 
			 return s.charAt(min);
		}
		return ' ';	
	}
	// using HashMap or HashSet record min =i , there is some test case containing problem eeeeer
	// therefore for we'd better, either use HashMap to add all character and count into hashMap
	// or use charSet[256] array to ensure O(n)
	public static char findFirstNonRepeatChar(String s) {
		char count[] = new char[256];
		for (int i=0;i<s.length();i++) {
			count[s.charAt(i)]++;
		}
		for (int i=0; i<s.length();i++) {
			if (count[s.charAt(i)]==1) {
				return s.charAt(i);
			}
		}
		return ' ';
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String s= "this is abcd this";
        System.out.println(firstCharNonRepeatedOnePass(s) );
        firstChar(s);
        String s2= "aaeadadgffag";
        firstChar(s2);
        String s3 = "abcabc";
        System.out.println(s3+" has "+find1stNonDupChar(s3));
        
        s3 = "abcdabc";
        System.out.println(s3+" has "+find1stNonDupChar(s3));
        
        s3 = "eabcdabc";
        System.out.println(s3+" has "+find1stNonDupChar(s3));
        
        s3 = "eyeabcdabc";
        System.out.println(s3+" has "+find1stNonDupChar(s3));
        
        s3 = "eeeeeeee";
        System.out.println(s3+" has "+find1stNonDupChar(s3));
        
        s3 = "eeeeeeer";   // prove hash set could be wrong
        System.out.println(s3+" has "+find1stNonDupChar(s3));
        
        firstChar(s3);
        
        System.out.println(s3+" has "+findFirstNonRepeatChar(s3));
	}

}
