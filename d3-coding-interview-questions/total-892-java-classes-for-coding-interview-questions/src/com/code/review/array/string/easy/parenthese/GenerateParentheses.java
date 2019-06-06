package com.code.review.array.string.easy.parenthese;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	 /**
    DFS
    Thoughts: 
 
	Either put ( or )
	can only put ( when # of ( < n
	can only put ) when # of ) < # of (
	If # of single '(' > 0, then we can put ')'
	If n > 0, we can split: 1. close it with ')'; or 2. add '('
	when n-- becomese = 0 and #p = 0, rst.add
	 */
	List<String> result = new ArrayList<String>();
	public List<String> generateParenthesis(int n) {
	    if (n<=0) return result;
	    List<String> list = new ArrayList<String>();
	    dfs(list,0,0,n);
	    return result;
	}
	public void dfs(List<String> list, int left, int right, int n) {
	    if (left == n && right==n ) {
	        StringBuffer sb = new StringBuffer();
	        for (String s:list) {
	            sb.append(s);
	        }
	        result.add(sb.toString());
	        return;
	    }
	    if (left<n) {
	        list.add("(");
	        dfs(list,left+1,right,n);
	        list.remove(list.size()-1);
	    }
	     if (right<left) {
	        list.add(")");
	        dfs(list,left,right+1,n);
	        list.remove(list.size()-1);
	    }
	}
}
