package com.loan.agent.calculators;

 
import com.loan.agent.calculators.vo.CompareLoanVo;

public class PurchaseCurrentLoanBuilder implements LoanBuilder{
	
	private LoanProduct loanProd;
	private LoanParam pm; 
	 
	
	public PurchaseCurrentLoanBuilder(
			LoanParam loanParam
			) {
		super();
		 this.pm = loanParam;
		 this.loanProd = new LoanProduct();
	}
	
	public void buildPaidNum(){
		loanProd.setPaid_num(0);
	}	
	public void buildRemainNum(){	
		
		loanProd.setRemain_num(pm.getInt_term());
	}
	public void buildRemainBalance(){
		loanProd.setRemain_balance(pm.getLoan_amt());
	}
	public void buildPMT(){
		loanProd.setPMT(Calculators.monthly_payment(pm.getLoan_amt(),pm.getInt_rate(),pm.getTerm()));
	}
	public void buildMonthlySaving(){
		 
			loanProd.setMonthly_saving(0.0);
		 
		 
	}
	public void buildBEP(){		 
		 
		 
			loanProd.setBEP(null);
		 
		 
	}
	public void buildTotalIntPaid(){
		loanProd.setTotal_int_paid(0.0);
	}
	public void buildUnIntPaid(){
		loanProd.setTotal_unint_paid(Calculators.total_unint_paid(pm.getLoan_amt(),0,pm.getTerm(),pm.getInt_rate()));
	}
	
	public void buildUnpaidLoanAmt(){
		loanProd.setUnpaidLoanAmt(pm.getLoan_amt());
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
