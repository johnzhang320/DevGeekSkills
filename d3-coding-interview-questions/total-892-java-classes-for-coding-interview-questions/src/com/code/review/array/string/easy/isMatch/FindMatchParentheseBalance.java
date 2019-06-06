package com.code.review.array.string.easy.isMatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 *  amazon-interview-questions

	Find if a mathematical string is balanced in terms of parenthesis. For example 
	"(1+2)" is balanced and "(1+2" is not balanced.
	My Approach:
	Create Map[0].input('(',')'), Map[1].put('[',']')
	current char c is found in map1 or map2, add stack.add(map[i].get(c))
	if stack.peek() == c , stack.pop()
	finally
	if stack.isEmpty() then return true otherwise return false
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
	 /**   leetcode verified !!
     *   (1) Create a map<Character,Character> save [], (), {} 
         (2) Create stack to save "open" parentheses
         (3) loop char at s, if find in map, save to stack,
         (4) if c is not find in map,  get lastchar = stack.peek()
         (5) if c==map.get(lastchar) , i++ else return false;
     */ 
    public static boolean isValid(String s) {
        if (null==s ) return false;
        
        if (s.equals("")) return true;
        Map<Character,Character> map =new HashMap<Character,Character>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        Stack<Character> stack = new Stack<Character>();
        char curr,lastchar;
        for (int i=0;i<s.length();i++) {
            curr = s.charAt(i);
            if (map.containsKey(curr)) {
                stack.push(curr); // save open
            } else {  // current char is not in map, it map be close
                if (!stack.isEmpty()) {
                    lastchar = stack.peek();
                    if (curr == map.get(lastchar))  {  // curr is last char 's close ?
                        stack.pop();                   // pair of open and close
                    } else {
                        return false;                  // not match
                    }
                } else {
                    return false;   // curr is not in map and stack is empty
                }
            }
        }
        return stack.isEmpty();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s= "[()]";
		String s1="()()][";
		String s2="(()";
		String s3="]";
		String s4="";
		
		System.out.println(balanceParent(s));
		System.out.println(balanceParent(s1));
		System.out.println(balanceParent(s2));
		System.out.println(balanceParent(s3));
		System.out.println(balanceParent(s4));
		
		
	System.out.println("-----------isValid(s)----------");
		System.out.println(isValid(s));
		System.out.println(isValid(s1));
		System.out.println(isValid(s2));
		System.out.println(isValid(s3));
		System.out.println(isValid(s4));
		
		
		
	}

}
