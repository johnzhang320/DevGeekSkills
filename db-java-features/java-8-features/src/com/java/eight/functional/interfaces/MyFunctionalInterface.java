package com.java.eight.functional.interfaces;

public class MyFunctionalInterface {
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
	 // using @FunctionalInterface compiler enforce interface contains only one abstract method, more 
	 // one , it causes compiler error
	 // If we define only one method without this annotation, compiler also accepts it works as Lambda 
	 // expression. more than one lambda expression will be confused
	 @FunctionalInterface
	 public interface AnnotationFunctionalInterface {
		 void anotherDisplay();
	 }
	 // Even in Functional interface, we can use default method implementation
	 @FunctionalInterface
	 public interface AnnotationFunctionalInterface2 {
		 void myDisplay();
		 default void show() {
			 System.out.println("Display from default method in Annotation Functional Interface");
			 
		 }
		 static void showStatic() {
			 System.out.println("Display from static  method in Annotation Functional Interface");
		 }
	 }
	 public interface myFunctionalInterface {
		 int operate(int a, int b);
	 }
	 /**
	  * Lambda Expression is a Functional Interface. Whereever we use Lambda Expressions that means we 
	  * are using Functional Interfaces.
	  * @param args
	  */
	  public static void main(String[] args){
		  //Old way using anonymous inner class
	     FunctionalInterfaceTest fit = new FunctionalInterfaceTest(){   // define functional interface outside of current class
	        public void display(){
	           System.out.println("Display from old way");
	     }};
	     fit.display();//outputs: Display from old way
	     //Using lambda expression
	     FunctionalInterfaceTest newWay = () ->System.out.println("Display new Lambda Expression from outside functional interface");
	     
	     newWay.display();//outputs : Display from new Lambda Expression
	      
	     AnnotationFunctionalInterface annoFi= () -> System.out.println("Display new Lambda Expression from inside annotation function interface");
	     annoFi.anotherDisplay();
	     
	     AnnotationFunctionalInterface2 showfi = ()->System.out.println("Display lambda expression from myDisplay");
	     showfi.myDisplay();
	     // Here is default function in interface
	     showfi.show();
	     // Here is static function in interface
	     AnnotationFunctionalInterface2.showStatic();
	     
	     myFunctionalInterface myfi= (int a,int b) ->{ return a*b; };
	     
	     myFunctionalInterface myfi2= (int a,int b) ->a+b ;
	     System.out.println("myFunctionalInterface="+ myfi.operate(5, 8));
	  }
}
