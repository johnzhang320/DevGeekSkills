package com.loan.mvc.validator;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.regexp.*;
 

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidateHelp {
	private static Logger LOG = Logger.getLogger(ValidateHelp.class);
	private static Map<String,String> fields = Collections.synchronizedMap(new HashMap<String,String>());
	private boolean valid=true;
	private static Map<String,pair> labelFields = Collections.synchronizedMap(new HashMap<String,pair>());

	public static void addField(String field,String value) {
		 
		fields.put(field, value);
	}
	class pair {
		public String label;
		public String value;
		public pair(String label, String value) {
			super();
			this.label = label;
			this.value = value;
		}
		
	}
	public static void addLabelField(String field,String label,String value) {
		ValidateHelp vp = new ValidateHelp(); 
		pair p = vp.new pair(label,value);
		labelFields.put(field, p);
	}
	
	
	
	
	
	public static void SpringCheckEmpty(Errors errors) {
		 
		 
	    synchronized (fields) {
	    	Iterator<String> itr = fields.keySet().iterator();
	    	while (itr.hasNext()) {
	    		String key = (String) itr.next();
	    		//LOG.info("Check if "+key+" is empty!");
	    		String value =(String) fields.get(key);
	    		if (value==null || value.length()<1) {
	    			LOG.info(key+" is required!");
	    				    			
	    			errors.rejectValue(key, "error.empty.field", key +" is required");
	    		}  
	    	}
	     	fields.clear();
	    }
	 }
	public static void SpringCheckEmptyLabel(Errors errors) {
				 
	    synchronized (labelFields) {
	    	Iterator<String> itr = labelFields.keySet().iterator();
	    	while (itr.hasNext()) {
	    		String key = (String) itr.next();
	    		//LOG.info("Check if "+key+" is empty!");
	    		pair p =(pair) labelFields.get(key);
	    		
	    		if (p.value==null || p.value.length()<1) {
	    			LOG.info(key+" is required!");
	    		
	    			
	    			errors.rejectValue(key, "error.empty.field", p.label +" is required");
	    		}  else {
	    			if (key.indexOf("email")!=-1) {
	    				 if (! ValidateHelp.isValidEmail(p.value))
	    				     errors.rejectValue(key, "Email Address", p.value+" is invalid email address!");
	    			}
	    			if (key.indexOf("phone")!=-1) {
	    				 if (! ValidateHelp.isValidPhone(p.value))
	    				     errors.rejectValue(key, "Phone Number", p.value+" is invalid phone numner!");
	    			}
	    			if (key.indexOf("state")!=-1) {
	    				 if (! ValidateHelp.isValidState(p.value))
	    				     errors.rejectValue(key, "State Code", p.value+" is invalid state code!");
	    			}

	    		}
	    	}
	    	pair password = (pair) labelFields.get("password");
	    	pair confirmPassword = (pair) labelFields.get("confirmPassword");
	    	 
	    	if (confirmPassword==null)  {
	    		 confirmPassword = (pair) labelFields.get("confirm_password");
	    	}
	    	if (confirmPassword==null)  {
	    		 confirmPassword = (pair) labelFields.get("confirmed_password");
	    	}
	    	if (confirmPassword==null)  {
	    		 confirmPassword = (pair) labelFields.get("confirmedPassword");
	    	}
	    	if (password!=null && confirmPassword!=null) {
	    		if (!password.value.equals(confirmPassword.value)) {
				     errors.rejectValue("password", "Password", "Password Not Matched!");
	    		}
	    	}
	     	labelFields.clear();
	    }
	 }
	 	 
	public static boolean  isBlankString(String str) {
	      if (str == null) return true;
	      return (str.length() == 0);
	  }

	  public static boolean isValidPostalCode(String str) {
	  try {
	      RE postal = new RE("\\d\\d\\d\\d\\d(\\-\\d\\d\\d\\d)?");
	      return (postal.match(str));
	  } catch (Exception ex) {
	      ex.printStackTrace();
	      return false;
	  }
	  }

	  public static boolean isDouble(String str) {
	  try {
	      Double.parseDouble(str);
	      return true;
	  } catch (Exception ex) {
	      return false;
	    }
	  }

	  public static boolean isValidPhone(String str) {
	  try {
	      RE phone = new RE("\\(?\\d\\d\\d\\)? *\\-? *\\d\\d\\d *\\-? *\\d\\d\\d\\d");
	      return (phone.match(str));
	  } catch (Exception ex) {
	      ex.printStackTrace();
	      return false;
	  }
	  }
	  public static boolean isValidEmail(String str) {
		  try {
		      RE email = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		    		  
		      return (email.match(str));
		  } catch (Exception ex) {
		      ex.printStackTrace();
		      return false;
		  }
	  }
	  public static String states[] = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE",
				       "DC","FM","FL","GA","GU","HI","ID","IL","IN",
				       "IA","KS","KY","LA","ME","MH","MD","MA","MI",
				       "MN","MS","MO","MT","NE","NV","NH","NJ","NM",
				       "NY","NC","ND","MP","OH","OK","OR","PW","PA",
				       "PR","RI","SC","SD","TN","TX","UT","VT","VI",
				       "VA","WA","WV","WI","WY"};

	  public static boolean isValidState(String str) {
	    for (int i = 0; i < states.length; i++) {
	      if (states[i].equalsIgnoreCase(str)) return true;
	    }
	    return false;
	  }
	  
	  public static Long getSimpleDateForm(String date) {
		  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		  Long dateAsLong = null;
		  try {
			  	dateAsLong =sdf.parse(date).getTime();
		  } catch (ParseException e) {
			 LOG.error("Date Format "+date +" is wrong!"); 
		  }
		  return dateAsLong;
	  
	  }
	  
	  public static Date ConvertToDate(String date) {
		  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		  Date date2 = null;
		  try {
			  	date2 =sdf.parse(date);
		  } catch (ParseException e) {
			 LOG.error("Date Format "+date +" is wrong!"); 
		  }
		  return date2;
	  
	  }
	  public static Date myConvertToDate(String month_year) {
		  SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		  Date date2 = null;
		  try {
			    
			  	date2 =sdf.parse(month_year);
		  } catch (ParseException e) {
			 LOG.error("Date Format "+month_year +" is wrong!"); 
		  }
		  return date2;
	  
	  }
	    
}
