package com.code.review.facebook.code.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class MaxInSlidingWindows {
	/**
	 * 
	 * A long array A[] is given to you. There is a sliding window of size w which is moving from the 
	 * very left of the array to the very right. You can only see the w numbers in the window. Each time 
	 * the sliding window moves rightwards 
	 * by one position.

		Example :
		
		The array is [1 3 -1 -3 5 3 6 7], and w is 3.
		Window position 	Max
		  	 
		[1 3 -1] -3 5 3 6 7 	3
		1 [3 -1 -3] 5 3 6 7 	3
		1 3 [-1 -3 5] 3 6 7 	5
		1 3 -1 [-3 5 3] 6 7 	5
		1 3 -1 -3 [5 3 6] 7 	6
		1 3 -1 -3 5 [3 6 7] 	7
		
		Input: A long array A[], and a window width w
		Output: An array B[], B[i] is the maximum value of from A[i] to A[i+w-1]
		Requirement: Find a good optimal way to get B[i]
		
		Note: If w > length of the array, return 1 element with the max of the array.
		My Approach
		If we use two loops i = 0 to a.size()-b and j = i to i+b and i+b<=a.size()-1; 
		we have O((N-b)*b) runtime, we can using reversed Set queue, ensure get
		max from b array always O(1) and insert b log(b) total O(N*logb) and remove first one
	 * 
	 */
	// O(NLogN) is not good enough to be efficient
	public static ArrayList<Integer> slidingMaximum(final List<Integer> a, int b) {
		 
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (null == a || 0==a.size()) return result;
		int len = a.size();
		int max = Integer.MIN_VALUE;
		if (b>len) {
			for (Integer i: a) {
				max = Math.max(max, i);
			}
			result.add(max);
			return result;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<Integer> (b, Collections.reverseOrder());
			
		for (int i=0; i<b;i++) {
			queue.add(a.get(i));			 
		}
		result.add(queue.peek());
		
		for (int i=b; i<a.size();i++) {
			queue.add(a.get(i));
		    queue.remove(a.get(i-b));
		    result.add(queue.peek());
		}
		return result;
	}
	// create LinkedList base deque, in deque we always keep index of maximum element in the Header of 
	// deque, remove all other elements indexes
	public static List<Integer> maxSlidingWindowDeque(int A[], int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (null==A || 0==A.length) {
			return result;
		}
		LinkedList<Integer> deque = new LinkedList<Integer>();
		for (int i=0; i<A.length;i++) {
			 
			if (!deque.isEmpty() && deque.peekFirst()==i-k) { 
				// if head of linkedlist is the index of last element (2 [3,4,5]) i-k means index of 2  )
				// we must remove it
				deque.poll();
			}
			while (!deque.isEmpty() && A[deque.peekLast()]<A[i]) {
				// the value of tail element is smaller than current value A[i], remove tail value index
				deque.removeLast();
			}
			 
			 
			deque.offer(i);
			if (i+1>=k) {
				// Header of deque always keep the maximum value
				result.add(A[deque.peek()]);
			}
		}
		return result;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> a = new ArrayList<Integer>();
		int A[] =  {1, 3, -1, -3,4,-8, -2, 5, 3, 6, 7};
		for (int i = 0; i<A.length; i++) {
			a.add(A[i]);
		}
		
		ArrayList<Integer> result= slidingMaximum(a, 3);
		for (Integer i: result)
		System.out.print(i+" ");
		System.out.println(" ");
		List<Integer> result1=maxSlidingWindowDeque(A, 3);
		for (Integer i: result1)
			System.out.print(i+" ");
	}

}
