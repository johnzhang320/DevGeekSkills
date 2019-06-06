package com.code.review.utils;

 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;


 

public class Lambda  {	
 	
	interface printMsg {
	   void say(String message);
	}
	public static void print(String msg) {	
		printMsg printStr = (message) -> System.out.println(message);
		printStr.say(msg);
	}
	
	@Test 
	public void testMsg() {
		print("Hello world");
	}
   
	public void print(List list) {
		System.out.println("");
		list.forEach((message)->System.out.print(message+" "));	
		System.out.println("\n");
	}
	
	@Test	 
	public void testPrintStringList() {
		  print("Test Print List<String>");
		  List<String> names1 = new ArrayList<String>();
	      names1.add("Mahesh ");
	      names1.add("Suresh ");
	      names1.add("Ramesh ");
	      names1.add("Naresh ");
	      names1.add("Kalpesh ");
	      sort(names1);
	      print(names1);
	}  
	@Test
	public void testPrintIntegerList() {
			 
	      print("Test Print List<Integer>");
		  List<Integer> names1 = new ArrayList<Integer>();
	      names1.add(123);
	      names1.add(3423);
	      names1.add(524);
	      names1.add(234);
	      names1.add(5234);
	       
	      print(names1);
	      
	}
	@Test
	public void testPrintCharacterList() {
			 
	      print("Test Print List<Character>");
		  List<Character> names1 = new ArrayList<Character>();
	      names1.add('1');
	      names1.add('a');
	      names1.add('L');
	      names1.add('I');
	      names1.add('S');
	      names1.add('T'); 
	      print(names1);
	      
	}
	public void print(Object A[]) {
		ArrayList<Object> arrayList = new ArrayList<Object>(Arrays.asList(A));
		print(arrayList);	
	}
	
	private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList(
		    Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class));
	public static boolean isWrapperType(Class clazz) {
		return WRAPPER_TYPES.contains(clazz);
	}
	
	@Test
	public void testPrintisWrapperType() {
			 
	   print("Test isWrapperType");
	   final Class clazz;
       
       clazz = int.class;
       System.out.println(clazz.isPrimitive());
	   
       System.out.println(isWrapperType(clazz));
	}
	
	@Test
	public void testPrintIntegerArray() {
			 
	      print("Test Print Array Integer");
		  Integer A[]={22,44,2,4,5,7,1,6,77,132};
		  Arrays.sort(A);
	      print(A);
	      
	}
	
	
	public void sort(List<String> names){
	      Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
	}
	 
}
