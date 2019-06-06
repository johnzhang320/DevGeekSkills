package com.loan.mvc.validator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.frontend.encrypt.utils.KeyPairManager;
import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.controller.AgentController;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

 
public class AgentValidator implements Validator{
	static final Logger LOG = Logger.getLogger(AgentValidator.class);
	Integer agentId = null;
	 
	private AgentReviewService agentReviewService;

	public boolean supports(Class givenClass)
	{
	      return givenClass.equals(AgentForm.class);
	}
	
	public void validate(Object obj,  Errors errors) {
	    agentReviewService = (AgentReviewService)SpringFramework.getBean("AgentReviewService");
	    
		KeyPairManager.getInstance();
		
		AgentForm giveData = (AgentForm) obj;
		if (giveData==null) {
			 errors.reject("error.nullpointer", "Null data received");
			 return ;
		}
		
		String emailAddress = giveData.getEmailAddress();
		String password = giveData.getPassword();
			 
		//this.agentId = agentReviewService.findAgentIdByEmailPassword(emailAddress, password);
		agentId = agentReviewService.findAgentIdByEmail(emailAddress);
		LOG.info("did search by emailAddress agentId = " +agentId);
		if (agentId==null) {
			LOG.info("Not Found emailAddress="+emailAddress); 
			errors.rejectValue("emailAddress", "error.empty.field", "This EmailAddress has not been registered and try again or Signup!");
			 return ;
		} else {
			LOG.info("now search by emailAddress ="+emailAddress+", password="+password); 
			agentId = agentReviewService.findAgentIdByEmailPassword(emailAddress, password);
			if (agentId==null) {
				LOG.info("Not Found password="+password);
				errors.rejectValue("password", "error.empty.field", "Password is not correct!   try again  !");
				 return ;
			}
		}
			 return;
	}
		
	
/*	public boolean validate(Object obj, HttpServletRequest request, Errors errors) {
		
		agentReviewService = (AgentReviewService)SpringFramework.getBean("AgentReviewService");
		
		AgentForm giveData = (AgentForm) obj;
		if (giveData==null) {
			 errors.reject("error.nullpointer", "Null data received");
			 return false;
		}
		
			String emailAddress = giveData.getEmailAddress();
			String encryptedPassword = giveData.getPassword();
			LOG.info("emailAddress="+emailAddress+",password="+encryptedPassword); 
			String password= DecryptUtil.decryptWebString(request, encryptedPassword);
			request.setAttribute(Constant.PASSWORD, password);
			//this.agentId = agentReviewService.findAgentIdByEmailPassword(emailAddress, password);
			agentId = agentReviewService.findAgentIdByEmail(emailAddress);
			LOG.info("did search by emailAddress agentId = " +agentId);
			if (agentId==null) {
				LOG.info("Not Found emailAddress="+emailAddress); 
				errors.rejectValue("emailAddress", "error.empty.field", "This EmailAddress has not been registered and try again or Signup!");
				 return false;
			} else {
				LOG.info("now search by emailAddress ="+emailAddress+",password="+password); 
				agentId = agentReviewService.findAgentIdByEmailPassword(emailAddress, password);
				if (agentId==null) {
					LOG.info("Not Found password="+password);
					errors.rejectValue("password", "error.empty.field", "Password is not correct!   try again  !");
					 return false;
				}
				
			}
			 return true;
	//	}
		
	}*/

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public AgentReviewService getAgentReviewService() {
		return agentReviewService;
	}

	public void setAgentReviewService(AgentReviewService agentReviewService) {
		this.agentReviewService = agentReviewService;
	}
	
}
