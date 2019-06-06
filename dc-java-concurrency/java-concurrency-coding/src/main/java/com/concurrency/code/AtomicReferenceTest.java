package com.concurrency.code;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
/**
 * A java.util.concurrent.atomic.AtomicReference class provides operations on underlying 
 * object reference that can be read and written atomically, and also contains advanced 
 * atomic operations. AtomicReference supports atomic operations on underlying object 
 * reference variable. It have get and set methods that work like reads and writes on 
 * volatile variables. That is, a set has a happens-before relationship with any subsequent 
 * get on the same variable. The atomic compareAndSet method also has these memory consistency
 * features.
 */
	   private static String message = "hello";
	   private static AtomicReference<String> atomicReference;

	   public static void main(final String[] arguments) throws InterruptedException {
	      atomicReference = new AtomicReference<String>(message);
	      new Thread("Thread 1") {
	         public void run() {
	            atomicReference.compareAndSet(message, "AtomicReference Thread 1");
	            message = message.concat("-Hehehe 1!");
	         };
	      }.start();

	      System.out.println("Message is: " + message);
	      System.out.println("Atomic Reference of Message is: " + atomicReference.get());
	   }  
}
