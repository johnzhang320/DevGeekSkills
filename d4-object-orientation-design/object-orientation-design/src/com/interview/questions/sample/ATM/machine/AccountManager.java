package com.interview.questions.sample.ATM.machine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author John Zhang
 *
 */
public  interface AccountManager {
	
	/**
	 * User and Account Management APIa
	 */
	 
	 
	 public void addAccountToUser(String userName, AccountType acctType);
	 
	 /**
	  * Simulate the open bank account, set session (userId and accountId)
	  * @param userName
	  * @param initBalance
	  * @param accType
	  */
	 public void openAccount(String userName, double initBalance, AccountType acctType, String ssno) ;
	 
	 
	/**
	 * login username to simulate login , if I have time to implement password
	 * session userid will be initialized , not found , initialize -1
	 * Performance, use userIndex, O(1) to find the user
	 * @param userName
	 * @return
	 */
	 public User login(String userName) ;
	 public User findUserBySSNO(String ssno);
	 public Account findMyAcct(String userName, AccountType type);
	 /**
	  * Deposit to specified Account type
	  * @param amount
	  * @param type
	  */
	 public void deposit(double amount,AccountType type) ;
	 public void withdraw(double amount,AccountType type) ;
	 /**
	  * Ensure found source account and destination account before transfering
	  * @param amount
	  * @param sourceType
	  * @param destType
	  */
	 public void reset();
	 public void transfer(double amount,AccountType sourceType, AccountType destType) ;
	 public int getUserId() ;
	 public void setUserId(int userId) ;
	 public int getAccountId() ;
	 public void setAccountId(int accountId) ;
	 
}
