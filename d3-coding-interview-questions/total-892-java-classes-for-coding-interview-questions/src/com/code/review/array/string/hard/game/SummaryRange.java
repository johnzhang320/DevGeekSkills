package com.code.review.array.string.hard.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.code.review.array.string.easy.largest.ConsectiveSubArrarys;

public class SummaryRange {

	/**
	 * Given a sorted integer array without duplicates, return the summary of 
	 * its ranges for consecutive numbers.

	 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 * implementation:
	 * In external loop i, prev = arr[i], start = prev.toString+"=>"
	 * In internal loop j=i, j< len; if (arr[j] == prev+1) j++, else add buf with start+arr[j].toString  
	 * @param args
	 */
	public static List<String> SummaryRange(int arr[]) {
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
					results.add(start+"->"+String.valueOf(prev));
					i = j-1;   // keep O(N} , j-1 , i++
					breakout=true;
					break;
				}
			}
			if (!breakout) {
				results.add(start+"->"+String.valueOf(prev));  // if internal not break out, means last element still sequence 
				break;
			}
		}
		
		return results;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConsectiveSubArrarys ref = new ConsectiveSubArrarys();
		 int arr[] = {0,1,2,4,5,6,7,9,10,11,40,41};
		 List<String> results = SummaryRange(arr);
 		 
		 /**
		  *  Convert List<String> to String[]
		  *  String [] strarray = list.toArray(new String[list.size()]);
		  *  
		  */
		 
		 String[] array = results.toArray(new String[results.size()]);
		 System.out.println(Arrays.toString(array));
	}

}
