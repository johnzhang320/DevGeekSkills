package com.loan.agent.calculators;



public interface LoanBuilder {
	public void buildPaidNum();
	public void buildRemainNum();
	public void buildRemainBalance();
	public void buildPMT();
	public void buildMonthlySaving();
	public void buildBEP();
	public void buildTotalIntPaid();
	public void buildUnIntPaid();
	public void buildUnpaidLoanAmt();
	public void buildCompareLoanVo();
	public LoanProduct getLoanProd();
}
