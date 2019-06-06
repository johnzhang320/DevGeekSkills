package com.code.review.array.string.hard.zigzag;

import java.util.LinkedList;

public class ProperIndentation {
	/**
	 * Pretty print a json object using proper indentation.

	    Every inner brace should increase one indentation to the following lines.
	    Every close brace should decrease one indentation to the same line and the following lines.
	    The indents can be increased with an additional ‘\t’

		Example 1:
		
		Input : {A:"B",C:{D:"E",F:{G:"H",I:"J"}}}
		Output : 
		{ 
		    A:"B",
		    C: 
		    { 
		        D:"E",
		        F: 
		        { 
		            G:"H",
		            I:"J"
		        } 
		    }     
		}
		
		Example 2:
		
		Input : ["foo", {"bar":["baz",null,1.0,2]}]
		Output : 
		[
		    "foo", 
		    {
		        "bar":
		        [
		            "baz", 
		            null, 
		            1.0, 
		            2
		        ]
		    }
		]
		
		[] and {} are only acceptable braces in this case.
		
		Assume for this problem that space characters can be done away with.
		
		Your solution should return a list of strings, where each entry corresponds to a single line. The strings should not have “\n” character in them.
	 * @param args
	 */
	public static String indentPrint(String s) {
		if (null==s || 0==s.length()) {
			return null;
		}
		StringBuffer brace = new StringBuffer();
		brace.append("\n");
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i<s.length();i++) {
			char c = s.charAt(i);
			String sc = Character.toString(c);
		 
			if (c=='{' || c=='[') {				 
				sb.append(brace.toString()+sc);
				brace.append("\t");
				sb.append(brace.toString());
			} else if (c==','){
				sb.append(sc);				
				sb.append(brace.toString());
				
			} else if (c=='}' || c==']'){
				brace = removeLastChar(brace);
				sb.append(brace.toString()+sc);
			} else {
				sb.append(sc);
			}
		} 
		return sb.toString();
	}
	
	public static StringBuffer removeLastChar(StringBuffer sb) {
		if (sb.length()>0) {
			sb.setLength(sb.length()-1);
		}
		return sb;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "{\n\tA:\"B\",\n\tC:\n\t{\n\t\tD:\"E\",F:{G:\"H\",I:\"J\"}}}";
		
		String s = "{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}";
		 
		String result =  indentPrint(s);
	    
		System.out.println(result);
		
		System.out.println("-------------------------------------------");
		
		String sq =  "[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]";
		
		result =  indentPrint(sq);
	    
		System.out.println(result);
		
	}

}
