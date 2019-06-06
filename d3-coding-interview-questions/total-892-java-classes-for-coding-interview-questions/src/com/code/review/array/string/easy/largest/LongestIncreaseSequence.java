package com.code.review.array.string.easy.largest;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreaseSequence {
	
	  /**
	   * Given an unsorted array of integers, find the length of longest increasing subsequence.

		Example:
		
		Input: [10,9,2,5,3,7,101,18]
		Output: 4 
		Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
		Note:
		
		There may be more than one LIS combination, it is only necessary for you to return the length.
		Your algorithm should run in O(n2) complexity.

    	Thoughts:
		O(nLogN) using binary serach.
		(1) define a List in increase order, which if A[i]>last element of this list(list.get(size-1))
		    then add to list
		(2) if A[i]<=last element of the list , search list to find where it is fit to insert
		(3) run list.set(index , A[i])
		- If not, do binary search with the list and see where the number may fit.
		- Every time, set num to where may fit in the list (find the smallest item from list which also > num)
		Why setting a number in the list?
		The list works as a baseline, which adjusts dynamically: any number less than the baseline won't be able to append.
		However, it can hellp refine (lower) the baseline, which potentially allow future number to append.
		In the end, return the size of list.
     */
    public int longestIncreaseSequence(int[] A) {
      
        if (A == null || A.length == 0) {
            return 0;
        }
        int n =A.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (list.size() == 0 || A[i] > list.get(list.size() - 1)) {
                list.add(A[i]);
            } else {
                int index = binarySearch(list, A[i]);
                list.set(index, A[i]);
            }
        }
        return list.size();
    }
     public int binarySearch(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return list.get(start) >= target ? start : end;
    }
     public static void main(String [] args) {
    	 LongestIncreaseSequence f = new LongestIncreaseSequence();
    	 int A[] = {10,9,2,5,3,7,101,18};
    	 System.out.println(f.longestIncreaseSequence(A));
    	 
    	 int A2[] = {10,9,8,5,0,7,1,18};
    	 System.out.println(f.longestIncreaseSequence(A2));
    	 
    	 int A3[] = {15,14,7,3,1};
    	 System.out.println(f.longestIncreaseSequence(A3));
     }
}