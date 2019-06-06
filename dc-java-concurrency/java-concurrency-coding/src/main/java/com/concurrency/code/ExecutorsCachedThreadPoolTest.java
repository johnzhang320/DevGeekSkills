package com.concurrency.code;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ExecutorsCachedThreadPoolTest {
	static Logger Log = Logger.getLogger(ExecutorsCachedThreadPoolTest.class);
	/**
	 * Executors provide a layer of indirection between a client and the executors of a task
	 * Intead of client executes a task directly, an intermediation of asynchronous tasks, 
	 * Executors allow you to manage the execution of without having to explicitly manage the lifecycle
	 * of threads (no need explicitly create a Thread Object , an ExecutorService knows how to build
	 * the appropriate context to execute the Runnable
	 * Below example The CachedThreadPool creates one object per task
	 *  
	 */
	 public static void main(final String[] arguments) throws InterruptedException {
		 //Executor executor = Executors.newCachedThreadPool();
		 // newCachedThreadPool means create thread into pool when executor.execute
		 // FixedTheadPool means do expensive thread allocation once, limited number of threads
		 // This save time because you do not need constantly paying for thread creation overhead
		 // for every single task, when the event driven request , event need the require threads can be 
		 // serviced as quickly as you want by simply fetch threads from the pool
		 // any thread pools existing threads are automatically reused when possible
		 
		 
		 ExecutorService executor = Executors.newCachedThreadPool();
		// ExecutorService executor = Executors.newFixedThreadPool(10);
		 
	      for (int i=0; i<5;i++) {
	    	   executor.execute(new Task()); 
	      }
	     // ThreadPoolExecutor pool = (ThreadPoolExecutor)executor;  // if Executor object , we must convert to pool to shutdown
	      //pool.shutdown();
	      executor.shutdown();   // if ExecutorService object, executor.shutdown directly
	   }  

	   static class Task implements Runnable {
		   
	      public void run() {
	         try {
	            Long duration = (long) (Math.random() * 5);
	            Log.info("Thread:"+Thread.currentThread().getName()+" Running Task!");
	           // TimeUnit.SECONDS.sleep(duration);
	            Thread.sleep(1000);
	            Log.info("Thread:"+Thread.currentThread().getName()+" Task Completed!");
	            
	         } 
	         catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	   }

}
