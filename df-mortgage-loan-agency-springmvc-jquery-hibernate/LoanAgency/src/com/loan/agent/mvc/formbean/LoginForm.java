package com.loan.agent.mvc.formbean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.ServletRequestUtils;

import com.loan.agent.dao.Agents;
import com.loan.agent.dao.Compare;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.Users;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
/**
 *  QuoteForm bean includes all data from two tables t_users and t_quote
 *  use this bean as one transaction unit for commandClass in multiple pages of 
 *  AbstractWizardController 
 * @author johnzhang
 *
 */
public class LoginForm implements Serializable {
	 private static final long serialVersionUID = 1L;

	private String userFormAction;
	private String userId=null; 
	private String firstName=null;
	private String lastName=null;
	private String emailAddress=null;
	private String username=null;
	private String password=null;
	private String confirmPassword=null;
	private String state=null;
	private String phoneNo=null;
	private String noneRentalIncome=null;
	private String creditScore=null;
	private String rentalIncome=null;
	private String modifiedDate=null;
	private String lastModifyDate=null;
 	
	 
	
	public Users modelUser() {
	
	 
		Users u  = new Users();
				
				u.setUserId(userId==null? null:Integer.parseInt(userId));
				u.setFirstName(firstName); 
				u.setLastName(lastName); 
				u.setEmailAddress(emailAddress);
				u.setPassword(getPassword());		     
			     		
				u.setState(state); 
				u.setNoneRentalIncome(Utility.getDouble(noneRentalIncome));
				u.setRentalIncome(Utility.getDouble(rentalIncome)); 
				u.setCredtScore(creditScore); 
				u.setLastModifyDate(Utility.getCurrentTimeStamp()); //lastModifyDate,
				u.setPhoneNo(phoneNo);
				 
		return u;
		 
	}
	
 
	public void renderUser(Users u) {
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.emailAddress = u.getEmailAddress();
		this.password = u.getPassword();
		this.state = u.getState();
		
		this.noneRentalIncome = Utility.renderDollar(u.getNoneRentalIncome());
		this.rentalIncome = Utility.renderDollar(u.getRentalIncome());
		this.creditScore = u.getCredtScore();
		this.lastModifyDate = Utility.renderTimestamp(u.getLastModifyDate());
		this.phoneNo = u.getPhoneNo();
	}
	
	
	
	 
	 

	 

	 
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getUserFormAction() {
		return userFormAction;
	}


	public void setUserFormAction(String userFormAction) {
		this.userFormAction = userFormAction;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getLastModifyDate() {
		return lastModifyDate;
	}


	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return ui.convertToSHA256(password);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNoneRentalIncome() {
		return noneRentalIncome;
	}
	public void setNoneRentalIncome(String noneRentalIncome) {
		this.noneRentalIncome = noneRentalIncome;
	}
	
	public String getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getRentalIncome() {
		return rentalIncome;
	}
	public void setRentalIncome(String rentalIncome) {
		this.rentalIncome = rentalIncome;
	}
	
}
