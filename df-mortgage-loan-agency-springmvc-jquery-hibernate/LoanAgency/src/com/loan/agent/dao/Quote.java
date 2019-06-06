package com.loan.agent.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Quote entity. @author MyEclipse Persistence Tools
 */

public class Quote  implements java.io.Serializable {


    // Fields    

     private Integer quoteId;
     private Agents agents;
     private Users users;
     private String loanType;
     private Double loanAmount;
     private Double purchasePrice;
     private String propertyCounty;
     private String purchaseStage;
     private Date purchaseDate;
     private String propertyAddress;
     private String propertyCity;
     private String propertyZipCode;
     private String propertyType;
     private String occupancyStatus;
     private String note;
     private Timestamp modifiedDate;
     private Double estimateHomeValue;
     private String refinanceCashOut;
     private Double firstLoanBalance;
     private Float firstLoanRate;
     private String firstLoanTerm;
     private Double secondLoanBalance;
     private Float secondLoanRate;
     private String secondLoanTerm;
     private String propertyState;
     private String processStatus;
     private Timestamp createdDate;
     private Date firstDate;
     private String selectedNiche;
     private String firstName;
     private String lastName;
     private String emailAddress;
     private String phoneNo;
     private String borrowerCreditScore;
     private String coBorrowerCreditScore;
     private Double annualIncome;
     private Set compares = new HashSet(0);


    // Constructors

    /** default constructor */
    public Quote() {
    }

    
    /** full constructor */
    public Quote(Agents agents, Users users, String loanType, Double loanAmount, Double purchasePrice, String propertyCounty, String purchaseStage, Date purchaseDate, String propertyAddress, String propertyCity, String propertyZipCode, String propertyType, String occupancyStatus, String note, Timestamp modifiedDate, Double estimateHomeValue, String refinanceCashOut, Double firstLoanBalance, Float firstLoanRate, String firstLoanTerm, Double secondLoanBalance, Float secondLoanRate, String secondLoanTerm, String propertyState, String processStatus, Timestamp createdDate, Date firstDate, String selectedNiche, String firstName, String lastName, String emailAddress, String phoneNo, String borrowerCreditScore, String coBorrowerCreditScore, Double annualIncome, Set compares) {
        this.agents = agents;
        this.users = users;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.purchasePrice = purchasePrice;
        this.propertyCounty = propertyCounty;
        this.purchaseStage = purchaseStage;
        this.purchaseDate = purchaseDate;
        this.propertyAddress = propertyAddress;
        this.propertyCity = propertyCity;
        this.propertyZipCode = propertyZipCode;
        this.propertyType = propertyType;
        this.occupancyStatus = occupancyStatus;
        this.note = note;
        this.modifiedDate = modifiedDate;
        this.estimateHomeValue = estimateHomeValue;
        this.refinanceCashOut = refinanceCashOut;
        this.firstLoanBalance = firstLoanBalance;
        this.firstLoanRate = firstLoanRate;
        this.firstLoanTerm = firstLoanTerm;
        this.secondLoanBalance = secondLoanBalance;
        this.secondLoanRate = secondLoanRate;
        this.secondLoanTerm = secondLoanTerm;
        this.propertyState = propertyState;
        this.processStatus = processStatus;
        this.createdDate = createdDate;
        this.firstDate = firstDate;
        this.selectedNiche = selectedNiche;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNo = phoneNo;
        this.borrowerCreditScore = borrowerCreditScore;
        this.coBorrowerCreditScore = coBorrowerCreditScore;
        this.annualIncome = annualIncome;
        this.compares = compares;
    }

   
    // Property accessors

    public Integer getQuoteId() {
        return this.quoteId;
    }
    
    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public Agents getAgents() {
        return this.agents;
    }
    
