package com.loan.agent.dao;

/**
 * RateSheet entity. @author MyEclipse Persistence Tools
 */

public class RateSheet implements java.io.Serializable {

	// Fields

	private Integer rateId;
	private Integer agentId;
	private Integer termId;
	private String term;
	private String conformRate;
	private String superConformRate;
	private String jumboRate;
	private String jumboApr;
	private String conformApr;
	private String superConformApr;
	private String creditScore;
	private String loanToValue;

	// Constructors

	/** default constructor */
	public RateSheet() {
	}

	/** minimal constructor */
	public RateSheet(Integer agentId) {
		this.agentId = agentId;
	}
	/**
	 * Full construction
	 * @param rateId
	 * @param agentId
	 * @param termId
	 * @param term
	 * @param conformRate
	 * @param superConformRate
	 * @param jumboRate
	 * @param jumboApr
	 * @param conformApr
	 * @param superConformApr
	 * @param creditScore
	 * @param loanToValue
	 */
	
     
	public RateSheet(Integer rateId, Integer agentId, Integer termId,
			String term, String conformRate, String superConformRate,
			String jumboRate, String jumboApr, String conformApr,
			String superConformApr, String creditScore, String loanToValue) {
		 
		this.rateId = rateId;
		this.agentId = agentId;
		this.termId = termId;
		this.term = term;
		this.conformRate = conformRate;
		this.superConformRate = superConformRate;
		this.jumboRate = jumboRate;
		this.jumboApr = jumboApr;
		this.conformApr = conformApr;
		this.superConformApr = superConformApr;
		this.creditScore = creditScore;
		this.loanToValue = loanToValue;
	}

 
	
	// Property accessors

	public Integer getRateId() {
		return this.rateId;
	}

	
	public String getJumboApr() {
		return jumboApr;
	}

	public void setJumboApr(String jumboApr) {
		this.jumboApr = jumboApr;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getTermId() {
		return this.termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getConformRate() {
		return this.conformRate;
	}

	public void setConformRate(String conformRate) {
		this.conformRate = conformRate;
	}

	public String getSuperConformRate() {
		return this.superConformRate;
	}

	public void setSuperConformRate(String superConformRate) {
		this.superConformRate = superConformRate;
	}

	public String getJumboRate() {
		return this.jumboRate;
	}

	public void setJumboRate(String jumboRate) {
		this.jumboRate = jumboRate;
	}

	public String getConformApr() {
		return this.conformApr;
	}

	public void setConformApr(String conformApr) {
		this.conformApr = conformApr;
	}

	public String getSuperConformApr() {
		return this.superConformApr;
	}

	public void setSuperConformApr(String superConformApr) {
		this.superConformApr = superConformApr;
	}

	public String getCreditScore() {
		return this.creditScore;
	}

	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}

	public String getLoanToValue() {
		return this.loanToValue;
	}

	public void setLoanToValue(String loanToValue) {
		this.loanToValue = loanToValue;
	}

}