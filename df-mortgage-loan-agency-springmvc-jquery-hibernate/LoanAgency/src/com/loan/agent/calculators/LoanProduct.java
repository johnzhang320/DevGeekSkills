package com.loan.agent.calculators;

import com.loan.agent.calculators.vo.CompareLoanVo;

public class LoanProduct {
	private Integer paid_num;
	private Integer remain_num;
	private Double remain_balance;
	private Double PMT;
	private Double monthly_saving;
	private String BEP;
	private Double total_int_paid;
	private Double total_unint_paid;
	private Double UnpaidLoanAmt;
	private CompareLoanVo compareLoanVo;
	
	
	
	public Double getTotal_unint_paid() {
		return total_unint_paid;
	}
	public void setTotal_unint_paid(Double total_unint_paid) {
		this.total_unint_paid = total_unint_paid;
	}
	public Integer getPaid_num() {
		return paid_num;
	}
	public void setPaid_num(Integer paid_num) {
		this.paid_num = paid_num;
	}
	public Integer getRemain_num() {
		return remain_num;
	}
	public void setRemain_num(Integer remain_num) {
		this.remain_num = remain_num;
	}
	public Double getRemain_balance() {
		return remain_balance;
	}
	public void setRemain_balance(Double remain_balance) {
		this.remain_balance = remain_balance;
	}
	public Double getPMT() {
		return PMT;
	}
	public void setPMT(Double pMT) {
		PMT = pMT;
	}
	public Double getMonthly_saving() {
		return monthly_saving;
	}
	public void setMonthly_saving(Double monthly_saving) {
		this.monthly_saving = monthly_saving;
	}
	public String getBEP() {
		return BEP;
	}
	public void setBEP(String bEP) {
		BEP = bEP;
	}
	public Double getTotal_int_paid() {
		return total_int_paid;
	}
	public void setTotal_int_paid(Double total_int_paid) {
		this.total_int_paid = total_int_paid;
	}
	 
	public Double getUnpaidLoanAmt() {
		return UnpaidLoanAmt;
	}
	public void setUnpaidLoanAmt(Double unpaidLoanAmt) {
		UnpaidLoanAmt = unpaidLoanAmt;
	}
	public CompareLoanVo getCompareLoanVo() {
		return compareLoanVo;
	}
	public void setCompareLoanVo(CompareLoanVo compareLoanVo) {
		this.compareLoanVo = compareLoanVo;
	}
	
}
