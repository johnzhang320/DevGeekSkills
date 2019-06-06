package com.code.review.array.string.easy.largest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsectiveSubArrarys {
	/**
	 * Given a sorted integer array without duplicates, return the summary of 
	 * its ranges for consecutive numbers.

	 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 * implementation:
	 * In external loop i, prev = arr[i], start = prev.toString+"=>"
	 * In internal loop j=i, j< len; if (arr[j] == prev+1) j++, else add buf with start+arr[j].toString  
	 * @param args
	 */
	public List<String> findConsectiveSubArrarys(int arr[]) {
		List<String> results = new ArrayList<String>();
		Integer prev=0;
		String start="";
		for (int i=0; i < arr.length; i++) {
			prev = arr[i];
		    boolean breakout=false;
			start = prev.toString() ;
			for (int j=i+1; j<arr.length;j++) {				 
				if (arr[j]==prev+1) {
					prev = arr[j];
				} else {
					results.add(getTheString(start,prev));
					i = j-1;   // keep O(N} , j-1 , i++
					breakout=true;
					break;
				}
			}
			if (!breakout) {
				results.add(getTheString(start,prev));  // if internal not break out, means last element still sequence 
				break;
			}
		}
		
		return results;
	}
	// even two loops , but cost O(N) because i=j-1, i++
	public List<String> FindConsectiveSubArrays(int A[]) {
		List<String> result = new ArrayList<String>();
		if (null==A || 0==A.length) {
			return result;
		}
		int prev=0;
		String start="";
		
		for (int i=0;i<A.length;i++) {
			prev = A[i];
			start = String.valueOf(A[i]);
			boolean ended=false;
			for (int j=i+1;j<A.length;j++) {
				if (A[j]==prev+1) {
					prev = A[j];
				} else {
					ended = true;
					result.add(getTheString(start,prev));
					i=j-1;    // make O(N) , 
					break;
				}
			}
			if (!ended) {
				result.add(getTheString(start,prev));
				break;
			}
		}
		return result;
	}
	private String getTheString(String start,int end) {
		if (null==start) return "";
		String endstr=String.valueOf(end);
		if (start.equals(endstr)) {
			return start;
		} 
		return start+"->"+endstr;
				
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsectiveSubArrarys ref = new ConsectiveSubArrarys();
		 int arr[] = {0,1,2,4,5,6,7,9,10,11,40,41,55,65,66,67,90};
		 List<String> results = ref.findConsectiveSubArrarys(arr);
 		 System.out.println(results.toString());
		 /**
		  *  Convert List<String> to String[]
		  *  String [] strarray = list.toArray(new String[list.size()]);
		  *  
		  */
		 
		// String[] array = results.toArray(new String[results.size()]);
		// System.out.println(Arrays.toString(array));
 		 results=ref.FindConsectiveSubArrays(arr);
		 System.out.println(results.toString());
	}

}
