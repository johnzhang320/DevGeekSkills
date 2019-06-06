package com.locale.message.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

 
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.regexp.*;
 
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils; 
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


 
/**
 * 
 * We create ValidateHelp to use ValidationUtils.rejectIfEmtyOrWhitespace()
 * or errors.reject() by passing default message with arguments or argument message code for international.
 *  For required / invalid validation , we  dramatically simplify the validation
 *
 * @param errors
 */
public abstract class ValidateHelp {
	private static Logger LOG = Logger.getLogger(ValidateHelp.class);
	private  Map<String,String> fields = Collections.synchronizedMap(new HashMap<String,String>());
    private static final String REQUIRE_FIELD="required.field"; 
    private static final String INVALID_VALUE_FIELD="invalid.value.field";  // invalid.value.field ={0} is invalid {1}
	public abstract void initializeValidator(HttpServletRequest request);
	/**
	 * Called by initialValidator
	 * @param request : passed by initialValidator
	 */
	protected void initilize(HttpServletRequest request) {
		LocaleMsg.initProperties(request);
	}
    /**
     * addField by filling field name in form and fieldCode in application, call isAnyFieldsEmpty
     * @param errors
     * @return
     */
	public  void addField(String field,String fieldCode) {
		 
		fields.put(field, fieldCode);
	}
	public Errors isAnyFieldsEmpty(Errors errors) {
 		
	    synchronized (fields) {
	    	Iterator<String> itr = fields.keySet().iterator();
	    	while (itr.hasNext()) {
	    		String field = (String) itr.next();	    		     	    		 
	    		String fieldCode =(String) fields.get(field);	    	  
	    		String finalDefaultMessage = LocaleMsg.getMessageFromCode(REQUIRE_FIELD, fieldCode);	    	 
	    		// dummy.field.code doesn't exist, enforce rejectIfEmptyOrWhitespace use international finalDefaultMessage
	    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "dummy.field.code",  finalDefaultMessage);
	    	}
	     	fields.clear();
	    }
	    return errors;
	 }
	
    
	 public  boolean isValidEmail(String field,String fieldCode,Errors errors) {
		 String fieldValue=isEmpty(field,fieldCode,errors);
		 if (null==fieldValue) {
			 return false;
		 }
		  try {
		      RE email = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		    		  
		      if (!email.match(fieldValue)) {
		    	  processMessage(field,fieldCode, errors);  
		    	  return false;
		      } 
		   
		  } catch (Exception ex) {
		      ex.printStackTrace();
		      return false;
		  }
		   return true;
	  }
	  public  boolean isValidPhone(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
			 return false;
		  }
	      try {
	          RE phone = new RE("\\(?\\d\\d\\d\\)? *\\-? *\\d\\d\\d *\\-? *\\d\\d\\d\\d");
	          if (!phone.match(fieldValue)) {
		    	  processMessage(field,fieldCode, errors);  
		    	  return false;
		      } 
	          
	      } catch (Exception ex) {
	          ex.printStackTrace();
	          return false;
	      }
	      return true;
	  } 
	 public  boolean isValidPostalCode(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
			 return false;
		  }
	      try {
	          RE postal = new RE("\\d\\d\\d\\d\\d(\\-\\d\\d\\d\\d)?");
	          if (!postal.match(fieldValue)) {
		    	  processMessage(field,fieldCode, errors);  
		    	  return false;
		      } 
	      } catch (Exception ex) {
	          ex.printStackTrace();
	          return false;
	      }
	      return true;
	  }

	  public  boolean isDouble(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
				 return false;
		  }
	      try {
	          Double.parseDouble(fieldValue);
		      return true;
	      } catch (Exception ex) {
	    	  processMessage(field,fieldCode, errors);  
	    	  return false;
	    
	      }
	  }

	
	  public  String states[] = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE",
				       "DC","FM","FL","GA","GU","HI","ID","IL","IN",
				       "IA","KS","KY","LA","ME","MH","MD","MA","MI",
				       "MN","MS","MO","MT","NE","NV","NH","NJ","NM",
				       "NY","NC","ND","MP","OH","OK","OR","PW","PA",
				       "PR","RI","SC","SD","TN","TX","UT","VT","VI",
				       "VA","WA","WV","WI","WY"};

	  public  boolean isValidState(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
				 return false;
		  }
	      for (int i = 0; i < states.length; i++) {
	          if (states[i].equalsIgnoreCase(fieldValue)) return true;
	      }
	      
	      processMessage(field,fieldCode, errors);  
    	  return false;
	  }
	  
	  //------------------------------Low level utilities--------------------------------
	  public String isEmpty(String field,String fieldCode,Errors errors) {
			 String fieldValue =(String) errors.getFieldValue(field);		 
			  if (isBlankString(fieldValue)) {
				  return null;
			  }
			  return fieldValue;
		 }
		 
       public void processMessage(String field,String fieldCode,Errors errors) {
			 String fieldValue =(String) errors.getFieldValue(field);
			 String fieldLabel = LocaleMsg.getMessage(fieldCode);
		     String finalDefaultMessage = LocaleMsg.getMessage(INVALID_VALUE_FIELD, fieldValue, fieldLabel);
		     LOG.info("field="+field+",field value="+ fieldValue+",fieldLabel ="+fieldLabel+",finalDefaultMessage="+finalDefaultMessage);
		     errors.rejectValue(field, "dummy.field.code", null, finalDefaultMessage);
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
	  /**
	   *  Test   
	   */
/*	  public static void main(String[] args) {
		  ValidateHelp handler= new ValidateHelp (); 
		  String[] argCodes = {"label.email.address"};
		  System.out.println(handler.getCodeMessage("required.field",argCodes));
		  System.out.println(handler.getCodeMessage( "label.email.address"));
		  String email = "johnz148yahoo.com";
		   if (!handler.isValidEmail(email)) {
			  String emailLabel=handler.getCodeMessage( "label.email.address");
			  handler.addList(email);
			  handler.addList(emailLabel);
			  String value = handler.getLabelMessage("invalid.value.field"); 
			  System.out.println(value); 
		  } 
	  } */
}
