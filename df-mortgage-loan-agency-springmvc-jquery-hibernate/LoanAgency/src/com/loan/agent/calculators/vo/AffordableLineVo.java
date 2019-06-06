package com.loan.agent.calculators.vo;

import java.io.Serializable;

public class AffordableLineVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	String aggresivePrice;
	String conservativePrice;
	
	public String getAggresivePrice() {
		return aggresivePrice;
	}
	public void setAggresivePrice(String aggresivePrice) {
		this.aggresivePrice = aggresivePrice;
	}
	public String getConservativePrice() {
		return conservativePrice;
	}
	public void setConservativePrice(String conservativePrice) {
		this.conservativePrice = conservativePrice;
	}
	
}
