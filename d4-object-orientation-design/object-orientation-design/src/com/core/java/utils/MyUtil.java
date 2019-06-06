package com.core.java.utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MyUtil {
	public static <K,V> HashMap<K,V> hashMapInstance() {
		return new HashMap<K,V>();
	}
	public static <K,V> ConcurrentHashMap<K,V> concurrentHashMapInstance() {
		return new ConcurrentHashMap<K,V>();
	}
}
