package com.concurrency.code;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

public class ThreadReadWriteLocks {
/**
 *   A java.util.concurrent.locks.ReadWriteLock interface allows multiple threads to read at a 
 *   time but only one thread can write at a time.
    A ReadWriteLock maintains a pair of associated locks, one for read-only operations and one for writing. 
    The read lock may be held simultaneously by multiple reader threads, so long as there are no writers. 
    The write lock is exclusive.
All ReadWriteLock implementations must guarantee that the memory synchronization effects of writeLock operations
 

     Read Lock - If no thread has locked the ReadWriteLock for writing, then multiple thread can 
     access the read lock.
     
     Write Lock - If no thread is reading or writing, then one thread can access the write lock.
     
     Write lock is harder to obtain than read lock , read lock can be acquired even other thread read but only blocked by write lock

 */
	   private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	   private static String message = "a";

	   public static void main(String[] args) {
		   
	      Thread t1 = new Thread(new WriterA());
	      t1.setName("Writer A");
	      Thread t2 = new Thread(new WriterB());
	      t2.setName("Writer B");
	      Thread t3 = new Thread(new Reader());
	      t3.setName("Reader");
	      t1.start();
	      t2.start();
	      t3.start();
	      try {
		      t1.join();
		      t2.join();
		      t3.join();
		  } catch (Exception e) {}
	   }

	 

	   static class WriterA implements Runnable {
		  Logger Log = Logger.getLogger(WriterA.class);
	      public void run() {
	    	 
	         lock.writeLock().lock();
	         try {
	            Long duration = (long) (Math.random() * 10000);
	            Log.info(Thread.currentThread().getName() 
	               + "  Time Taken " + (duration / 1000) + " seconds.");
	            Thread.sleep(duration);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         } finally {
	            message = message.concat("a");
	            Log.info("write a to message:"+message);
	            lock.writeLock().unlock();
	         }
	      }
	   }

	   static class WriterB implements Runnable {
		  Logger Log = Logger.getLogger(WriterB.class);
	      public void run() {
	         lock.writeLock().lock();
	         try {
	            Long duration = (long) (Math.random() * 10000);
	            Log.info(Thread.currentThread().getName() 
	               + "  Time Taken " + (duration / 1000) + " seconds.");
	            Thread.sleep(duration);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         } finally {
	            message = message.concat("b");
	            Log.info("Wrtie b to message:"+message);
	            lock.writeLock().unlock();
	         }
	      }
	   }
	   static class Reader implements Runnable {
		  Logger Log = Logger.getLogger(Reader.class);
	      public void run() {
	    	  
	         if(lock.isWriteLocked()) {
	        	lock.readLock().lock();
	            Log.info("Write Lock Present, but obtain readLock!!");
	         }  else {
	        	lock.readLock().lock();
	        	Log.info("Write Lock not Present, obtain readLock!!");
	         }
	         
	         
	         try {
	            Long duration = (long) (Math.random() * 10000);
	            Log.info(Thread.currentThread().getName() 
	               + "  Time Taken " + (duration / 1000) + " seconds.");
	            Thread.sleep(duration);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         } finally {
	            Log.info(Thread.currentThread().getName() +", now message: "+ message );
	            lock.readLock().unlock();
	         }
	      }
	   }
}
