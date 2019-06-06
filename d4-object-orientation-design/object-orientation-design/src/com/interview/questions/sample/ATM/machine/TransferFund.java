package com.interview.questions.sample.ATM.machine;

public class TransferFund {
	private Account source, dest;

	public TransferFund(Account source, Account dest) {
		super();
		this.source = source;
		this.dest = dest;
	}
	public void transfer(double amount) {
		Account first, second;
		/**
		 *  Ensure which account id is smaller , lock it first, so prevent dead lock
		 */
		if (source.getAccountId()<dest.getAccountId()) {
			first = source;
			second = dest;
		} else {
			first = dest;
			second = source;
		}
		synchronized (first) {
			Thread.yield();
			synchronized(second) {
				System.out.println("Transfer "+source.getAccountType().getName()+" amount " +amount+ " from ");
				source.withdraw(amount);
				System.out.println("To " +dest.getAccountType().getName());
				dest.deposit(amount);
			}
		} 
	}
}
