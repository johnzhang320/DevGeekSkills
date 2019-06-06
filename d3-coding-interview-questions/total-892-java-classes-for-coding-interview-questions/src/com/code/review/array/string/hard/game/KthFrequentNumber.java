package com.code.review.array.string.hard.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class KthFrequentNumber {
	/**
	 *  LeetCode – Top K Frequent Elements (Java)
 

		Given a non-empty array of integers, return the k most frequent elements.
		my approach
		(1) create pair data class with reverse comparator implements interface comparable
		(2) add all elements into map<element key, count> 
		(3) create pair instance (elemkey and count) and add to TreeSet
		(4) loop kth time get kth frequent element 
	 * @param args
	 */
	public class Pair implements Comparable {
		public int data;
		public int count;
		// reverse comparator created
		public int compareTo(Object o) {
			if (o==null) return -1;
			Pair o1 = this;
			Pair o2 = (Pair) o;
			return o2.count-o1.count;
		}
		public Pair(int data, int count) {
			super();
			this.data = data;
			this.count = count;
		}
		
	}
	public int KthFrequenceNumber(int A[],int k) {
		Set<Pair> set = new TreeSet<Pair>();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (Integer key: A) {
			if (map.containsKey(key)) {
				map.put(key, map.get(key)+1);
			} else {
				map.put(key,1);
			}
		}
		
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			Pair pair = new Pair(entry.getKey(),entry.getValue());
			set.add(pair);
		}
		int i=1;
		for (Pair p:set) {
			if (i==k) {
				return p.data;
			}
			i++;
		}
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KthFrequentNumber ref = new KthFrequentNumber();
		int A[]={1,2,2,5,3,4,6,7,3,3,4,4,8,4,9};
		int k=1;
		System.out.println(k+"th frequence data ="+ref.KthFrequenceNumber(A, k));
		k=2;
		System.out.println(k+"th frequence data ="+ref.KthFrequenceNumber(A, k));
		k=3;
		System.out.println(k+"th frequence data ="+ref.KthFrequenceNumber(A, k));
	}

}
