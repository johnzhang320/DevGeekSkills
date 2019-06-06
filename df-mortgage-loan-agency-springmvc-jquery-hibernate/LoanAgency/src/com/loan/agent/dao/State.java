package com.loan.agent.dao;

import java.util.HashSet;
import java.util.Set;

/**
 * State entity. @author MyEclipse Persistence Tools
 */

public class State implements java.io.Serializable {

	// Fields

	private Integer stateId;
	private String stateSymbol;
	private String stateName;
	private Set counties = new HashSet(0);

	// Constructors

	/** default constructor */
	public State() {
	}

	/** minimal constructor */
	public State(String stateSymbol) {
		this.stateSymbol = stateSymbol;
	}

	/** full constructor */
	public State(String stateSymbol, String stateName, Set counties) {
		this.stateSymbol = stateSymbol;
		this.stateName = stateName;
		this.counties = counties;
	}

	// Property accessors

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateSymbol() {
		return this.stateSymbol;
	}

	public void setStateSymbol(String stateSymbol) {
		this.stateSymbol = stateSymbol;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Set getCounties() {
		return this.counties;
	}

	public void setCounties(Set counties) {
		this.counties = counties;
	}

}