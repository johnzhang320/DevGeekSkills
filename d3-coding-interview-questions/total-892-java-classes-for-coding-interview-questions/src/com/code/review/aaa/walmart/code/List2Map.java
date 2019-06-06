package com.code.review.aaa.walmart.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class List2Map<T>{
	/**
	 *  Convert List to Map, keep original order in Map and iterator Map to return List
	 *  Using index of List as Map Key
	 */
	public Map<Integer, T> list2Map(List<T> list) {
		if (null==list) return null;
		Map<Integer,T> map = new HashMap<Integer,T> ();
		for (int i=0;i<list.size();i++) {
			T value = list.get(i);
			map.put(i, value);
		}
		return map;
	}
	public List<T> iterateMap(Map<Integer,T> map) {
		if (map==null) return null;
		List<T> list = new ArrayList<T>();
		Iterator<Integer>  itr = map.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next();
			T value = map.get(key);
			list.add(value);
		}
		return list;
	}
	public static void main(String args[]) {
		List2Map<String> ref = new List2Map<String>();
		String array[] = {"This", "is", "crazy", "rich","asia", "girl"};
		List<String> list = Arrays.asList(array);
		Map<Integer, String> map =ref.list2Map(list);
		map.forEach((k,v)->System.out.print((k+","+v+" ")));
		list = ref.iterateMap(map);
		list.forEach(x->System.out.print(x+" "));
	}
}
