package com.code.review.aaa.yahoo.code.test;

public class RemoveRepeatedCharToOne {
	/**
	 * Remove extra space for a char array in place.
		Example: 
		Input: �I--love-----coding----�
		Output: �I-love-coding�. Make sure the first 13 characters is �I-love-coding�.
	 *  
	 */
	 
	 
	 public static String RemoveExtraSpace(String s) {
	     if (null==s || 0==s.length()) return null;
	   
	     char prev = s.charAt(0); 
	     StringBuffer sb = new StringBuffer();
	     for (int i=1; i<s.length(); i++) {
	         char curr = s.charAt(i);
	         if (curr=='-' && prev!='-' && i==1) {
	             sb.append(prev); 
	         } 
	         if (curr!='-' && prev!='-') {
	           sb.append(curr);
	         } 
	         if (curr!='-' && prev=='-' ) {
	           sb.append("-"+curr);
	         }
	          
	         prev = curr;   
	         
	     }
	     return sb.toString();
	  }
	  public static void main(String[] args) {
	    String s = "I--love-----coding----";
	    
	     String s1 = "I----lov---e-----coding----";
	    
	    System.out.println(RemoveExtraSpace(s));
	    
	     System.out.println(RemoveExtraSpace(s1));
	     String s2 = "I----lov---e-----coding";
	     
	     String s3 = "--I----lov---e-----coding";
	    
	     System.out.println(RemoveExtraSpace(s2));
	     
	     System.out.println(RemoveExtraSpace(s3));
	  }
}
