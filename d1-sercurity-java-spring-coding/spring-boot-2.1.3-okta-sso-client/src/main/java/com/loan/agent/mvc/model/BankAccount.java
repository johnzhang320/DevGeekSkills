package com.loan.agent.mvc.model;

public class BankAccount {
	String name;
	String email;
	String address;
	String checking;
	String saving;
	
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BankAccount(String name, String email, String address, String checking, String saving) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.checking = checking;
		this.saving = saving;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getChecking() {
		return checking;
	}
	public void setChecking(String checking) {
		this.checking = checking;
	}
	public String getSaving() {
		return saving;
	}
	public void setSaving(String saving) {
		this.saving = saving;
	}
	
}
