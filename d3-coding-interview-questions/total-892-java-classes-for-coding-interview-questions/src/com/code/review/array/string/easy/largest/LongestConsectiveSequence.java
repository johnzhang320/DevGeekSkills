package com.code.review.array.string.easy.largest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.code.review.utils.Utils;

public class LongestConsectiveSequence {
	/**
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

		For example, given [100, 4, 200, 1, 3, 2], the longest consecutive elements sequence should be 
		[1, 2, 3, 4]. Its length is 4.
		
		Your algorithm should run in O(n) complexity.
		
		Solution: Add array to TreeSet, check if arr[i]+1 exist in Set, if exist, arr[i] is start element 
	 * @param args
	 */
	// O(Nlogn)
	public static int LongestConsectiveSequence(int arr[]) {
		if (null==arr || arr.length==0) return 0;
		if (arr.length==1) return 1;
		
		Set<Integer> set = new TreeSet<Integer>();
		int max = 1;
		for (Integer i: arr) set.add(i);
		for (int i=0; i<arr.length; i++) {
            int left = arr[i]-1;
            int right = arr[i]+1;
            int count=1;
            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }
            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }
            max = Math.max(max,count);
        }
		return max;
	}
	/*
	 *  O(N) solution:
	 *  (1) create HashSet and add all elements inside
	 *  (2) check if (A[i]-1) not exists in set, yes, then take it as sequence start point j
	 *  (3) loop check if j is in set, if yes, j++;
	 *  (4) check if j-A[i] > max, if yes, max = j-A[i]
	 */
	public static int findLongestConsectiveSequence(int A[]) {
		if (null==A || 0==A.length) return 0;
		Set<Integer> set =new  HashSet<Integer>();
		for (Integer num:A) {
			set.add(num);
		}
		int max = Integer.MIN_VALUE;
		int start = 0;
		
		for (int i=0;i<A.length;i++) {
			// check if A[i]-1 not exists in set, if yes then A[i] = start;
			if (!set.contains(A[i]-1)) {
				start = A[i];
				// check if sequence element starting with start and increment one
				while (set.contains(start)) {
					start++;
				}
				// store max one
				if (start-A[i]>max) {
					max = start-A[i];
				}
			} 			
		}
		return max;
	}
	
	public static List<Integer> LongestConsectiveSequenceString(int arr[]) {
		List<Integer> result = new ArrayList<Integer>();
		if (null==arr || arr.length==0) return result;
		if (arr.length==1) return result;
		 
		Set<Integer> set = new TreeSet<Integer>();
		
		int max = Integer.MIN_VALUE;
		
		for (Integer i: arr) set.add(i);
		
		List<List<Integer>> buffer = new ArrayList<List<Integer>>();
		
		Iterator<Integer> itr = set.iterator();
		int prev = itr.next();
	    result.add(prev);
		while (itr.hasNext()) {
	        int curr = itr.next();
	        
	        if (curr==prev+1) {
	        	result.add(curr);
	        } else {
	        	List<Integer> temp = new ArrayList<Integer>(result);
	        	buffer.add(temp);
	        	result = new ArrayList<Integer>();
	        	  
	        }
	        prev = curr;
		}
		List<Integer> temp = new ArrayList<Integer>(result);
    	buffer.add(temp);
		int s=0;
		for (List<Integer> l: buffer) {
			if (l.size()>s) {
				s = l.size();
				result = l;
			}
		}
		System.out.println("len="+result.size());
		return result;
	}
	
	/**
	 *  Very simple is 
	 *  int i=0; i < A.length; i++
	 *  curr = A[i];
	 *  left = curr-1;
	 *  right = curr+1;
	 *  while (set.contains(left)
	 *  
	 */
	public static int LCS(int A[]) {
		int count=0;
		int left,right, curr;
		int maxLen = 0;
		Set<Integer> set = new HashSet<Integer>();
		for (Integer i:A) set.add(i);
		for (int i=0;i<A.length;i++) {
			curr = A[i];
			left = curr-1;
			right = curr+1;
			count=1;
			while(set.contains(left)) {
				count++;
				set.remove(left);
				left--; // check again
			}
			while(set.contains(right)) {
				count++;
				set.remove(right);
				right++; // check again
			}
			maxLen = Math.max(maxLen, count);
		}
		return maxLen;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {100, 4, 200, 1, 3, 2};		
		int result = LongestConsectiveSequence(arr);
        result = LCS(arr);	
		System.out.println("LCS="+result);
		
		int arr2[] = {100, 4, 200,201,202, 1, 3, 2,5,6};		
		int result2 = LongestConsectiveSequence(arr2);		
		//System.out.println(result2);
		int arr3[] = {1};		
		int result3 = LongestConsectiveSequence(arr3);		
		//System.out.println(result3);
		 
		System.out.println(LongestConsectiveSequenceString(arr2).toString());
		
		int A[] = {208, 4, 200,201,202, 1, 3,100, 2,203,204,205,206,207,5,6};		
		
		System.out.println(LongestConsectiveSequenceString(A).toString());
		
		int B[] = {208, 4, 200,201,202, 1, 3,100, 2,203,204,205,206,207,5,6,7,8,9};		
		
		System.out.println(LongestConsectiveSequenceString(B).toString());
		
		int C[] = {208, 4, 200,201,202, 1, 3,100, 105,106,2,203,204,107,205,108,109,110,206,207,5,6,7,8,9,101,102,103,104};		
		
		System.out.println(LongestConsectiveSequenceString(C).toString());
		
		System.out.println(LongestConsectiveSequence(C));	
	}

}
