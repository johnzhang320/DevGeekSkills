package com.concurrency.code;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.Logger;

 
public class ForkJoinTest {
	static Logger Log =  Logger.getLogger(ForkJoinTest.class);
/**
 *  The fork-join framework allows to break a certain task on several workers and then 
 *  wait for the result to combine them. It leverages multi-processor machine's capacity 
 *  to great extent. Following are the core concepts and objects used in fork-join framework.
 *  Fork/Join

	The fork/join framework is an implementation of the ExecutorService interface that helps 
	you take advantage of multiple processors. It is designed for work that can be broken 
	into smaller pieces recursively. The goal is to use all the available processing power 
	to enhance the performance of your application.
	
	As with any ExecutorService implementation, the fork/join framework distributes tasks 
	to worker threads in a thread pool. The fork/join framework is distinct because it 
	uses a work-stealing algorithm. Worker threads that run out of things to do can 
	steal tasks from other threads that are still busy.
	
	The center of the fork/join framework is the ForkJoinPool class, an extension of 
	the AbstractExecutorService class. ForkJoinPool implements the core work-stealing 
	algorithm and can execute ForkJoinTask processes.
	Basic Use
	
	The first step for using the fork/join framework is to write code that performs 
	a segment of the work. Your code should look similar to the following pseudocode:
	
	if (my portion of the work is small enough)
	  do the work directly
	else
	  split my work into two pieces
	  invoke the two pieces and wait for the results
	
	Wrap this code in a ForkJoinTask subclass, typically using one of its more specialized 
	types, either RecursiveTask (which can return a result) or RecursiveAction.
	
	After your ForkJoinTask subclass is ready, create the object that represents all the 
	work to be done and pass it to the invoke() method of a ForkJoinPool instance.
 */
	
 /*
  * Fork

	Fork is a process in which a task splits itself into smaller and independent sub-tasks 
	which can be executed concurrently.
	Syntax
	
	Sum left  = new Sum(array, low, mid);
	left.fork();
	
	Here Sum is a subclass of RecursiveTask and left.fork() spilts the task into sub-tasks. 	
  */
  /*
   * Join

	Join is a process in which a task join all the results of sub-tasks once the subtasks have 
	finished executing, otherwise it keeps waiting.
	Syntax
	
	left.join();
	
	Here left is an object of Sum class.	
   */
	/*
	 * ForkJoinPool

		it is a special thread pool designed to work with fork-and-join task splitting.
		Syntax
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		
		Here a new ForkJoinPool with a parallelism level of 4 CPUs.
	 */
	/*
	 * RecursiveAction

		RecursiveAction represents a task which does not return any value.
		Syntax
		
		class Writer extends RecursiveAction {
		   @Override
		   protected void compute() { }
		}
	 */
	/*
	 * RecursiveTask

		RecursiveTask represents a task which returns a value.
		Syntax
		
		class Sum extends RecursiveTask {
		   @Override
		   protected Long compute() { return null; }
		}
	 */
	/*
	 * Example

		The following TestThread program shows usage of Fork-Join framework in 
		thread based environment. Number of CPU in a CPU core matrix
	 */
	

	   public static void main(final String[] arguments) throws InterruptedException, ExecutionException {
	      // Find how many CPUs in your runtime environment
		  int nThreads = Runtime.getRuntime().availableProcessors();
	      Log.info("current environment CPUs number:"+ nThreads + ", which means we can split " + nThreads+" Threads");
	      Log.info("We want each threads parallelly caculation per each CPU of " + nThreads);
	      int[] numbers = new int[100000]; 

	      for(int i=0; i< numbers.length; i++){
	         numbers[i] = i;
	      }

	      ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
	      Long start = System.currentTimeMillis();
	      Long result = forkJoinPool.invoke(new Sum(numbers,0,numbers.length));  // forkJoinPool invoke,
	      Long duration = System.currentTimeMillis()-start;
	      Log.info("4 CPUs using Fork / Join to Calculate Summay="+result+", took "+duration+" ms");
	      Thread.sleep(1000);
	      start = System.currentTimeMillis();
	      Long sResult=0L;
	      for(int i=0; i< numbers.length; i++){
	    	    // Thread.sleep(10);
		         sResult+=numbers[i];
		  }
	     // result = forkJoinPool.invoke(new Sum(numbers,0,numbers.length));
	      duration = System.currentTimeMillis()-start;
	      Log.info("Uing 1 regular Loop Calculate Summay="+sResult+", took "+duration+" ms");
 
	   }  

	   static class Sum extends RecursiveTask<Long> {     // RecursiveTask could return value from compute() but RecursiveAction won't

	      int low;
	      int high;
	      int[] array;

	      Sum(int[] array, int low, int high) {
	         this.array = array;
	         this.low   = low;
	         this.high  = high;
	      }

	      protected Long compute() {
	         if(high - low <= 10) {  // split into 10 numbers and then to do addition
	            long sum = 0;
	            for(int i=low; i < high; ++i) {
	              
	               sum += array[i];
	            }
	               return sum;
	         } else {	    	
	            int mid = low + (high - low) / 2;
	            Sum left  = new Sum(array, low, mid);
	            Sum right = new Sum(array, mid, high);
	            left.fork(); //left.fork() spilts the task into sub-tasks , calculate low to mid
	            long rightResult = right.compute();   // main process calculate mid to high
	            long leftResult  = left.join();
	            return leftResult + rightResult;   
	         }
	      }
	   }
}
