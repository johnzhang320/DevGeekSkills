package com.java.eight.lambda;

public class LambdaTester {
	/**
	 * Following are the important characteristics of a lambda expression −
	•	Optional type declaration − No need to declare the type of a parameter. The compiler can 
	    inference the same from the value of the parameter.
	•	Optional parenthesis around parameter − No need to declare a single parameter in parenthesis. 
		For multiple parameters, parentheses are required.
	•	Optional curly braces − No need to use curly braces in expression body if the body contains a 
		single statement.
	•	Optional return keyword − The compiler automatically returns the value if the body has a single 
		expression to return the value. Curly braces are required to indicate that expression returns
		a value.
		Lambda Expressions Example

	 * @param args
	 */
	public static void main(String[] args) { //with type declaration
		
		
		  LambdaTester tester = new LambdaTester();
		  
		  // MathOperation and its operation
		  MathOperation addition = (int a, int b) -> a + b;
			
	      //with out type declaration
	      MathOperation subtraction = (a, b) -> a - b;
			
	      //with return statement along with curly braces
	      MathOperation multiplication = (int a, int b) -> { return a * b; };
			
	      //without return statement and without curly braces
	      MathOperation division = (int a, int b) -> a / b;
			
	      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
	      System.out.println("addition.operation 10 + 5 = " + addition.operation(10, 5));
	      System.out.println("subtraction.operation 10 - 5 = " + subtraction.operation(10, 5));
	      System.out.println("multiplication 10 x 5 = " + multiplication.operation(10, 5));
	      System.out.println("10 / 5 = " + tester.operate(10, 5, division));
			
	      // GreetingService and its SayMessage method
	      
	      //without parenthesis
	      GreetingService greetService1 = message -> System.out.println("Hello " + message);
			
	      //with parenthesis
	      GreetingService greetService2 = (message) -> System.out.println("Hello " + message);
			
	      greetService1.sayMessage("Mahesh");
	      greetService2.sayMessage("Suresh");
	   }
	   // Define Interface to accept (int a , int b)	
	   interface MathOperation {
	      int operation(int a, int b);
	   }
		
	   interface GreetingService {
	      void sayMessage(String message);
	   }
		
	   private int operate(int a, int b, MathOperation mathOperation){
	      return mathOperation.operation(a, b);
	   }
/**
 * Following are the important points to be considered in the above example.

    Lambda expressions are used primarily to define inline implementation of a functional interface, 
    i.e., an interface with a single method only. In the above example, we've used various types of 
    lambda expressions to define the operation method of MathOperation interface. Then we have
    defined the implementation of sayMessage of GreetingService.

    Lambda expression eliminates the need of anonymous class and gives a very simple yet powerful
    functional programming capability to Java.

 */
}
