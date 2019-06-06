package com.code.review.queue.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CheckAnyDuplicatedParenthesis {
	/**
	 amazon-interview-questions
 
		give an algorithm for finding duplicate parenthesis in a expression.
		
		
		{{ (( a + b ) * ( c + d )) }}
	 *   
	 */
	public static boolean isDuplicatedParenthesis(String s) {
		if (null==s || 0==s.length()) return false;
		
		Stack<Character> stack = new Stack<Character>();
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put(')','(');
		map.put(']', '[');
		map.put('}', '{');
		s = s.replace(" " , "");
		s = s.replace(" ", "");
		for (int i=0 ; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c==' ') {
				continue; // filter out space
			} else {
				stack.push(c);
				if (map.containsKey(c)) {  // find close parenthesis
					char find = ' ';
					 
					
					while (!stack.isEmpty() && stack.peek()!=map.get(c)) { // keep going until find opening
						find=stack.pop();
						 
					}
					if (stack.isEmpty()) break;
					find = stack.pop();
				
					if (find==map.get(c) && !stack.isEmpty()) {
						char repeatedOpen = stack.pop();  // if it exists
						char repeatedClose = ' ';
						if ((i+1)<s.length()) {
						   repeatedClose = s.charAt(i+1); 
						  
						}
						if (repeatedClose!=' ' && map.containsKey(repeatedOpen) && repeatedClose==map.get(repeatedOpen)) { // repeated open and close exist
							return true;
						}
					}
					
				}
			} 
			
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "{{ (( a + b ) * ( c + d )) }}";
		System.out.println(s+" duplicated parenthesis is "+isDuplicatedParenthesis(s));
		 
	}

}
