package com.code.review.queue.stack;

import java.util.Stack;

public class StackCalculator {
	/**
	 *  google-interview-questions
 

		design and implement a calculater that can calculate expressions like:
		+ 2 4
		* 8 ( + 7 12)
		( + 7 ( * 8 12 ) ( * 2 ( + 9 4 ) 7 ) 3 )
		
		(PS:all items are space delimetered.)
		
		Example answers
		+ 2 4 => 2 + 4 = 6
		* 8 ( + 7 12) => 8 * ( 7 + 12 ) = 152
		( + 7 ( * 8 12 ) ( * 2 ( + 9 4 ) 7 ) 3 ) => 7+8*12+2*(9+4)*7+3 = 288
		- 2 4 => 4-2 = 2
		/ 3 9 => 9 / 3 = 3		
	 *  (1) 
	 */
	private static int calculator(String s) {
		if (null==s || 0==s.length()) return 0;
		System.out.println(s); 
		String expr[] = s.split(" ");
		String operator = "+-*/";
		String parenthese= "()[]";
		
		Stack<Integer> stack = new Stack<Integer>(); 
		int len = expr.length;
		int a,b;
		for (int i=len-1;i>=0;i--) {
			String t = expr[i];
			if (!operator.contains(t)) {
				if (!parenthese.contains(t)) {   // it is number
					stack.push(Integer.valueOf(t));
				}
			} else {
				while (stack.size()>1) {   // stack.size() >2 , we must make sure it be one
					a = stack.pop();
					b = stack.pop();
					switch (t) {
					case "+":
						stack.push(a + b);
						break;
					case "-":
						stack.push(a - b);    // substraction is second data minus first data
						break;
					case "*":
						stack.push(a * b);
						break;
					case "/":
						stack.push(a / b);    // divid is second be divided by first one
						break;					
					}
				}
			}
		}
		return stack.pop();
		
	}
	private static int calculatorOperatorInRightSide(String s) {
		if (null==s || 0==s.length()) return 0;
		System.out.println(s); 
		String expr[] = s.split(" ");
		String operator = "+-*/";
		String parenthese= "()[]";
		
		Stack<Integer> stack = new Stack<Integer>(); 
		int len = expr.length;
		int a,b;
		for (int i=0;i<len;i++) {
			String t = expr[i];
			if (!operator.contains(t)) {
				if (!parenthese.contains(t)) {   // it is number
					stack.push(Integer.valueOf(t));
				}
			} else {
				while (stack.size()>1) {   // stack.size() >2 , we must make sure it be one
					a = stack.pop();
					b = stack.pop();
					switch (t) {
					case "+":
						stack.push(a + b);
						break;
					case "-":
						stack.push(a - b);    // substraction is second data minus first data
						break;
					case "*":
						stack.push(a * b);
						break;
					case "/":
						stack.push(a / b);    // divid is second be divided by first one
						break;					
					}
				}
			}
		}
		return stack.pop();
		
	}	 
	public static int parseCalculator(String s) {
		if (null==s || 0==s.length()) return 0;		 
		String expr[] = s.split(" ");
		Stack<String> stack = new Stack<String>(); 
		for (String ss:expr) {
			if (!ss.equals(")")) {
			stack.add(ss);
			} else {
				
				StringBuffer buf = new StringBuffer();
				while (!stack.peek().equals("(")) {
					buf.append(stack.pop()+" ");
					
				}
				if (!buf.equals("")) {
					stack.pop(); // remove "("
					int curr = calculatorOperatorInRightSide(buf.toString());
					System.out.println(curr);
					stack.push(String.valueOf(curr));
				}
			}
		}
		return Integer.valueOf(stack.pop());
	}
	
	
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "+ 2 4";
		String s2 = "* 8 ( + 7 12 )";
		String s3 = "( + 7 ( * 8 12 ) ( * 2 ( + 9 4 ) 7 ) 3 )";
		String s4 = "- 2 4";
		String s5 = "/ 3 9";
		String s[] = s2.split(" ");
		int result1 = 7+8*12+2*(9+4)*7+3;
		String s6 = "( + 7 ( / 24 6 ) ( * 2 ( - 4 9 ) 7 ) 3 )";   // 7+24/6+2(9-4)*7+3
		int result2 = 7+24/6+2*(4-9)*7+3;
		/*System.out.println(s1+" => "+calculator(s1));
		System.out.println(s2+" => "+calculator(s2));
		System.out.println(s3+" => "+calculator(s3));
		System.out.println(s4+" => "+calculator(s4));
		 */
		System.out.println("7+8*12+2*(9+4)*7+3 = "+result1);
		System.out.println("7+24/6+2*(4-9)*7+3 = "+result2);
		System.out.println(s3+" => "+parseCalculator(s3));
		
		System.out.println(s6+" => "+parseCalculator(s6));
	}

}
