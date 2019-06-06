package com.concurrency.code;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.concurrency.code.BlockQueueTest.Consumer;
import com.concurrency.code.BlockQueueTest.Producer;

public class BlockQueueTest2 {
	static Logger Log = Logger.getLogger(BlockQueueTest2.class);
	static final int NUMBERS=100;
	static final int MAX_THREADS=40;
	
	public static void main(final String[] arguments) throws InterruptedException {
	      BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1000);
	      ExecutorService exec = Executors.newFixedThreadPool(MAX_THREADS);
	      for (int i=0; i<MAX_THREADS; i++) {
	        exec.execute(new Consumer(queue));    
		  }
 
	      Thread.sleep(4000);
	      
	      Producer producer = new Producer(queue);
	      
	      new Thread(producer).start();
	      
	      exec.shutdown();
	   }  


	   static class Producer implements Runnable {
		  static Logger Log = Logger.getLogger(Producer.class);
	      private BlockingQueue<Integer> queue;

	      public Producer(BlockingQueue queue){
	         this.queue = queue;
	      }

	      @Override
	      public void run() {
	         Random random = new Random();

	         try {
	        	Long start = System.currentTimeMillis(); 
	        	for (int i=0; i<NUMBERS; i++) { 
	        		int result = random.nextInt(1000);	
	        		queue.put(result);
          		    Log.info("Produce Thread: "+Thread.currentThread().getName()+" has added number : " + result);
	        	}
	            Log.info("Produce Thread: "+Thread.currentThread().getName()+" has added "+NUMBERS+" integers took "+(System.currentTimeMillis()-start)+" ms!");	             
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }	   
	   }

	   static class Consumer implements Runnable {
		  static Logger Log = Logger.getLogger(Consumer.class);
	      private BlockingQueue<Integer> queue;

	      public Consumer(BlockingQueue queue){
	         this.queue = queue;
	      }
	      
	      @Override
	      public void run() {
	         try {
	        	 while(true) { 	 
	        		 if (queue.isEmpty()) {
	        			 Log.info("Thread:"+Thread.currentThread().getName()+" found Queue is empty and waiting now....");
	        		 }
	        		 Integer number = queue.take();
	        		 Log.info("Thread:"+Thread.currentThread().getName()+" removed number: " + number+" starting process...");
	        		 Thread.sleep(1000);
	        	 }
	        	 
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	   }
}
