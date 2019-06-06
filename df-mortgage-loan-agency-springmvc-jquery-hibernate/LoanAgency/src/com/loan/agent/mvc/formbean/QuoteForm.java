package com.loan.agent.mvc.formbean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

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
public class QuoteForm implements Serializable {
	 private static final long serialVersionUID = 1L;
	private String quoteId=null;
	private String agentId=null;
	private String userId=null;
	private String loanType=null;
	private String loanAmount=null;
	private String purchasePrice=null;
	private String propertyCounty=null;
	private String purchaseStage=null;
	private String purchaseDate=null;
	private String propertyAddress=null;
	private String propertyCity=null;
	private String propertyZipCode=null;
	private String estimateHomeValue=null;
	private String refinanceCashOut=null;
	private String propertyType=null;
	private String occupancyStatus=null;
	private String note=null;
	private String propertyState=null;
	private String firstLoanBalance=null;
	private String firstLoanRate=null;
	private String firstLoanTerm=null;
	private String firstDate=Utility.getHalfYearBeforeToday();
	private String secondLoanBalance=null;
	private String secondLoanRate=null;
	private String secondLoanTerm=null;
	private String firstName=null;
	private String lastName=null;
	private String emailAddress=null;
	private String password=null;
	private String state=null;
	private String phoneNo=null;
	private String noneRentalIncome=null;
	private String creditScore=null;
 	private String rentalIncome=null;
	private String modifiedDate=null;
	private String lastModifyDate=null;
	private String monthDebt=null;
	private List<String> loanTerms=new ArrayList<String>();
	private String monthlyDebt;
	
	
	private String borrowerCreditScore=null;
	private String coBorrowerCreditScore=null;
	private String annualIncome=null;
	
	/**
	 *  Calculate fields
	 */
	private String monthlyIncome=null;		
	private String loanToValue=null;	
	
	public String getMonthlyDebt() {
		return monthlyDebt;
	}

	public void setMonthlyDebt(String monthlyDebt) {
		this.monthlyDebt = monthlyDebt;
	}
   
	public Quote modelQuote() {
		Quote q = new  Quote();
		/**
		 *  Ignore data which are not String, then use BeanUtils.copyProperties copy all String form variable to quote
		 */
        String []  ignoreProperties ={
        		"quoteId",
        		"agents",
        		"users",		        		 
        		"loanAmount",
        		"purchasePrice",
        		"purchaseDate",
        		"modifiedDate",
        		"estimateHomeValue",
        		"firstLoanBalance",
        		"firstLoanRate",
        		"firstDate",
        		"secondLoanBalance",
        		"secondLoanRate",
        		"compare",
        		"userId",
				"noneRentalIncome",
				"annualIncome",
				"monthDebt",
				"rentalIncome",
				"credtScore",
				"lastModifyDate",
				"phoneNo",
				"quotes"	  
        };
        BeanUtils.copyProperties(this, q, ignoreProperties);

				q.setOccupancyStatus(occupancyStatus);
				
				q.setPropertyType(propertyType);
			 	q.setPropertyAddress(propertyAddress);
			 	q.setPropertyCity(propertyCity);
			 	q.setPropertyState(propertyState);
			 	q.setPropertyZipCode(propertyZipCode);
			 	q.setPropertyCounty(propertyCounty);
			 	 
		        q.setFirstName(this.firstName);
		        q.setLastName(this.lastName);
		        q.setEmailAddress(this.emailAddress);
		        q.setPhoneNo(this.phoneNo); 
		        q.setAnnualIncome(Utility.getDouble(annualIncome));
		        q.setBorrowerCreditScore(borrowerCreditScore);
		        q.setCoBorrowerCreditScore(coBorrowerCreditScore);
		        
		        q.setLoanAmount(Utility.getDouble(loanAmount));
		        q.setPurchasePrice(Utility.getDouble(purchasePrice)); 
		        
		    	//q.setPurchaseDate(Utility.getDate(purchaseDate));
		    	q.setModifiedDate(Utility.getCurrentTimeStamp()); //modifiedDate);
				q.setEstimateHomeValue(Utility.getDouble(estimateHomeValue));
			 
				q.setFirstLoanBalance(Utility.getDouble(firstLoanBalance)); 
				q.setFirstLoanRate(Utility.getFloat(firstLoanRate)); 
			 
				q.setSecondLoanBalance(Utility.getDouble(secondLoanBalance)); 
				q.setSecondLoanRate(Utility.getFloat(secondLoanRate));
				q.setFirstDate(Utility.getDate(firstDate));
				//q.setCreateDate(Utility.getCurrentTimeStamp());
			
				
				
				 
		return q;
	}
	
 

