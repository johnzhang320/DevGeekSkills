package com.code.review.array.string.easy.isMatch;

import java.util.ArrayList;
import java.util.List;

public class WordLadder {
	 /**
	  *  LeetCode – WoWordLadderrd Ladder


		Given two words (start and end), and a dictionary, find the length of shortest transformation sequence 
		from start to end, such that only one letter can be changed at a time and each intermediate word must 
		exist in the dictionary. For example, given:
		
		start = "hit"
		end = "cog"
		dict = ["hot","dot","dog","lot","log"]
		
		One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the program should return its 
		length 5.
		
		Analysis
		
		UPDATED on 06/07/2015.
		
		So we quickly realize that this is a search problem, and breath-first search guarantees the optimal 
		solution. 
	
		we check begin word in dictionary , equal or approximately equal(one character mismatch only), 
		record the begin index and then find the end word after this begin word, record the end index, 
		return the dict(beginIndex, endIndex)
		
	  */
	  private boolean approximateMatch(String word1, String word2, int approximate) {
		  if (word1.length()!=word2.length()) {
			  return false;
		  } 
		  int misMatch=0;
		  for (int i=0; i < word1.length();i++) {
			  char c1 = word1.charAt(i);
			  char c2 = word2.charAt(i);
			  if (c1!=c2) {
				  misMatch++;
			  }
		  }
		  if (misMatch<=approximate) {
			  return true;
		  } 
		  return false;
	  }
	  public String[] wordLadderI(String beginWord, String endWord, String[] dict) {
		  int beginIndex, endIndex=dict.length;
		  boolean beginFound=false;
		  for (beginIndex=0; beginIndex< dict.length;beginIndex++) {
			  if (approximateMatch(beginWord,dict[beginIndex],1)) {
				  beginFound = true;
				  break;
			  }
		  }
		  if (beginFound) {
			  for (endIndex = beginIndex; endIndex<dict.length;endIndex++) {
				  if (approximateMatch(endWord,dict[endIndex],1)) {
					  
					  break;
				  }
			  }
		  }
		  
		  String result[] = new String[endIndex - beginIndex+1+2]; // include first one , must add one, include beginWord and endWord must add 2
		  System.arraycopy(dict, beginIndex, result, 1, endIndex-beginIndex+1);
		  result[0]=beginWord;
		  result[result.length-1]= endWord;
		  return result;
	  }
	  public List<String> wordLadderII(String beginWord, String endWord, String[] dict) {
		  List<String> result = new ArrayList<String>();
		  int beginIndex, endIndex=dict.length;
		  boolean beginFound=false;
		  for (beginIndex=0; beginIndex< dict.length;beginIndex++) {
			  if (approximateMatch(beginWord,dict[beginIndex],1)) {
				  beginFound = true;
				  break;
			  }
		  }
		  if (beginFound) {
			  for (endIndex = beginIndex; endIndex<dict.length;endIndex++) {
				  if (approximateMatch(endWord,dict[endIndex],1)) {
					  
					  break;
				  }
			  }
		  }
		  result.add(beginWord);
		  for (int i=beginIndex;i<=endIndex;i++) {
			  result.add(dict[i]);
		  }
		  result.add(endWord);
		  return result;
	  } 
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		String start = "hit";
		String end = "cog";
		String [] dict = {"hot","dot","dog","lot","log"};
		WordLadder ref = new WordLadder();
		List<String> result = ref.wordLadderII(start, end, dict); 
		for (String s: result) System.out.print(s+" ");
		
	}

}
