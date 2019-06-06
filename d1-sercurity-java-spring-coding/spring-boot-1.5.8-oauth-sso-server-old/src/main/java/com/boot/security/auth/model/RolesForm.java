package com.boot.security.auth.model;

import java.util.List;


public class RolesForm {
	private String username;
	private List<String> myRoles;
	private String message;
	
	
	public RolesForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(List<String> myRoles) {
		this.myRoles = myRoles;
	}

	@Override
	public String toString() {
		return "RolesForm [username=" + username + ", myRoles=" + myRoles + ", message=" + message + "]";
	}
 

 
 
	
}
