package com.concurrency.code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.apache.log4j.Logger;

public class FolderProcessor extends RecursiveTask<List<String>>{
	static Logger Log =  Logger.getLogger(FolderProcessor .class);
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
	private static final long serialVersionUID = 1L;
	   //This attribute will store the full path of the folder this task is going to process.
	   private final String      path;
	   //This attribute will store the name of the extension of the files this task is going to look for.
	   private final String      extension;
	 
	   //Implement the constructor of the class to initialize its attributes
	   public FolderProcessor(String path, String extension)
	   {
	      this.path = path;
	      this.extension = extension;
	   }
	 
	   //Implement the compute() method. As you parameterized the RecursiveTask class with the List<String> type, 
	   //this method has to return an object of that type.
	   @Override
	   protected List<String> compute()
	   {
	      //List to store the names of the files stored in the folder.
	      List<String> list = new ArrayList<String>();
	      //FolderProcessor tasks to store the subtasks that are going to process the subfolders stored in the folder
	      List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
	      //Get the content of the folder.
	      File file = new File(path);
	      File content[] = file.listFiles();
	      //For each element in the folder, if there is a subfolder, create a new FolderProcessor object 
	      //and execute it asynchronously using the fork() method.
	      if (content != null)
	      {
	         for (int i = 0; i < content.length; i++)
	         {
	            if (content[i].isDirectory())
	            {
	               FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
	               task.fork();   // once create FolderProcessor object, using fork to execute it asynchronously
	               tasks.add(task);
	            }
	            //Otherwise, compare the extension of the file with the extension you are looking for using the checkFile() method 
	            //and, if they are equal, store the full path of the file in the list of strings declared earlier.
	            else
	            {
	               if (checkFile(content[i].getName()))
	               {
	                  list.add(content[i].getAbsolutePath());
	               }
	            }
	         }
	      }
	      //If the list of the FolderProcessor subtasks has more than 50 elements, 
	      //write a message to the console to indicate this circumstance.
	      if (tasks.size() > 50)
	      {
	         System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
	      }
	      //add to the list of files the results returned by the subtasks launched by this task.
	      addResultsFromTasks(list, tasks);
	      //Return the list of strings
	      return list;
	   }
	 
	   //For each task stored in the list of tasks, call the join() method that will wait for its finalization and then will return the result of the task. 
	   //Add that result to the list of strings using the addAll() method.
	   private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks)
	   {
	      for (FolderProcessor item : tasks)
	      {
	         list.addAll(item.join());
	      }
	   }
	 
	   //This method compares if the name of a file passed as a parameter ends with the extension you are looking for.
	   private boolean checkFile(String name)
	   {
	      return name.endsWith(extension);
	   }
}
