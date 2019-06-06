package com.code.review.array.string.easy.isMatch;
/**
 *  yahoo-interview-questions
 

		write custom pattern match function to match following logic
		.’ Matches any single character.
		‘*’ Matches zero or more of the preceding element.
		The matching should cover the entire input string (not partial).
		The function prototype should be:
		
		bool isMatch(const char *s, const char *p)
		Some examples:
		isMatch(“aa”,”a”) → false
		isMatch(“aa”,”aa”) → true
		isMatch(“aaa”,”aa”) → false
		isMatch(“aa”, “a*”) → true
		isMatch(“aa”, “.*”) → true
		isMatch(“ab”, “.*”) → true
		isMatch(“aab”, “c*a*b”) → true
		isMatch(“ccca”, “c*a”) → true
 */
public class IsMatch {


   public static boolean isMatch(final String string, final String pattern){
      if(string == null || pattern == null || pattern.length() == 0 || pattern.substring(0,1).equals("*")){
         return false;
      }

      char[] stringChars = string.toCharArray();
      char[] patternChars = pattern.toCharArray();


      int patternIndex = 0;
      for(int i = 0; i < stringChars.length; i++){
         if(patternIndex == patternChars.length){
            return false;
         }else if(stringChars[i] == patternChars[patternIndex] || patternChars[patternIndex] == '.'){
            patternIndex++;
            continue;
         } else if(patternChars[patternIndex] == '*'){
            if(stringChars[i] == patternChars[patternIndex - 1] || patternChars[patternIndex - 1] == '.'){
               continue;
            } else {
               patternIndex++;
            }
         } else if (patternIndex+1 < patternChars.length && patternChars[patternIndex + 1] == '*') {
            patternIndex += 2;
            continue;
         } else {
            return false;
         }
      }

      return true;
   }
   public static void main(String[] args) {
	      System.out.println("isMatch(\"aa\", \"a\") -> " + isMatch("aa", "a"));
	      System.out.println("isMatch(\"aa\", \"aa\") -> " + isMatch("aa", "aa"));
	      System.out.println("isMatch(\"aaa\", \"aa\") -> " + isMatch("aaa", "aa"));
	      System.out.println("isMatch(\"aa\", \"a*\") -> " + isMatch("aa", "a*"));
	      System.out.println("isMatch(\"aa\", \".*\") -> " + isMatch("aa", ".*"));
	      System.out.println("isMatch(\"ab\", \".*\") -> " + isMatch("ab", ".*"));
	      System.out.println("isMatch(\"aab\", \"c*a*b\") -> " + isMatch("aab", "c*a*b"));
	      System.out.println("isMatch(\"ccca\", \"c*a\") -> " + isMatch("ccca", "c*a"));
   }
}
