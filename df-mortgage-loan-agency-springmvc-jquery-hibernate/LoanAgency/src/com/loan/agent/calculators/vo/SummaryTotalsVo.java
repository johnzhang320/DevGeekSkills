package com.loan.agent.calculators.vo;

import java.io.Serializable;

import com.loan.agent.mvc.utils.Utility;

public class SummaryTotalsVo implements Serializable {
	 private static final long serialVersionUID = 1L;
    private Double summaryMonthPayment=0.0;
    private Double summaryIntPayment=0.0;
    private Double summaryPrinPayment=0.0;
    
    
    private String strSummaryMonthPayment;
    private String strSummaryIntPayment;
    private String strSummaryPrinPayment;
    private String monthlyPayment;
    private String realPayMonths;
    private String adjustedMonthlyPayment;
    
    private String pay_extra;
    
    static  SummaryTotalsVo handler=null;
    static {
 	   handler=new SummaryTotalsVo();
    }
    public static  SummaryTotalsVo getInstance() {
    	   handler.summaryMonthPayment=0.0;
    	   handler.summaryIntPayment=0.0;
    	   handler.summaryPrinPayment=0.0;
      	   handler. strSummaryMonthPayment=null;
    	   handler.strSummaryIntPayment=null;
    	   handler.strSummaryPrinPayment=null;
    	   handler.monthlyPayment=null;
    	   handler.realPayMonths=null;
    	   handler.adjustedMonthlyPayment=null;
 	   return handler;
    }
    private SummaryTotalsVo() {
     	  
     }
    
	public Double getSummaryMonthPayment() {
		return summaryMonthPayment;
	}
	public void setSummaryMonthPayment(Double summaryMonthPayment) {
		this.summaryMonthPayment = summaryMonthPayment;
	}
	public Double getSummaryIntPayment() {
		return summaryIntPayment;
	}
	public void setSummaryIntPayment(Double summaryIntPayment) {
		this.summaryIntPayment = summaryIntPayment;
	}
	public Double getSummaryPrinPayment() {
		return summaryPrinPayment;
	}
	public void setSummaryPrinPayment(Double summaryPrinPayment) {
		this.summaryPrinPayment = summaryPrinPayment;
	}
	public String getStrSummaryMonthPayment() {
		return Utility.renderDollar(summaryMonthPayment);
	}
	public void setStrSummaryMonthPayment(String strSummaryMonthPayment) {
		this.strSummaryMonthPayment = strSummaryMonthPayment;
	}
	public String getStrSummaryIntPayment() {
		return Utility.renderDollar(summaryIntPayment);
	}
	public void setStrSummaryIntPayment(String strSummaryIntPayment) {
		this.strSummaryIntPayment = strSummaryIntPayment;
	}
	public String getStrSummaryPrinPayment() {
		return Utility.renderDollar(summaryPrinPayment);
	}
	public void setStrSummaryPrinPayment(String strSummaryPrinPayment) {
		this.strSummaryPrinPayment = strSummaryPrinPayment;
	}
	public String getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(String monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public String getRealPayMonths() {
		return realPayMonths;
	}
	public void setRealPayMonths(String realPayMonths) {
		this.realPayMonths = realPayMonths;
	}
	public String getPay_extra() {
		return pay_extra;
	}
	public void setPay_extra(String pay_extra) {
		this.pay_extra = pay_extra;
	}
	public String getAdjustedMonthlyPayment() {
		return adjustedMonthlyPayment;
	}
	public void setAdjustedMonthlyPayment(String adjustedMonthlyPayment) {
		this.adjustedMonthlyPayment = adjustedMonthlyPayment;
	}
    
}
