package com.concurrency.code;

import org.apache.log4j.Logger;

public class ThreadLocalTest implements Runnable {
	static Logger Log = Logger.getLogger(ThreadLocalTest.class);
/**
 	What is ThreadLocal? A Simple Example

	As its name suggests, a single instance of ThreadLocal can store different values for each thread 
	independently. Therefore, the value stored in a ThreadLocal instance is specific (local) to the 
	current running thread and any other code logic running on the same thread will see the same value, 
	but not the values set on the same instance by other threads. (There are exceptions, like 
	InheritableThreadLocal, which inherits its parent thread�s values by default.) The Internals of ThreadLocal and How it Works

	The Internals of ThreadLocal and How it Works
	
	Let�s drill down a little bit into ThreadLocal�s internals. ThreadLocal is implemented by having a map
	 (a ThreadLocalMap) as a field (with a WeakReference entry) within each thread instance. 
	 (There are actually two maps. The second one is used for InheritableThreadLocal, but let�s not 
	 complicate things.) The keys of those maps are the corresponding ThreadLocals themselves. Therefore, 
	 when a set/get is called on a ThreadLocal, it looks at the current thread, finds the map, and looks
	 up the value with �this� ThreadLocal instance.
	
	Still confused? I certainly am. Let�s look at a real example.

    Code running in Thread 1 calls set() on ThreadLocal instance �A� with value "123"
    Code running in Thread 2 calls set() on ThreadLocal instance �A� with value "234"
    Code running in Thread 1 calls set() on ThreadLocal instance �B� with value "345"
    
    How to use ThreadLocal
    
    We can define ThreadLocal inside Thread but we have to define it as static
 	
 */
	 
	   int counter=1;
	   static ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>();
	
	   public void run() {     
	      counter++;
	      if(threadLocalCounter.get() != null){
	         threadLocalCounter.set(threadLocalCounter.get().intValue() + 1);
	      }else{
	         threadLocalCounter.set(10);
	      }
	   
	      Log.info("Thread:"+Thread.currentThread().getName()+",Counter: " + counter);
	      Log.info("Thread:"+Thread.currentThread().getName()+",threadLocalCounter: " + threadLocalCounter.get());
	   }
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 
			Thread t1 = new Thread(new ThreadLocalTest());
			
		 
			Thread t2 = new Thread(new ThreadLocalTest());
			
			Thread t3 = new Thread(new ThreadLocalTest());
			
			Thread t4 = new Thread(new ThreadLocalTest());
			t1.start();
			 
			//Thread.sleep(1000);
			   
			      
			t2.start();
			
			//Thread.sleep(1000);
			t3.start();
			//Thread.sleep(1000);
			t4.start();
			
			
	      // wait for threads to end
	     try {
	         t1.join();
	         t2.join();
	         t3.join();
	         t4.join();
	      }catch( Exception e) {
	         Log.info("Interrupted");
	      }
	}
 
}
