package com.loan.agent.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.services.QuoteDBService;
import org.apache.log4j.*;

public class AgentFormValidator implements Validator {
	Logger LOG = Logger.getLogger(AgentFormValidator.class);
	QuoteDBService quoteDBService;
	@Override
	public boolean supports(Class clazz) {
		//just validate the AgentForm instances
		return AgentForm.class.isAssignableFrom(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		AgentForm a = (AgentForm) target;
		String email = a.getEmailAddress();
		LOG.info("Email="+email);
	//	LOG.info("a.getAgentFormAction()="+a.getAgentFormAction()+",quoteDBService.isAgentEmailExists(email)="+quoteDBService.isAgentEmailExists(email));
		
		/*if ("login".equals(a.getAgentFormAction())) {
			if (!quoteDBService.isAgentEmailExists(email))
			{
				
				errors.rejectValue("emailAddress", "error.empty.field", "Email Address:" +email+" has NOT been registered!");
			}
		}
		if ("signup".equals(a.getAgentFormAction())) {
			if (quoteDBService.isAgentEmailExists(email))
			{
				errors.rejectValue("emailAddress", "error.empty.field", "Email Address:" +email+" has been registered, use another one!");
			}
		}
		if ("forget".equals(a.getAgentFormAction())) {
			if (!quoteDBService.isAgentEmailExists(email))
			{
				errors.rejectValue("emailAddress", "error.empty.field", "Email Address:" +email+" has NOT been registered!");
			}
		}	*/	
	}
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}
	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	 
	
}
