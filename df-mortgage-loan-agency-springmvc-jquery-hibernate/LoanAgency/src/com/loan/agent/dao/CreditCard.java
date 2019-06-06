package com.loan.agent.dao;

import java.sql.Timestamp;

/**
 * CreditCard entity. @author MyEclipse Persistence Tools
 */

public class CreditCard implements java.io.Serializable {

	// Fields

	private Integer creditId;
	private Integer agentId;
	private String holderFirstName;
	private String holderLastName;
	private String creditCardNo;
	private String verifyNo;
	private String expireDate;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private Timestamp modifiedTime;

	// Constructors

	/** default constructor */
	public CreditCard() {
	}

	/** minimal constructor */
	public CreditCard(Integer agentId) {
		this.agentId = agentId;
	}

	/** full constructor */
	public CreditCard(Integer agentId, String holderFirstName,
			String holderLastName, String creditCardNo, String verifyNo,
			String expireDate, String address, String city, String state,
			String zipCode, Timestamp modifiedTime) {
		this.agentId = agentId;
		this.holderFirstName = holderFirstName;
		this.holderLastName = holderLastName;
		this.creditCardNo = creditCardNo;
		this.verifyNo = verifyNo;
		this.expireDate = expireDate;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.modifiedTime = modifiedTime;
	}

	// Property accessors

	public Integer getCreditId() {
		return this.creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getHolderFirstName() {
		return this.holderFirstName;
	}

	public void setHolderFirstName(String holderFirstName) {
		this.holderFirstName = holderFirstName;
	}

	public String getHolderLastName() {
		return this.holderLastName;
	}

	public void setHolderLastName(String holderLastName) {
		this.holderLastName = holderLastName;
	}

	public String getCreditCardNo() {
		return this.creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getVerifyNo() {
		return this.verifyNo;
	}

	public void setVerifyNo(String verifyNo) {
		this.verifyNo = verifyNo;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}