package com.boot.oauth.server.model2;
/*
 * Define the model class Employee. We will be return this model class as JSON response.
 */
public class Employee {

    private String empId;
    private String empName;
    private String gender;
    private Integer age;
    private double salary;
    
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String empId, String empName, String gender, Integer age, double salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.age = age;
		this.salary = salary;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", gender=" + gender + ", age=" + age + ", salary="
				+ salary + "]";
	}

   

}
