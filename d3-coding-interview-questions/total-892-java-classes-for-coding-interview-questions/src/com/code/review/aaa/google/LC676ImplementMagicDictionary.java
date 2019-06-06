package com.code.review.aaa.google;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 *  Implement a magic directory with buildDict, and search methods.

	For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
	
	For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.
	
	Example 1:
	
	Input: buildDict(["hello", "leetcode"]), Output: Null
	Input: search("hello"), Output: False
	Input: search("hhllo"), Output: True
	Input: search("hell"), Output: False
	Input: search("leetcoded"), Output: False
	Note:
	
	You may assume that all the inputs are consist of lowercase letters a-z.
	For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
	Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across 
 *
 */
public class LC676ImplementMagicDictionary {
	/**
	 *  (1) add * to each word[i], each change to save to Hashset
	        for example hello , save each *ello, h*llo, he*lo , hel*o and hell* to HashSet
	    (2) when we search word[i]='*' i 0 to word.size()-1, 
	    (3) count not match time , if disMatch ==  word.size()-1, means one character mismatch return true
	    (4) Space O(N*M) and Search time Q(m)
	 */
 
	    
	    Set<String> set = null;
	    /** Initialize your data structure here. */
	    public LC676ImplementMagicDictionary() {
	        set =new HashSet<String>();
	    }
	    
	    /** Build a dictionary through a list of words */
	    public void buildDict(String[] dict) {
	        for (String s: dict) {
	            char word[] = s.toCharArray();
	            for (int i=0;i<word.length;i++) {
	                word[i]='*';
	                String s2 = new String(word);
	                set.add(s2);
	            }
	        }
	    }
	    
	    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
	    public boolean search(String word) {
	        char wch[] = word.toCharArray();
	        int misMatch=0;
	        int n = word.length();
	        for (int i=0;i<n;i++) {
	            wch[i]='*';
	            String s = new String(wch);
	            if (!set.contains(s)) {
	               misMatch++; 
	            }
	        }
	        System.out.println("word searched "+word+", mismatched "+misMatch);
	        if (misMatch==1) {
	            return true;
	        }
	        return false;
	    }
	}
