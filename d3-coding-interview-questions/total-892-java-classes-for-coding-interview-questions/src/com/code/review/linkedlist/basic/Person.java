package com.code.review.linkedlist.basic;
public class Person {
	 
	private String email;
	private String name;
	private int age;
 
	public Person(String email, String name, int age) {
		this.email = email;
		this.name = name;
		this.age = age;
	}
	
	//Getters, setters 
 
        @Override
        public String toString() {
            return "Person [email=" + email + ", name=" + name + ", age=" + age + "]";
	}
 
}