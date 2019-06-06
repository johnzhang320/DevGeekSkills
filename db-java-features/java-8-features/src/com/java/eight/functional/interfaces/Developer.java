package com.java.eight.functional.interfaces;

import java.math.BigDecimal;

public class Developer {
	public String name;
	public BigDecimal salary;
	public int age;
	
	
	public Developer(String name, BigDecimal salary, int age) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Developer [name=" + name + ", salary=" + salary + ", age=" + age + "]";
	}
	
	 
	
}
