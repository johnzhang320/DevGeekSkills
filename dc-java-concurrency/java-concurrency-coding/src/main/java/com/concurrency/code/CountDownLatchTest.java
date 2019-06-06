package com.concurrency.code;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;

public class CountDownLatchTest {
	/**
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
	static Logger Log = Logger.getLogger(CountDownLatchTest.class);
	
	// Test multiple threads start at same time
	class StartSeverialThread implements Runnable {
		private CountDownLatch startLatch;
		private long fibNumber;
		// start count down
		public StartSeverialThread(long fibNumber, CountDownLatch startLatch) {
			this.fibNumber = fibNumber;
			this.startLatch = startLatch;
		}
		public void run() {
			try {
				startLatch.await();
				Long start = System.currentTimeMillis();
				Log.info("Starting calculating Fibonacci at " + fibNumber+" at Thread:"+Thread.currentThread().getName());
				
				long result = stupidfibRecursion(fibNumber);
				
				Log.info("StartSeverialThread,Thread:"+Thread.currentThread().getName()+",Input fib number:"+fibNumber+" calculated Result:"+result+", take "
				+(System.currentTimeMillis()-start)+" ms");
			} catch(InterruptedException e) {
				Log.error(e.getMessage());
			}
		}
	}
	
	public class StopLatchedThread implements Runnable {
		  private final CountDownLatch stopLatch;
		  private long fibNumber;
		  public StopLatchedThread(long fibNumber,CountDownLatch stopLatch) {
		    this.stopLatch = stopLatch;
		    this.fibNumber = fibNumber;
		  }
		  public void run() {
		    try {
		      // perform interesting task
		    	Long start = System.currentTimeMillis();
		    	long result = stupidfibRecursion(fibNumber);
				Log.info("StopLatchedThread ,Thread:"+Thread.currentThread().getName()+",Input fib number:"+fibNumber+" calculated Result:"+result+", take "
				+(System.currentTimeMillis()-start)+" ms");

				
		    } finally {
		        stopLatch.countDown();
 		    }
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
		int count = 10;   // four cpus
		CountDownLatch latch = new CountDownLatch(1);
		for (int i=0; i <count; i++) {
			Long fibs = ThreadLocalRandom.current().nextLong(46);			
			CountDownLatchTest cdt = new CountDownLatchTest();
			Thread t = new Thread(cdt.new StartSeverialThread(fibs,latch) );
			t.start();
		}
		try {
			Thread.sleep(1000);
		} catch(Exception e){}
		
		latch.countDown();
		
		CountDownLatch cdl = new CountDownLatch(10);
		 for (int i = 0; i < 10; i++) {
		    Long fibs = ThreadLocalRandom.current().nextLong(46);			
			CountDownLatchTest cdt = new CountDownLatchTest();
		    Thread t = new Thread(cdt.new StopLatchedThread(fibs,cdl));
		    t.start();
		 }
		 try {
			 cdl.await();
			 Log.info("10 threads stopped !");
		 } catch(Exception e){}
	}
	 
}
