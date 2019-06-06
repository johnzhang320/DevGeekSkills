package com.loan.agent.dao;

/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply implements java.io.Serializable {

	// Fields

	private ReplyId id;
	private Integer agentId;
	private Integer userId;
	private String term;
	private String remainBalance;
	private String termVo;
	private String intRateVo;
	private String closingFee;
	private String point;
	private String monthPayment;
	private String monthSaving;
	private String breakEventPoint;
	private String timesAlreadyPaid;
	private String remainTimes;
	private String paidInterest;
	private String unpaidInterets;
	private String unpainLoanAmt;
	private String affordAprice;
	private String affordCprice;
	private Double loanAmt;
	private Double homePrice;
	private String adviceNote;

	// Constructors

	/** default constructor */
	public Reply() {
	}

	/** minimal constructor */
	public Reply(ReplyId id) {
		this.id = id;
	}

	/** full constructor */
	public Reply(ReplyId id, Integer agentId, Integer userId, String term,
			String remainBalance, String termVo, String intRateVo,
			String closingFee, String point, String monthPayment,
			String monthSaving, String breakEventPoint,
			String timesAlreadyPaid, String remainTimes, String paidInterest,
			String unpaidInterets, String unpainLoanAmt, String affordAprice,
			String affordCprice, Double loanAmt, Double homePrice,
			String adviceNote) {
		this.id = id;
		this.agentId = agentId;
		this.userId = userId;
		this.term = term;
		this.remainBalance = remainBalance;
		this.termVo = termVo;
		this.intRateVo = intRateVo;
		this.closingFee = closingFee;
		this.point = point;
		this.monthPayment = monthPayment;
		this.monthSaving = monthSaving;
		this.breakEventPoint = breakEventPoint;
		this.timesAlreadyPaid = timesAlreadyPaid;
		this.remainTimes = remainTimes;
		this.paidInterest = paidInterest;
		this.unpaidInterets = unpaidInterets;
		this.unpainLoanAmt = unpainLoanAmt;
		this.affordAprice = affordAprice;
		this.affordCprice = affordCprice;
		this.loanAmt = loanAmt;
		this.homePrice = homePrice;
		this.adviceNote = adviceNote;
	}

	// Property accessors

	public ReplyId getId() {
		return this.id;
	}

	public void setId(ReplyId id) {
		this.id = id;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getRemainBalance() {
		return this.remainBalance;
	}

	public void setRemainBalance(String remainBalance) {
		this.remainBalance = remainBalance;
	}

	public String getTermVo() {
		return this.termVo;
	}

	public void setTermVo(String termVo) {
		this.termVo = termVo;
	}

	public String getIntRateVo() {
		return this.intRateVo;
	}

	public void setIntRateVo(String intRateVo) {
		this.intRateVo = intRateVo;
	}

	public String getClosingFee() {
		return this.closingFee;
	}

	public void setClosingFee(String closingFee) {
		this.closingFee = closingFee;
	}

	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getMonthPayment() {
		return this.monthPayment;
	}

	public void setMonthPayment(String monthPayment) {
		this.monthPayment = monthPayment;
	}

	public String getMonthSaving() {
		return this.monthSaving;
	}

	public void setMonthSaving(String monthSaving) {
		this.monthSaving = monthSaving;
	}

	public String getBreakEventPoint() {
		return this.breakEventPoint;
	}

	public void setBreakEventPoint(String breakEventPoint) {
		this.breakEventPoint = breakEventPoint;
	}

	public String getTimesAlreadyPaid() {
		return this.timesAlreadyPaid;
	}

	public void setTimesAlreadyPaid(String timesAlreadyPaid) {
		this.timesAlreadyPaid = timesAlreadyPaid;
	}

	public String getRemainTimes() {
		return this.remainTimes;
	}

	public void setRemainTimes(String remainTimes) {
		this.remainTimes = remainTimes;
	}

	public String getPaidInterest() {
		return this.paidInterest;
	}

	public void setPaidInterest(String paidInterest) {
		this.paidInterest = paidInterest;
	}

	public String getUnpaidInterets() {
		return this.unpaidInterets;
	}

	public void setUnpaidInterets(String unpaidInterets) {
		this.unpaidInterets = unpaidInterets;
	}

	public String getUnpainLoanAmt() {
		return this.unpainLoanAmt;
	}

	public void setUnpainLoanAmt(String unpainLoanAmt) {
		this.unpainLoanAmt = unpainLoanAmt;
	}

	public String getAffordAprice() {
		return this.affordAprice;
	}

	public void setAffordAprice(String affordAprice) {
		this.affordAprice = affordAprice;
	}

	public String getAffordCprice() {
		return this.affordCprice;
	}

	public void setAffordCprice(String affordCprice) {
		this.affordCprice = affordCprice;
	}

	public Double getLoanAmt() {
		return this.loanAmt;
	}

	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public Double getHomePrice() {
		return this.homePrice;
	}

	public void setHomePrice(Double homePrice) {
		this.homePrice = homePrice;
	}

	public String getAdviceNote() {
		return this.adviceNote;
	}

	public void setAdviceNote(String adviceNote) {
		this.adviceNote = adviceNote;
	}

}