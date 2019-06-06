package com.code.review.queue.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParentheseBalance {
	/**
	 *     amazon-interview-questions

		Find if a mathematical string is balanced in terms of parenthesis. For example 
		"(1+2)" is balanced and "(1+2" is not balanced.
		implementation
	 * 
	 */
	public boolean parenthesisBalance(String s) {
		if (null==s || 0==s.length()) return false;
		//(1) create map include "(",")" and "[","]"
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put('(',')');
		map.put('[', ']');
		map.put('{', '}');
		Stack<Character> stack = new Stack<Character>();
		for (int i=0; i<s.length();i++) {
			char c=s.charAt(i);
			if (map.containsKey(c)) {
				stack.add(map.get(c));
			} else {
				if (!stack.isEmpty() && c==stack.peek()) {
					stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
