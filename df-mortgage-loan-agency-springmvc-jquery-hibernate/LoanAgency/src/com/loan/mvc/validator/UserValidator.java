package com.loan.mvc.validator;

 
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

 import com.loan.agent.mvc.formbean.LoginForm;
 
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.UserReviewService;

 
public class UserValidator implements Validator{
	static final Logger LOG = Logger.getLogger(UserValidator.class);
	Integer userId = null;
	 
	@Autowired
	@Resource(name="UserReviewService")
	private UserReviewService userReviewService;

	public boolean supports(Class givenClass)
	{
	      return givenClass.equals(LoginForm.class);
	}
	
	public void validate(Object obj, Errors errors) {
		
		//quoteDBService = (QuoteDBService)SpringFramework.getBean("QuoteDBService");
		
		LoginForm giveData = (LoginForm) obj;
		if (giveData==null) {
			 errors.reject("error.nullpointer", "Null data received");
		}
		
	
		
			String emailAddress = giveData.getEmailAddress();
			String password = giveData.getPassword();
			
			LOG.info("emailAddress="+emailAddress); 
			LOG.info("password="+password);
			 
			userId = userReviewService.findUserIdByEmail(emailAddress);
			if (userId==null) {
			 
				errors.rejectValue("emailAddress", "error.empty.field", "EmailAddress is not registered and try again or Signup!");
				return;
			} else {
				userId = userReviewService.findUserIdByEmailPassword(emailAddress, password);
				 
				if (userId==null) {
					errors.rejectValue("password", "error.empty.field", "Password is not correct!   try again  !");
					return;
				}
			}
			 
	 
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserReviewService getUserReviewService() {
		return userReviewService;
	}

	public void setUserReviewService(UserReviewService userReviewService) {
		this.userReviewService = userReviewService;
	}

	 
	
}
