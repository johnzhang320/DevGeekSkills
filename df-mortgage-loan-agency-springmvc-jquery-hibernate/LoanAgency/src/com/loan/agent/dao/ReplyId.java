package com.loan.agent.dao;

/**
 * ReplyId entity. @author MyEclipse Persistence Tools
 */

public class ReplyId implements java.io.Serializable {

	// Fields

	private Integer quoteId;
	private Integer loanId;

	// Constructors

	/** default constructor */
	public ReplyId() {
	}

	/** full constructor */
	public ReplyId(Integer quoteId, Integer loanId) {
		this.quoteId = quoteId;
		this.loanId = loanId;
	}

	// Property accessors

	public Integer getQuoteId() {
		return this.quoteId;
	}

	public void setQuoteId(Integer quoteId) {
		this.quoteId = quoteId;
	}

	public Integer getLoanId() {
		return this.loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReplyId))
			return false;
		ReplyId castOther = (ReplyId) other;

		return ((this.getQuoteId() == castOther.getQuoteId()) || (this
				.getQuoteId() != null && castOther.getQuoteId() != null && this
				.getQuoteId().equals(castOther.getQuoteId())))
				&& ((this.getLoanId() == castOther.getLoanId()) || (this
						.getLoanId() != null && castOther.getLoanId() != null && this
						.getLoanId().equals(castOther.getLoanId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getQuoteId() == null ? 0 : this.getQuoteId().hashCode());
		result = 37 * result
				+ (getLoanId() == null ? 0 : this.getLoanId().hashCode());
		return result;
	}

}