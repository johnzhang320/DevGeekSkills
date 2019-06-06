package com.java.eight.streams.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class SortList {
	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "Mahesh", 12));
		list.add(new Student(2, "Suresh", 15));
		list.add(new Student(3, "Nilesh", 10));
		list.add(new Student(4, "Leo Smith", 15));
		list.add(new Student(5, "Sarah", 14));
		list.add(new Student(6, "Linda", 16));
		list.add(new Student(7, "Alex", 17));
		list.add(new Student(8, "Bob", 15));
		list.add(new Student(9, "Amanda", 18));
		
		System.out.println("---Natural Sorting by Name---");
		List<Student> slist = list.stream().sorted().collect(Collectors.toList());
		slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));
		
		System.out.println("---Natural Sorting by Name in reverse order---");
		slist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));		
		
		System.out.println("---Sorting using Comparator by Age---");
		slist = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
		slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));
		
		System.out.println("---Sorting using Comparator by Age with reverse order---");
		slist = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());
		slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));
	}
} 