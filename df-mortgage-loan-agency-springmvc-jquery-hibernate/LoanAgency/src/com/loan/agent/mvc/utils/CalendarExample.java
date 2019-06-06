package com.loan.agent.mvc.utils;

import java.util.Date;
     import java.util.Calendar;
     import java.text.SimpleDateFormat;
     import java.util.*;

                  public class CalendarExample {
                         
                         private static void CalendarTimemethod() {
                         Date date = Calendar.getInstance().getTime();
                         System.out.println("Current date and time is: " + date);
                         System.out.println();
                         }

                         private static void SimpleDateFormatmethod() {
                         Calendar date = Calendar.getInstance();
                         SimpleDateFormat dateformatter = new SimpleDateFormat
                         ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
                         System.out.println("Current date and time in simple date format: " 
                         + dateformatter.format(date.getTime()));
                         System.out.println();
                         }

                         private static void Adddates() {

                         System.out.println("Performing perations on calendar dates.");

                         // Get today's date
                         Calendar date = Calendar.getInstance();
                         Calendar cldr;
                         SimpleDateFormat dateformatter = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

                         cldr = (Calendar) date.clone();
                         cldr.add(Calendar.DAY_OF_YEAR, - (365 / 2));
                         System.out.println("Before Half years it was: " + dateformatter.format(cldr.getTime()));
                         
                         cldr = (Calendar) date.clone();
                         cldr.add(Calendar.MONTH, + 2);
                         System.out.println("After 2 month  it was: " + dateformatter.format(cldr.getTime()));

                         
                         cldr = (Calendar) date.clone();
                         cldr.add(Calendar.DAY_OF_YEAR, + 5);
                         System.out.println("After five years it will be: "  + dateformatter.format(cldr.getTime()));

                         System.out.println();
                         }

                         private static void DateDifference() {
                         
                         System.out.println("Difference between two dates");
                         Date startDate1 = new GregorianCalendar(2005, 02, 25, 14, 00).getTime();
                         Date endDate1 = new Date();;

                         long diff = endDate1.getTime() - startDate1.getTime();

                         System.out.println("  Difference between " + endDate1);
                         System.out.println("  and " + startDate1 + " is "    + (diff /  (1000L*60L*60L*24L)) + " days.");
                         System.out.println();
                         }

                         private static void Getcalendermethods() {

                         System.out.println("Various get methods   of the calendar class:");
                         Calendar calender = Calendar.getInstance();

                         System.out.println("Year : "         + calender.get(Calendar.YEAR));
                         System.out.println("Month  : "            + calender.get(Calendar.MONTH));
                         System.out.println("Day of Month  : " 

                        + calender.get(Calendar.DAY_OF_MONTH));
                         System.out.println("Day of Week  : "     + calender.get(Calendar.DAY_OF_WEEK));
                         System.out.println("Day of Year  : "     + calender.get(Calendar.DAY_OF_YEAR));
                         System.out.println("Week of Year  : " 

                        + calender.get(Calendar.WEEK_OF_YEAR));
                         System.out.println("Week of Month  : "   + calender.get(Calendar.WEEK_OF_MONTH));
                         System.out.println
                          ("Day of the Week in Month : " 

                        + calender.get(Calendar.DAY_OF_WEEK_IN_MONTH));  
                         System.out.println("Hour   : " + calender.get(Calendar.HOUR));
                         System.out.println("AM PM   : " + calender.get(Calendar.AM_PM));
                         System.out.println("Hour of the Day  : " + calender.get(Calendar.HOUR_OF_DAY));
                         System.out.println("Minute     : " + calender.get(Calendar.MINUTE));
                         System.out.println("Second   " + calender.get(Calendar.SECOND));
                         System.out.println();
                         }

                         public static void main(String[] args) {
                         System.out.println();
                         CalendarTimemethod();
                         SimpleDateFormatmethod();
                         Adddates();
                         DateDifference();
                         Getcalendermethods();
                         }
                        } 
