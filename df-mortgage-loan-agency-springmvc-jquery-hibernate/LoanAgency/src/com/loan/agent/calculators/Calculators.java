package com.loan.agent.calculators;
 
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.Math.*;
import java.text.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.loan.agent.calculators.vo.AffordableLineVo;
import com.loan.agent.calculators.vo.AmortParamVo;
import com.loan.agent.calculators.vo.AmortizationLineVo;
import com.loan.agent.calculators.vo.SummaryTotalsVo;
import com.loan.agent.calculators.vo.YearlyAmortizationVo;
import com.loan.agent.mvc.controller.CalculatorController;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.Constant;



public class Calculators {
  private static Logger LOG = Logger.getLogger(Calculators .class);
  private Double monthlypayment;
  private Double totalpayment;
  private Double amt;
  private Double r;
  private Double months;
  private Double period;
  

  public Calculators () {
    monthlypayment=0.0;
    totalpayment=0.0;
    amt=0.0;
    r=0.0;
    months=0.0;
    period=0.0;
   }


  public Calculators (Double amt,Double r,Double months,Double period) {
    this.amt=amt;
    this.r=r;
    this.months=months;
    this.period=period;
    monthlypayment=(amt*Math.pow((1 + r),months) * r) / (Math.pow((1 + r),months) - 1);
    totalpayment=period*monthlypayment;
   }

  
  public static Double monthly_payment(HttpServletRequest request) {
	  Double loan_amt= Utility.getDouble(request.getParameter(Constant.LOAN_AMT));
	  Double int_rate= Utility.getDouble(request.getParameter(Constant.INT_RATE));
	  Double term= Utility.getDouble(request.getParameter(Constant.TERM));
	  return monthly_payment(loan_amt,int_rate,term);      
  }
  
  
  
