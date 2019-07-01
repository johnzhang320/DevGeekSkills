package com.ssl.encrypted.jersey.restful.by.sslcontext.model;

 
 
public class Person {
	
	private int deptId;
	
 	private String firstName;
	  
	private String lastName;
	
	private String dateOfBirth;
	  
	private String gender;
	  
	private double salary;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(int deptId,String firstName, String lastName, String dateOfBirth, String gender, double salary) {
		super();
		this.deptId = deptId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.salary = salary;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Person [deptId=" + deptId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", salary=" + salary + "]";
	}

	 
 
}
