package com.interview.questions.sample.ATM.machine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author John Zhang
 *
 */
public abstract class AccountManagerImpl implements AccountManager {
	/**
	 * static acctMap and userMap simulate database, each one keep one copy to keep data consistent
	 */
	 private static SafeStorage<Account> acctMap =new SafeStorage<Account>();
	 private static SafeStorage<User> userMap = new SafeStorage<User>();	
	 /**
	  *  Index username and ssNo create by Map<T,Id> for performance, id is joint to userId of userMap
	  */
	 private static IndexStorage<String> userIndex = new IndexStorage<String>();
	 private static IndexStorage<String> ssnoIndex = new IndexStorage<String>();
		 
	 // current session information
	 private int userId=-1;
	 private int accountId=-1;
	 public AccountManagerImpl() {
		 initialize();
	 }
	 public abstract void initialize();
	 
	 public void addAccountToUser(String userName, AccountType acctType) { 
		 
		try {  
			 if (userIndex.hasItem(userName)) {	
				 userId = userIndex.getQuantity(userName);
				 User user = userMap.get(userId);
				 List<Account> list = user.getAccountList();
				
				 if (isAccountTypeExist(acctType, list)) {				 
					 throw new AccountExistsException("Account already exist on username "+userName);
				 } else {
					  //  We already deposit initial amount while open account
					 Account acct = new Account(acctType);
					 list.add(acct);
					 user.setAccountList(list);
					 userMap.put(userId, user);
				 } 
			 }
		 } catch (AccountExistsException e ) {
				System.out.println(e.getMessage());
		 } catch (WrongIDException e ) {
				System.out.println(e.getMessage());
		 }
	}
	private boolean isAccountTypeExist(AccountType type, List<Account> list) {
		for (Account acct: list) {
			if (type==acct.getAccountType()) {
				return true;
			}
		}
		return false;
	}
	private Account findAccountTypeByUser(User user, AccountType type) {
		List<Account> list = user.getAccountList();
		for (Account acct: list) {
			if (type==acct.getAccountType()) {
				return acct;
			}
		}
		return null;
	}
	 /**
	  * Simulate the open bank account, set session (userId and accountId)
	  * @param userName
	  * @param initBalance
	  * @param accType
	  */
	 public void openAccount(String userName, double initBalance, AccountType acctType, String ssno) { 
		 
		try {  
			 if (!userIndex.hasItem(userName)) {		
				 User user = new User(userName,ssno);				 
				 Account acct = new Account(acctType);
				 acct.deposit(initBalance);
				 List<Account> list = new ArrayList<Account>();
				 list.add(acct);
				 user.setAccountList(list);
				 acctMap.put(acct.getAccountId(),acct);
				 userMap.put(user.getUserId(), user);	
				 userId = user.getUserId();
				 userIndex.put(userName, userId);
				 ssnoIndex.put(ssno, userId);
				 accountId = acct.getAccountId();
			 }else {
				 throw new AccountExistsException("Account already exist on username "+userName);
			 }
		  
		} catch (AccountExistsException e ) {
			System.out.println(e.getMessage());
		}
	 }
	 
	 
	/**
	 * login username to simulate login , if I have time to implement password
	 * session userid will be initialized , not found , initialize -1
	 * Performance, use userIndex, O(1) to find the user
	 * @param userName
	 * @return
	 */
	 public User login(String userName) {
		 User retVal=null;
		 try {
			 if (userIndex.hasItem(userName)) {
				 userId = userIndex.getQuantity(userName);
				 retVal = userMap.get(userId);
			 } else {
				
				 throw new UserNotFoundException("User not found on username "+userName);
			 }
		} catch (UserNotFoundException e ) {
			System.out.println(e.getMessage());
			userId = -1;
		} catch (WrongIDException e ) {
			System.out.println(e.getMessage());
		}
		 
		return retVal;
	 }
	 public User findUserBySSNO(String ssno) {
		 User retVal=null;
		 try {
			 if (ssnoIndex.hasItem(ssno)) {
				 userId = ssnoIndex.getQuantity(ssno);
				 retVal = userMap.get(userId);
			 } else {
				
				 throw new UserNotFoundException("User not found on social security "+ssno);
			 }
		} catch (UserNotFoundException e ) {
			System.out.println(e.getMessage());
			userId = -1;
		} catch (WrongIDException e ) {
			System.out.println(e.getMessage());
		}
		 
		return retVal;
	 }
	 public Account findMyAcct(String userName, AccountType type) {
	 		 
		 User user = login(userName);
		 if (null!=user) {
			 return findAccountTypeByUser(user,type);
		 }
	 	 return null;
	 }
	 /**
	  * Deposit to specified Account type
	  * @param amount
	  * @param type
	  */
	 public void deposit(double amount,AccountType type) {
		 if (userId==-1) {
			 System.out.println("You are not login");
			 return;
		 }
		 User user=null;
		 if (userMap.findId(userId)) {
			 user = userMap.getStorage().get(userId);
		 }
		 if (null!=user) {
			 List<Account> list = user.getAccountList();
			 for (Account acct: list) {
				 if (acct.getAccountType()==type) {
					 acct.deposit(amount);
					 return;
				 }
			 }
		 }
		 
	 }
	 public void withdraw(double amount,AccountType type) {
		 if (userId==-1) {
			 System.out.println("You are not login");
			 return;
		 }
		 User user=null;
		 if (userMap.findId(userId)) {
			 user = userMap.getStorage().get(userId);
		 }
		 if (null!=user) {
			 List<Account> list = user.getAccountList();
			 for (Account acct: list) {
				 if (acct.getAccountType()==type) {
					 acct.withdraw(amount);
					 return;
				 }
			 }
		 }
	 }
	 /**
	  * Ensure found source account and destination account before transfering
	  * @param amount
	  * @param sourceType
	  * @param destType
	  */
	 public void transfer(double amount,AccountType sourceType, AccountType destType) {
		 if (userId==-1) {
			 System.out.println("You are not login");
			 return;
		 }
		 User user=null;
		 if (userMap.findId(userId)) {
			 user = userMap.getStorage().get(userId);
		 }
		 Account source =null;
		 Account dest = null;
		 if (null!=user) {
			 List<Account> list = user.getAccountList();
			 for (Account acct: list) {
				 if (acct.getAccountType()==sourceType) {
					 source = acct;
				 }
				 if (acct.getAccountType()==destType) {
					 dest = acct;
				 }
			 }
			 if (null==source) {
				System.out.println("Source Account not found");
				return;
			 } else if (null==dest) {
				 System.out.println("Destination Account not found");
				 return; 
			 } else {
				 TransferFund tran = new TransferFund(source,dest);
				 tran.transfer(amount);
			 }
			 
		 }
	 }
	public void reset() {
		userMap.clear();
		acctMap.clear();
		userIndex.clear();
		ssnoIndex.clear();
		userId=-1;
		accountId = -1;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	 
}
