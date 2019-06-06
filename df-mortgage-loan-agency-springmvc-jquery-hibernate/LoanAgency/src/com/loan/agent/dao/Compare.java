package com.loan.agent.dao;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Compare entity. @author MyEclipse Persistence Tools
 */

public class Compare implements java.io.Serializable {

	// Fields

	private Integer quoteId;
	private Quote quote;
	private String loanTerm;
	private String loanType;
	private String loanAmount;
	private Float interestRate;
	private Date firstPaymentDate;
	private String loan1Term;
	private Double loan1Amount;
	private Float loan1InterestRate;
	private Double loan1ClosingCost;
	private String loan1Points;
	private String loan2Term;
	private Double loan2Amount;
	private Float loan2InterestRate;
	private Double loan2ClosingCost;
	private String loan2Points;
	private String loan3Term;
	private Double loan3Amount;
	private Float loan3InterestRate;
	private Double loan3ClosingCost;
	private String loan3Points;
	private String compareResult;
	private Timestamp modifiedDate;
	private String resultFile;

	// Constructors

	/** default constructor */
	public Compare() {
	}

	/** minimal constructor */
	public Compare(Quote quote) {
		this.quote = quote;
	}

	/** full constructor */
	public Compare(Quote quote, String loanTerm, String loanType,
			String loanAmount, Float interestRate, Date firstPaymentDate,
			String loan1Term, Double loan1Amount, Float loan1InterestRate,
			Double loan1ClosingCost, String loan1Points, String loan2Term,
			Double loan2Amount, Float loan2InterestRate,
			Double loan2ClosingCost, String loan2Points, String loan3Term,
			Double loan3Amount, Float loan3InterestRate,
			Double loan3ClosingCost, String loan3Points, String compareResult,
			Timestamp modifiedDate, String resultFile) {
		this.quote = quote;
		this.loanTerm = loanTerm;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.firstPaymentDate = firstPaymentDate;
		this.loan1Term = loan1Term;
		this.loan1Amount = loan1Amount;
		this.loan1InterestRate = loan1InterestRate;
		this.loan1ClosingCost = loan1ClosingCost;
		this.loan1Points = loan1Points;
		this.loan2Term = loan2Term;
		this.loan2Amount = loan2Amount;
		this.loan2InterestRate = loan2InterestRate;
		this.loan2ClosingCost = loan2ClosingCost;
		this.loan2Points = loan2Points;
		this.loan3Term = loan3Term;
		this.loan3Amount = loan3Amount;
		this.loan3InterestRate = loan3InterestRate;
		this.loan3ClosingCost = loan3ClosingCost;
		this.loan3Points = loan3Points;
		this.compareResult = compareResult;
		this.modifiedDate = modifiedDate;
		this.resultFile = resultFile;
	}

	// Property accessors

	public Integer getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(Integer quoteId) {
		this.quoteId = quoteId;
	}

	public Quote getQuote() {
		return this.quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public String getLoanTerm() {
		return this.loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLoanType() {
		return this.loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Float getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}

	public Date getFirstPaymentDate() {
		return this.firstPaymentDate;
	}

	public void setFirstPaymentDate(Date firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	public String getLoan1Term() {
		return this.loan1Term;
	}

	public void setLoan1Term(String loan1Term) {
		this.loan1Term = loan1Term;
	}

	public Double getLoan1Amount() {
		return this.loan1Amount;
	}

	public void setLoan1Amount(Double loan1Amount) {
		this.loan1Amount = loan1Amount;
	}

	public Float getLoan1InterestRate() {
		return this.loan1InterestRate;
	}

	public void setLoan1InterestRate(Float loan1InterestRate) {
		this.loan1InterestRate = loan1InterestRate;
	}

	public Double getLoan1ClosingCost() {
		return this.loan1ClosingCost;
	}

	public void setLoan1ClosingCost(Double loan1ClosingCost) {
		this.loan1ClosingCost = loan1ClosingCost;
	}

	public String getLoan1Points() {
		return this.loan1Points;
	}

	public void setLoan1Points(String loan1Points) {
		this.loan1Points = loan1Points;
	}

	public String getLoan2Term() {
		return this.loan2Term;
	}

	public void setLoan2Term(String loan2Term) {
		this.loan2Term = loan2Term;
	}

	public Double getLoan2Amount() {
		return this.loan2Amount;
	}

	public void setLoan2Amount(Double loan2Amount) {
		this.loan2Amount = loan2Amount;
	}

	public Float getLoan2InterestRate() {
		return this.loan2InterestRate;
	}

	public void setLoan2InterestRate(Float loan2InterestRate) {
		this.loan2InterestRate = loan2InterestRate;
	}

	public Double getLoan2ClosingCost() {
		return this.loan2ClosingCost;
	}

	public void setLoan2ClosingCost(Double loan2ClosingCost) {
		this.loan2ClosingCost = loan2ClosingCost;
	}

	public String getLoan2Points() {
		return this.loan2Points;
	}

	public void setLoan2Points(String loan2Points) {
		this.loan2Points = loan2Points;
	}

	public String getLoan3Term() {
		return this.loan3Term;
	}

	public void setLoan3Term(String loan3Term) {
		this.loan3Term = loan3Term;
	}

	public Double getLoan3Amount() {
		return this.loan3Amount;
	}

	public void setLoan3Amount(Double loan3Amount) {
		this.loan3Amount = loan3Amount;
	}

	public Float getLoan3InterestRate() {
		return this.loan3InterestRate;
	}

	public void setLoan3InterestRate(Float loan3InterestRate) {
		this.loan3InterestRate = loan3InterestRate;
	}

	public Double getLoan3ClosingCost() {
		return this.loan3ClosingCost;
	}

	public void setLoan3ClosingCost(Double loan3ClosingCost) {
		this.loan3ClosingCost = loan3ClosingCost;
	}

	public String getLoan3Points() {
		return this.loan3Points;
	}

	public void setLoan3Points(String loan3Points) {
		this.loan3Points = loan3Points;
	}

	public String getCompareResult() {
		return this.compareResult;
	}

	public void setCompareResult(String compareResult) {
		this.compareResult = compareResult;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getResultFile() {
		return this.resultFile;
	}

	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}

}