package com.code.review.aaa.career.cup.array.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 *  amazon-interview-questions

	Find if a mathematical string is balanced in terms of parenthesis. For example 
	"(1+2)" is balanced and "(1+2" is not balanced.
	My Approach:
	(1) Create Map[0].input('(',')'), Map[1].put('[',']')
	(2) current char c is found in map1 or map2, add stack.add(map[i].get(c))
	(3) if stack.peek() == c , stack.pop()
	(4) finally	if stack.isEmpty() then return true otherwise return false
 *
 */

public class FindMatchParentheseBalance {
	public static boolean balanceParent(String s) {
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put('(',')');
		map.put('[', ']');
		Stack<Character> stack = new Stack<Character>();
		boolean open = false;
		for (int i=0; i<s.length();i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				stack.push(map.get(c));
				open=true;
			} else {
				if (!stack.isEmpty() && c==stack.peek()) {
					stack.pop();
				}
			}
		}
		if (!open) {
			return true;
		}
		return stack.isEmpty();
	}
	
	/**
	 *  (1) Create map to contain parenthese open as key and close as value, create Stack<Character>,map<Character,Charater>
	 *  (2) create map.put('[',']') ......
	 *  
	 *  (3) i =0; i<s.length();i++ , if (map.containsKey(s.charAt(i)) 
	 *    
	 *  
	 */
	public static boolean isValidate(String s) {
		Map<Character,Character> map = new HashMap<Character,Character>();
		map.put('[', ']');
		map.put('{', '}');
		map.put('(', ')');
		Stack<Character> stack = new Stack<Character>();
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				stack.push(c);
			} else if (map.containsValue(c)){
				 
				if (!stack.isEmpty() && map.get(stack.peek()) == c) {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s= "[(1+2)+5]+4";
		String s1="(1+5)]";
		String s2="((1+5)";
		
		System.out.println(isValidate(s));
		System.out.println(isValidate(s1));
		System.out.println(isValidate(s2));
	}

}
