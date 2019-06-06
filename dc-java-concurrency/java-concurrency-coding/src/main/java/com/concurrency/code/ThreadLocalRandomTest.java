package com.concurrency.code;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 
 * A java.util.concurrent.ThreadLocalRandom is a utility class introduced from jdk 1.7 onwards 
 * and is useful when multiple threads or ForkJoinTasks are required to generate random numbers. 
 * It improves performance and have less contention than Math.random() method.
 *
 */
public class ThreadLocalRandomTest implements Runnable {
  
	  int counter=0;
	   ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>();
	   Lock lock = new ReentrantLock();
	   public void run() {     
		  lock.lock();
		  try {
		      counter++;
		      Long times = ThreadLocalRandom.current().nextLong(10);
		      for (Long i = 0L ; i<times; i++) {
			      if(threadLocalCounter.get() != null){
			         threadLocalCounter.set(threadLocalCounter.get().intValue() + 1);
			      }else{
			         threadLocalCounter.set(0);
			      }
		      }
		      System.out.println("Thread:"+Thread.currentThread().getName()+": Counter: " + counter);
		      System.out.println("Thread:"+Thread.currentThread().getName()+":threadLocalCounter: " + threadLocalCounter.get());
	      
		  } catch (Exception e) {
			  e.getLocalizedMessage();
		  } finally {
			  lock.unlock();
		  }
	  }
	   
	   public static void main(String args[]) {
		   	  ThreadLocalRandomTest commonInstance = new ThreadLocalRandomTest();

		      Thread t1 = new Thread( commonInstance);
		      Thread t2 = new Thread( commonInstance);
		      Thread t3 = new Thread( commonInstance);
		      Thread t4 = new Thread( commonInstance);

		      t1.start();
		      t2.start();
		      t3.start();
		      t4.start();

		      // wait for threads to end
		      try {
		         t1.join();
		         t2.join();
		         t3.join();
		         t4.join();
		      }catch( Exception e) {
		         System.out.println("Interrupted");
		      }
		   }
}