package com.loan.mvc.validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loan.agent.dao.Quote;
import com.loan.agent.mvc.formbean.QuoteForm;

 

public class LoanQuoteValidator implements Validator {
	@Override
	public boolean supports(Class clazz) {
		//just validate the User instances
		return QuoteForm.class.isAssignableFrom(clazz);
	}
	public void validatePage1Form(Object target, Errors errors) {
		//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
		//			"required.userName", "Field name is required.");
			// Quote q = (Quote) target; 
			// ValidateHelp.addLabelField("userName", "User Name", q.getLoanAmount());
			// ValidateHelp.SpringCheckEmptyLabel(errors);
	}
	public void validatePage2Form(Object target, Errors errors) {
		//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
		//			"required.userName", "Field name is required.");
			// Quote q = (Quote) target; 
		//	 ValidateHelp.addLabelField("userName", "User Name", q.getLoanAmount());
		//	 ValidateHelp.SpringCheckEmptyLabel(errors);
	}
	@Override
	public void validate(Object target, Errors errors) {
	//	validatePage1Form(target, errors);
	//	validatePage1Form(target, errors);
	}
}
