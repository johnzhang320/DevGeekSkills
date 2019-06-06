package com.locale.message.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.regexp.*;
 
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils; 
 
/**
 * 
 * We create ValidateHelp to use ValidationUtils.rejectIfEmtyOrWhitespace()
 * or errors.reject() by passing default message with arguments or argument message code for international.
 *  For required / invalid validation , we  dramatically simplify the validation
 *
 * @param errors
 */
public  class ValidateHelp extends ValidateUtils {
	private static Logger LOG = Logger.getLogger(ValidateHelp.class);
	private  Map<String,String> fields = Collections.synchronizedMap(new HashMap<String,String>());
    private static final String REQUIRE_FIELD="required.field"; 
    private static final String STOP_XSS_ATTACK="stop.xss.attack"; 
    
    private static final String INVALID_VALUE_FIELD="invalid.value.field";  // invalid.value.field ={0} is invalid {1}
	//public abstract void initializeValidator(HttpServletRequest request);
	/**
	 * Called by initialValidator
	 * @param request : passed by initialValidator
	 */
    public ValidateHelp() {}
    public ValidateHelp(boolean mock) {
    	LocaleMsg.mockInitProperties();
    }
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
	    		// prevent XSS attack 
	    		isXSSAttack(field,fieldCode,errors);
	    	}
	     	fields.clear();
	    }
	    return errors;
	 }
    
	
	 //-----------------Eliminate XSS atack -----------------------
	 public  boolean isXSSAttack(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
			 return false;
		  } else {
			  fieldValue = fieldValue.toLowerCase();			 
			  if(findXSSTagPair(fieldValue)) {				 
				 String fieldLabel = LocaleMsg.getMessage(fieldCode);
			     String finalDefaultMessage = LocaleMsg.getMessage(STOP_XSS_ATTACK, fieldLabel);
			     errors.rejectValue(field, "dummy.field.code", null, finalDefaultMessage);
				 return true;
			  }
		  }
		 return false;
	 }
	
	 public  boolean isValidEmail(String field,String fieldCode,Errors errors) {
		 String fieldValue=isEmpty(field,fieldCode,errors);
		 if (null==fieldValue) {
			 return false;
		 }
		 if (!isValidEmailString(fieldValue)) {
			 processMessage(field,fieldCode, errors);  
	    	 return false;
		 }
		 return true;
	  }
	  
	  public  boolean isValidPhone(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
			 return false;
		  }
		  if (!isValidPhoneString(fieldValue)) {
				 processMessage(field,fieldCode, errors);  
		    	 return false;
		  }
	      return true;
	  } 
	 public  boolean isValidPostalCode(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
			 return false;
		  }
		  if(!isValidPostalCodeString(fieldValue)) {
			 processMessage(field,fieldCode, errors);  
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
	  
	  // ---------------check if valid IP address ---------------------
	  public  boolean isValidIP(String field,String fieldCode,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
				 return false;
		  }
		  LOG.info("current IP fieldValue="+fieldValue+",Field="+field+",FieldCode = "+fieldCode);
			
	      if(!isValidIPAddress(fieldValue)) {
	    	  LOG.info("Invalid IP fieldValue="+fieldValue+",Field="+field+",FieldCode = "+fieldCode);
		 	  processMessage(field,fieldCode, errors);  
	    	  return false;
	      }
	    	
	     return true;	
	  }
	  public  boolean isValidIPRange(String field,String fieldCode,String CIDRIP,Errors errors) {
		  String fieldValue=isEmpty(field,fieldCode,errors);
		  if (null==fieldValue) {
				 return false;
		  }	
		 
		  if (!checkIPCIDRRange(fieldValue, CIDRIP)) {			  
		      String finalDefaultMessage = LocaleMsg.getMessage("label.ip.address.range",CIDRIP);
		      errors.rejectValue(field, "dummy.field.code", null, finalDefaultMessage);
			  
			  return false;
		  }
		  return true;
	  }
	  public void processMessage(String field,String fieldCode,Errors errors) {		  
		 String fieldValue =(String) errors.getFieldValue(field);		 
		 String fieldLabel = LocaleMsg.getMessage(fieldCode);
		 LOG.info("fieldValue="+fieldValue+",fieldLabel="+fieldLabel);
		 String finalDefaultMessage = null;
		 
		
		 if (null==fieldLabel && null==fieldValue) {
		     finalDefaultMessage = LocaleMsg.getMessage("invalid.field.input");
		 } 
		 if (null==fieldLabel && null!=fieldValue ) {
			 finalDefaultMessage = LocaleMsg.getMessage("invalid.field",fieldValue);
		 }  
		 if (null!=fieldLabel && null==fieldValue ) {
			finalDefaultMessage = LocaleMsg.getMessage("invalid.field",fieldLabel);
		 }  
		 if (null!=fieldLabel && null!=fieldValue ) {
			  finalDefaultMessage = LocaleMsg.getMessage(INVALID_VALUE_FIELD, fieldValue, fieldLabel);
		 }  
	   
	     errors.rejectValue(field, "dummy.field.code", null, finalDefaultMessage);
	  }
}
