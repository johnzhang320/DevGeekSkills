package com.interview.questions.sample.ATM.machine;
/**
 * Account 
 * @author John Zhang
 *
 */
public class Account { 
	//If I have more time implement MIN_BALANCE 
	private final double MIN_BALANCE = 100.0;
	private AccountType accountType;
	private int accountId;
	private double currentBalance=0.0;
	
	public Account(AccountType accountType) {
		super();
		this.accountType = accountType;
		this.accountId = IdGenerator.getRandomNumber();
	}

	public Account(AccountType accountType, int accountId, double initAmount) {
		super();
		this.accountType = accountType;
		this.accountId = accountId;
		this.currentBalance = initAmount;
	}

	public void deposit(double amount) {
		/**
		 *  lock this object before anyone can change the account
		 *  Delay one seconds to simulate the deposit processing
		 */
		synchronized(this) {
			double prevBalance = currentBalance;
			try {
				System.out.println("Try to deposit ammount:"+amount);
				Thread.sleep(1000); 
				
			} catch (Exception e ) {}
			currentBalance = prevBalance + amount;
		}
	}
	public void withdraw (double amount) {
		/**
		 *  lock this object before anyone can change the account
		 *  Delay one seconds to simulate the deposit processing
		 */
		synchronized(this) {
			double prevBalance = currentBalance;
			
			try {
				if (prevBalance>=amount) {
					try {
						System.out.println("Try to withdraw ammount:"+amount);
						Thread.sleep(1000); 
						
						} catch (Exception e ) {}
						currentBalance = prevBalance - amount;
				} else {
					throw new NoSufficientFundException("No Sufficient Fund for your withdrawing amount "+amount);
				} 
				 
			} catch (NoSufficientFundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
}