  public static Double monthly_payment(Double amt,Double r,Double months) {
      Double monthly_payment=0.0;
      
     if (months>0.0 && r>0.0 && amt>0.0) {  
       r=r/100.0/12.0;   // convert to monthly rate
       monthly_payment=(amt*Math.pow((1 + r),months) * r) / (Math.pow((1 + r),months) - 1);
      }  
     return monthly_payment;   
  }

public static AffordableLineVo  AffordHomePrice(
		HttpServletRequest request		) {
	  Double int_rate= Utility.getDouble(request.getParameter(Constant.INT_RATE));
	  Double term= Utility.getDouble(request.getParameter(Constant.TERM));	  
	  Double nonRentalIncome= Utility.getDouble(request.getParameter(Constant.NON_RENTAL_INCOME));
	  Double rentalIncome= Utility.getDouble(request.getParameter(Constant.RENTAL_INCOME));
	  Double debt= Utility.getDouble(request.getParameter(Constant.DEBT));
	  Double downPayment= Utility.getDouble(request.getParameter(Constant.DOWN_PAYMENT));
	  Double LTV= Utility.getDouble(request.getParameter(Constant.LTV));
	  Double propTax= Utility.getDouble(request.getParameter(Constant.PROPERTY_TAX));
	  Double propIns= Utility.getDouble(request.getParameter(Constant.PROPERTY_INSURANCE));
	  
	  return AffordHomePrice(
				nonRentalIncome,
				rentalIncome,
				int_rate,
				term,
				LTV,
				debt,
				downPayment,
				propTax,
				propIns
				);

}

public static AffordableLineVo  AffordHomePrice(
		Double nonRentalIncome,
		Double rentalIncome,
		Double intRate,
		Double months,
		Double LTV,
		Double debt,
		Double downPayment,
		Double propTax,
		Double propIns
		) {
	    Double aPrice,cPrice;
	    // grossIncome
		Double grossIncome = nonRentalIncome+rentalIncome*0.75;
		Double r = (intRate/100.0)/12.0;
		// DTI -- Debt To Income
		Double hDebtToIncome = 0.50;    // 418k ~ 625k High balance loan for aggressively afford home price
		Double hPITI = hDebtToIncome * grossIncome - debt;
		Double hLoanAmt = (hPITI*(Math.pow((1 + r),months) - 1)) / (Math.pow((1 + r),months) * r) ;
		Double propertyTax = hLoanAmt * propTax / 1200.00;
	    Double propertyIns = hLoanAmt * propIns / 1200.00;
	    
        LOG.info("hLoanAmt="+hLoanAmt+",downPayment="+downPayment);
		Double aPrice1 = (hLoanAmt)/LTV;
		Double aPrice2 = hLoanAmt+downPayment;
		
		if (hLoanAmt/aPrice2 > LTV) {
			aPrice =downPayment/(1/LTV-1);
		} else {
			aPrice = aPrice2;
		}
		
		
		Double cDebtToIncome = 0.45;    // < 417k confirming balance loan for conservatively afford home price
		Double cPITI = cDebtToIncome * grossIncome - debt;
		Double cLoanAmt = (cPITI*(Math.pow((1 + r),months) - 1)) / (Math.pow((1 + r),months) * r) ;
		propertyTax = cLoanAmt * propTax / 1200.00;
	    propertyIns = cLoanAmt * propIns / 1200.00;
	
		Double cPrice1 = (cLoanAmt)/LTV;
	    Double cPrice2 = cLoanAmt+downPayment;
	    
	    if (cLoanAmt/cPrice2 > LTV) {
			cPrice =downPayment/(1/LTV-1);
		} else {
			cPrice = cPrice2;
		}
	    LOG.info("aPrice="+aPrice+",cPrice="+cPrice);
	    AffordableLineVo vo = new AffordableLineVo();
	    vo.setAggresivePrice(Utility.renderDollar(aPrice));
	    vo.setConservativePrice(Utility.renderDollar(cPrice));
	    return vo;
} 
  
public static ArrayList<AmortizationLine>  amortizationCalculator(		
		HttpServletRequest request, SummaryTotalsVo outParam, AmortParamVo aparam)  
{		
		
	Double begin_balance= Utility.getDouble(request.getParameter(Constant.LOAN_AMT));
    Double year_int_rate = Utility.getDouble(request.getParameter(Constant.INT_RATE));
    Double month =   Utility.getDouble(request.getParameter(Constant.TERM));    
    
    Integer first_mm = Utility.getMonth(request.getParameter(Constant.FIRST_DATE));         //first_mm));
    Integer first_yyyy = Utility.getYear(request.getParameter(Constant.FIRST_DATE));         //first_yyyy));
    Double extra_PMT = Utility.getDouble(request.getParameter(Constant.EXTRA_PMT));         //extra_PMT));
    Integer extra_PMT_mm =Utility.getMonth(request.getParameter(Constant.PMT_DATE));         //PMT_mm));
    Integer extra_PMT_yyyy =Utility.getYear(request.getParameter(Constant.PMT_DATE));         //PMT_yyyy));
    Double  extra_YPMT = Utility.getDouble(request.getParameter(Constant.EXTRA_YPMT));         //extra_YPMT));
    Integer extra_YPMT_mm =Utility.getMonth(request.getParameter(Constant.YPMT_DATE));         //YPMT_mm));
    Integer extra_YPMT_yyyy =Utility.getYear(request.getParameter(Constant.YPMT_DATE));         //YPMT_yyyy));
    Double extra_YPMT_once = Utility.getDouble(request.getParameter(Constant.EXTRA_YPMT_ONCE));         //extra_YPMT_once));
    Integer extra_YPMT_once_mm =Utility.getMonth(request.getParameter(Constant.YPMT_ONCE_DATE));         //YPMT_once_mm));
    Integer extra_YPMT_once_yyyy =Utility.getYear(request.getParameter(Constant.YPMT_ONCE_DATE));         //YPMT_once_yyyy));
 	
    aparam.setLoanAmount(request.getParameter(Constant.LOAN_AMT));
    aparam.setInterestRate(request.getParameter(Constant.INT_RATE));
    aparam.setTerm(request.getParameter(Constant.TERM));
    aparam.setExtra_PMT(request.getParameter(Constant.EXTRA_PMT));
    aparam.setExtra_YPMT(request.getParameter(Constant.EXTRA_YPMT));
    aparam.setExtra_YPMT_once(request.getParameter(Constant.EXTRA_YPMT_ONCE));
    
    aparam.setFirst_Date(request.getParameter(Constant.FIRST_DATE));         //first_date));
    aparam.setPMT_Date(request.getParameter(Constant.PMT_DATE));         //PMT_date));
    aparam.setYPMT_Date(request.getParameter(Constant.YPMT_DATE));         //YPMT_date));
    aparam.setYPMT_once_Date(request.getParameter(Constant.ONCE_DATE));      
     
				return  amortizationCalculator( 
						begin_balance,
						year_int_rate,
						month,
						first_mm,
						first_yyyy,
						extra_PMT,
						extra_PMT_mm,
						extra_PMT_yyyy,
						extra_YPMT,
						extra_YPMT_mm,
						extra_YPMT_yyyy,
						extra_YPMT_once,
						extra_YPMT_once_mm,
						extra_YPMT_once_yyyy,
						outParam
						);

}

				
	public static ArrayList<AmortizationLine>  amortizationCalculator(		
	                Double begin_balance,
					Double year_int_rate,
					Double month,
					Integer first_mm,
					Integer first_yyyy,
					Double extra_PMT,
					Integer extra_PMT_mm,
					Integer extra_PMT_yyyy,
					Double extra_YPMT,
					Integer extra_YPMT_mm,
					Integer extra_YPMT_yyyy,
					Double extra_YPMT_once,
					Integer extra_YPMT_once_mm,
					Integer extra_YPMT_once_yyyy,
					SummaryTotalsVo summaryVo
					)
 {
		
       ArrayList<AmortizationLine> list = new ArrayList<AmortizationLine>();
 
       Double r = (year_int_rate/100.0)/12.0;
       Double remain_balance=0.0;       
       Double month_payment=0.0;
       Double int_paid=0.0;
       Double prin_paid=0.0;
       
       Integer year_th,year_cnt=1;
       Integer month_th=1;
       Integer real_year,real_year_cnt=first_yyyy;
       Integer real_month=first_mm;
      
	   

       month_payment=(begin_balance*Math.pow((1 + r),month) * r) / (Math.pow((1 + r),month) - 1);
       
       Double original_month_payment =  month_payment;
       Double adjusted_month_payment =month_payment;
       String real_date;

       boolean month_once_flag=false;
       boolean year_once_flag=false;
       boolean pay_once_flag=false;
       Double accu_int_paid=0.0;
       Double totalMonthlyPayment=0.0;
       Double totalIntPayment=0.0;
       Double totalPrinciplePayment=0.0; 
       
       Double summaryMonthPayment=0.0;
       Double summaryIntPayment=0.0;
       Double summaryPrinPayment=0.0;
       
       boolean pay_extra=false;
       Integer i=0;
       for (i=0; i < month; i++) {  // loop
        
        int_paid=begin_balance*r;

      
         prin_paid = month_payment - int_paid;

         remain_balance = begin_balance - prin_paid;
           
    
        year_th =((i+1)%12>0 ? year_cnt:year_cnt++);

        real_year =((first_mm+i)%12>0 ? real_year_cnt:real_year_cnt++);
    
        real_date=(real_month<10 ? "0"+Integer.toString(real_month):Integer.toString(real_month))+"-01-"+Integer.toString(real_year);
      //  LOG.info("External,real_month="+real_month+",real_year="+real_year+", extra_PMT="+extra_PMT+",extra_PMT_mm="+extra_PMT_mm+",extra_PMT_yyyy="+extra_PMT_yyyy);

        if (extra_PMT!=null && extra_PMT_mm!=null && extra_PMT_yyyy!=null) {    
        if (!month_once_flag && extra_PMT>0.0 && extra_PMT_mm>0 && extra_PMT_yyyy>0) {
       
          if (real_month>=extra_PMT_mm && real_year>=extra_PMT_yyyy ) {
        	  LOG.info("Internal monthly_payment="+month_payment+",extra_PMT,="+extra_PMT+",extra_PMT_mm="+extra_PMT_mm+",extra_PMT_yyyy="+extra_PMT_yyyy);

               month_payment = month_payment+extra_PMT;
               adjusted_month_payment = month_payment;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
     
              month_once_flag=true;
             
            }
          pay_extra=true;
       }
      
     }
     if (extra_YPMT!=null && extra_YPMT_mm!=null && extra_YPMT_yyyy!=null) {
    	 if (extra_YPMT>0.0 && extra_YPMT_mm>0 && extra_YPMT_yyyy>0) {
         if (real_month==extra_YPMT_mm && real_year>=extra_YPMT_yyyy ) {
              month_payment = month_payment+extra_YPMT;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              year_once_flag=true;
             } else if (year_once_flag) {
              month_payment = month_payment-extra_YPMT;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              year_once_flag=false;
            } 
         pay_extra=true;
          }	
     }
     if (extra_YPMT_once!=null && extra_YPMT_once_mm!=null && extra_YPMT_once_yyyy!=null) {
       if (extra_YPMT_once>0.0 && extra_YPMT_once_mm>0 && extra_YPMT_once_yyyy>0) {
     
          if (real_month==extra_YPMT_once_mm && real_year==extra_YPMT_once_yyyy ) {
              month_payment = month_payment+extra_YPMT_once;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              pay_once_flag=true;
            } else if (pay_once_flag) {
              month_payment = month_payment-extra_YPMT_once;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              pay_once_flag=false;

           }
          pay_extra=true;
       }
       
     }
      
        if (remain_balance<0.001) {
           month_payment=begin_balance;
           remain_balance=0.0;
           prin_paid = month_payment - int_paid;
        }
        // System.out.println(((i+1)%12==0 ? ++count:count)+"th year "+((i+1)%12+1)+"th Month (total:"+(i+1)+"month), begin_balance="+begin_balance+",monthlyPayment="+month_payment+",Interest Paid="+int_paid+",Principle paid="+prin_paid+",remain_balance="+(remain_balance<0.001 ? 0: remain_balance)+"\n");
        accu_int_paid+=int_paid;
    	totalMonthlyPayment+=month_payment;
    	totalIntPayment+=int_paid;
    	totalPrinciplePayment+=prin_paid;
    	//LOG.info("amortizationCalculator,accu_int_paid="+accu_int_paid);
        if  (real_month==12 || i==month-1 ) {
        	list.add(new AmortizationLine( 
                    begin_balance,
                    remain_balance,       
                    month_payment,
  	                int_paid,
                    prin_paid,
                    Integer.toString(year_th)+"/"+Integer.toString(month_th),
                    real_date,
                    (i+1),
                    accu_int_paid,
                    totalMonthlyPayment,
                    totalIntPayment,
                    totalPrinciplePayment
		    )
        );  
        	summaryMonthPayment+=totalMonthlyPayment;
            summaryIntPayment+=totalIntPayment;
            summaryPrinPayment+=totalPrinciplePayment;
            
        	totalMonthlyPayment=0.0;
     		totalIntPayment=0.0;
     		totalPrinciplePayment=0.0; 
     		
        } else {
        list.add(new AmortizationLine( 
	                     begin_balance,
	                     remain_balance,       
	                     month_payment,
       	                 int_paid,
	                     prin_paid,
	                     Integer.toString(year_th)+"/"+Integer.toString(month_th),
	                     real_date,
	                     (i+1),
	                     accu_int_paid,
	                     0.00,
	                     0.00,
	                     0.00
			    )
          );          
        }
            

            begin_balance = remain_balance;        // critical step
          
          if (remain_balance<0.001) 
            {
             remain_balance=0.001;
             break;
           } 
         
          month_th++;
          if ((i+1)%12==0) {
           month_th=1;
          }  
          
         real_month++; 
       
          if ((first_mm+i)%12==0) {
           real_month=1;
          }  
     
         
        }      // Loop
       if (summaryVo!=null) {
    	   summaryVo.setSummaryIntPayment(summaryIntPayment);
    	   summaryVo.setSummaryMonthPayment(summaryMonthPayment);
    	   summaryVo.setSummaryPrinPayment(summaryPrinPayment);
    	   summaryVo.setMonthlyPayment(Utility.renderDollar(original_month_payment));
    	   summaryVo.setRealPayMonths(Utility.renderInteger((i+1)));
    	   summaryVo.setAdjustedMonthlyPayment(Utility.renderDollar(adjusted_month_payment));
    	   if (pay_extra) {
    		   summaryVo.setPay_extra("yes");
    	   }
       }
      	//LOG.info("Calculator, summaryVo="+summaryVo+","+summaryVo.getSummaryMonthPayment());
       return list;   // return vector
    }

	

