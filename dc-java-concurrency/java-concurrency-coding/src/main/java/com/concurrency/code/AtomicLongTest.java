package com.concurrency.code;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

public class AtomicLongTest {
	static Logger Log = Logger.getLogger(AtomicLongTest.class);
	/**
	 * A java.util.concurrent.atomic.AtomicLong class provides operations on underlying long value 
	 * that can be read and written atomically, and also contains advanced atomic operations. 
	 * AtomicLong supports atomic operations on underlying long variable. It have get and set 
	 * methods that work like reads and writes on volatile variables. That is, a set has a 
	 * happens-before relationship with any subsequent get on the same variable. The atomic 
	 * compareAndSet method also has these memory consistency features.
	 * 
	 * The following program spaw 1000 thread, each one increase AtomicLong variable once
	 * which variable can be accessed correct by one thread at one same time
	 */

	   static class Counter {
	      private AtomicLong c = new AtomicLong(0);

	      public void increment() {
	         c.getAndIncrement();
	      }

	      public long value() {
	         return c.get();
	      }
	   }
	   public static void main(final String[] arguments) throws InterruptedException {
	      final Counter counter = new Counter();
	      //1000 threads
	      for(int i = 0; i < 1000 ; i++) {
	         new Thread(new Runnable() {
	            public void run() {
	               counter.increment();
	            }
	         }).start();	
	      }  
	      Thread.sleep(6000);			   		  

	      Log.info("Final number (should be 1000): " + counter.value());
	   }  

}
