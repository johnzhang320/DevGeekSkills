package com.code.review.array.string.easy.duplicate;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LongestStringNoRepeat {
	/**
	 * No. 49 - Longest Substring without Duplication
		Given a string, please get the length of the longest substring which does not 
		have duplicated characters. Supposing all characters in the string are in the 
		range  
		String:abcdcerfghabcdc
		longest String without repeat is dcerfghab
		max=9
		String:abcabcfdab
		longest String without repeat is abcfd
        max=5
	 *  
	 *  
	 */
	/*
	 *  Analysis
	 *  set start to beginning position of non repeated string, check of each characters after s(start) is q
	 *   
	 *  (2) i = 0 to n loop, current = start.charAt(i)
	 *  (3) k=start to i, in start to i, we check if each element[k] == current
	 *  
	 */
	public static int longestStringNoDuplicate ( String str) {
		int start=0;
		int max = Integer.MIN_VALUE;
		for (int i=0;i<str.length();i++) {
			int current = str.charAt(i);
			for (int k=start; k < i ; k++) {   // check if current exists in sub string
				if (str.charAt(k) == current) { 
					// exist, move start to next check
					start = k+1;   
					break;
				}
				max = Math.max(max, i-start);
			}
		}
		return max;
	}
	/**
	 * (1) define start=0, result[2], max = min 
	 * (2) i = 0; str.length()-1, c = str.charAt(i)
	 * (3) k=start; k<i; k++, str.charAt(k) == c then start = k+1; break; means skip chars at front of c 
	 * (4) in k loop; if (i-start)>max then max = i-start, result[0] = start and result[1]=i
	 * (5) end of iteration; str.substring(result[0], result[1])
	 * 
	 */
	/*
	 *  cases studies str = "abcaebbcfdab";
	 *  (1) i=0, start=0, curr=str(0)=a, k==i , not enter internal loop
	 *  (2) i=1, start=0  curr=c, k = 0 to 1, enter internal loop , a <> b, then b==b, k=1, start =2
	 *  (3) i=2 start = 2 skip internal loop, 
	 *  (4) i=3, start=2, enter internal loop. k=2, s(2) = c and curr = a, s(3) = curr= a, start = 4
	 *  (5) i=5 
	 */
	public static String longestStringNoDuplicate2 ( String str) {
		int start=0;
		int result[]=new int[2];
		int max = Integer.MIN_VALUE;
		for (int i=0;i<str.length();i++) {
			int current = str.charAt(i);
			for (int k=start; k < i ; k++) {   // check if current exists in sub string
				if (str.charAt(k) == current) { 
					// exist, move start to next check
					start = k+1;
					
					break;
				}
				if (i-start>max) {
					result[0]=start;
					result[1] = i;
					max = i-start;
				}
				 
			}
		}
		System.out.println("max="+max);
		return str.substring(result[0],result[1]);
	}
	public static Set<Character> lengthOfLongestSubstring(String s) {
	    if(s==null||s.length()==0){
	        return null;
	    }
	    int result = 0;
	    int start=0;
	    Set<Character> set = new LinkedHashSet<Character>();
	    for(int i=0; i<s.length(); i++){
	        char c = s.charAt(i);
	        if(!set.contains(c)){
	            set.add(c);
	           // result = Math.max(result, set.size());
	        }else{
	            while(start<i){
	                if(s.charAt(start)==c){
	                    start++;
	                    break;
	                }else{
	                    set.remove(s.charAt(start));
	                    start++;
	                }
	            }
	        	
	        }  
	    }
	 
	    return set;
	}
	class Pair {
		Character c;
		int index;
		public Pair(Character c, int i) {
			this.c = c;
			this.index = i;
		}
		public boolean equals(Pair o) {
			return this.c ==o.c;
		}
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcdcerfghabcdc";
		System.out.println("String:"+str+"\nlongest String without repeat is "+longestStringNoDuplicate(str));
		
		str = "abcaebbcfdab";
		System.out.println("String:"+str+"\nlongest String without repeat is "+longestStringNoDuplicate2(str));
		str = "abcaebbcfdab";
		System.out.println("String:"+str+"\nlongest String without repeat is ");
			
		
		Set<Character> set=	lengthOfLongestSubstring(str);
		set.forEach(x->System.out.print(x));
	
	}

}
