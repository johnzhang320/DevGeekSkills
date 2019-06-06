package com.code.review.encode.decode;

public class NumberOfCodeWay {
/**
 *  A message containing letters from A-Z is being encoded to numbers using the following mapping:
	
	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
	
	Given an encoded message containing digits, determine the total number of ways to decode it.
	
	For example,
	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	
	The number of ways decoding "12" is 2. 
	
	Apply dynamic programming dp[i] = dp[i-1]+dp[i-2]
	(1) initialize dp[] = new int[s.length()]
	    dp[0]=1  means at least 1 way to decode
	    how do we initialize dp[1]  
	    check first 2 digits , if it > 26 then s[1]!='0' dp[1]=1 else dp[1]=0
 *      if first 2 digits <=26, s[1]!='0' dp[1]=2 else dp[1]=1
 *  (2) formula 
 *      i = 2 to len, s[i]!='0' dp[i]+=p[i-1]
 *      val =s[i-1,i+1] if val<=26 && val>=10
 *      dp[i]+=dp[i-2] 
 */
	
	public static int numberOfDecodeWays(String s) {
		if(s==null || s.length()==0 || s.charAt(0)=='0')
	        return 0;
		if (s.length()==1)
			return 1;
		int len = s.length();
		int dp[] = new int[len];
		dp[0]=1;
		if (Integer.valueOf(s.substring(0,2))>26) { // first 2 digits >26
			if (s.charAt(1)!='0') {
				dp[1] = 1;  // second digits of first 2 digits is not '0', must be two digits numbers 
			} else {
				dp[1] = 0;  
			}
		} else {  // first two digits <26, could be Letter
			if (s.charAt(1)!='0') {
				dp[1]=2;   // if first 2 digits is 12 , decode to "AB" or "L"  dp[1]=2
			} else {
				dp[1]=1;   // dp[1] =1 means '10', '20', '30'... only one to one mapping, '0' mapping nothing
			}
		}
		for (int i=2; i<len; i++) {
			if (s.charAt(i)!=0) {
				dp[i]+=dp[i-1];
			}
			int val = Integer.valueOf(s.substring(i-1,i+1));
			if (val<=26 && val>=10) {
				dp[i]+=dp[i-2];
			}
		}
		return dp[len-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="2630";
		System.out.println(numberOfDecodeWays(s) );
	}

}
