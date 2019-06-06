package com.loan.mvc.validator;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loan.agent.dao.Agents;
import com.loan.agent.mvc.controller.AgentController;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.eUtil;

 
public class EmailValidator implements Validator{
	static final Logger LOG = Logger.getLogger(EmailValidator.class);
	Integer agentId = null;
	 
	private AgentReviewService agentReviewService;

	public boolean supports(Class givenClass)
	{
	      return givenClass.equals(AgentForm.class);
	}
	
	public void validate(Object obj, Errors errors) {
		
		agentReviewService = (AgentReviewService)SpringFramework.getBean("AgentReviewService");
		
		AgentForm giveData = (AgentForm) obj;
		if (giveData==null) {
			 errors.reject("error.nullpointer", "Null data received");
		}
		
	
	 
		String emailAddress = giveData.getEmailAddress();		 	
		 
		agentId = agentReviewService.findAgentIdByEmail(emailAddress);
		
		LOG.info("did search by emailAddress agentId = " +agentId);
		
		if (agentId==null) {
			LOG.info("Not Found emailAddress="+emailAddress); 
			errors.rejectValue("emailAddress", "error.empty.field", "This Email Address has not been registered as an Agent!");
			return;
		}  
	}

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
