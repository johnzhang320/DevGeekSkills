package com.loan.agent.calculators.vo;

import java.io.Serializable;

public class EmailLineVO implements Serializable {
	 private static final long serialVersionUID = 1L;
	String firstName=null;
	String middleName = null;
	String lastName=null;
	String emailAddress=null;
	boolean valid=false;
	
	 
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName =rltrim(middleName);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName =rltrim(firstName);
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName =rltrim(lastName);
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress =rltrim(emailAddress);
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid =valid;
	}
	public static String rltrim(String s) {
		if (s==null) {
			return null;
		}
		return ltrim(s.trim());
	}
	public static String ltrim(String s) {
	    int i = 0;
	    while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
	        i++;
	    }
	    return s.substring(i);
		
	}
}
