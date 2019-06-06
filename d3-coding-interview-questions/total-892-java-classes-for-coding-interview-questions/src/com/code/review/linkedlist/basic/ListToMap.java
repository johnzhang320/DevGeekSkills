package com.code.review.linkedlist.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListToMap<T> {
	/**
	 * Once Convert to Map, keep the order
	 * (1) Using traditional way , using index of list as Map key and value as Map Value
	 * (2) Using Java 8 stream and collecter
	 * @param args
	 */
	
	public Map<Integer,T> convertListToMap(List<T> list) {
		Map<Integer,T> map = new HashMap<Integer,T>();
		for (int i=0;i<list.size();i++) {
			map.put(i, list.get(i));
		}
		return map;
	}
	/**
	 * Using Java 8 stream and collector
	 * @param list
	 * @return
	 */
	public static void Java8List2Map(List<String> list) {
		System.out.println(" Java 8 , Not keep original order of list by HashMap ");
		Map<String, Object> map = list.stream().collect(Collectors.toMap(Function.identity(), s->s));
		map.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y+" "));
		
		System.out.println(" Java 8 , keep original order of list by using LinkedHashMap ");
		Map<String, Object> map2 = list.stream().collect(Collectors.toMap(
				s->s, 
				s->s,
				(oldValue, newValue) -> oldValue, 
				LinkedHashMap::new));
 		map2.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y+" "));
 		 
 		
		/**
		 *  Java 8 , keep original order of list by using index number
		 */
		System.out.println("Java 8 , keep original order of list by using index number ");
		  AtomicInteger counter = new AtomicInteger();
		  Map<Integer,String> map3= list.stream()
		            .collect(Collectors.toMap( (s) -> counter.incrementAndGet(),(s) -> s));
		  map3.forEach((x, y) -> System.out.println("Key: " + x +", value: "+ y));
		  
		  
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s[] = {"This","is","crazy","rich","big","mouth"};   // rich is duplicated
		List<String> list = new ArrayList<String>(Arrays.asList(s));
		ListToMap<String> ref = new ListToMap<String>();
		Map<Integer,String> map = ref.convertListToMap(list);
		map.forEach((k,v)->System.out.print("value="+k+",value="+v+" "));
		
		ref.Java8List2Map(list);
		
		
	}

}
