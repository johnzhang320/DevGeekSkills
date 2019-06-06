package com.interview.questions.sample.ATM.machine;

import java.util.List;

public class User {
	// name here can be username or bank card bar code 
	private String name;
	// If I have more time , I will implement pin
	private String pin;
	// List of account is account type list such as checking , saving or brokerage account
	private List<Account> accountList;
	private int userId;
	private String ssno;   // social security No
	 
	
	public User(String name, String pin) {
		super();
		this.name = name;
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getSsno() {
		return ssno;
	}
	public void setSsno(String ssno) {
		this.ssno = ssno;
	}
	
}
