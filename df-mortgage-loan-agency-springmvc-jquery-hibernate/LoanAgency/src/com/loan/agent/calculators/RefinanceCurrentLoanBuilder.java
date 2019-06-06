package com.loan.agent.calculators;

import com.loan.agent.calculators.vo.CompareLoanVo;

public class RefinanceCurrentLoanBuilder implements LoanBuilder{
	
	private LoanProduct loanProd;
	private LoanParam pm; 
	 
	
	public RefinanceCurrentLoanBuilder(
			LoanParam loanParam
			) {
		super();
		 this.pm = loanParam;
		 this.loanProd = new LoanProduct();
	}
	
	public void buildPaidNum(){		
	 
		loanProd.setPaid_num(Calculators.uptoday_months(pm.getFirst_yyyy(),pm.getFirst_mm()));
			
	}	
	public void buildRemainNum(){	
		 
		loanProd.setRemain_num(pm.getInt_term()-loanProd.getPaid_num());
		 
	}
	public void buildRemainBalance(){
		loanProd.setRemain_balance(Calculators.uptonow_remain_balance(
				pm.getLoan_amt(),
				loanProd.getPaid_num(),
				pm.getTerm(),
				pm.getInt_rate()));
	}
	public void buildPMT(){
		loanProd.setPMT(Calculators.monthly_payment(
				pm.getLoan_amt(),
				pm.getInt_rate(),
				pm.getTerm()));
	}
	public void buildMonthlySaving(){
	 
			loanProd.setMonthly_saving(0.0);
		 
	}
	public void buildBEP(){		 
	 
	 
			loanProd.setBEP(null);
		 
		 
	}
	public void buildTotalIntPaid(){
		 
			loanProd.setTotal_int_paid(
					Calculators.total_int_paid(
							pm.getLoan_amt(),
							loanProd.getPaid_num(),
							pm.getTerm(),
							pm.getInt_rate(),
							pm.getFirst_mm(),
							pm.getFirst_yyyy()
							));
		 
	}
	public void buildUnIntPaid(){
	 
	 
			loanProd.setTotal_unint_paid(
					Calculators.total_unint_paid(
							pm.getLoan_amt(),
							loanProd.getPaid_num(),
							pm.getTerm(),
							pm.getInt_rate()
							));
			
		 
	}
	
	public void buildUnpaidLoanAmt(){
		loanProd.setUnpaidLoanAmt(loanProd.getRemain_balance()
				+loanProd.getTotal_unint_paid());
	}
	

	
	public void buildCompareLoanVo(){
			CompareLoanVo curLoan = new CompareLoanVo();
			curLoan.setMonthPayment(loanProd.getPMT());
			curLoan.setInt_rate(pm.getInt_rate());
		    curLoan.setTerm(pm.getInt_term());
			curLoan.setTimesAlreadyPaid(loanProd.getPaid_num());
			curLoan.setRemainTimes(loanProd.getRemain_num());
			curLoan.setPaidInterest(loanProd.getTotal_int_paid());
			curLoan.setUnpaidInterets(loanProd.getTotal_unint_paid());
			curLoan.setUnpainLoanAmt(loanProd.getUnpaidLoanAmt()); 
			loanProd.setCompareLoanVo(curLoan);
			
			 
	} 
	public LoanProduct getLoanProd() {
		return loanProd;
	}	 
}
