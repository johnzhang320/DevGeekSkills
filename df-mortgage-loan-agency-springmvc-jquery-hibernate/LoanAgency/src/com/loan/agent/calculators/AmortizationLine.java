package com.loan.agent.calculators;

import org.apache.log4j.Logger;

import com.loan.agent.calculators.vo.AmortizationLineVo;
import com.loan.agent.mvc.controller.CalculatorController;
import com.loan.agent.mvc.utils.Utility;

public class AmortizationLine {
	Logger LOG = Logger.getLogger(AmortizationLine.class);
	
       private Double begin_balance;
       private Double remain_balance;       
       private Double month_payment;
       private Double int_paid; 
       private Double accu_int_paid; 
       private Double prin_paid;
       private String year_month_th;
       private String payment_date;
       private Integer total_month;  
       private Double totalMonthlyPayment;
       private Double totalIntPayment;
       private Double totalPrinciplePayment;    
       private AmortizationLineVo lineVo;
       
   
  public AmortizationLine() {
       Double begin_balance=0.0;
       Double remain_balance=0.0;       
       Double month_payment=0.0;
       Double int_paid=0.0;
       Double prin_paid=0.0;
       Integer  year_th=0;
       Integer  month_th=0;
       Integer  total_month=0;

    }
  public AmortizationLine(
       Double begin_balance,
       Double remain_balance,       
       Double month_payment,
       Double int_paid,
       Double prin_paid,
       String year_month_th,
       String payment_date,
       Integer  total_month, 
       Double accu_int_paid,
       Double totalMonthlyPayment,
       Double totalIntPayment,
       Double totalPrinciplePayment   

       
  ) {
    
       this.begin_balance=begin_balance;
       this.remain_balance=remain_balance;       
       this.month_payment=month_payment;
       this.int_paid=int_paid;
       this.prin_paid=prin_paid;
       this.year_month_th=year_month_th;
       this.payment_date=payment_date;
       this.total_month=total_month;
       this.accu_int_paid =accu_int_paid;
       this.totalMonthlyPayment=totalMonthlyPayment;
       this.totalIntPayment=totalIntPayment;
       this.totalPrinciplePayment=totalPrinciplePayment;   
   }
  
  
  
public AmortizationLineVo getLineVo() {
	//LOG.info(" getLineVo,accu_int_paid="+this.accu_int_paid); 
	return  AmortizationLineVo.initInstance(
			Utility.renderDollar(this.begin_balance),
			Utility.renderDollar(this.remain_balance),
			Utility.renderDollar(this.month_payment), 
			Utility.renderDollar(this.int_paid), 
			Utility.renderDollar(this.prin_paid),
			this.year_month_th, 
			this.payment_date, 
			this.total_month,
			Utility.renderDollar(this.accu_int_paid),
			Utility.renderDollar(this.totalMonthlyPayment),
			Utility.renderDollar(this.totalIntPayment),
			Utility.renderDollar(this.totalPrinciplePayment)    	
			); 
}

public AmortizationLineVo getNewLineVo() {
	//LOG.info(" getLineVo,accu_int_paid="+this.accu_int_paid); 
	return  new AmortizationLineVo (
			Utility.renderDollar(this.begin_balance),
			Utility.renderDollar(this.remain_balance),
			Utility.renderDollar(this.month_payment), 
			Utility.renderDollar(this.int_paid), 
			Utility.renderDollar(this.prin_paid),
			this.year_month_th, 
			this.payment_date, 
			this.total_month,
			Utility.renderDollar(this.accu_int_paid)
			); 
}

public Double getBegin_balance() {
	return begin_balance;
}
public void setBegin_balance(Double begin_balance) {
	this.begin_balance = begin_balance;
}
public Double getRemain_balance() {
	return remain_balance;
}
public void setRemain_balance(Double remain_balance) {
	this.remain_balance = remain_balance;
}
public Double getMonth_payment() {
	return month_payment;
}
public void setMonth_payment(Double month_payment) {
	this.month_payment = month_payment;
}
public Double getInt_paid() {
	return int_paid;
}
public void setInt_paid(Double int_paid) {
	this.int_paid = int_paid;
}
public Double getPrin_paid() {
	return prin_paid;
}
public void setPrin_paid(Double prin_paid) {
	this.prin_paid = prin_paid;
}
public String getYear_month_th() {
	return year_month_th;
}
public void setYear_month_th(String year_month_th) {
	this.year_month_th = year_month_th;
}
public String getPayment_date() {
	return payment_date;
}
public void setPayment_date(String payment_date) {
	this.payment_date = payment_date;
}
public Integer getTotal_month() {
	return total_month;
}
public void setTotal_month(Integer total_month) {
	this.total_month = total_month;
}
public Double getAccu_int_paid() {
	return accu_int_paid;
}
public void setAccu_int_paid(Double accu_int_paid) {
	this.accu_int_paid = accu_int_paid;
}
public Double getTotalMonthlyPayment() {
	return totalMonthlyPayment;
}
public void setTotalMonthlyPayment(Double totalMonthlyPayment) {
	this.totalMonthlyPayment = totalMonthlyPayment;
}
public Double getTotalIntPayment() {
	return totalIntPayment;
}
public void setTotalIntPayment(Double totalIntPayment) {
	this.totalIntPayment = totalIntPayment;
}
public Double getTotalPrinciplePayment() {
	return totalPrinciplePayment;
}
public void setTotalPrinciplePayment(Double totalPrinciplePayment) {
	this.totalPrinciplePayment = totalPrinciplePayment;
}
public void setLineVo(AmortizationLineVo lineVo) {
	this.lineVo = lineVo;
} 

 }