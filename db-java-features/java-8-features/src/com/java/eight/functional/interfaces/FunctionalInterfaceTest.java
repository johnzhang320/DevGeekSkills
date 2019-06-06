package com.java.eight.functional.interfaces;

public interface FunctionalInterfaceTest {
	/**
	 * We can declare our own Functional Interface by defining Single Abstract Method in interface.
	 *Is is possible to define our own Functional Interface? What is @FunctionalInterface? What are the rules to define a Functional Interface?
		Yes, it is possible to define our own Functional Interfaces. We use Java SE 8’s 
		@FunctionalInterface annotation to mark an interface as Functional Interface.
		
		We need to follow these rules to define a Functional Interface:
		
		    Define an interface with one and only one abstract method.
		    We cannot define more than one abstract method.
		    Use @FunctionalInterface annotation in interface definition.
		    We can define any number of other methods like Default methods, Static methods.
		    If we override java.lang.Object class’s method as an abstract method, which does not count as an abstract method.
		
		Is @FunctionalInterface annotation mandatory to define a Functional Interface? 
		What is the use of @FunctionalInterface annotation? Why do we need Functional Interfaces 
		in Java?
		It is not mandatory to define a Functional Interface with @FunctionalInterface annotation.
		If we don’t want, We can omit this annotation. However, if we use it in Functional Interface
		 definition, Java Compiler forces to use one and only one abstract method inside that interface.
		
		Why do we need Functional Interfaces? The type of a Java SE 8’s Lambda Expression is a 
		Functional Interface. Whereever we use Lambda Expressions that means we are using 
		Functional Interfaces.
	 */
	void display();
}	