	public Users modelUser() {
		  
		  
		Users u  = new Users();
		
				//u.setUserId(userId==null? null:Integer.parseInt(userId));
				u.setFirstName(firstName); 
				u.setLastName(lastName); 
				u.setEmailAddress(emailAddress);	 
			    
			    u.setPassword(password);	
			     
				u.setState(state); 
				u.setNoneRentalIncome(Utility.getDouble(noneRentalIncome));
				u.setRentalIncome(Utility.getDouble(annualIncome)); 
				u.setCredtScore(borrowerCreditScore); 
				u.setLastModifyDate(Utility.getCurrentTimeStamp()); //lastModifyDate,
				u.setPhoneNo(phoneNo);
				u.setMonthlyDebt(Utility.getDouble(monthlyDebt));
		return u;
	}
	
	public void renderQuote(Quote q) {
		this.loanType = q.getLoanType();
		this.loanAmount = Utility.renderDollar(q.getLoanAmount());
		this.purchasePrice = Utility.renderDollar(q.getPurchasePrice());
	    if (null==q.getAnnualIncome()) q.setAnnualIncome(0.0);
	    if (null==q.getLoanAmount()) q.setLoanAmount(0.0);
	  
		this.annualIncome = Utility.renderDollar(q.getAnnualIncome());
		 
		this.monthlyIncome =  Utility.renderDollar(q.getAnnualIncome()/12.0);
		if ("Refinance".equalsIgnoreCase(this.loanType)) {
			  if (null==q.getEstimateHomeValue() || 0.0==q.getEstimateHomeValue()) {
				  this.loanToValue = "";
			  } else {
				  this.loanToValue =  Utility.renderRate((q.getLoanAmount()/q.getEstimateHomeValue())*100.0);
			  }
		}
		if ("Purchase".equalsIgnoreCase(this.loanType)) {
			 if (null==q.getPurchasePrice() || 0.0==q.getPurchasePrice()) {
				 this.loanToValue ="";
			 } else {
				 this.loanToValue =  Utility.renderRate((q.getLoanAmount()/q.getPurchasePrice())*100.0);
			 }
		}
		this.borrowerCreditScore = q.getBorrowerCreditScore();
		this.coBorrowerCreditScore = q.getCoBorrowerCreditScore();
		this.propertyCounty =  q.getPropertyCounty();
		this.purchaseStage = q.getPurchaseStage();
		this.purchaseDate =  Utility.renderDate(q.getPurchaseDate());
		this.propertyAddress = q.getPropertyAddress();
		this.propertyCity = q.getPropertyCity();
		this.propertyState = q.getPropertyState();
		this.propertyZipCode = q.getPropertyZipCode();
		this.propertyType = q.getPropertyType();
		this.occupancyStatus = q.getOccupancyStatus();
		this.note = q.getNote();
		this.modifiedDate = Utility.renderTimestamp(q.getModifiedDate());
		this.estimateHomeValue = Utility.renderDollar(q.getEstimateHomeValue());
		this.refinanceCashOut = q.getRefinanceCashOut();
		this.firstLoanBalance = Utility.renderDollar(q.getFirstLoanBalance());
		this.firstLoanRate = Utility.renderRate(q.getFirstLoanRate());
		this.firstLoanTerm = q.getFirstLoanTerm();
		this.secondLoanBalance = Utility.renderDollar(q.getSecondLoanBalance());
		this.secondLoanRate = Utility.renderRate(q.getSecondLoanRate());
		this.secondLoanTerm = q.getSecondLoanTerm();
		this.firstName = q.getFirstName();
		this.lastName = q.getLastName();
		this.emailAddress = q.getEmailAddress();
		this.phoneNo = q.getPhoneNo();
	
	}
	public void renderUser(Users u) {
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.emailAddress = u.getEmailAddress();
		this.password = u.getPassword();
		this.state = u.getState();
		
		this.noneRentalIncome = Utility.renderDollar(u.getNoneRentalIncome());
		this.annualIncome = Utility.renderDollar(u.getRentalIncome());
		this.borrowerCreditScore = u.getCredtScore();
		this.lastModifyDate = Utility.renderTimestamp(u.getLastModifyDate());
		this.phoneNo = u.getPhoneNo();
		this.monthlyDebt = Utility.renderDollar(u.getMonthlyDebt());
	}
	
	
	
	 
	 

	 

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getMonthDebt() {
		return monthDebt;
	}

