package com.spring.rest.ssl.model;

import java.util.ArrayList;
import java.util.List;

public class Dept {
	private int deptId;
	private String dept_name;
	private String dept_location;
	private List<Person> persons=new ArrayList<>();
	
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Dept(int deptId, String dept_name, String dept_location, List<Person> persons) {
		super();
		this.deptId = deptId;
		this.dept_name = dept_name;
		this.dept_location = dept_location;
		this.persons = persons;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_location() {
		return dept_location;
	}

	public void setDept_location(String dept_location) {
		this.dept_location = dept_location;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", dept_name=" + dept_name + ", dept_location=" + dept_location + ", persons="
				+ persons+ "]";
	}
	
	
	
}
