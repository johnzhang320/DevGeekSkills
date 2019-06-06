package com.loan.agent.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;

import com.loan.agent.mvc.formbean.EmailServerForm;
 
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.SendHTMLAttachEmails;
 
public class ConfigureEmailServerController extends SimpleFormController {
Logger LOG = Logger.getLogger( ConfigureEmailServerController.class);
	
	 
	 
	public ConfigureEmailServerController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	   ui.setRequest(request);
		/**
		 *  GET form and initialize form
		 */
	  
	   EmailServerForm serverForm = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);
	    if (serverForm ==null) {
		   serverForm = new EmailServerForm();
	    }  
	    String email = ServletRequestUtils.getStringParameter(request,Constant.EMAIL_ADDRESS);
	    	
	    String password = ServletRequestUtils.getStringParameter(request,Constant.PASSWORD);
	    
	    serverForm.setEmailAddress(email);	
	    serverForm.setPassword(password);	 
		 
		return serverForm;
		
	} 
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		LOG.info("ConfigureEmailServer onSubmit begin");
		ui.setRequest(request);
		
		EmailServerForm form =  (EmailServerForm) command;
		
		String email = form.getEmailAddress();
		
		String password= form.getPassword();
		
		LOG.info("email="+email+",password="+password);		 
	 
		SendHTMLAttachEmails handler = new SendHTMLAttachEmails(email,password);
		
		String esConnectionStatus = handler.getEmailServerConnectionStatus();
		
		form.setEsConnectionStatus(esConnectionStatus);
		LOG.info("Email Server Connection Status="+esConnectionStatus);
		
		if (!"OK".equalsIgnoreCase(esConnectionStatus)) {
			//form.setEmailAddress(null);
			//form.setPassword(null);
			 
		}
		ui.setRequest(request);
		
		ui.setSessionAttribute(Constant.AUTH_EMAIL_ADDRESS, form.getEmailAddress());
		ui.setSessionAttribute(Constant.AUTH_PASSWORD, form.getPassword());
		
		
		ui.setSessionAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS, esConnectionStatus);
	   
		ModelAndView mode=new ModelAndView(Constant.FORWARD_CONFIGURE_EMAIL_SERVER);
		
		mode.addObject(Constant.EMAIL_SERVER_FORM,form);		
		 
		 ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form);
		 
		LOG.info("ConfigureEmailServer onSubmit end");
	 	 
		return mode;
		
	}
	 
}
