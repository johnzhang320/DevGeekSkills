package com.concurrency.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 
 *ExecutorService executor = Executors.newCachedThreadPool();
	where

    if Maximum 2 threads will be active to process tasks.
    If more than 2 threads are submitted then they are held in a queue until threads become available
    A new thread is created to take its place if a thread terminates due to failure during execution 
    shutdown on executor is not yet called.

    Any thread exists till the pool is shutdown.
   	newCachedThreadPool means create thread into pool when executor.execute
	  FixedTheadPool means do expensive thread allocation once, limited number of threads
	  This save time because you do not need constantly paying for thread creation overhead
	  for every single task, when the event driven request , event need the require threads can be 
	  serviced as quickly as you want by simply fetch threads from the pool
	  any thread pools existing threads are automatically reused when possible
		 
 *
 */
public class ExecutorNewFixedThreadPoolTest {
	static Logger Log = Logger.getLogger(ExecutorNewFixedThreadPoolTest.class);

	   public static void main(final String[] arguments) throws InterruptedException {
	      ExecutorService executor = Executors.newFixedThreadPool(2);

	      // Cast the object to its class type
	      ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

	      //Stats before tasks execution
	      Log.info("Core threads: " + pool.getCorePoolSize());
	      Log.info("Largest executions: "
	         + pool.getLargestPoolSize());
	      Log.info("Maximum allowed threads: "
	         + pool.getMaximumPoolSize());
	      Log.info("Current threads in pool: "
	         + pool.getPoolSize());
	      Log.info("Currently executing threads: "
	         + pool.getActiveCount());
	      Log.info("Total number of threads(ever scheduled): "
	         + pool.getTaskCount());
	      
	      executor.submit(new Task());
	      executor.submit(new Task());
	      // Below task must be done after previous two tasks finish
	      executor.submit(new Task());
	      //Stats after tasks execution
	      Log.info("Core threads: " + pool.getCorePoolSize());
	      Log.info("Largest executions: "
	         + pool.getLargestPoolSize());
	      Log.info("Maximum allowed threads: "
	         + pool.getMaximumPoolSize());
	      Log.info("Current threads in pool: "
	         + pool.getPoolSize());
	      Log.info("Currently executing threads: "
	         + pool.getActiveCount());
	      Log.info("Total number of threads(ever scheduled): "
	         + pool.getTaskCount());

	      executor.shutdown();
	   }  

	   static class Task implements Runnable {

	      public void run() {
	         try {
	            Long duration = (long) (Math.random() * 5);
	            Log.info("Running Task! Thread Name: " + Thread.currentThread().getName());
	               TimeUnit.SECONDS.sleep(duration);
	            Log.info("Task Completed! Thread Name: "+ Thread.currentThread().getName());
	         } 
	         catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	   }
}
