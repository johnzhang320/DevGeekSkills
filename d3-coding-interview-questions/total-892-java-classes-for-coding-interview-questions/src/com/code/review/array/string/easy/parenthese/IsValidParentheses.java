package com.code.review.array.string.easy.parenthese;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValidParentheses {
	/**
	 *     Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
	 *     determine if the input string is valid.
     *     The brackets must close in the correct order, "()" and "()[]{}" are all 
     *     valid but "(]" and "([)]" are not.
	 * @param args
	 */
	public static boolean isValidParentheses(String str) {
		Map<Character, Character> map = new HashMap<Character,Character>();
		map.put('[', ']');
		map.put('{', '}');
		map.put('(', ')');
		Stack<Character> stack = new Stack<Character>();
		for (int i=0; i<str.length();i++) {
			char occur = str.charAt(i);
			if (map.containsKey(occur)) {
				stack.push(occur);
			} else if (!stack.isEmpty() && map.get(stack.peek())==occur) {
				stack.pop();
			} else {
				return false;
			}
		}
		return stack.isEmpty();
		
	}
	public static boolean isValidParenthesesForWords(String str) {
		Map<Character, Character> map = new HashMap<Character,Character>();
		map.put('[', ']');
		map.put('{', '}');
		map.put('(', ')');
		Stack<Character> stack = new Stack<Character>();
		boolean begin=false;
		for (int i=0; i<str.length();i++) {
			char occur = str.charAt(i);
			if (map.containsKey(occur)) {
				stack.push(occur);
				begin=true;
			} else if (!stack.isEmpty() && map.get(stack.peek())==occur) {
				stack.pop();				 
			} 
		}
		return stack.isEmpty() && begin;  // there is begin and stack empty
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "{}";
		System.out.println(str+", is "+isValidParentheses(str));
		str = "{]";
		System.out.println(str+", is "+isValidParentheses(str));
		
		str = "{This is test}";
		System.out.println(str+", is "+isValidParenthesesForWords(str));
		
		str = "[Hello world}";
		System.out.println(str+", is "+isValidParenthesesForWords(str));
		
		str = "{[Hello max]}";
		System.out.println(str+", is "+isValidParenthesesForWords(str));
		
		str = "Hello max]}";
		System.out.println(str+", is "+isValidParenthesesForWords(str));
		
	}

}
