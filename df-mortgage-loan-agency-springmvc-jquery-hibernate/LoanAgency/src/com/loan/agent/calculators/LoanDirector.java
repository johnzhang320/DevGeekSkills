package com.loan.agent.calculators;

public class LoanDirector {
    private static LoanDirector handler=null;
    public static LoanDirector getInstance() {
    	if (handler==null) 
    	{
    		handler = new LoanDirector();
    	}
    	return handler;
    }
	private LoanDirector() {
		
	}
	public LoanProduct buildProduct(LoanBuilder builder) {		 
		builder.buildPaidNum();
		builder.buildRemainNum();
		builder.buildRemainBalance();
		builder.buildPMT();
		builder.buildMonthlySaving();
		builder.buildBEP();
		builder.buildTotalIntPaid();
		builder.buildUnIntPaid();
		builder.buildUnpaidLoanAmt();
		builder.buildCompareLoanVo();	 
		return builder.getLoanProd();
	}
	 
	 
	
	 
}