    public void setAgents(Agents agents) {
        this.agents = agents;
    }

    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }

    public String getLoanType() {
        return this.loanType;
    }
    
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return this.loanAmount;
    }
    
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getPurchasePrice() {
        return this.purchasePrice;
    }
    
    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPropertyCounty() {
        return this.propertyCounty;
    }
    
    public void setPropertyCounty(String propertyCounty) {
        this.propertyCounty = propertyCounty;
    }

    public String getPurchaseStage() {
        return this.purchaseStage;
    }
    
    public void setPurchaseStage(String purchaseStage) {
        this.purchaseStage = purchaseStage;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }
    
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPropertyAddress() {
        return this.propertyAddress;
    }
    
    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyCity() {
        return this.propertyCity;
    }
    
    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPropertyZipCode() {
        return this.propertyZipCode;
    }
    
    public void setPropertyZipCode(String propertyZipCode) {
        this.propertyZipCode = propertyZipCode;
    }

    public String getPropertyType() {
        return this.propertyType;
    }
    
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getOccupancyStatus() {
        return this.occupancyStatus;
    }
    
    public void setOccupancyStatus(String occupancyStatus) {
        this.occupancyStatus = occupancyStatus;
    }

    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Double getEstimateHomeValue() {
        return this.estimateHomeValue;
    }
    
    public void setEstimateHomeValue(Double estimateHomeValue) {
        this.estimateHomeValue = estimateHomeValue;
    }

    public String getRefinanceCashOut() {
        return this.refinanceCashOut;
    }
    
    public void setRefinanceCashOut(String refinanceCashOut) {
        this.refinanceCashOut = refinanceCashOut;
    }

    public Double getFirstLoanBalance() {
        return this.firstLoanBalance;
    }
    
    public void setFirstLoanBalance(Double firstLoanBalance) {
        this.firstLoanBalance = firstLoanBalance;
    }

    public Float getFirstLoanRate() {
        return this.firstLoanRate;
    }
    
    public void setFirstLoanRate(Float firstLoanRate) {
        this.firstLoanRate = firstLoanRate;
    }

    public String getFirstLoanTerm() {
        return this.firstLoanTerm;
    }
    
    public void setFirstLoanTerm(String firstLoanTerm) {
        this.firstLoanTerm = firstLoanTerm;
    }

    public Double getSecondLoanBalance() {
        return this.secondLoanBalance;
    }
    
    public void setSecondLoanBalance(Double secondLoanBalance) {
        this.secondLoanBalance = secondLoanBalance;
    }

    public Float getSecondLoanRate() {
        return this.secondLoanRate;
    }
    
    public void setSecondLoanRate(Float secondLoanRate) {
        this.secondLoanRate = secondLoanRate;
    }

    public String getSecondLoanTerm() {
        return this.secondLoanTerm;
    }
    
    public void setSecondLoanTerm(String secondLoanTerm) {
        this.secondLoanTerm = secondLoanTerm;
    }

    public String getPropertyState() {
        return this.propertyState;
    }
    
    public void setPropertyState(String propertyState) {
        this.propertyState = propertyState;
    }

    public String getProcessStatus() {
        return this.processStatus;
    }
    
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public Timestamp getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Date getFirstDate() {
        return this.firstDate;
    }
    
    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public String getSelectedNiche() {
        return this.selectedNiche;
    }
    
    public void setSelectedNiche(String selectedNiche) {
        this.selectedNiche = selectedNiche;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBorrowerCreditScore() {
        return this.borrowerCreditScore;
    }
    
    public void setBorrowerCreditScore(String borrowerCreditScore) {
        this.borrowerCreditScore = borrowerCreditScore;
    }

    public String getCoBorrowerCreditScore() {
        return this.coBorrowerCreditScore;
    }
    
    public void setCoBorrowerCreditScore(String coBorrowerCreditScore) {
        this.coBorrowerCreditScore = coBorrowerCreditScore;
    }

    public Double getAnnualIncome() {
        return this.annualIncome;
    }
    
    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Set getCompares() {
        return this.compares;
    }
    
    public void setCompares(Set compares) {
        this.compares = compares;
    }
   








}