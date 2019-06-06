package com.java.eight.lambda;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestSort {
	public static void main(String[] args) {
	
		List<Developer> listDevs = getDevelopers();
		System.out.println("Before Sort");
	
		for (Developer developer : listDevs) {
			System.out.println(developer);
		}
	
		System.out.println("After Sort by Age");
	
		//lambda here!
		listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());
	
		//java 8 only, lambda also, to print the List from list instance listDevs , developer must implement toString()
		listDevs.forEach((developer)->System.out.println(developer));
		
		System.out.println("After Sort by name");
		//lambda o1 and o2 dynamic determined by getter return
		listDevs.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
		 
		//java 8 only, lambda also, to print the List
		listDevs.forEach((developer)->System.out.println(developer));
		
		System.out.println("After Sort by Salary");
		listDevs.sort((o1, o2)->o1.getSalary().compareTo(o2.getSalary()));
		
		listDevs.forEach((developer)->System.out.println(developer));
	}

	private static List<Developer> getDevelopers() {
	
		List<Developer> result = new ArrayList<Developer>();
	
		result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
		result.add(new Developer("alvin", new BigDecimal("80000"), 20));
		result.add(new Developer("jason", new BigDecimal("100000"), 10));
		result.add(new Developer("iris", new BigDecimal("170000"), 55));
	
		return result;
	
	}
}

