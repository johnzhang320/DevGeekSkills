package com.concurrency.code;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

 
public class ForkJoinTest2RunFolderProcessor {
	static Logger Log =  Logger.getLogger(ForkJoinTest2RunFolderProcessor.class);
/**
 *  The effective use of parallel cores in a Java program has always been a challenge. 
 *  There were few home-grown frameworks that would distribute the work across multiple 
 *  cores and then join them to return the result set. Java 7 has incorporated this 
 *  feature as a Fork and Join framework.

	Basically the Fork-Join breaks the task at hand into mini-tasks until the mini-task 
	is simple enough that it can be solved without further breakups. 
	It’s like a divide-and-conquer algorithm. One important concept to note in this framework 
	is that ideally no worker thread is idle. They implement a work-stealing algorithm in that 
	idle workers steal the work from those workers who are busy.
 */
	
 /*
  * It’s based on the work of Doug Lea, a thought leader on Java concurrency. Fork/Join deals with 
  * the threading hassles; you just indicate to the framework which portions of the work can be 
  * broken apart and handled recursively. It employs pseudocode (as taken from Doug Lea’s paper on 
  * the subject):

	Result solve(Problem problem) {
	if (problem is small)
		directly solve problem
	else {
		split problem into independent parts
		fork new subtasks to solve each part
		join all subtasks
		compose result from subresults
	}
}
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
	 * Core Classes used in Fork/Join Framework

		The core classes supporting the Fork-Join mechanism are ForkJoinPool and ForkJoinTask.
		
		Let’s learn about their roles in detail.
		ForkJoinPool
		
		The ForkJoinPool is basically a specialized implementation of ExecutorService implementing the 
		work-stealing algorithm we talked about above. We create an instance of ForkJoinPool by providing
		 the target parallelism level i.e. the number of processors as shown below:

    	ForkJoinPool pool = new ForkJoinPool(numberOfProcessors);

    	Where numberOfProcessors = Runtime.getRunTime().availableProcessors();
    	
    	Although you specify any initial pool size, the pool adjusts its size dynamically in an attempt 
    	to maintain enough active threads at any given point in time. Another important difference 
    	compared to other ExecutorService's is that this pool need not be explicitly shutdown upon 
    	program exit because all its threads are in daemon mode.

		There are three different ways of submitting a task to the ForkJoinPool.
		
		1) execute() method //Desired asynchronous execution; call its fork method to split the work 
		   between multiple threads.
		2) invoke() method: //Await to obtain the result; call the invoke method on the pool.
		3) submit() method: //Returns a Future object that you can use for checking status and 
		   obtaining the result on its completion.
	 */
	/*
	 * ForkJoinTask

		This is an abstract class for creating tasks that run within a ForkJoinPool. The Recursiveaction 
		and RecursiveTask are the only two direct, known subclasses of ForkJoinTask. The only difference
		between these two classes is that the RecursiveAction does not return a value while RecursiveTask 
		does have a return value and returns an object of specified type.
	
	    In both cases, you would need to implement the compute method in your subclass that performs the 
	    main computation desired by the task.
	
		The ForkJoinTask class provides several methods for checking the execution status of a task. 
		The isDone() method returns true if a task completes in any way. The isCompletedNormally() 
		method returns true if a task completes without cancellation or encountering an exception, 
		and isCancelled() returns true if the task was cancelled. Lastly, isCompletedabnormally() 
		returns true if the task was either cancelled or encountered an exception.
	 */
	/**
	 * Example Implementations of Fork/Join Pool Framework

		In this example, you will learn how to use the asynchronous methods provided by the 
		ForkJoinPool and ForkJoinTask classes for the management of tasks. You are going to 
		implement a program that will search for files with a determined extension inside a 
		folder and its subfolders. The ForkJoinTask class you’re going to implement will 
		process the content of a folder. For each subfolder inside that folder, it will send a 
		new task to the ForkJoinPool class in an asynchronous way. For each file inside that 
		folder, the task will check the extension of the file and add it to the result list 
		if it proceeds.
		
		The solution to above problem is implemented in FolderProcessor class, which is given below:
	 */
	

	   public static void main(final String[] arguments) throws InterruptedException, ExecutionException {
		 //Create ForkJoinPool using the default constructor.
		      ForkJoinPool pool = new ForkJoinPool();
		      //Create three FolderProcessor tasks. Initialize each one with a different folder path.
		      FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
		      FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
		      FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");
		      //Execute the three tasks in the pool using the execute() method.
		      pool.execute(system);
		      pool.execute(apps);
		      pool.execute(documents);
		      //Write to the console information about the status of the pool every second 
		      //until the three tasks have finished their execution.
		      do
		      {
		         System.out.printf("******************************************\n");
		         System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
		         System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
		         System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
		         System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
		         System.out.printf("******************************************\n");
		         try
		         {
		            TimeUnit.SECONDS.sleep(1);
		         } catch (InterruptedException e)
		         {
		            e.printStackTrace();
		         }
		      } while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
		      //Shut down ForkJoinPool using the shutdown() method.
		      pool.shutdown();
		      //Write the number of results generated by each task to the console.
		      List<String> results;
		      results = system.join();
		      System.out.printf("System: %d files found.\n", results.size());
		      results = apps.join();
		      System.out.printf("Apps: %d files found.\n", results.size());
		      results = documents.join();
		      System.out.printf("Documents: %d files found.\n", results.size());
 
	   }  

	  
}
