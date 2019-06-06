package com.loan.agent.dao;

/**
 * County entity. @author MyEclipse Persistence Tools
 */

public class County implements java.io.Serializable {

	// Fields

	private Integer countyId;
	private State state;
	private String countyName;
	private String stateSymbol;

	// Constructors

	/** default constructor */
	public County() {
	}

	/** minimal constructor */
	public County(String countyName, String stateSymbol) {
		this.countyName = countyName;
		this.stateSymbol = stateSymbol;
	}

	/** full constructor */
	public County(State state, String countyName, String stateSymbol) {
		this.state = state;
		this.countyName = countyName;
		this.stateSymbol = stateSymbol;
	}

	// Property accessors

	public Integer getCountyId() {
		return this.countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getStateSymbol() {
		return this.stateSymbol;
	}

	public void setStateSymbol(String stateSymbol) {
		this.stateSymbol = stateSymbol;
	}

}