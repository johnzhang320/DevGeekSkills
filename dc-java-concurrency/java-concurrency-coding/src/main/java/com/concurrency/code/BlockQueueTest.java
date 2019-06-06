package com.concurrency.code;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class BlockQueueTest {
	static Logger Log = Logger.getLogger(BlockQueueTest.class);
	
	public static void main(final String[] arguments) throws InterruptedException {
	      BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	      Producer producer = new Producer(queue);
	      Consumer consumer = new Consumer(queue);

	      new Thread(producer).start();
	      new Thread(consumer).start();

	      Thread.sleep(4000);
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
	            int result = random.nextInt(100);
	            Thread.sleep(1000);
	            queue.put(result);
	            Log.info("Added: " + result);
	            result = random.nextInt(100);
	            Thread.sleep(1000);
	            queue.put(result);
	            Log.info("Added: " + result);
	            result = random.nextInt(100);
	            Thread.sleep(1000);
	            queue.put(result);
	            Log.info("Added: " + result);
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
	            Log.info("Removed: " + queue.take());
	            Log.info("Removed: " + queue.take());
	            Log.info("Removed: " + queue.take());
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         }
	      }
	   }
}
