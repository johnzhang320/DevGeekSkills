package com.concurrency.code;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadLocalLock implements Runnable {
	   int counter=0;
	   ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>();
	   Lock lock = new ReentrantLock();
	   public void run() {    
		   Long times = ThreadLocalRandom.current().nextLong(10);
		      for (Long i = 0L ; i<times; i++) {
			      if(threadLocalCounter.get() != null){
			         threadLocalCounter.set(threadLocalCounter.get().intValue() + 1);
			      }else{
			         threadLocalCounter.set(0);
			      }
		      }
		  lock.lock();
		  try {
		      counter++;
		     
		      System.out.println("Thread:"+Thread.currentThread().getName()+": Global Counter (only one thread at same time can get lock to increase it): " + counter);
		      System.out.println("Thread:"+Thread.currentThread().getName()+":threadLocalCounter: (can local increase it):" + threadLocalCounter.get());
	      
		  } catch (Exception e) {
			  e.getLocalizedMessage();
		  } finally {
			  lock.unlock();
		  }
	  }
	   
	   public static void main(String args[]) {
		      ThreadLocalLock commonInstance = new ThreadLocalLock();

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
