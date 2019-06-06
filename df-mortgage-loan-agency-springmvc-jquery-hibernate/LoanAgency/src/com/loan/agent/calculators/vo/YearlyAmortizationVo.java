package com.loan.agent.calculators.vo;

import java.io.Serializable;

public class YearlyAmortizationVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	private String yearNo;
	private String year;
	private String beginBalance;
	private String yearlyPayment;
	private String interestPayment;
	private String principlePayment;
	private String remainBalance;
	
	public String getYearNo() {
		return yearNo;
	}
	public void setYearNo(String yearNo) {
		this.yearNo = yearNo;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getBeginBalance() {
		return beginBalance;
	}
	public void setBeginBalance(String beginBalance) {
		this.beginBalance = beginBalance;
	}
	 
	public String getYearlyPayment() {
		return yearlyPayment;
	}
	public void setYearlyPayment(String yearlyPayment) {
		this.yearlyPayment = yearlyPayment;
	}
	public String getInterestPayment() {
		return interestPayment;
	}
	public void setInterestPayment(String interestPayment) {
		this.interestPayment = interestPayment;
	}
	public String getPrinciplePayment() {
		return principlePayment;
	}
	public void setPrinciplePayment(String principlePayment) {
		this.principlePayment = principlePayment;
	}
	public String getRemainBalance() {
		return remainBalance;
	}
	public void setRemainBalance(String remainBalance) {
		this.remainBalance = remainBalance;
	}
 
	
}
