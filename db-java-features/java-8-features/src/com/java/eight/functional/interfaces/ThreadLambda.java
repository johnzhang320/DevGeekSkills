package com.java.eight.functional.interfaces;

public class ThreadLambda {
	/**
	 * To Support lambda expressions in Java 8, they introduced Functional Interfaces.

		An interface which has Single Abstract Method can be called as Functional Interface.
		
		Runnable, Comparator,Cloneable are some of the examples for Functional Interface. 
		We can implement these Functional Interfaces by using Lambda expression.
	 * @param args
	 */
	
	public static void main(String[] args) {
		//This is the old way of creating a Thread.
		Thread t =new Thread(new Runnable(){
			   public void run(){
			     System.out.println("Runnable implemented without using Lambda Expression");
			   }
		});
		t.start();
		//As Runnable is having Single Abstract Method, we can consider this as a Functional 
		// Interface and we can use Lambda expression like below.
		Thread t2 = new Thread(()->{System.out.println("Runnable implemented by using Lambda Expression");
		});
		t2.start();
		//Here instead of passing Runnable object we just passed lambda expression.
	}

}
