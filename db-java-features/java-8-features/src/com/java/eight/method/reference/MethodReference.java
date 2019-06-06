package com.java.eight.method.reference;

import java.util.ArrayList;
import java.util.List;

public class MethodReference {
	/**
	 * Method references help to point to methods by their names. A method reference is described 
	 * using :: (double colon) symbol. A method reference can be used to point the following types 
	 * of methods −

	    Static methods
	    Instance methods
	    Constructors using new operator (TreeSet::new)

	 * @param args
	 */
	public void display(List<String> list) {
		int i=0;
		for(String s: list) {
			i++;
			System.out.println("Line "+i+" "+s);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  List<String> names = new ArrayList<String>();
		
	      names.add("Mahesh");
	      names.add("Suresh");
	      names.add("Ramesh");
	      names.add("Naresh");
	      names.add("Kalpesh");
		/**
		 * Here we have passed System.out::println method as a static method reference.	
		 */
	      names.forEach(System.out::println);
	      
	      
	      
	     
	}

}
