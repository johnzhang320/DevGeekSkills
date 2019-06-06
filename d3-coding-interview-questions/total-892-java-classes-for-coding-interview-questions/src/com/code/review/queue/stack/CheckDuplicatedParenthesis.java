package com.code.review.queue.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CheckDuplicatedParenthesis {
	/**
	 *  amazon-interview-questions
 
		give an algorithm for finding duplicate parenthesis in a expression.
		
		example :
		
		(( a + b ) + (( c + d )))  output true
	 *  (( a + b ) + ( c + d ))    output false
	 *   
	 */
	public static boolean isDuplicatedParenthesis(String s) {
		if (null==s || 0==s.length()) return false;
		
		Stack<Character> stack = new Stack<Character>();
	
		for (int i=0 ; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c==' ') {
				continue; // filter out space
			} else {
				stack.push(c);
				if (c==')') {  // find close parenthesis
					char find = ' ';
					String ss="";
					
					while (!stack.isEmpty() && stack.peek()!='(') { // keep going until find opening
						find=stack.pop();
						ss =find+ss;
					}
					if (stack.isEmpty()) break;
					find = stack.pop();
				
					if (find=='(' && !stack.isEmpty()) {
						char repeatedOpen = stack.pop();  // if it exists
						char repeatedClose = ' ';
						if ((i+1)<s.length()) {
						   repeatedClose = s.charAt(i+1); 
						  
						}
						if (repeatedClose!=' ' && repeatedOpen=='(' && repeatedClose==')') { // repeated open and close exist
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
		String s = "(( a + b ) + (( c + d )))";
		System.out.println(s+" duplicated parenthesis is "+isDuplicatedParenthesis(s));
		String s1 = "(( a + b ) + ( c + d ))";
		System.out.println(s1+" duplicated parenthesis is "+isDuplicatedParenthesis(s1));
	}

}
