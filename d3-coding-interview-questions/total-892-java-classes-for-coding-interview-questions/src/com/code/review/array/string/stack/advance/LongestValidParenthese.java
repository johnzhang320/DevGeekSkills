package com.code.review.array.string.stack.advance;

import java.util.Arrays;
import java.util.Stack;

public class LongestValidParenthese {
	/**
	 * Given a string containing just the characters '(' and ')', find the length of the longest valid 
	 * (well-formed) parentheses substring.

		For "(()", the longest valid parentheses substring is "()", which has length = 2.
		Another example is ")()())", where the longest valid parentheses substring is "()()", 
		which has length = 4.
		
		Analysis
		
		(1) This problem is similar with Valid Parentheses, which can be solved by using a stack. 
		(2) case one if stack.isEmpty and A[i] = ')' continue else if A[i]=="(" , stack.push("(")
		(3) if !stack.isEmpty and stack.peek()=="(" and A[i]==")" stack.pop() and stack.pop(); count+=2 
		
		we can 
		
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
				 result+=2;
			 }
		 }
		 return result;
	 }
	 /**
	  * 
	 	
		Analysis
		
		(1) This problem is similar with Valid Parentheses, which can be solved by using a stack. 
		(2) case one if stack.isEmpty and A[i] = ')' continue else if A[i]=="(" , stack.push("(")
		(3) if !stack.isEmpty and stack.peek()=="(" and A[i]==")" stack.pop() and stack.pop(); count+=2 
	 * @param args
	 */
	public static int longestValid(String s) {
		if (null==s || s.length()==0) return -1;
		StringBuffer buf = new StringBuffer();
		Stack<Integer> stack = new Stack<Integer>();
		int count=0;
		for (int i=0;i<s.length();i++) {
			char ch = s.charAt(i);
			if (stack.isEmpty() ) {
				if (ch==')') {
					stack.push(i);
				   continue;
				} else if (ch=='(') {
					 
					stack.push(i);
				}
			} else {
				/**
				 *  stack always store "("
				 */
				if (!stack.isEmpty()) {
					if (ch==')') {
						stack.pop();
						count+=2;
						
					} else if (ch=='(') {
						stack.push(i);
					}
				}
			}
		}
		 
		return count;
	}
	/**
	 * Really password the leetcode test case
	 * @param s
	 * @return
	 */
	 public int longestValidParenthesesLeetCode(String s) {
	        if (null==s || s.length()==0) return 0;
	        s = s.trim();
	        if  (s.length()==0) return 0;    
	         int maxans = 0;
	        Stack<Integer> stack = new Stack<>();
	        stack.push(-1);
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == '(') {
	                stack.push(i);
	            } else {
	                stack.pop();
	                if (stack.empty()) {
	                    stack.push(i);
	                } else {
	                    maxans = Math.max(maxans, i - stack.peek());
	                }
	            }
	        }
	        return maxans;
	    }

	public class CN {
		public char ch;
		public int j;
		public CN(char ch,int j) {
			this.ch = ch;
			this.j = j;
		}
	}
	public static String deleteInvalidChar(char A[]) {
		if (null==A || A.length==0) return null;
		StringBuffer buf = new StringBuffer();
		Stack<Integer> stack = new Stack<Integer>();
		int count=0;
		for (int i=0;i<A.length;i++) {
			char ch = A[i];
			if (stack.isEmpty() ) {
				if (ch==')') {
					A[i]=' ';
				   continue;
				} else if (ch=='(') {
					 
					stack.push(i);
				}
			} else {
				/**
				 *  stack always store "("
				 */
				if (!stack.isEmpty()) {
					if (ch==')') {
						stack.pop();
					} else if (ch=='(') {
						stack.push(i);
					}
				}
			}
		}
		while (!stack.isEmpty()) {
			int i = stack.pop();
			A[i]=' ';
		}
		 
	 
		String result = new String( A ).replace(" ","");
		System.out.println(result); 
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=")()()((()()(()(";
   	 
    	int result = findLongestValidParenthese(str);
    	System.out.println(result);
    	
     
    	
    	
    	result = longestValid(str);
    	System.out.println(result);
    	
    	 str=")(())()(()(((((((";
    	 
    	//result = longestValidParentheses(str);
    	
    	//System.out.println(result);
    	
    	
    	result = longestValid(str);
    	System.out.println(result);
    	
    	String str2="))(()))())))(())))(((((((";
    	System.out.println(deleteInvalidChar(str2.toCharArray()).length());
	}

}
