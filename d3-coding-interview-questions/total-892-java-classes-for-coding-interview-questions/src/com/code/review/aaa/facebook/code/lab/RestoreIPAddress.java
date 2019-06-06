package com.code.review.aaa.facebook.code.lab;

import java.util.ArrayList;

public class RestoreIPAddress {
	/**
	 *  LeetCode – Restore IP Addresses (Java)
	 

		Given a string containing only digits, restore it by returning all possible valid IP
		 address combinations.
		
		For example: given "25525511135",return ["255.255.11.135", "255.255.111.35"].
	 *  Facebook interview question
	 *  Given a string containing only digits, restore it by returning all possible valid IP address 
	 *  combinations.

		A valid IP address must be in the form of A.B.C.D, where A,B,C and D are numbers from 0-255. 
		The numbers cannot be 0 prefixed unless they are 0.
		
		Example:
		
		Given “25525511135”,
		
		return [“255.255.11.135”, “255.255.111.35”]. (Make sure the returned strings are sorted in order)
	 *  
	 *  This is typical Depth First Search using recursive like combination
	 *  Boundary conditions are important
	 * void dfs(ArrayList<ArrayList> result, String s, int start, ArrayList<String> currIPComb);
	 *  (1) currIPComb.size()>4 && start>s.length return
	 *  (2) currIPComb.size()+s.length - start+1 <4 return, allow three spaces for period '.'
	    (3) if currIPComb.size==4 && start==s.length, which means current combination meet IP address format
	        ArrayList<String> tmp = new ArrayList<String>(currIPComb);
	        result.add(tmp); return
	    (4) for (int i=1; i<=3; i++) {
	        1) make sure index within boundary
	        2) make i>1 && s.charAt(start)==0 break;
	        3) sub string value not greater than 255
	        currIPComb.add(sub)
	        dfs(result,s, start+i, currIPComb);
	        currIPComb.remove(currIPComb.size()-1);
	             
	 */
	public ArrayList<ArrayList<String>> IPAddressCombination(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> currIPComb = new ArrayList<String>();
		dfs(result,s,0,currIPComb);
		return result;
		
	}
	public void dfs(ArrayList<ArrayList<String>> result, String s, int start, ArrayList<String> currIPComb) {
		if (currIPComb.size()>4 && start!=s.length()) {
			return;
		}
		if (currIPComb.size()+s.length()-start+1<4) {
			return;
		}
		if (currIPComb.size()==4 && start==s.length()) {
			 ArrayList<String> tmp = new ArrayList<String>(currIPComb);
		     result.add(tmp);
		     return;
		}
		for (int i=1;i<=3;i++) {
			// make sure index in boundary
			if (start+i>s.length()) {
				break;
			}
			// make sure no any IP address has no leading zero
			if (i>1 && s.charAt(start)=='0') {
				break;
			}
			String sub = s.substring(start,start+i);
			if (Integer.valueOf(sub)>255) {
				break;
			}
			currIPComb.add(sub);
			dfs(result,s, i+start,currIPComb);
			currIPComb.remove(currIPComb.size()-1);
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 RestoreIPAddress ref = new RestoreIPAddress();
		 String s="25525511135";
		 ArrayList<ArrayList<String>> result = ref.IPAddressCombination(s);
		 for (ArrayList<String> list:result) {
			 System.out.println("");
			 for (String sc: list) {
				 System.out.print(sc+".");
			 }
		 }
	}

}
