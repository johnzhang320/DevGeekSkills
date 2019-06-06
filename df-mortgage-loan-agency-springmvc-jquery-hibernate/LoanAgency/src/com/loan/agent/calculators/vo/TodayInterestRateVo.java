package com.loan.agent.calculators.vo;

import java.io.Serializable;

public class TodayInterestRateVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	String intRate;
	String termName;
	String postedTime;
	public String getIntRate() {
		return intRate;
	}
	public void setIntRate(String intRate) {
		this.intRate = intRate;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	
}