   //================AmortizationYearly ===================

  public static List<YearlyAmortizationVo> YearlyAmortization(List<AmortizationLine> paramList) {
	  List<YearlyAmortizationVo> list = new ArrayList<YearlyAmortizationVo>();
	  Integer mCount=0;
	  Double beginBalance=null;
	  Integer year=1;
	  
	  for (AmortizationLine line : paramList) {
		  
		  if (mCount==0) {
			  beginBalance=line.getBegin_balance();
		  }
		  
		  if (line.getTotalIntPayment()>0.0) 
		  {
			  YearlyAmortizationVo v = new YearlyAmortizationVo();
			  AmortizationLineVo lineVo = line.getLineVo();
			  v.setBeginBalance(Utility.renderDollar(beginBalance));
			  v.setInterestPayment(lineVo.getTotalIntPayment());
			  v.setYearlyPayment(lineVo.getTotalMonthlyPayment());
			  v.setPrinciplePayment(lineVo.getTotalPrinciplePayment());
			  v.setYear(lineVo.getPayment_date().substring(6, 10));
			  v.setYearNo(Utility.renderInteger(year++));
			  v.setRemainBalance(lineVo.getRemain_balance());
			  list.add(v);
			  beginBalance = line.getRemain_balance();
			  
		  }
		  
		  
		  mCount++;
	  }
	  return list;
  }
//==================ToTodayMonths=================
  
