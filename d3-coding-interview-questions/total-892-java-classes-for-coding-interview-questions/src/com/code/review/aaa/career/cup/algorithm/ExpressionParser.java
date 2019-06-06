package com.code.review.aaa.career.cup.algorithm;

import java.util.Stack;

public class ExpressionParser {
	 //
	 // Evaluation of the arithmetic experience , 
	 // 
	 // input ["17","4","5","2","/","*","-","2","+","9","3","/","+"]
	 // output:17- 4*5/2 +2 +9/3= 14
	 
	 // My Implementation:
	 //  (1) create string operators=+-*/
	 //  (2) stack<String> , 		
	 //  (3) String expr[] = input string
	 //  (4) for (String p : expr) check if operators.indexOf(p)==-1 push itinto stack if it is number
	 //  (5) if is operator, string a = stack.pop() ,string b= stack.pop(), 
	 //  (6) switch case the p , if p=="+" , push a+b to stack, so on so forth
	 
	 
	public static int evalExpression(String[] expr) {
		int retVal = 0;
		String operator="+-*/";
		Stack<String> stack = new Stack<String>();
		for (String p: expr) {
			if (!operator.contains(p)) { // it is number 
				stack.push(p);
			} else { // it is operator, parse result and put into stack
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());
				switch(p) {
				case "+":
					stack.push(String.valueOf(a + b));
					break;
				case "-":
					stack.push(String.valueOf(b - a));    // a is substracting number
					break;
				case "*":
					stack.push(String.valueOf(a * b));
					break;
				case "/":
					stack.push(String.valueOf(b / a));   // a is dividing number 
					break;	
				}
			}
			 
		}
		retVal = Integer.valueOf(stack.pop());
		return retVal;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1[]={"17","4","5","2","/","*","-","2","+","9","3","/","+"};
		System.out.println(evalExpression(s1));
	}

}
