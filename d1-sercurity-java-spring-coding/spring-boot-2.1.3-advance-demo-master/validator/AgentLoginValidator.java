package com.loan.agent.mvc.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.basic.hibernate.dao.AgentTable;
import com.locale.message.utils.ValidateHelp;

@Service 
public class AgentLoginValidator extends ValidateHelp implements Validator {
    
	@Override 
	public boolean supports(Class givenClass)
	{
	      return givenClass.equals(AgentTable.class);
	}
    
	/**
	 * Called by submit form method
	 * public abstract void initializeValidator(HttpServletRequest request) in ValidateHelp;
	 */
	//@Override 
	public void initializeValidator(HttpServletRequest request) {
		initilize(request);
	}
	
	public void validate(Object obj, Errors errors) {
		 	
	      addField("emailAddress","label.email.address");
	      addField("password","label.password");	     
	      errors =isAnyFieldsEmpty(errors);	      
	      isValidEmail("emailAddress","label.email.address", errors);	
	     
	 }
}
