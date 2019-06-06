package com.demo.models;

public class User {

    private String name;
    private String dept;
    private String title;
    private String address;
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public User() {
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User(String name, String dept) {

        this.name = name;
        this.dept = dept;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", dept=" + dept + ", title=" + title + ", address=" + address + "]";
	}

   
   
}
