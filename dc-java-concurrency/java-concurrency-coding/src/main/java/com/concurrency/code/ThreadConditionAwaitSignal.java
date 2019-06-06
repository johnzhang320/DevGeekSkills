package com.concurrency.code;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

import com.concurrency.code.ThreadReadWriteLocks.WriterA;

public class ThreadConditionAwaitSignal {

	   public static void main(String[] args) throws InterruptedException{
	      ItemQueue itemQueue = new ItemQueue(10);

	      //Create a producer and a consumer.
	      Thread producer = new Producer(itemQueue);
	      Thread consumer = new Consumer(itemQueue);

	      //Start both threads.
	      producer.start();
	      consumer.start();

	      //Wait for both threads to terminate.
	      producer.join();
	      consumer.join();
	   }

   static class ItemQueue {
	  
	  static Logger Log =Logger.getLogger(ItemQueue.class);
      private Object[] items = null;
      private int current = 0;
      private int placeIndex = 0;
      private int removeIndex = 0;

      private final Lock lock;
      private final Condition isEmpty;
      private final Condition isFull;

      public ItemQueue(int capacity) {
         this.items = new Object[capacity];
         lock = new ReentrantLock();
         isEmpty = lock.newCondition();
         isFull = lock.newCondition();
      }

      public void add(Object item) throws InterruptedException {
         lock.lock();
          while(current >= items.length) {
        	Log.info("queue is full , producer isFull await()...");
            isFull.await();
         }

         items[placeIndex] = item;

         placeIndex = (placeIndex + 1) % items.length;  // if reach items.length , placeIndex = 0;

         ++current;
         Log.info("Added number="+item+",items.length="+items.length+",placeIndex="+placeIndex);
         
         //Notify the consumer that there is data available.
         Log.info("queue is not empty, producer signal cosumer...");
         isEmpty.signal();

         lock.unlock();
      }

      public Object remove() throws InterruptedException {
         Object item = null;

         lock.lock();
         while(current <= 0){
        	Log.info("Queue is empty, consumer isEmpty.await()...");
            isEmpty.await();
         }
         item = items[removeIndex];
         Log.info("Removed number="+item+",items.length="+items.length+",placeIndex="+placeIndex);
         
         removeIndex = (removeIndex + 1) % items.length;

         --current;

         //Notify the producer that there is space available.
         Log.info("queue is not full, consumer signal producer...");
         isFull.signal();
         lock.unlock();

         return item;
      }

      public boolean isEmpty(){
         return (items.length == 0);
      }
   }

   static class Producer extends Thread {
	  static Logger Log =Logger.getLogger(Producer.class);
      private final ItemQueue queue;
      public Producer(ItemQueue queue) {
         this.queue = queue;
      }

      @Override
      public void run() {
         String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

         try {
            for(String number: numbers){
               queue.add(number);
               Log.info("[Producer]: " + number);
               Thread.sleep(1000);
            }
            queue.add(null);
         }
         catch (InterruptedException ex) {
            ex.printStackTrace();
         } 
      }
   }

   static class Consumer extends Thread {
	  static Logger Log =Logger.getLogger(Producer.class);
      private final ItemQueue queue;
      public Consumer(ItemQueue queue) {
         this.queue = queue;
      }

      @Override
      public void run() {
         try {
            do {
               Object number = queue.remove();
               Log.info("[Consumer]: " + number);
               if(number == null){
                  return;
               }
               Thread.sleep(100);  // consumer consuming faster than producer
            } while(!queue.isEmpty());
         }
         catch (InterruptedException ex) {
            ex.printStackTrace();
         }
      }
   }
}
