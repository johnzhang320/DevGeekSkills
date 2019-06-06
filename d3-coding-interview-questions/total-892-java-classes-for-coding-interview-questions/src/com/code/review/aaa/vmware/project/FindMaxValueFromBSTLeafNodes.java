package com.code.review.aaa.vmware.project;
import junit.framework.TestCase;
/**
 * 
 *  Reproduce Dead Lock
 *
 */
public class FindMaxValueFromBSTLeafNodes extends TestCase {
	FindMaxValueFromBSTLeafNodes ref = null;
	public void setUp () {
		ref = new FindMaxValueFromBSTLeafNodes();
	}
	
 
class resData {
   Integer account;
   Integer balance;
   public resData(int account, int balance) {
	super();
	this.account = account;
	this.balance = balance;
   }
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
   
}

  class thread1 implements Runnable {
	private resData res;
	public thread1(resData res) {
		this.res = res;
	}
	
	public void run() {
		
		synchronized (res.account) {
			synchronized(res.balance) {
				res.balance= 1000;
			}
		}
	}
  }
	class thread2 implements Runnable {
		private resData res;
		public thread2(resData res) {
			this.res = res;
		}
		
		public void run() {
			synchronized(res.balance) {
			synchronized (res.account) {
				
					res.balance= 1000;
				}
			}
		}
	}
	public void testDeadLock() {
		resData res1 = new resData(1000,800); 
		 
		Thread t1 = new Thread(new thread1(res1));
		Thread t2 = new Thread(new thread2(res1));
		
		t1.start();
		t2.start();
	}
}
 