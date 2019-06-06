package com.code.review.array.string.easy.isMatch;

public class RegressionMatch {
/**
 * mplement regular expression matching with support for '.' and '*'.

		'.' Matches any single character.
		'*' Matches zero or more of the preceding element.
		
		The matching should cover the entire input string (not partial).
		
		The function prototype should be:
		bool isMatch(const char *s, const char *p)
		
		Some examples:
		isMatch("aa","a") return false
		isMatch("aa","aa") return true
		isMatch("aaa","aa") return false
		isMatch("aa", "a*") return true
		isMatch("aa", ".*") return true
		isMatch("ab", ".*") return true
		isMatch("aab", "c*a*b") return true
		
		1. Analysis
		
		First of all, this is one of the most difficulty problems. It is hard to think through all different 
		cases. The problem should be simplified to handle 2 basic cases:
		
		    the second char of pattern is "*"
		    the second char of pattern is not "*"
		
		For the 1st case, if the first char of pattern is not ".", the first char of pattern and string 
		should be the same. Then continue to match the remaining part.
		
		For the 2nd case, if the first char of pattern is "." or first char of pattern == the first i char
		 of string, continue to match the remaining part. 
 * @param args
 */
   public static boolean isMatch(String s, String p) {
	   
        if(p.length() == 0)
            return s.length() == 0;
 
        //p's length 1 is special case    
        if(p.length() == 1 || p.charAt(1) != '*'){   // second char is not "*"
            if(s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))  // both sides first char is not match
                return false;
            return isMatch(s.substring(1), p.substring(1));    // both char(0) match , char second char
 
        }else{  // second char is "*"
            int len = s.length();
 
            int i = -1; 
            while(i<len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))){
                if(isMatch(s.substring(i+1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        } 
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				System.out.println(isMatch("aa","a") );
				System.out.println(isMatch("aa","aa") );
				System.out.println(isMatch("aaa","aa") );
				System.out.println(isMatch("aa", "a*") );
				System.out.println(isMatch("aa", ".*") );
				System.out.println(isMatch("ab", ".*") );
				System.out.println(isMatch("aab", "c*a*b") );
	}

}
