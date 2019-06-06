package com.code.review.array.string.easy.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertListToMap {
	/**
	 * Convert List to Map and Iterator Map to display same order as List
	 * @param args
	 */
	public static Map<Integer,String> convertListToMap(List<String> list) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		if (null==list) return null;
 		for (int i=0; i<list.size();i++) {
			String value = list.get(i);
			map.put(i, value);
		}
		return map;
	}
	public static List<String> iteratorMap(Map<Integer,String> map) {
		if (null==map) return null;
		List<String> list = new ArrayList<String>();
		for (int i=0; i<map.size();i++) {
			String value = map.get(i);
			list.add(value);
		}
		return list;
	}
 	public static void main(String[] args) {
		// TODO Auto-generated method stub
 		String [] str = {"This","is","nicopingting","my","godness","kind","joyful","sexual","health","Yugo","girl"};
 		List<String> list = Arrays.asList(str); 
 		Map<Integer,String> map = convertListToMap(list);
 		list = iteratorMap(map); 
 		list.forEach(x->System.out.print(x+" "));
	}

}
