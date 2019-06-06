package com.code.review.array.string.stack.advance;

import java.util.ArrayList;
import java.util.List;
 

 
public class GenerateParentheses {
	/**
	 * 
	 *  LeetCode � Generate Parentheses (Java)
 

		Given n pairs of parentheses, write a function to generate all combinations of well-formed
		 parentheses.
		
		For example, given n = 3, a solution set is:
		
		"((()))", "(()())", "(())()", "()(())", "()()()"
		
		Java Solution 1 - DFS
		
		This solution is simple and clear. In the dfs() method, left stands for the remaining 
		number of (, right stands for the remaining number of ).s
	 */
	public List<String> generateParenthesis(int n) {
	    ArrayList<String> result = new ArrayList<String>();
	    dfs(result, "", n, n);
	    return result;
	}
	/*
	left and right represents the remaining number of ( and ) that need to be added. 
	When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops. 
	*/
	public void dfs(ArrayList<String> result, String s, int left, int right){
	    if(left > right)
	        return;
	 
	    if(left==0&&right==0){
	        result.add(s);
	        return;
	    }
	 
	    if(left>0){
	        dfs(result, s+"(", left-1, right);
	    }
	 
	    if(right>0){
	        dfs(result, s+")", left, right-1);
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenerateParentheses ref = new GenerateParentheses();
		List<String> list =  ref.generateParenthesis(3); 
		for (String s:list) {
			System.out.println(s);
		}
	}

}
