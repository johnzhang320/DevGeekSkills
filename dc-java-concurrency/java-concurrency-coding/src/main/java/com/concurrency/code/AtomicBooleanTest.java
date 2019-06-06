package com.concurrency.code;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {
	public static void main(final String[] arguments) throws InterruptedException {
		 
	      final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
	      new Thread("Thread 1") {
	         public void run() {
	            while(true){
	               System.out.println(Thread.currentThread().getName() 
	                  +" Waiting for Thread 2 to set Atomic variable to true. Current value is "
	                  + atomicBoolean.get());
	                  try {
	                	  Thread.sleep(100);
	                  } catch (InterruptedException e) {}
	               if(atomicBoolean.compareAndSet(true, false)) {
	                  System.out.println("Done!");
	                  break;
	               }
	            }};
	      }.start();

	      new Thread("Thread 2") {
	         public void run() {
	        	 try {
	              	  Thread.sleep(1000);
	                } catch (InterruptedException e) {}
	            System.out.println(Thread.currentThread().getName() + ", Atomic Variable: " +atomicBoolean.get()); 
	            System.out.println(Thread.currentThread().getName() +" is setting the variable to true ");
	            atomicBoolean.set(true);
	            System.out.println(Thread.currentThread().getName() + ", Atomic Variable: " +atomicBoolean.get()); 
	            
	         };
	      }.start();
	   }  
}
