package com.code.review.array.string.easy.largest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordDictionary {
	/*
	 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
		
		If there is no answer, return the empty string.
		Example 1:
		
		Input: 
		words = ["w","wo","wor","worl", "world"]
		Output: "world"
		Explanation: 
		The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
		Example 2:
		
		Input: 
		words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
		Output: "apple"
		Explanation: 
		Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically 
		smaller than "apply".
		Note:
		
		All the strings in the input will only contain lowercase letters.
		The length of words will be in the range [1, 1000].
		The length of words[i] will be in the range [1, 30]
	 */
	 /**
     *  (1) Array.sort (words)
     *  (2) create HashSet<String> 
     *. (3) check if previous word of current word exists by len-1, result = current word because last word 
            must be in set by my logic
     *. (4) add current word to hashset unconditionally
     */
	 public String longestWord(String[] words) {
         if (words == null || words.length == 0) {
            return null;
        }
        String result = "";
        Arrays.sort(words);
        
        final Set<String> set = new HashSet<>();
        set.add(result);
        
        for (String word : words) {
            // 'w' word.length()-1=1 but word.substring(0,0)=""
            if (set.contains(word.substring(0, word.length() - 1))) {
                if (word.length() > result.length()) {
                    result = word;    
                }
                set.add(word);
            }
        }
        return result;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
