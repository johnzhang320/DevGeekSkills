package com.custom.annotation.model;

import com.custom.annotation.define.InitMethod;
import com.custom.annotation.define.JsonElementField;
import com.custom.annotation.define.JsonSerializableType;
/*
 * By using our custom annotations, we’re indicating that we can serialize a Employee object to a JSON string. In addition,
 *  the output should contain only the firstName, lastName, and age fields of that object. Moreover, we want the initNames()
 *   method to be called before serialization.

	By setting the key parameter of the @JsonElement annotation to “employeeAge”, we are indicating that we’ll use this name as the 
	identifier for the field in the JSON output.
	
	For the sake of demonstration, we made initNames() private, so we can’t initialize our object by calling it manually, and our 
	constructors aren’t using it either.
 */
@JsonSerializableType
public class Employee {


	@JsonElementField
	private String firstName;
	
	@JsonElementField
	private String lastName;
	
	@JsonElementField(key="employeeAge")
	private String age;
	
	private String address;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String lastName, String age, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
	}

	@InitMethod
	 private void initNames() {
        this.firstName = this.firstName.substring(0, 1).toUpperCase() 
          + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0, 1).toUpperCase() 
          + this.lastName.substring(1);
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", address=" + address
				+ "]";
	}
	
}
