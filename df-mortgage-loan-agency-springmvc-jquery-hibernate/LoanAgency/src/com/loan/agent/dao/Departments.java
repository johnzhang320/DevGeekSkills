package com.loan.agent.dao;

import java.util.HashSet;
import java.util.Set;

/**
 * Departments entity. @author MyEclipse Persistence Tools
 */

public class Departments implements java.io.Serializable {

	// Fields

	private Integer departmentId;
	private String departmentName;
	private Set employeeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Departments() {
	}

	/** minimal constructor */
	public Departments(String departmentName) {
		this.departmentName = departmentName;
	}

	/** full constructor */
	public Departments(String departmentName, Set employeeses) {
		this.departmentName = departmentName;
		this.employeeses = employeeses;
	}

	// Property accessors

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set getEmployeeses() {
		return this.employeeses;
	}

	public void setEmployeeses(Set employeeses) {
		this.employeeses = employeeses;
	}

}