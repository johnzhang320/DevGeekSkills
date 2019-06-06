package com.loan.agent.mvc.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.loan.agent.dao.County;
import com.loan.agent.dao.CountyDAO;
import com.loan.agent.dao.StateDAO;

public class TestDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*CountyDAO countyDao = (CountyDAO)  SpringFramework.getBean("CountyDAO");
		List<String> list = countyDao.findByStateSymbol("CA");
		
		for (String s:list) {
			System.out.println(s);
		}*/
		String str="21230000120";
		
		char cstr[] = str.toCharArray();
		String a="";
		int count=0;
		int len = cstr.length-1;
		for (int i=len;i>=0;i--) {
			if (count==2) {
				count=0;
				if (i==0) a=cstr[i]+a; else	a=","+cstr[i]+a;
			} else {
				a=cstr[i]+a;
				count++;
			}
			
		}
		System.out.println(a);
		
	    String s = "1234567890.67012".replace(",","");  
	    
	    BigDecimal bd = new BigDecimal(s);  
	    System.out.println(bd+",doubleValue()="+bd.doubleValue()+",floatValue()="+bd.floatValue());  
	    Double d = Double.parseDouble(s);  
	    DecimalFormat df = new DecimalFormat("#,###.##");
	    String str1 = df.format(d);
        System.out.println(str1);
        
        Float f = Float.parseFloat(s);
        
        
	    System.out.println(d+",d.toString()="+d.toString());  
	    
	    
	}	
	
	 
}
