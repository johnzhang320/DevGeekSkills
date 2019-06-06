package com.concurrency.code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalExample implements Runnable{
	
	static Lock lock = new ReentrantLock();  // ensure all threads running in their starting sequence
	                                         // thread 1 not finished and thread 2 could not reentry
	                                         // mothod or synchronized ensure the threads blocked before it running
    // SimpleDateFormat is not thread-safe, so give one to each thread
 // SimpleDateFormat is not thread-safe, so give one to each thread
    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("MM/dd/yyyy HH:mm");
        }
    };
    
    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for(int i=0 ; i<10; i++){
            Thread t = new Thread(obj, ""+i);
            //Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    public String convertDateToString(String dateString)  {
    	
    	String retVal=null;
    	try {
    	  retVal = formatter.get().format(new Date());
    	} catch (Exception e ) {
    		System.out.println(e.getMessage());
    		 e.printStackTrace();
    	}
    	return retVal;
    }
    public void run() {
    	
    	try {
	    	lock.tryLock(2000L, TimeUnit.MILLISECONDS);    // Apply lock , we can ensure thread to run critical section not to be reentranced
	    	// lock.lock();
	    	//critical section
	    	//synchronized (this) {   // Applied synchronized (this) to ensure below three statement display continuous three lines
	        System.out.println("Thread Name= "+Thread.currentThread().getName()+" customized Formatter = "+formatter.get().toPattern());
	      
	        System.out.println("Thread Name= "+Thread.currentThread().getName()+","+convertDateToString("12/21/2015"));
	        formatter.set(new SimpleDateFormat());
	        
	        System.out.println("Thread Name= "+Thread.currentThread().getName()+" standard formatter = "+formatter.get().toPattern());
	    	//}
	        lock.unlock();
    	} catch(Exception e) {}
    }

}
