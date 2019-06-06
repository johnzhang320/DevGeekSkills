package com.loan.agent.calculators.vo;

import java.io.Serializable;

import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.Constant;

public class AgentEmailVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	String hostName;
	Integer agentId;
	String purchaseQuoteStr="Purchase Loan Quote.";
	String purchaseQuoteLink;
	
	String refinanceQuoteStr="Refinance Loan Quote.";
	String refinanceQuoteLink;
	
	String monthlyAmortStr="Monthly Amortization Reports (download pdf format)";
	String monthlyAmortLink;
	String yearlyAmortStr="Yearly Amortization Reports (download pdf format)";
	String yearlyAmortLink;
	String monthlyExpenseStr="Calculate Monthly House Expense";
	String monthlyExpenseLink;
	String affordableCapabilityStr="Estimate Affordable Capability";
	String affordableCapabilityLink="";
	String refinanceLoansComparerStr="Financially Comparing 3 different Refinance Loans";
	String refinanceLoansComparerLink="";
	String puschaseLoansComparerStr="inancially Comparing 3 different Purchase Loans";
	String puschaseLoansComparerLink="";
	String loadEducationStr="Loan Knowledges ( Loan Qualify | Preparing documents | Loan Options | Loan Procedure |  Basic Criterias)";
 
	String loadEducationLink="";
	String downloadApplicationFormStr="Download Application Form";
	String downloadApplicationFormLink="";
	String freeCreditScoreStr="Free Credit Score Check";
	String freeCreditScoreLink="";
	
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getHostName() {
		hostName = SysPath.getHostName();
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getMonthlyAmortStr() {
		return monthlyAmortStr;
	}
	public void setMonthlyAmortStr(String monthlyAmortStr) {
		this.monthlyAmortStr = monthlyAmortStr;
	}
	public String getMonthlyAmortLink() {
		return monthlyAmortLink;
	}
	public void setMonthlyAmortLink(String monthlyAmortLink) {
		this.monthlyAmortLink = monthlyAmortLink;
	}
	public String getYearlyAmortStr() {
		return yearlyAmortStr;
	}
	public void setYearlyAmortStr(String yearlyAmortStr) {
		this.yearlyAmortStr = yearlyAmortStr;
	}
	public String getYearlyAmortLink() {
		return yearlyAmortLink;
	}
	public void setYearlyAmortLink(String yearlyAmortLink) {
		this.yearlyAmortLink = yearlyAmortLink;
	}
	public String getMonthlyExpenseStr() {
		return monthlyExpenseStr;
	}
	public void setMonthlyExpenseStr(String monthlyExpenseStr) {
		this.monthlyExpenseStr = monthlyExpenseStr;
	}
	public String getMonthlyExpenseLink() {
		return monthlyExpenseLink;
	}
	public void setMonthlyExpenseLink(String monthlyExpenseLink) {
		this.monthlyExpenseLink = monthlyExpenseLink;
	}
	public String getAffordableCapabilityStr() {
		return affordableCapabilityStr;
	}
	public void setAffordableCapabilityStr(String affordableCapabilityStr) {
		this.affordableCapabilityStr = affordableCapabilityStr;
	}
	public String getAffordableCapabilityLink() {
		return affordableCapabilityLink;
	}
	public void setAffordableCapabilityLink(String affordableCapabilityLink) {
		this.affordableCapabilityLink = affordableCapabilityLink;
	}
	public String getRefinanceLoansComparerStr() {
		return refinanceLoansComparerStr;
	}
	public void setRefinanceLoansComparerStr(String refinanceLoansComparerStr) {
		this.refinanceLoansComparerStr = refinanceLoansComparerStr;
	}
	public String getRefinanceLoansComparerLink() {
		return refinanceLoansComparerLink;
	}
	public void setRefinanceLoansComparerLink(String refinanceLoansComparerLink) {
		this.refinanceLoansComparerLink = refinanceLoansComparerLink;
	}
	public String getPuschaseLoansComparerStr() {
		return puschaseLoansComparerStr;
	}
	public void setPuschaseLoansComparerStr(String puschaseLoansComparerStr) {
		this.puschaseLoansComparerStr = puschaseLoansComparerStr;
	}
	public String getPuschaseLoansComparerLink() {
		return puschaseLoansComparerLink;
	}
	public void setPuschaseLoansComparerLink(String puschaseLoansComparerLink) {
		this.puschaseLoansComparerLink = puschaseLoansComparerLink;
	}
	public String getLoadEducationStr() {
		return loadEducationStr;
	}
	public void setLoadEducationStr(String loadEducationStr) {
		this.loadEducationStr = loadEducationStr;
	}
	public String getLoadEducationLink() {
		return loadEducationLink;
	}
	public void setLoadEducationLink(String loadEducationLink) {
		this.loadEducationLink = loadEducationLink;
	}
	public String getDownloadApplicationFormStr() {
		return downloadApplicationFormStr;
	}
	public void setDownloadApplicationFormStr(String downloadApplicationFormStr) {
		this.downloadApplicationFormStr = downloadApplicationFormStr;
	}
	public String getDownloadApplicationFormLink() {
		return downloadApplicationFormLink;
	}
	public void setDownloadApplicationFormLink(String downloadApplicationFormLink) {
		this.downloadApplicationFormLink = downloadApplicationFormLink;
	}
	public String getFreeCreditScoreStr() {
		return freeCreditScoreStr;
	}
	public void setFreeCreditScoreStr(String freeCreditScoreStr) {
		this.freeCreditScoreStr = freeCreditScoreStr;
	}
	public String getFreeCreditScoreLink() {
		return freeCreditScoreLink;
	}
	public void setFreeCreditScoreLink(String freeCreditScoreLink) {
		this.freeCreditScoreLink = freeCreditScoreLink;
	}
	 
	public String getPurchaseQuoteStr() {
		return purchaseQuoteStr;
	}
	public void setPurchaseQuoteStr(String purchaseQuoteStr) {
		this.purchaseQuoteStr = purchaseQuoteStr;
	}
	public String getPurchaseQuoteLink() {
		return purchaseQuoteLink;
	}
	public void setPurchaseQuoteLink(String purchaseQuoteLink) {
		this.purchaseQuoteLink = purchaseQuoteLink;
	}
	public String getRefinanceQuoteStr() {
		return refinanceQuoteStr;
	}
	public void setRefinanceQuoteStr(String refinanceQuoteStr) {
		this.refinanceQuoteStr = refinanceQuoteStr;
	}
	public String getRefinanceQuoteLink() {
		return refinanceQuoteLink;
	}
	public void setRefinanceQuoteLink(String refinanceQuoteLink) {
		this.refinanceQuoteLink = refinanceQuoteLink;
	}
	public void setLinks(Agents agents) {
		
		this.agentId = agents.getAgentId();
		
		 
		/**
		 *  encoded the agentId number
		 */
		String param =  "?"+Constant.PARAM_AGENT_ID+"="+ui.getEncodedString(agentId.toString());
		this.hostName = SysPath.getHostName()+"/";
		
		this.purchaseQuoteStr="Send a Purchase Loan Quote to " + agents.getFirstName()+" "+agents.getLastName()+" with your detail purchase information.";
		 
		
		this.refinanceQuoteStr="Send a Refinance Loan Quote to "+ agents.getFirstName()+" "+agents.getLastName()+" with your detail refinance information.";
		 
		
		this.purchaseQuoteLink=hostName+"purchaseQuote.html"+param;
		this.refinanceQuoteLink=hostName+"refinanceQuote.html"+param;		
		this.monthlyAmortLink=hostName+"monthlyAmortization.html"+param;
		this.yearlyAmortLink=hostName+"yearlyAmortization.html"+param;
		this.monthlyExpenseLink=hostName+"calculateMonthlyExpense.html"+param;
		this.affordableCapabilityLink=hostName+"affordableHomePrice.html"+param;
		this.refinanceLoansComparerLink=hostName+"compareRefinanceLoans.html"+param;
		this.puschaseLoansComparerLink=hostName+"comparePurchaseLoans.html"+param;
		this.loadEducationLink=hostName+"education.html"+param;
		this.downloadApplicationFormLink=hostName+"downloadApplicationForm.html"+param;
		this.freeCreditScoreLink=hostName+"creditReportWebsite.html"+param;
	}
	
}
