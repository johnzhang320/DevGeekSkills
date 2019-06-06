package com.concurrency.code;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.concurrency.code.ExecutorsCachedThreadPoolTest.Task;

/**
 * 
 * A Runnable is separate task that performs work , but it does not return a value
 * Implement Callable interface and mothed call() could return Future<String> 
 * You must use ExecuteService.submit() method 
 *
 */
public class FutureCallableThreadTest {
	static Logger Log = Logger.getLogger(FutureCallableThreadTest.class);

	
	public static void main(final String[] arguments) throws Exception {
		 //Executor executor = Executors.newCachedThreadPool();
		 // newCachedThreadPool means create thread into pool when executor.execute
		 // FixedTheadPool means do expensive thread allocation once, limited number of threads
		 // This save time because you do not need constantly paying for thread creation overhead
		 // for every single task, when the event driven request , event need the require threads can be 
		 // serviced as quickly as you want by simply fetch threads from the pool
		 // any thread pools existing threads are automatically reused when possible
		 
		 
		 ExecutorService executor = Executors.newCachedThreadPool();
		 
		 ArrayList<Future<String>> result = new ArrayList<Future<String>>(); 
	      for (int i=0; i<5;i++) {
	    	  Future<String> future =(Future<String>) executor.submit(new Task(i+1)); 
	    	  result.add(future);
	      }
	     // ThreadPoolExecutor pool = (ThreadPoolExecutor)executor;  // if Executor object , we must convert to pool to shutdown
	      //pool.shutdown();
	      for (Future<String> fs : result) {
	    	  Log.info(fs.get());  // get block until completion
	      }
	      executor.shutdown();   // if ExecutorService object, executor.shutdown directly
	      
	   }  

	   static class Task implements Callable<String> {
		  private int id;
		  public Task(int id) {
			  this.id = id;
		  }
	      public String call() {
	         try {
	            Long duration = (long) (Math.random() * 5);
	            Log.info("Thread:"+Thread.currentThread().getName()+" Running Task!");
	           
	            Thread.sleep(1000);
	            
	            Log.info("Thread:"+Thread.currentThread().getName()+" Task Completed!");
	            
	         } 
	         catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	         return "Thread:"+Thread.currentThread().getName()+" call() return value:"+id;
	      }
	      
	   }
}
