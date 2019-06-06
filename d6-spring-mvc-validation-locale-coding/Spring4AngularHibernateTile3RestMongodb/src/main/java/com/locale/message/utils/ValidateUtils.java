package com.locale.message.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;
import org.springframework.validation.Errors;

public class ValidateUtils {
	  private static Logger LOG = Logger.getLogger(ValidateUtils.class);
	  private static final String REQUIRE_FIELD="required.field"; 
	  private static final String STOP_XSS_ATTACK="stop.xss.attack"; 
	  private static final String INVALID_VALUE_FIELD="invalid.value.field";  // invalid.value.field ={0} is invalid {1}

	  //------------------------------Low level utilities--------------------------------
	  
	
	  public boolean findXSSTagPair(String fieldValue) {
		 if (null==fieldValue || 0==fieldValue.length()) return false;
		 fieldValue = fieldValue.toLowerCase();
		 if (fieldValue.contains("<script>") && fieldValue.contains("</script>")) {
			 LOG.error("Hacked by XSS <script>");
			 return true;
		 }
		 if (fieldValue.contains("<script") && fieldValue.contains("/>")) {
			 LOG.error("Hacked by XSS <script>");
			 return true;
		 }
		 if (fieldValue.contains("<img") && fieldValue.contains("</img>")) {
			 LOG.error("Hacked by XSS <img>");
			 return true;
		 }
		 if (fieldValue.contains("<img") && fieldValue.contains("/>")) {
			 LOG.error("Hacked by XSS <img>");
			 return true;
		 }
		 if (fieldValue.contains("<img ") && fieldValue.contains(">")) {
			 LOG.error("Hacked by XSS <img>");
			 return true;
		 }
		 if (fieldValue.contains("<a") && fieldValue.contains("</a>")) {
			 LOG.error("Hacked by XSS <a>");
			 return true;
		 }
		 if (fieldValue.contains("<a") && fieldValue.contains("/>")) {
			 LOG.error("Hacked by XSS <a>");
			 return true;
		 }
		 if (fieldValue.contains("<a ") && fieldValue.contains(">")) {
			 LOG.error("Hacked by XSS <a>");
			 return true;
		 } 
		 int len = fieldValue.length();
		 int open=fieldValue.indexOf("<"); 
		 boolean first=false;
		/**
		 *  < ... > ..... </...  > pair
		 *  < ... /> 
		 */
		 if (open!=-1) {			 
			 while (open<len) {
				 if (fieldValue.charAt(open)=='>') {
					 first=true;
					 break;
				 }
				 open++;
			 }
		 } else {
			 return false;
		 }
		 if (first) {
			 if (fieldValue.indexOf("/>")!=-1) {
				 return true;
			 }
			 int close = fieldValue.indexOf("</");
			 if (close!=-1) {
				 while(close<len) {
					 if (fieldValue.charAt(close)=='>') {
						 return true;
					 }
					 close++;
				 }
			 } else {
				 return false;
			 }
		 }
		 
			 
		 return false;
	 }
  
	 public String isEmpty(String field,String fieldCode,Errors errors) {
		 String fieldValue =(String) errors.getFieldValue(field);		 
		  if (isBlankString(fieldValue)) {
			  return null;
		  }
		  return fieldValue;
	 }
		 
   
	 public  Long getSimpleDateForm(String date) {
		  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		  Long dateAsLong = null;
		  try {
			  	dateAsLong =sdf.parse(date).getTime();
		  } catch (ParseException e) {
			 LOG.error("Date Format "+date +" is wrong!"); 
		  }
		  return dateAsLong;
	  
	  }
	  
	  public  Date ConvertToDate(String date) {
		  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		  Date date2 = null;
		  try {
			  	date2 =sdf.parse(date);
		  } catch (ParseException e) {
			 LOG.error("Date Format "+date +" is wrong!"); 
		  }
		  return date2;
	  
	  }
	  public  Date myConvertToDate(String month_year) {
		  SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		  Date date2 = null;
		  try {
			    
			  	date2 =sdf.parse(month_year);
		  } catch (ParseException e) {
			 LOG.error("Date Format "+month_year +" is wrong!"); 
		  }
		  return date2;
	}
	    
	public  boolean  isBlankString(String str) {
	     if (str == null) return true;
	      return (str.length() == 0);
	}
	
	public String rltrim(String s) {
		if (isBlankString(s)) return "";
		 return s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
	}
	
