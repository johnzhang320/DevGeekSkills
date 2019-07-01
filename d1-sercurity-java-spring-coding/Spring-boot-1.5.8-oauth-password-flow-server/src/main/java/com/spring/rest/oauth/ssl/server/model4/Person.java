package com.spring.rest.oauth.ssl.server.model4;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	private Integer id; 
	private String name;
  	private Address address;
  	public Person(){}
  	public Person(Integer id, String name, Address address){
  		this.id = id;
  		this.name = name;
  		this.address = address;
  	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
