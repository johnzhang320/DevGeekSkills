package com.email.list.reader;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

public class SimpleEmailLineVO implements Serializable{
	private static final Long SerialVersionUID=1L;
	private String lineId=null;
	private int EMAIL_COL;
	private int FIRST_NAME_COL;
	String firstName=null;	 
	String middleName = null;
	String lastName=null;	 
	String emailAddress=null;
	boolean valid=false;
	
	List<EmailDynamicVO> dynaList = null; 
	
	public SimpleEmailLineVO() {}
	
	public SimpleEmailLineVO(int FIRST_NAME_COL,int EMAIL_COL) 
	{
		this.FIRST_NAME_COL=FIRST_NAME_COL;
		this.EMAIL_COL=EMAIL_COL;
	}
	
	public void setEmailLineVO(int col,String firstName,String emailAddress) {
	  if (col==this.EMAIL_COL) {
         this.emailAddress= rltrim(emailAddress);
	  }
      if (col== this.FIRST_NAME_COL) {
            this.firstName =rltrim( firstName);
            
        }
	}
	 
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public List<EmailDynamicVO> getDynaList() {
		return dynaList;
	}

	public void setDynaList(List<EmailDynamicVO> dynaList) {
		this.dynaList = dynaList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public static String rltrim(String s) {
		if (s==null) {
			return null;
		}
		return ltrim(s.trim());
	}
	public static String ltrim(String s) {
	    int i = 0;
	    char arr[]= s.toCharArray();
	    while (i < s.length() && (' '==arr[i] || '\t'==arr[i])) {
	        i++;
	    }
	    return s.substring(i);
		
	}
}
