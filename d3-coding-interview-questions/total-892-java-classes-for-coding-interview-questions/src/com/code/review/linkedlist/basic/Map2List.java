package com.code.review.linkedlist.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Map2List<K,V>{
	/**
	 * Convert Map to List 
	 * @param args
	 */
	class keypair<K,V> {
		public K key;
		public V value;
		public keypair(K key, V value) {
			this.key = key;
			this.value=value;
		}
		public String toString() {
			return "key="+key+",value="+value;
		}
	}
	public List<keypair> map2List(Map<K,V> map) {
		if (null==map) return null;
		List<keypair> list = new ArrayList<keypair>();
		for (Map.Entry<K, V> entry: map.entrySet()) {
			keypair<K,V> kp = new keypair<K,V>(entry.getKey(),entry.getValue());
			list.add(kp);
			 
		}
		return list;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List2Map<String> ref = new List2Map<String>();
		String array[] = {"This", "is", "crazy", "rich","asia", "girl"};
		List<String> list = Arrays.asList(array);
		Map<Integer, String> map =ref.list2Map(list);
		Map2List<Integer,String> ref2 = new Map2List<Integer,String>();
		List<Map2List.keypair> list2 = ref2.map2List(map);
		list2.forEach(x->System.out.print(x.toString()+ " "));
	}

}
