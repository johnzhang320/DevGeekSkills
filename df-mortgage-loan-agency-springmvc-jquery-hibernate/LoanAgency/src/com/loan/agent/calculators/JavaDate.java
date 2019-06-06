package com.loan.agent.calculators;
import java.util.*;
import java.text.*;

public class JavaDate {

  private DateFormat format4 = new SimpleDateFormat( "MM-dd-yyyy" );
  private DateFormat format2 = new SimpleDateFormat( "MM-dd-yy" );
   
public JavaDate() {
       
    }

public String gettoday_str() {
     
      return format4.format(new Date());   
    }


public String gettoday_day() {
      Date now = new Date();
       String day =  format4.format(now);
       return day.substring(3,5);
  }


public String gettoday_month() {
     Date now = new Date();
     String day =  format4.format(new Date());
     return day.substring(0,2);
  }

public String gettoday_year() {
     Date now = new Date();
     String day =  format4.format(new Date());
     return day.substring(6,10);
    }
public int getMonth() {
        Date now = new Date();
        return now.getMonth()+1;
  }

public int getYear() {
     Date now = new Date();
      return now.getYear()+1900;
    }
public int uptoday_months(int past_year,int past_month) {
     Date now = new Date();
     return (now.getYear()+1900)*12+now.getMonth()+1-past_year*12-past_month;       
 }


public long diff2Dates(String startDate,String endDate)  {
        int yyyy1 = Integer.parseInt(startDate.substring(6,10));
        int dd1 = Integer.parseInt(startDate.substring(3,5));
        int mm1 = Integer.parseInt(startDate.substring(0,2));

        int yyyy2 = Integer.parseInt(endDate.substring(6,10));
        int dd2 = Integer.parseInt(endDate.substring(3,5));
        int mm2 = Integer.parseInt(endDate.substring(0,2));

        Date startDate1 = new GregorianCalendar(yyyy1, mm1-1, dd1).getTime();
        Date endDate1   = new GregorianCalendar(yyyy2, mm2-1, dd2).getTime();

        return (endDate1.getTime() - startDate1.getTime())/(1000L*60L*60L*24L);
     }


public int Compare2Dates(String Date1,String Date2)  {
     // return 
     // 1   Date1>Date2
     // 0   Date1=Date2
     // -1  Date1<Date2      
     int ret=0;
        int yyyy1 = Integer.parseInt(Date1.substring(6,10));
        int dd1 = Integer.parseInt(Date1.substring(3,5));
        int mm1 = Integer.parseInt(Date1.substring(0,2));

        int yyyy2 = Integer.parseInt(Date2.substring(6,10));
        int dd2 = Integer.parseInt(Date2.substring(3,5));
        int mm2 = Integer.parseInt(Date2.substring(0,2));

        Date rDate1 = new GregorianCalendar(yyyy1, mm1-1, dd1).getTime();
        Date rDate2   = new GregorianCalendar(yyyy2, mm2-1, dd2).getTime();

        if (rDate1.after(rDate2)) {
         ret = 1;
        }

        if (rDate1.equals(rDate2)) {
         ret= 0;
        }

        if (rDate1.before(rDate2)) {
         ret= -1;
        }
       return ret;
     }  

public String add_return_yy(String Date1,int days) throws  java.text.ParseException {
       
      Date dStartDate = format4.parse(Date1); 
      Calendar stCal = Calendar.getInstance(); 
      stCal.setTime(dStartDate); 
      stCal.add(Calendar.DAY_OF_YEAR,days);
      return format2.format(stCal.getTime());
   }  

public String add_return_yyyy(String Date1,int days) throws  java.text.ParseException {
       
      Date dStartDate = format4.parse(Date1); 
      Calendar stCal = Calendar.getInstance(); 
      stCal.setTime(dStartDate); 
      stCal.add(Calendar.DAY_OF_YEAR,days);
      return format4.format(stCal.getTime());
   }  

public String add_months_yyyy(String Date1,int months) throws  java.text.ParseException {
       
      Date dStartDate = format4.parse(Date1); 
      Calendar stCal = Calendar.getInstance(); 
      stCal.setTime(dStartDate); 
      stCal.add(Calendar.MONTH,months);
      return format4.format(stCal.getTime());
   }  
}    // javaDate
 


    