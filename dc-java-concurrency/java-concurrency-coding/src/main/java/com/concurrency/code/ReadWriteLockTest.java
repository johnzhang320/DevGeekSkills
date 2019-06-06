package com.concurrency.code;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
/**
 * A java.util.concurrent.locks.ReadWriteLock interface allows multiple threads to read at a 
 * time but only one thread can write at a time.

    Read Lock - If no thread has locked the ReadWriteLock for writing then multiple 
    thread can access the read lock.

    Write Lock - If no thread is reading or writing, then one thread can access the write lock.

 * @param args
 */

	   private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	   private static String message = "a";

	 
	  static class Reader implements Runnable {

	      public void run() {
	         if(lock.isWriteLocked()) {
	            System.out.println("Thread"+Thread.currentThread().getName()+" find Write Lock Present.");
	         }   
	         lock.readLock().lock();
	         try {
	            Long duration = (long) (Math.random() * 10000);
	            System.out.println("Thread"+Thread.currentThread().getName()+"  Time Taken " + (duration / 1000) + " seconds.");
	            Thread.sleep(duration);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         } finally {
	            System.out.println("Thread"+Thread.currentThread().getName()+": "+ message );
	            lock.readLock().unlock();
	         }
	      }
	   }

	   static class WriterA implements Runnable {

	      public void run() {
	         lock.writeLock().lock();
	         try {
	            Long duration = (long) (Math.random() * 10000);
	            System.out.println("Thread"+Thread.currentThread().getName()+" Time Taken " + (duration / 1000) + " seconds.");
	            Thread.sleep(duration);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         } finally {
	            message = message.concat("a");
	            lock.writeLock().unlock();
	         }
	      }
	   }

	   static class WriterB implements Runnable {

	      public void run() {
	         lock.writeLock().lock();
	         try {
	            Long duration = (long) (Math.random() * 10000);
	            System.out.println("Thread"+Thread.currentThread().getName()+"  Time Taken " + (duration / 1000) + " seconds.");
	            Thread.sleep(duration);
	         } catch (InterruptedException e) {
	            e.printStackTrace();
	         } finally {
	            message = message.concat("b");
	            lock.writeLock().unlock();
	         }
	      }
	   }
	public static void main(String[] args) throws InterruptedException{
	      Thread t1 = new Thread(new WriterA());
	      t1.setName("Writer A");
	      Thread t2 = new Thread(new WriterB());
	      t2.setName("Writer B");
	      Thread t3 = new Thread(new Reader());
	      t3.setName("Reader");
	      t1.start();
	      t2.start();
	      t3.start();
	      t1.join();
	      t2.join();
	      t3.join();
	}

}
