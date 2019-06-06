package com.loan.agent.calculators.vo;

import java.io.Serializable;

import com.loan.agent.mvc.utils.Utility;

public class CompareLoanVo implements Serializable{
   private static final long serialVersionUID = 1L;
   private String term;	
   private String int_rate;	
   private String closing_fee;	
   private String remainBalance;
   private String point;	
   private String monthPayment;
   private String monthSaving;
   private String breakEventPoint;
   private String timesAlreadyPaid;
   private String remainTimes;
   private String paidInterest;
   private String unpaidInterets;
   private String unpainLoanAmt;
   private String AffordAPrice;  
   private String AffordCPrice; 

   
   
public String getRemainBalance() {
	return remainBalance;
}
public void setRemainBalance(Double remainBalance) {
	this.remainBalance = Utility.renderDollar( remainBalance);
}
public String getAffordAPrice() {
	return AffordAPrice;
}
public void setAffordAPrice(Double affordAPrice) {
	AffordAPrice = Utility.renderDollar(affordAPrice);
}
public String getAffordCPrice() {
	return AffordCPrice;
}
public void setAffordCPrice(Double affordCPrice) {
	AffordCPrice = Utility.renderDollar(affordCPrice);
}
public String getTerm() {
	return term;
}
public void setTerm(Integer term) {
	this.term =  Utility.renderInteger(term);
}
public String getInt_rate() {
	return int_rate;
}
public void setInt_rate(Double int_rate) {
	this.int_rate = Utility.renderRate(int_rate);
}
public String getClosing_fee() {
	return closing_fee;
}
public void setClosing_fee(Double closing_fee) {
	this.closing_fee = Utility.renderDollar(closing_fee);
}
public String getPoint() {
	return point;
}
public void setPoint(Double point) {
	this.point = Utility.renderDouble(point);
}
public String getMonthPayment() {
	return monthPayment;
}
public void setMonthPayment(Double monthPayment) {
	this.monthPayment = Utility.renderDollar(monthPayment);
}
public String getMonthSaving() {
	return monthSaving;
}
public void setMonthSaving(Double monthSaving) {
	this.monthSaving = Utility.renderDollar(monthSaving);
}
public String getBreakEventPoint() {
	return breakEventPoint;
}
public void setBreakEventPoint(String breakEventPoint) {
	this.breakEventPoint =breakEventPoint;
}
public String getTimesAlreadyPaid() {
	return timesAlreadyPaid;
}
public void setTimesAlreadyPaid(Integer timesAlreadyPaid) {
	this.timesAlreadyPaid = Utility.renderInteger(timesAlreadyPaid);
}
public String getRemainTimes() {
	return remainTimes;
}
public void setRemainTimes(Integer remainTimes) {
	this.remainTimes = Utility.renderInteger(remainTimes);
}
public String getUnpaidInterets() {
	return unpaidInterets;
}
public void setUnpaidInterets(Double unpaidInterets) {
	this.unpaidInterets = Utility.renderDollar(unpaidInterets);
}
public String getUnpainLoanAmt() {
	return unpainLoanAmt;
}
public void setUnpainLoanAmt(Double unpainLoanAmt) {
	this.unpainLoanAmt = Utility.renderDollar(unpainLoanAmt);
}
public String getPaidInterest() {
	return paidInterest;
}
public void setPaidInterest(Double paidInterest) {
	this.paidInterest = Utility.renderDollar(paidInterest);
}
   
   
   
}
