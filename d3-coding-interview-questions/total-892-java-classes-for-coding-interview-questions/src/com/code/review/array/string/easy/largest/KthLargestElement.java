package com.code.review.array.string.easy.largest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class KthLargestElement {
	/**
	 * find Kth largest element from unsorted array
	 * So far two solutions:
	 * (1) Sorted array and fetch kth element, O(NlogN)
	 * (2) Insert priority queue, most efficient is O(Nlogk) k is kth, 
	 * (3) add k number of element to priority queue, then add one and remove one, finally peek top one
	 * @param args
	 */
	// O(NlogN)
	public static int kthLargest(int[] array, int k) {
		Arrays.sort(array);
		return array[array.length - 1 - k];
	}
	// O(Nlogk)
	public static int kthLargestEfficient(int[] array, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0; i<k+1; i++) { // add k numbers 
			pq.add(array[i]); 
		}
		for(int i=k+1; i<array.length; i++) {
			pq.add(array[i]);     
			pq.poll();             // make priority queue only exist K element, make insert time is constantly logk time
		}
		int kthLargest = pq.peek();
		return kthLargest;
	}
	
	// in same loop add all elements to priorityqueue,  is size > k , poll() out, end of loop , p.peek()
	
	public static int kthLargestEfficient2(int[] array, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0; i<array.length; i++) { // add k numbers 
			pq.offer(array[i]); 
			if (pq.size()>k) {
 				pq.poll();  
			}// make priority queue only exist K element, make insert time is constantly logk time
		}
		int kthLargest = pq.peek();
		return kthLargest;
	}
	
	// Quick Sort n(logn)
	
	
	
	// Add to TreeSet, take Kth
	public static int KthLargestElement(int[] A, int k) {
		Set<Integer> set = new TreeSet<Integer>();
		if (null==A || 0==A.length) return 0;
		if (A.length<k) return 0;
		 
		for (Integer i:A) set.add(i);
		int i=0;	
		int result = 0; 
		Iterator<Integer> itr = set.iterator();
		while (itr.hasNext()) {
			result = itr.next();
			if (i==A.length-k-1) {
				break;
			}
			i++;
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {5,4,3,1,22,24,56,12,23};
		
		System.out.println(kthLargest(arr,4));
		
		System.out.println(kthLargestEfficient(arr,4));
		
		System.out.println(KthLargestElement(arr, 4));
	}

}
