package com.loan.agent.calculators;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loan.agent.calculators.vo.CompareLoanVo;

public class PurchaseNewLoanBuilder implements LoanBuilder{
	
	private LoanProduct loanProd;
	private LoanParam pm; 
	 
	
	public PurchaseNewLoanBuilder(
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
		 
		loanProd.setMonthly_saving(pm.getCurrentLoan().getPMT() - loanProd.getPMT());
		 
		 
	}
	public void buildBEP(){		 
		 
			loanProd.setBEP(Calculators.BEP_prn(
					pm.getCurrentParam().getLoan_amt(),
					pm.getCurrentLoan().getPMT(),
					loanProd.getPMT(),
					pm.getClosing_cost(),
					pm.getPoint()));
		 
		 
	}
	public void buildTotalIntPaid(){
		loanProd.setTotal_int_paid(0.0);
	}
	public void buildUnIntPaid(){
		loanProd.setTotal_unint_paid(Calculators.total_unint_paid(pm.getCurrentParam().getLoan_amt(),0,pm.getTerm(),pm.getInt_rate()));
	}
	
	public void buildUnpaidLoanAmt(){
		loanProd.setUnpaidLoanAmt(pm.getLoan_amt());
	}
	

	
	public void buildCompareLoanVo(){
		   
			CompareLoanVo newLoan = new CompareLoanVo();
			newLoan.setMonthPayment(loanProd.getPMT());
			newLoan.setInt_rate(pm.getInt_rate());
		    newLoan.setTerm(pm.getInt_term());
		    newLoan.setClosing_fee(pm.getClosing_cost());
		    newLoan.setPoint(pm.getPoint());
			newLoan.setMonthSaving(loanProd.getMonthly_saving());
			newLoan.setBreakEventPoint(loanProd.getBEP());          
			newLoan.setRemainTimes(loanProd.getRemain_num());      
			newLoan.setUnpaidInterets(loanProd.getTotal_unint_paid());
		 
			newLoan.setUnpainLoanAmt(loanProd.getUnpaidLoanAmt());
	
	        loanProd.setCompareLoanVo(newLoan);
			 
       
	}	 
	public LoanProduct getLoanProd() {
		return loanProd;
	} 
}
