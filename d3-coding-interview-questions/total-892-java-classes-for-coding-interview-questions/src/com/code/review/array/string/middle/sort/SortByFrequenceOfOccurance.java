package com.code.review.array.string.middle.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SortByFrequenceOfOccurance {
	/**
	 *  bloomberg-lp-interview-questions
 
		Sort elements by frequency, print the elements of an array in the decreasing frequency if 
		2 numbers have same frequency then print the one which came first.
		Input [3,4,5,3,3,4,5,5,5,6,6]
		output (5,3,4)
		
		(1) Create LinkedHashMap<Integer, count>  add all element 
		(2) Create class pair , implement reverse order comparaTo , collections.sort(List<Pair>
		(3) add element from map to List<Pair> 
	 */
	public class Pair implements Comparable {
		int number;
		int count;
		
		public Pair(int number, int count) {
			super();
			this.number = number;
			this.count = count;
		}
	    public boolean Equals(Object obj) {
	    	Pair o1 = this;
			Pair o2 = (Pair) obj;
			if (o1.count==o2.count) {
				return true;
			}
			return false;
	    }
		public int compareTo(Object obj) {
			Pair o1 = this;
			Pair o2 = (Pair) obj;
			if (o1.count<o2.count) {
				return 1;
			} else if (o1.count>o2.count){
				return -1;
			} else {
				return 0;
			}
		}
	}
	public void sortByFrequenceOfOccurance(int A[])  {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer> ();  // kep insert order
		List<Pair> result = new ArrayList<Pair>();
		Set<Integer> set = new HashSet<Integer>();
		for (Integer n: A) {
			if (map.containsKey(n)) {
				map.put(n,map.get(n)+1);
			} else {
				map.put(n,1);
			}
		}
		System.out.println(map.toString());
		Iterator<Integer> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next();
			int count = map.get(key);
			Pair obj = new Pair(key,count);
			if (!set.contains(count)) {
				result.add(obj);
				set.add(count);
			}  
		}
		Collections.sort(result);
		for (Pair s: result) {
			System.out.println(s.number);
		}
	}
    
	public void sortByFrequenceOfOccurance2(int A[])  {
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer> ();  // kep insert order
		 
		Set<Pair> set = new TreeSet<Pair>();
		for (Integer n: A) {
			if (map.containsKey(n)) {
				map.put(n,map.get(n)+1);
			} else {
				map.put(n,1);
			}
		}
		System.out.println(map.toString());
		Iterator<Integer> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next();
			int count = map.get(key);
			Pair obj = new Pair(key,count);
			if (!set.contains(obj)) {				
				set.add(obj);
			}  
		}
		 
		for (Pair s: set) {
			System.out.println(s.number);
		}
	}
	//listDevs.sort((o1, o2)->o2.getName().compareTo(o1.getName()));
	public class  elem {
		Integer number;
		Integer count;
		
		public elem(Integer number, Integer count) {
			super();
			this.number = number;
			this.count = count;
		}
		   public boolean Equals(Object obj) {
		    elem o1 = this;
		    elem  o2 = ( elem ) obj;
				if (o1.count==o2.count) {
					return true;
				}
				return false;
		    }
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		public Integer getCount() {
			return count;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		
		
	}
	public void SortJava8(int A[]) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>() ;
		
		for (Integer n:A) {
			if (map.containsKey(n)) {
				map.put(n,map.get(n)+1);
 			} else {
 				map.put(n,1);
 			}
		}
		List<elem> list = new ArrayList<elem>();
		Iterator<Integer> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next();
			int count = map.get(key);
			elem obj = new elem(key,count);
			if (!list.contains(obj)) {
				list.add(obj);
			}
			 
		}
		list.sort((o1,o2)->o2.getCount().compareTo(o1.getCount()));
		list .forEach((x)->System.out.print(x.getNumber()+"->"+x.getCount()+" ")); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = {3,4,5,3,3,4,5,5,5,6,6};
		SortByFrequenceOfOccurance rf = new SortByFrequenceOfOccurance();
		rf.sortByFrequenceOfOccurance2(A); 
		rf.SortJava8(A);
	}
}