  public static Integer uptoday_months(Integer past_year,Integer past_month) {
	    JavaDate now = new JavaDate();
	    int this_year = now.getYear();
	    int this_month = now.getMonth();  
		 int pnum = (this_year*12+this_month+1-past_year*12-past_month);  
		 if (pnum<0) pnum=1;
	     return pnum;       
	 }

//====================Break Even Point=====================================

// Up to now remaining balance

public static Double uptonow_remain_balance(Double loan_amt,Integer paid_num,Double term,Double int_rate)
{
	if (term==null && int_rate==null) return null;  
    Double remain_bal=0.0;
    JavaDate now = new JavaDate();
    Integer first_mm = now.getMonth();   // no meaningful but for let function work
    Integer first_yyyy = now.getYear();
    
    ArrayList<AmortizationLine> list = new ArrayList<AmortizationLine>();
    
    list  = amortizationCalculator(
    		       loan_amt,
					int_rate,
					term,
					first_mm,
					first_yyyy,
					0.0,   //Double extra_PMT,
					0,     //Integer extra_PMT_mm,
					0,     //Integer extra_PMT_yyyy,
					0.0,   //Double extra_YPMT,
					0,     //Integer extra_YPMT_mm,
					0,     //Integer extra_YPMT_yyyy,
					0.0,   // Double extra_YPMT_once,
					0,     //Integer extra_YPMT_once_mm,
					0,      //Integer extra_YPMT_once_yyyy
					null
					);

    
     AmortizationLine amortizationLine = new AmortizationLine();
      
     
       amortizationLine = (AmortizationLine) list.get(paid_num-1);  // paid_num means paid month number

       remain_bal=amortizationLine.getRemain_balance();
     

    return remain_bal;
   }

public static Double total_int_paid(Double loan_amt,Integer paid_num,Double term,Double int_rate,Integer first_mm,Integer first_yyyy)
   {
	if (term==null && int_rate==null) return null; 
	ArrayList<AmortizationLine> list = new ArrayList<AmortizationLine>();
	 
    Double int_paid=0.0;
     
    list = amortizationCalculator(	
    		        loan_amt,
					int_rate,
					term,
					first_mm,
					first_yyyy,
					0.0,   //Double extra_PMT,
					0,     //Integer extra_PMT_mm,
					0,     //Integer extra_PMT_yyyy,
					0.0,   //Double extra_YPMT,
					0,     //Integer extra_YPMT_mm,
					0,     //Integer extra_YPMT_yyyy,
					0.0,   // Double extra_YPMT_once,
					0,     //Integer extra_YPMT_once_mm,
					0,     //Integer extra_YPMT_once_yyyy
					null
					);

    
     AmortizationLine amort = new AmortizationLine();
     for (Integer i=0 ; i<list.size() && i<paid_num; i++) {
       amort = (AmortizationLine) list.get(i);
       int_paid+=amort.getInt_paid();
     }

    return int_paid;
   }

public static Double total_unint_paid(Double remain_bal,Integer paid_num,Double term,Double int_rate)
 {
	if (term==null && int_rate==null) return null;
	 ArrayList<AmortizationLine> list = new ArrayList<AmortizationLine>();
    Double unint_paid=0.0;
    JavaDate now = new JavaDate();
    Integer first_mm = now.getMonth();
    Integer first_yyyy = now.getYear();

    list = amortizationCalculator(remain_bal,
					int_rate,
					term,
					first_mm,
					first_yyyy,
					0.0,   //Double extra_PMT,
					0,     //Integer extra_PMT_mm,
					0,     //Integer extra_PMT_yyyy,
					0.0,   //Double extra_YPMT,
					0,     //Integer extra_YPMT_mm,
					0,     //Integer extra_YPMT_yyyy,
					0.0,   // Double extra_YPMT_once,
					0,     //Integer extra_YPMT_once_mm,
					0,      //Integer extra_YPMT_once_yyyy
					null
					);

    
     AmortizationLine amort = new AmortizationLine();
     for (Integer i=paid_num; i<list.size(); i++) {
       amort = (AmortizationLine) list.get(i);
       unint_paid+=amort.getInt_paid();
     }

    return unint_paid;
   }


public static String BEP_prn(Double loan_amt,Double PMT,Double PMT_new,Double closing_cost,Double point)
  {
     String str="0";
     LOG.info("Loan_Amt="+loan_amt+",close_cost="+closing_cost+",point="+point);
    
     if (closing_cost>0.0 || point>0.0 ) {
    	 Double total_cost=closing_cost+point*loan_amt/100.0;
    	 double BEP_time = total_cost/(PMT-PMT_new);
    	 int BEP_Integer = (int) BEP_time;
    	 double BEP_Double = (double) BEP_Integer;
    	 Integer BEP_months = BEP_Integer+((BEP_time - BEP_Integer)>0 ? 1:0);
    	 str =Integer.toString(BEP_months/12)+" yrs"+Integer.toString(BEP_months%12)+" mo";
    } 
     LOG.info("BEP="+str);
     return str;
 }         

     
 
   


//==================== year use same method but different function name========================

public static List<AmortizationLine> amortizedYearCalculator(
			AmortParamVo inParam, SummaryTotalsVo outParam)  
{		
		         	Double begin_balance= Utility.getDouble(inParam.getLoanAmount());
					Double year_int_rate= Utility.getDouble(inParam.getInterestRate());
					Double month= Utility.getDouble(inParam.getTerm());
					Integer first_mm= Utility.getMonth(inParam.getFirst_Date());
					Integer first_yyyy= Utility.getYear(inParam.getFirst_Date());
					Double extra_PMT= Utility.getDouble(inParam.getExtra_PMT());
					Integer extra_PMT_mm= Utility.getMonth(inParam.getPMT_Date());
					Integer extra_PMT_yyyy= Utility.getYear(inParam.getPMT_Date());
					Double extra_YPMT= Utility.getDouble(inParam.getExtra_YPMT());
					Integer extra_YPMT_mm= Utility.getMonth(inParam.getYPMT_Date());
					Integer extra_YPMT_yyyy= Utility.getYear(inParam.getYPMT_Date());
					Double extra_YPMT_once= Utility.getDouble(inParam.getExtra_YPMT_once());
					Integer extra_YPMT_once_mm= Utility.getMonth(inParam.getYPMT_once_Date());
					Integer extra_YPMT_once_yyyy = Utility.getYear(inParam.getYPMT_once_Date());
		
					 
 
	   ArrayList<AmortizationLine> list = new ArrayList<AmortizationLine>();
 
       Double r = (year_int_rate/100.0)/12.0;
       Double remain_balance=0.0;       
       Double month_payment=0.0;
       Double int_paid=0.0;
       Double prin_paid=0.0;
       
       Integer year_th,year_cnt=1;
       Integer month_th=1;
       Integer real_year,real_year_cnt=first_yyyy;
       Integer real_month=first_mm;
       
       month_payment=(begin_balance*Math.pow((1 + r),month) * r) / (Math.pow((1 + r),month) - 1);
      
       String real_date;

       boolean month_once_flag=false;
       boolean year_once_flag=false;
       boolean pay_once_flag=false;
       Double accu_int_paid=0.0;
       Integer i=0;
       for (i=0; i < month; i++) {  // loop
        
        int_paid=begin_balance*r;

      
         prin_paid = month_payment - int_paid;

         remain_balance = begin_balance - prin_paid;
           
    
        year_th =((i+1)%12>0 ? year_cnt:year_cnt++);

        real_year =((first_mm+i)%12>0 ? real_year_cnt:real_year_cnt++);
    
        real_date=(real_month<10 ? "0"+Integer.toString(real_month):Integer.toString(real_month))+"-01-"+Integer.toString(real_year);

    if (!month_once_flag) {
     if (extra_PMT!=null && extra_PMT_mm!=null && extra_PMT_yyyy!=null) {   
      if (extra_PMT>0.0 && extra_PMT_mm>0 && extra_PMT_yyyy>0) {
          if (real_month>=extra_PMT_mm && real_year>=extra_PMT_yyyy ) {
               month_payment = month_payment+extra_PMT;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              month_once_flag=true;
            }
       }
     }
    }
     if (extra_YPMT!=null && extra_YPMT_mm!=null && extra_YPMT_yyyy!=null) {
     if (extra_YPMT>0.0 && extra_YPMT_mm>0 && extra_YPMT_yyyy>0) {
         if (real_month==extra_YPMT_mm && real_year>=extra_YPMT_yyyy ) {
              month_payment = month_payment+extra_YPMT;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              year_once_flag=true;
             } else if (year_once_flag) {
              month_payment = month_payment-extra_YPMT;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              year_once_flag=false;
            } 
          }	
     }
     if (extra_YPMT_once!=null && extra_YPMT_once_mm!=null && extra_YPMT_once_yyyy!=null) {
       if (extra_YPMT_once>0.0 && extra_YPMT_once_mm>0 && extra_YPMT_once_yyyy>0) {
     
          if (real_month==extra_YPMT_once_mm && real_year==extra_YPMT_once_yyyy ) {
              month_payment = month_payment+extra_YPMT_once;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              pay_once_flag=true;
            } else if (pay_once_flag) {
              month_payment = month_payment-extra_YPMT_once;
              prin_paid = month_payment - int_paid;
              remain_balance = begin_balance - prin_paid;
              pay_once_flag=false;

           }
       }
     }


        if (remain_balance<0.001) {
           month_payment=begin_balance;
           remain_balance=0.0;
           prin_paid = month_payment - int_paid;
        }

        // System.out.println(((i+1)%12==0 ? ++count:count)+"th year "+((i+1)%12+1)+"th Month (total:"+(i+1)+"month), begin_balance="+begin_balance+",monthlyPayment="+month_payment+",Interest Paid="+int_paid+",Principle paid="+prin_paid+",remain_balance="+(remain_balance<0.001 ? 0: remain_balance)+"\n");
        accu_int_paid+=int_paid;
    	LOG.info("amortizationYearlyCalculator,accu_int_paid="+accu_int_paid);
        list.add(new AmortizationLine( 
	                     begin_balance,
	                     remain_balance,       
	                     month_payment,
       	                 int_paid,
	                     prin_paid,
	                     Integer.toString(year_th)+"/"+Integer.toString(month_th),
	                     real_date,
	                     new Integer(i+1),
	                     accu_int_paid,
	                     0.00,
	                     0.00,
	                     0.00
	                     
			    )
              );          


            begin_balance = remain_balance;        // critical step
    
          if (remain_balance<0.001) 
            {
             remain_balance=0.001;
             break;
           } 
          month_th++;
          if ((i+1)%12==0) {
           month_th=1;
          }  
          
         real_month++; 
       
          if ((first_mm+i)%12==0) {
           real_month=1;
          }  
     
         
        }      // Loop
  
       return list;   // return vector
    }


 
     public static Double  monthly_charge(Double amt,Double r,Double months) {
    	 if (months==null && r==null) return null;
         Double monthly_payment=0.0;
          if (months>0.0 && r>0.0 && amt>0.0) { 
            Double r1=r/100.0/12.0;    // convert to monthly rate!!!!!!!
            monthly_payment=(amt*Math.pow((1 + r1),months) * r1) / (Math.pow((1 + r1),months) - 1);
           }  
           return monthly_payment;    
      }

     public static Double total_charge(Double period,Double monthlypayment) {
          Double totalpayment=period*monthlypayment;
          return totalpayment;
      }

     public Double getmonthly_charge() {
         return monthlypayment;
      }

     public Double gettotal_charge() {
        return totalpayment;
     }


	public Double getMonthlypayment() {
		return monthlypayment;
	}


	public void setMonthlypayment(Double monthlypayment) {
		this.monthlypayment = monthlypayment;
	}


	public Double getTotalpayment() {
		return totalpayment;
	}


	public void setTotalpayment(Double totalpayment) {
		this.totalpayment = totalpayment;
	}

 
   
   }  //end of class
