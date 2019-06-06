package com.loan.agent.mvc.validator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loan.agent.mvc.formbean.AgentTable;
import com.locale.message.utils.ValidateHelp;

/**
 * We define two types of property files for International language environment:
 * (1) message_xx.properties ----- define validation message
 * (2) application_xx.propertie -- define form field labels
 * Instead of explicitly defining:
 * Email is required
 * Full Name is required
 * Email is invalid
 * Phone number is invalid
 * We define:
 * required.field ={0} is required
 * invalid.value.field ={0} is invalid {1}
 * invalid.field ={0} is invalid 
 * .....
 * 
 * Using arguments is not only able to compress message_xx.properties but only is necessary for international environment.
 * Such as Email: johnzhang2133yahoo.com is invalid, if email is Chinese or France, we have to use argument {0}
 * Unfortunately, Spring MVC does not provide such argument filling mechanism, ValidationUtils.rejectIfEmtyOrWhitespace()
 * or errors.reject() have message code interface and have no argument interface.
 * 
 * We create ValidateHelp to use ValidationUtils.rejectIfEmtyOrWhitespace()
 * or errors.reject() with arguments. For required / invalid validation , we 
 * dramatically simplify the validation
 *
 * @param errors
 */

public class AgentSignupValidator extends ValidateHelp implements Validator {
    
	@Override 
	public boolean supports(Class givenClass)
	{
	      return givenClass.equals(AgentTable.class);
	}
    
	/**
	 * Called by submit form method
	 * public abstract void initializeValidator(HttpServletRequest request) in ValidateHelp;
	 */
	 @Override 
	 public void initializeValidator(HttpServletRequest request) {
		initilize(request);
	 }
	
	public void validate(Object obj, Errors errors) {
		 	
	      addField("emailAddress","label.email.address");
	      addField("password","label.password");
	      addField("firstName","label.firstname");
	      addField("dreNo","label.license.no");	 
	      addField("companyName","label.company.name");
	      addField("workPhone","label.work.phone");
	      addField("state","label.state"); 	
	      errors =isAnyFieldsEmpty(errors);
	      
	      isValidEmail("emailAddress","label.email.address", errors);
	      isValidPhone("workPhone","label.work.phone",errors);
	      isValidState("state","label.state",errors);
	     
	 }
}