	// -------------------isValidEmailString --------------------------
	 public  boolean isValidEmailString(String fieldValue) {
		 
		 if (null==fieldValue) {
			 return false;
		 }
		  try {
		      RE email = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		    		  
		      if (!email.match(fieldValue)) {		    	
		    	  return false;
		      } 
		   
		  } catch (Exception ex) {
		      ex.printStackTrace();
		      return false;
		  }
		   return true;
	  }
	  //-----------------IsValidPhoneString -------------------------
	  public  boolean isValidPhoneString(String fieldValue) {		  
		  if (null==fieldValue) {
			 return false;
		  }
	      try {
	    	  String str = fieldValue.replace("(", "").replace(")", "").replace("-", "");
	    	  int len = str.length();
	    	  int count=0;
	    	  for (int i=0;i<len;i++) {
	    		  int c =str.charAt(i)-'0';
	    		  if (c>=0 && c<=9) {
	    			  count++;
	    		  }
	    	  }
	    	
	    	  if (count>10) {
	    		  return false;
	    	  }
	    	  
	          RE phone = new RE("\\(?\\d\\d\\d\\)? *\\-? *\\d\\d\\d *\\-? *\\d\\d\\d\\d");
	          if (!phone.match(fieldValue)) {		    	  
		    	  return false;
		      } 
	          
	      } catch (Exception ex) {
	          ex.printStackTrace();
	          return false;
	      }
	      return true;
	  } 
	// ---------------IS Valid IP address----------------------
	  public  boolean isValidPostalCodeString(String fieldValue) {
		 
		  if (null==fieldValue) {
			 return false;
		  }
	      try {
	          RE postal = new RE("\\d\\d\\d\\d\\d(\\-\\d\\d\\d\\d)?");
	          if (!postal.match(fieldValue)) {		    	  
		    	  return false;
		      } 
	      } catch (Exception ex) {
	          ex.printStackTrace();
	          return false;
	      }
	      return true;
	  }
	//-------------- check valid IP address-------------------
	  private Pattern pattern;
	  private Matcher matcher;
	
	  private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
  
	 public boolean isValidIPAddress(final String ip){	
	  	  if (null==ip) {
	  		  return false;
	  	  }
	  	 
	  	  int l4=ip.indexOf("/");
	  	  int l6=ip.indexOf(":");
	  	  
	  	  if (l4!=-1 && l6!=-1 ) {  
	  		  return false;
	  	  }
	  	 if (l6==-1) {  // PV4
	  		 if (l4!=-1) {  // CIDR IP
	  			 return isValidCIDR(ip);
	  		 } else {  // normal ipv4
	  			 return isValidSimpleIP(ip);
	  		 }
	  	 } else {  // is IPv6
	  		 return isValidSimpleIPv6(ip);
	  	 }
 	  	   
    }
    public boolean isValidSimpleIPv6(final String ip) {
	  	  if (null==ip) {
	  		  return false;
	  	  }
	  	  String ipv6[] = ip.split("\\:");
	  	  if (null!=ipv6 || ipv6.length>1) {
	  		  return true;
	  	  }
	  	  return false;
    }
    
    public boolean isValidSimpleIP(final String ip){	
	  	  if (null==ip) {
	  		  return false;
	  	  }
	  	  
	  	  pattern = Pattern.compile(IPADDRESS_PATTERN);
		  matcher = pattern.matcher(ip);
		  return matcher.matches();	    	    
    }
    
    public boolean isValidCIDR(final String cidrIP) {
  	  
	  	  if (null==cidrIP) {
	  		  return false;
	  	  }
	  	  String cidr[] = cidrIP.split("/");
	     	  if (cidr.length!=2) {
	  		  return false;
	  	  }
	  	  //Check first part
	  	  if (!isValidSimpleIP(cidr[0])) {
	  		  return false;
	  	  }
	  	
	  	  try {
	  		  Integer dd=Integer.parseInt(cidr[1]);
	  		  if (dd==32 || dd==24 || dd==16 || dd==8) {
	  			  return true;
	  		  } else {
	  			  return false;
	  		  }
	  	  } catch (Exception e) {
	  		  return false;
	  	  }
	  	 
    }
    
	//-----------------IP Address is CIDR range Validation-----------------------
	public boolean checkIPCIDRRange(String ip, String CIDRIP) {
		 boolean retValue = false; 
		  if (isBlankString(ip)) {
		   	 return retValue;
		  }
		  if (isBlankString(CIDRIP)) {
			   	 return retValue;
		  }
		  
		  ip = rltrim(ip);
		  CIDRIP = rltrim(CIDRIP);
		  
		  if (! isValidSimpleIP(ip) || ! isValidCIDR(CIDRIP)) {
			  return false;
		  }
 		  
		  IPAddressUtils ipHandler = new IPAddressUtils();
		  ipHandler.calculate(CIDRIP);
			 /**
		  *  Found first IP and last IP for current CIDR IP
		  */
		  String lowIP = ipHandler.getInfo().getLowAddress(); 
		  String highIP = ipHandler.getInfo().getHighAddress();
		  int lowInt = ipHandler.toInteger(lowIP);
		  int highInt = ipHandler.toInteger(highIP);
		  ipHandler = new IPAddressUtils();
		  ipHandler.calculate(ip);
		  int ipInt = ipHandler.toInteger(ip);
		  if (ipInt>=lowInt && ipInt<=highInt) {
			  retValue = true;
		  } 
		  return retValue;
	 }

}
