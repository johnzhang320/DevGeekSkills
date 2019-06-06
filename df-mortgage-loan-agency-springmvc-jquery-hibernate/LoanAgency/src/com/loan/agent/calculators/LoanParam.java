package com.loan.agent.calculators;

public class LoanParam {
	private Double loan_amt;
	private Double int_rate;
	private Double term;
	private Integer int_term;
	private Double closing_cost;
	private Double point;
	private Integer first_mm;        
	private Integer first_yyyy;	 
 
	private LoanProduct currentLoan; 
	private LoanParam currentParam; 
	
	 
	public LoanParam(
			Double loan_amt, 
			Double int_rate, 
			Double term,
			Integer int_term,
			Double closing_cost, 
			Double point,
			Integer first_mm, 
			Integer first_yyyy, 
			LoanProduct currentLoan,
			LoanParam currentParam
			) {
		super();
		this.loan_amt = loan_amt;
		this.int_rate = int_rate;
		this.term = term;
		this.int_term = int_term;
		this.closing_cost = closing_cost;
		this.point = point;
		this.first_mm = first_mm;
		this.first_yyyy = first_yyyy;
	 
		this.currentLoan = currentLoan;
		this.currentParam = currentParam;
	}
	
	 

	public LoanParam getCurrentParam() {
		return currentParam;
	}

	public void setCurrentParam(LoanParam currentParam) {
		this.currentParam = currentParam;
	}

	public LoanProduct getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(LoanProduct currentLoan) {
		this.currentLoan = currentLoan;
	}
	 
	public Double getLoan_amt() {
		return loan_amt;
	}
	public void setLoan_amt(Double loan_amt) {
		this.loan_amt = loan_amt;
	}
	public Double getInt_rate() {
		return int_rate;
	}
	public void setInt_rate(Double int_rate) {
		this.int_rate = int_rate;
	}
	public Double getTerm() {
		return term;
	}
	public void setTerm(Double term) {
		this.term = term;
	}
	public Integer getInt_term() {
		return int_term;
	}
	public void setInt_term(Integer int_term) {
		this.int_term = int_term;
	}
	public Double getClosing_cost() {
		return closing_cost;
	}
	public void setClosing_cost(Double closing_cost) {
		this.closing_cost = closing_cost;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Integer getFirst_mm() {
		return first_mm;
	}
	public void setFirst_mm(Integer first_mm) {
		this.first_mm = first_mm;
	}
	public Integer getFirst_yyyy() {
		return first_yyyy;
	}
	public void setFirst_yyyy(Integer first_yyyy) {
		this.first_yyyy = first_yyyy;
	}
	
}
