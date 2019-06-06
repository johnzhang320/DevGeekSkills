package com.code.review.array.string.easy.parenthese;

import java.util.Stack;

public class LongestValidParenthese {
	/**
	 * Given a string containing just the characters '(' and ')', find the length of the longest valid 
	 * (well-formed) parentheses substring.

		For "(()", the longest valid parentheses substring is "()", which has length = 2.
		Another example is ")()())", where the longest valid parentheses substring is "()()", 
		which has length = 4.
		
		Analysis
		
		This problem is similar with Valid Parentheses, which can be solved by using a stack. 
		stack 
	 * @param args
	 */
	public static int longestValidParentheses(String s) {
		Stack<int[]> stack = new Stack<int[]>();
		int result = 0;
	 
		for(int i=0; i<=s.length()-1; i++){
			char c = s.charAt(i);
			if(c=='('){
				int[] a = {i,0};
				stack.push(a);
			}else{
				if(stack.empty()||stack.peek()[1]==1){
					int[] a = {i,1};
					stack.push(a);
				}else{
					stack.pop();
					int currentLen=0;
					if(stack.empty()){
						currentLen = i+1;
					}else{
						currentLen = i-stack.peek()[0];
					}
					result = Math.max(result, currentLen);
				}
			}
		}
	 
		return result;
	}
	/**
	 * Analysis
		
		This problem is similar with Valid Parentheses, which can be solved by using a stack.  
		implementation 
		very simple
		int i = 0 to len loop, add '(' or ')' to
		if arr[0]='(' and arr[1]=')' , rsult+=2; recreate arr[2]; i+=2;
		if arr[0]=')' and arr[1] = ')' i+=2;
		if arr[0]='(' and arr[1] = '(' i+=1;
		if arr[0]=')' and arr[1] = '(' i+=1;
	 */
	 
	 public static int findLongestValidParenthese(String str) {
		 char wind[] = new char[2];
		 int result = 0;
		 for (int i=0; i <str.length()-1;i++) {
			 wind[0]=str.charAt(i);
			 wind[1]=str.charAt(i+1);
			 if (wind[0]=='(' && wind[1]==')') {
				 result+=1;
			 }
		 }
		 return result;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=")()()((()()(";
   	 
    	int result = findLongestValidParenthese(str);
    	System.out.println(result);
    	
    	result = longestValidParentheses(str);
    	
    	System.out.println(result);
	}

}
