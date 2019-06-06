package com.concurrency.practices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class StartThreadsCountDownLatch {
	/**
	 * 
	 * /**
	 *  CountDownLatch:
	 *  CountDownLatch allows us to set initial count number, threads could be awaitiong this
	 *  count and block at CountDown count until other thread decrease the count to zero, these threads
	 *  which are awaiting the count will start
	 *  
	 *  (1) start all threads to work at same time
	 *   set a CountDownLatch =1, in main , start all threads which are awaiting count to zero, then main
	 *   code to CountDown() then CountDownLatch to zero, all thread are waken to start
	 *  (2) count all threads have been worked
	 *   Suppose we try to start N of threads, then we set CountDownLatch = N,
	 *   We can start threads even N-1 threads start, each one will be await CountDown be zero
	 *   when we finally start thread N, then all thread unblock and start to run
	 *   
	 *  
	 */
	public class StartThread implements Callable<Long> {
		private CountDownLatch startCount;
		private long fib;
		public StartThread(CountDownLatch startCount,long fib) {
			this.startCount = startCount;
			this.fib = fib;
		}
		public Long call() {
			String threadName = Thread.currentThread().getName();
			System.out.println("Thread:"+threadName+", awaiting now ");
			long retVal=0L;
			try {
				startCount.await();
				System.out.println("Thread:"+threadName+", start calculation now ");
				retVal = stupidfibRecursion(fib);
			} catch (Exception e) {
				System.out.println("Failed thread:"+threadName);
			}
			System.out.println("Thread:"+threadName+",done with calculation now ");
			return retVal;
		}
	 }
	 public long stupidfibRecursion(long n) {
        if (n < 2) {
           return n;
        }
        else {
        	return stupidfibRecursion(n-1)+stupidfibRecursion(n-2);
        }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int THREADS = 10;
		CountDownLatch startCount = new CountDownLatch(1);   // main start threads by startCount.down
		ExecutorService service = Executors.newFixedThreadPool(THREADS);
		List<Future<Long>> results = new ArrayList<Future<Long>> ();
		for (int i=0; i<THREADS; i++) {
			Long fibs = ThreadLocalRandom.current().nextLong(46);		
			if (fibs<10) fibs=30L;
			Future<Long> future = service.submit(new StartThreadsCountDownLatch().new StartThread(startCount,fibs));
			results.add(future);
		}
		System.out.println("All threads are  awaiting");
		try {
			
			Thread.sleep(5000);
			startCount.countDown();
			System.out.println("Race Start");
		} catch (InterruptedException e) {}
		for (Future<Long> f: results) {
			try {
				System.out.println("results:"+f.get());
			} catch (Exception e) {
				
			}
		}
		service.shutdown();
	}

}
