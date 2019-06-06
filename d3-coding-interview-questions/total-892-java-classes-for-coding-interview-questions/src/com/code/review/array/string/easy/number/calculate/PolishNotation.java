package com.code.review.array.string.easy.number.calculate;

import java.util.Stack;
 

public class PolishNotation {
	/**
	 *  LeetCode – Evaluate Reverse Polish Notation
 

		Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /.
		 Each operand may be an integer or another expression. For example:
		
	    ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	    ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
		implementation 
		put above array string to stack, data will be put first and operator will be put later
		
		
	 */
	public static int arithmaticStack(String token[]) {
		Stack<String> stack = new Stack<String>();
		String operator = "+-*/";
		int retVal=0;
		for (String s: token) {
			if (!operator.contains(s)) {
				stack.push(s);
			} else {
				Integer a = Integer.valueOf(stack.pop());
				Integer b = Integer.valueOf(stack.pop());
				switch (s) {
				case "+":
					stack.push(String.valueOf(a+b));
					break;
				case "-":
					stack.push(String.valueOf(a-b));
					break;
				case "*":
					stack.push(String.valueOf(a*b));
					break;
				case "/":
					stack.push(String.valueOf(a/b));
					break;
				}
			}
		}
		retVal = Integer.valueOf(stack.pop());
		return retVal;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] token = new String[] { "2", "1", "+", "3", "*" };
    	int result = arithmaticStack(token);
     
    	System.out.println(result);
	}

}
