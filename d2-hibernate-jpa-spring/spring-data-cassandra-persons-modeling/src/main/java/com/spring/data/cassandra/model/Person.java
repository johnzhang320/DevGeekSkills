package com.spring.data.cassandra.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Indexed;
/**
 * Critical thing is primary key in below mapping
 * The primary key is marked by using the aptly named @PrimaryKey annotation. Depending on how your table is constructed the 
 * key field could be a simple field like a String but due to this example using a more interesting key, a separate class has been used.

 table structure would be helpful.
	
	CREATE TABLE people_by_social_security(
	  social_security TEXT,
	  first_name TEXT,
	  date_of_birth TIMESTAMP,
	  person_id UUID,
	  last_name TEXT,
	  salary DOUBLE,
	  PRIMARY KEY ((social_security), date_of_birth, person_id)
	) WITH CLUSTERING ORDER BY (date_of_birth ASC, person_id DESC);
	
    primary key consists of partition and clustering columns, in this example the only partition column is social_security and 
    the clustering columns are date_of_birth and person_id. 
	Primary key is defined by class : PersonKey.
 */
@Table("people_by_social_security")
public class Person {

  @PrimaryKey 
  private PersonKey key;

  @Indexed(value="first_name")   // I want to search by first_name 
  private String firstName;

 
  @Indexed(value="last_name")   // I want to search by last_name 
  private String lastName;

  @Column 
  private double salary;

  public Person(final PersonKey key, final String firstName, final String lastName, final double salary) {
    this.key = key;
    this.firstName=firstName;
    this.lastName = lastName;
    this.salary = salary;
  }

  

  @Override
public String toString() {
	return "Person [key=" + key.toString() + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary + "]";
}



public PersonKey getKey() {
    return key;
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



public double getSalary() {
	return salary;
}



public void setSalary(double salary) {
	this.salary = salary;
}



public void setKey(PersonKey key) {
	this.key = key;
}

 
 
}
