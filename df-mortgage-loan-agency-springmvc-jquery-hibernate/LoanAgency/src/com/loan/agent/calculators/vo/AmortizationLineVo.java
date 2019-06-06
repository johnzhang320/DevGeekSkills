package com.loan.agent.calculators.vo;

import java.io.Serializable;

import com.loan.agent.mvc.utils.Utility;

 

public class AmortizationLineVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	 private String total_month;
	 private  String payment_date;
	 private  String begin_balance;
	 private  String remain_balance;       
	 private  String month_payment;
	 private  String int_paid;
	 private  String prin_paid;
	 private  String year_month_th;
	 private  String accu_int_paid;
     private String totalMonthlyPayment;
     private String totalIntPayment;
     private String totalPrinciplePayment;    

    static  AmortizationLineVo handler=null;
   static {
	   handler=new AmortizationLineVo();
   }
   public static  AmortizationLineVo getInstance() {
	   return handler;
   }
   private AmortizationLineVo() {
    	  
    }
      
	public AmortizationLineVo(
			String begin_balance, 
			String remain_balance,
			String month_payment, 
			String int_paid, 
			String prin_paid,
			String year_month_th, 
			String payment_date, 
			Integer total_month,
			String accu_int_paid
			) {
		super();
		this.begin_balance = begin_balance;
		this.remain_balance = remain_balance;
		this.month_payment = month_payment;
		this.int_paid = int_paid;
		this.prin_paid = prin_paid;
		this.year_month_th = year_month_th;
		this.payment_date = payment_date;
		this.total_month =new Integer(total_month).toString();
		this.accu_int_paid = accu_int_paid;
	}
	
	public static AmortizationLineVo initInstance(
			String begin_balance,
			String remain_balance,
			String month_payment, 
			String int_paid, 
			String prin_paid,
			String year_month_th,
			String payment_date, 
			Integer total_month,
			String accu_int_paid,
			String totalMonthlyPayment,
			String totalIntPayment,
			String totalPrinciplePayment     	
			) {
		synchronized(handler) {
			handler.begin_balance = begin_balance;
			handler.remain_balance = remain_balance;
			handler.month_payment = month_payment;
			handler.int_paid = int_paid;
			handler.prin_paid = prin_paid;
			handler.year_month_th = year_month_th;
			handler.payment_date = payment_date;
			handler.total_month =new Integer(total_month).toString();
			handler.accu_int_paid = accu_int_paid;
			handler.totalMonthlyPayment=totalMonthlyPayment;
			handler.totalIntPayment = totalIntPayment;
			handler.totalPrinciplePayment = totalPrinciplePayment;
		}
		return handler;
	}
	public String getBegin_balance() {
		return begin_balance;
	}
	public void setBegin_balance(String begin_balance) {
		this.begin_balance = begin_balance;
	}
	public String getRemain_balance() {
		return remain_balance;
	}
	public void setRemain_balance(String remain_balance) {
		this.remain_balance = remain_balance;
	}
	public String getMonth_payment() {
		return month_payment;
	}
	public void setMonth_payment(String month_payment) {
		this.month_payment = month_payment;
	}
	public String getInt_paid() {
		return int_paid;
	}
	public void setInt_paid(String int_paid) {
		this.int_paid = int_paid;
	}
	public String getPrin_paid() {
		return prin_paid;
	}
	public void setPrin_paid(String prin_paid) {
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
	public String getTotal_month() {
		return total_month;
	}
	public void setTotal_month(String total_month) {
		this.total_month = total_month;
	}


	public String getAccu_int_paid() {
		return accu_int_paid;
	}


	public void setAccu_int_paid(String accu_int_paid) {
		this.accu_int_paid = accu_int_paid;
	}
	public String getTotalMonthlyPayment() {
		return totalMonthlyPayment;
	}
	public void setTotalMonthlyPayment(String totalMonthlyPayment) {
		this.totalMonthlyPayment = totalMonthlyPayment;
	}
	public String getTotalIntPayment() {
		return totalIntPayment;
	}
	public void setTotalIntPayment(String totalIntPayment) {
		this.totalIntPayment = totalIntPayment;
	}
	public String getTotalPrinciplePayment() {
		return totalPrinciplePayment;
	}
	public void setTotalPrinciplePayment(String totalPrinciplePayment) {
		this.totalPrinciplePayment = totalPrinciplePayment;
	}
      
}
