package com.concurrency.code;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableTest2 {

	   public static void main(final String[] arguments) throws InterruptedException, ExecutionException {
	      ExecutorService executor = Executors.newSingleThreadExecutor();

	     
	      Future<Long> result10 = executor.submit(new FactorialService(10));
	      System.out.println("Factorial Service called for 10!");
	     
	      Future<Long> result20 = executor.submit(new FactorialService(20));
	      System.out.println("Factorial Service called for 20!");
	      Long factorial10 = result10.get();
	      System.out.println("10! = " + factorial10);

	      Long factorial20 = result20.get();
	      System.out.println("20! = " + factorial20);

	      executor.shutdown();
	   }  

	 static class FactorialService implements Callable<Long>{
	      private int number;
	      public FactorialService(int number) {
	         this.number = number;
	      }

	      @Override
	      public Long call() throws Exception {
	         return factorial();
	      }

	      private Long factorial() throws InterruptedException{
	         long result = 1; 
	         while (number != 0) { 
	            result = number * result; 
	            number--; 
	            Thread.sleep(100); 
	         } 
	         return result;	
	      }
	   }
}
