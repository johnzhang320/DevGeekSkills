package com.design.pattern.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite pattern is used where we need to treat a group of objects in similar way as a single object.
 *  Composite pattern composes objects in term of a tree structure to represent part as well as whole hierarchy. 
 *  This type of design pattern comes under structural pattern as this pattern creates a tree structure of group of objects.

This pattern creates a class that contains group of its own objects. This class provides ways to modify its group of same objects.

We are demonstrating use of composite pattern via following example in which we will show employees hierarchy of an organization.
 * @author jianzhang
 *
 */
	public class Employee {
		   private String name;
		   private String dept;
		   private int salary;
		   private List<Employee> subordinates;

		   // constructor
		   public Employee(String name,String dept, int sal) {
		      this.name = name;
		      this.dept = dept;
		      this.salary = sal;
		      subordinates = new ArrayList<Employee>();
		   }

		   public void add(Employee e) {
		      subordinates.add(e);
		   }

		   public void remove(Employee e) {
		      subordinates.remove(e);
		   }

		   public List<Employee> getSubordinates(){
		     return subordinates;
		   }

		   public String toString(){
		      return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary+" ]");
		   }   
}
