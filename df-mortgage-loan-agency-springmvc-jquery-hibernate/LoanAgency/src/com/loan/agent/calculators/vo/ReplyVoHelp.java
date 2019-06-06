package com.loan.agent.calculators.vo;

import java.io.Serializable;

import com.loan.agent.dao.Reply;
import com.loan.agent.dao.ReplyId;
import com.loan.agent.mvc.utils.Utility;

public class ReplyVoHelp implements Serializable {
	 private static final long serialVersionUID = 1L;
	
	public static Reply setCurrentLoan(Reply r,CompareLoanVo v,
			Double loanAmt,String adviceNote,Integer agentId,Integer userId)
		{
		  r.setAdviceNote(adviceNote);
		  r.setAgentId(agentId);
		  r.setUserId(userId);
		  r.setLoanAmt(loanAmt);
		 return setReplyByCompareLoanVo(r,v);
	}
	
	
	public static Reply setReplyByCompareLoanVo(Reply r,CompareLoanVo v) {
		   String term=v.getTerm();			   
		   String intRateVo=v.getInt_rate();	
		   String closingFee=v.getClosing_fee();	
		   String remainBalance=v.getRemainBalance();
		   String point=v.getPoint();	
		   String monthPayment=v.getMonthPayment();
		   String monthSaving=v.getMonthSaving();
		   String breakEventPoint=v.getBreakEventPoint();
		   String timesAlreadyPaid=v.getTimesAlreadyPaid();
		   String remainTimes=v.getRemainTimes();
		   String paidInterest=v.getPaidInterest();
		   String unpaidInterets=v.getUnpaidInterets();
		   String unpainLoanAmt=v.getUnpainLoanAmt();
		   String AffordAPrice=v.getAffordAPrice();  
		   String AffordCPrice=v.getAffordCPrice(); 
	       r.setTermVo(Utility.TermToDescription(term));
	       r.setIntRateVo(intRateVo);
	       r.setClosingFee(closingFee);
	       r.setRemainBalance(remainBalance);
	       r.setPoint(point);
	       r.setMonthPayment(monthPayment);
	       r.setMonthSaving(monthSaving);
	       r.setBreakEventPoint(breakEventPoint);
	       r.setTimesAlreadyPaid(timesAlreadyPaid);
	       r.setRemainTimes(remainTimes);
	       r.setPaidInterest(paidInterest);
	       r.setUnpaidInterets(unpaidInterets);
	       r.setUnpainLoanAmt(unpainLoanAmt);
	       
	      return r;
		 
	}
}
