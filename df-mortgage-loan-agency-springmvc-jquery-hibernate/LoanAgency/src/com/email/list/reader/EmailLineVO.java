package com.email.list.reader;

import org.apache.poi.ss.usermodel.Cell;

public class EmailLineVO {
	private int EMAIL_COL;
	private int FIRST_NAME_COL;
	private int LAST_NAME_COL;
	private int PHONE_NUMBER_COL;
	
	String firstName=null;
	String middleName = null;
	String lastName=null;
	String emailAddress=null;
	String address=null;
	String phoneNumber=null;
	String category=null;
	boolean valid=false;
	
	
	public EmailLineVO() {}
	
	public EmailLineVO(int EMAIL_COL,int FIRST_NAME_COL) 
	{
		this.EMAIL_COL=EMAIL_COL;
		this.FIRST_NAME_COL=FIRST_NAME_COL;
	}
	
	 
	public int getEMAIL_COL() {
		return EMAIL_COL;
	}

	public void setEMAIL_COL(int eMAIL_COL) {
		EMAIL_COL = eMAIL_COL;
	}

	public int getFIRST_NAME_COL() {
		return FIRST_NAME_COL;
	}

	public void setFIRST_NAME_COL(int fIRST_NAME_COL) {
		FIRST_NAME_COL = fIRST_NAME_COL;
	}

	public int getLAST_NAME_COL() {
		return LAST_NAME_COL;
	}

	public void setLAST_NAME_COL(int lAST_NAME_COL) {
		LAST_NAME_COL = lAST_NAME_COL;
	}

	public int getPHONE_NUMBER_COL() {
		return PHONE_NUMBER_COL;
	}

	public void setPHONE_NUMBER_COL(int pHONE_NUMBER_COL) {
		PHONE_NUMBER_COL = pHONE_NUMBER_COL;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
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
