package com.loan.agent.calculators.vo;

import java.io.Serializable;

public class AmortParamVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	String loanAmount;
	String interestRate;
	String term;
	String extra_PMT;	
	String extra_YPMT;	
	String extra_YPMT_once;
	String first_Date;
	String PMT_Date;
	String YPMT_Date;
	String YPMT_once_Date;
	
	 static  AmortParamVo handler=null;
	    static {
	 	   handler=new AmortParamVo();
	    }
	    public static  AmortParamVo getInstance() {
	    
	      handler.loanAmount=null;
	      handler.interestRate=null;
	      handler.term = null;
	      handler.extra_PMT = null;
	      handler.extra_YPMT = null;
	      handler.extra_YPMT_once = null;
	      handler.PMT_Date = null;
	      handler.YPMT_Date = null;
	      handler.YPMT_once_Date = null;
	      handler.first_Date = null;
	      
	 	   return handler;
	    }
	    private AmortParamVo() {
	     	  
	     }
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getExtra_PMT() {
		return extra_PMT;
	}
	public void setExtra_PMT(String extra_PMT) {
		this.extra_PMT = extra_PMT;
	}
	public String getExtra_YPMT() {
		return extra_YPMT;
	}
	public void setExtra_YPMT(String extra_YPMT) {
		this.extra_YPMT = extra_YPMT;
	}
	public String getExtra_YPMT_once() {
		return extra_YPMT_once;
	}
	public void setExtra_YPMT_once(String extra_YPMT_once) {
		this.extra_YPMT_once = extra_YPMT_once;
	}
	public String getFirst_Date() {
		return first_Date;
	}
	public void setFirst_Date(String first_Date) {
		this.first_Date = first_Date;
	}
	public String getPMT_Date() {
		return PMT_Date;
	}
	public void setPMT_Date(String pMT_Date) {
		PMT_Date = pMT_Date;
	}
	public String getYPMT_Date() {
		return YPMT_Date;
	}
	public void setYPMT_Date(String yPMT_Date) {
		YPMT_Date = yPMT_Date;
	}
	public String getYPMT_once_Date() {
		return YPMT_once_Date;
	}
	public void setYPMT_once_Date(String yPMT_once_Date) {
		YPMT_once_Date = yPMT_once_Date;
	}
	
}