	public void setMonthDebt(String monthDebt) {
		this.monthDebt = monthDebt;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getLoanTerms() {
		return loanTerms;
	}

	public void setLoanTerms(List<String> loanTerms) {
		this.loanTerms = loanTerms;
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

	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getPropertyCounty() {
		return propertyCounty;
	}
	public void setPropertyCounty(String propertyCounty) {
		this.propertyCounty = propertyCounty;
	}
	public String getPurchaseStage() {
		return purchaseStage;
	}
	public void setPurchaseStage(String purchaseStage) {
		this.purchaseStage = purchaseStage;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getPropertyCity() {
		return propertyCity;
	}
	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}
	public String getPropertyZipCode() {
		return propertyZipCode;
	}
	public void setPropertyZipCode(String propertyZipCode) {
		this.propertyZipCode = propertyZipCode;
	}
	public String getEstimateHomeValue() {
		return estimateHomeValue;
	}
	public void setEstimateHomeValue(String estimateHomeValue) {
		this.estimateHomeValue = estimateHomeValue;
	}
	 
	public String getRefinanceCashOut() {
		return refinanceCashOut;
	}
	public void setRefinanceCashOut(String refinanceCashOut) {
		this.refinanceCashOut = refinanceCashOut;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getOccupancyStatus() {
		return occupancyStatus;
	}
	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPropertyState() {
		return propertyState;
	}
	public void setPropertyState(String propertyState) {
		this.propertyState = propertyState;
	}
	public String getFirstLoanBalance() {
		return firstLoanBalance;
	}
	public void setFirstLoanBalance(String firstLoanBalance) {
		this.firstLoanBalance = firstLoanBalance;
	}
	public String getFirstLoanRate() {
		return firstLoanRate;
	}
	public void setFirstLoanRate(String firstLoanRate) {
		this.firstLoanRate = firstLoanRate;
	}
	public String getFirstLoanTerm() {
		return firstLoanTerm;
	}
	public void setFirstLoanTerm(String firstLoanTerm) {
		this.firstLoanTerm = firstLoanTerm;
	}
	public String getSecondLoanBalance() {
		return secondLoanBalance;
	}
	public void setSecondLoanBalance(String secondLoanBalance) {
		this.secondLoanBalance = secondLoanBalance;
	}
	public String getSecondLoanRate() {
		return secondLoanRate;
	}
	public void setSecondLoanRate(String secondLoanRate) {
		this.secondLoanRate = secondLoanRate;
	}
	public String getSecondLoanTerm() {
		return secondLoanTerm;
	}
	public void setSecondLoanTerm(String secondLoanTerm) {
		this.secondLoanTerm = secondLoanTerm;
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
		return password;
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
	
 

	public String getBorrowerCreditScore() {
		return borrowerCreditScore;
	}

	public void setBorrowerCreditScore(String borrowerCreditScore) {
		this.borrowerCreditScore = borrowerCreditScore;
	}

	public String getCoBorrowerCreditScore() {
		return coBorrowerCreditScore;
	}

	public void setCoBorrowerCreditScore(String coBorrowerCreditScore) {
		this.coBorrowerCreditScore = coBorrowerCreditScore;
	}

	 

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}



	public String getLoanToValue() {
		return loanToValue;
	}

	public void setLoanToValue(String loanToValue) {
		this.loanToValue = loanToValue;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
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
