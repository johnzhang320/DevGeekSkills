package com.code.review.array.string.easy.merge;

import java.util.ArrayList;

public class InsertInterval {
	/**
	 * 
	 *  LeetCode � Insert Interval
 
		
		Problem:
		
		    Given a set of non-overlapping & sorted intervals, insert a new interval into the 
		    intervals (merge if necessary).
		
		Example 1:
		Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
		
		Example 2:
		Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
		
		This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
		
		Thoughts of This Problem
		
		Quickly summarize 3 cases. Whenever there is intersection, created a new interval.
		
		insert-interval
	 */
	/**
	 * Definition for an interval.
	 * (1) Create interval class add all pair of array to intervals
	 * (2) loop intervals list as interval
	 * (3) if interval.end < newInterval.start , add interval to result
	 * (4) if interval.start > newInterval.end, add interval to result
	 * (5) if interval within newInterval (interval.start<=newInterval.end or interval.end>=newInterval.start
	 * 
	 */
	public class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	 }
	
	 
	    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
	 
	        ArrayList<Interval> result = new ArrayList<Interval>();
	 
	        for(Interval interval: intervals){
	            if(interval.end < newInterval.start){
	                result.add(interval);
	            }else if(interval.start > newInterval.end){
	                result.add(newInterval);
	                newInterval = interval;        
	            }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
	                newInterval = new Interval(Math.min(interval.start, newInterval.start), 
	                		                   Math.max(newInterval.end, interval.end));
	            }
	        }
	 
	        result.add(newInterval); 
	 
	        return result;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
