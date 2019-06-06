package com.loan.agent.dao;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String state;
	private Double noneRentalIncome;
	private Double rentalIncome;
	private Double monthlyDebt;
	private String credtScore;
	private Timestamp lastModifyDate;
	private String phoneNo;
	 

	// Constructors

	public Double getMonthlyDebt() {
		return monthlyDebt;
	}

	public void setMonthlyDebt(Double monthlyDebt) {
		this.monthlyDebt = monthlyDebt;
	}

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(String firstName, String lastName, String emailAddress,
			String password, String state, Double noneRentalIncome,
			Double rentalIncome, String credtScore, Timestamp lastModifyDate,
			String phoneNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.state = state;
		this.noneRentalIncome = noneRentalIncome;
		this.rentalIncome = rentalIncome;
		this.credtScore = credtScore;
		this.lastModifyDate = lastModifyDate;
		this.phoneNo = phoneNo;
		 
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getNoneRentalIncome() {
		return this.noneRentalIncome;
	}

	public void setNoneRentalIncome(Double noneRentalIncome) {
		this.noneRentalIncome = noneRentalIncome;
	}

	public Double getRentalIncome() {
		return this.rentalIncome;
	}

	public void setRentalIncome(Double rentalIncome) {
		this.rentalIncome = rentalIncome;
	}

	public String getCredtScore() {
		return this.credtScore;
	}

	public void setCredtScore(String credtScore) {
		this.credtScore = credtScore;
	}

	public Timestamp getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(Timestamp lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	 

}