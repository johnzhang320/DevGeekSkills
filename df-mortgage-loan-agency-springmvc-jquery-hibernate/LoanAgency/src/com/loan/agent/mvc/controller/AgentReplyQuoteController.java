package com.loan.agent.mvc.controller;

import java.io.File;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


import com.loan.agent.dao.Agents;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.mvc.formbean.RateSheetForm;
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 

import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;

import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.eUtil;
 

public class AgentReplyQuoteController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(AgentReplyQuoteController.class);
	
 	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
	@Autowired
	@Resource(name="AgentReviewService")
	private AgentReviewService agentReviewService;
		
	 
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("Email Marketing formBackingObject Begin....");
		Utility.setParamAgentId(request);
		 ui.setRequest(request);
		 request.getSession().setAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS, null);
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		
	 	String gmailAddress = (String) request.getSession().getAttribute(Constant.LOGIN_AGENT_EMAIL);
		String gmailPassword =(String) request.getSession().getAttribute(Constant.LOGIN_AGENT_PASSWORD);	  
		
		String emailSubject =(String) request.getSession().getAttribute(Constant.EMAIL_SUBJECT);	  
		   
	    EmailServerForm form = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);
	    
	    if (form==null) {
	    	form = new EmailServerForm();
	    	
	    	// set defualt value for radio button sendEmailTo
	    	ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, null);
	    	
	    }
	    
	   	form.setSendEmailTo("emailList");
	    
	   	
	   	
	    ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, null);
	    
		if (form==null) {			 
			form.setConnectionStatus("notSetup");
		} else {
		 
			String connStatus = form.getEsConnectionStatus();
			 
			if (connStatus==null) {
				form.setConnectionStatus("notSetup");
			} else {
				form.setConnectionStatus(connStatus);
			}
		}
			
		if (!Utility.isEmpty(gmailAddress) && !Utility.isEmpty(gmailPassword)) {
			form.setConnectionStatus("OK");
			/**
			 *  This system is using agnet gmail address and password to login smtp.gmail.com server and use same email as fromHeaderEmail
			 */
			if (Utility.isEmpty(form.getFromEmailAddress())) {
				form.setFromEmailAddress(gmailAddress);
			}
			
			
			form.setEmailAddress(gmailAddress);
			form.setPassword(gmailPassword);		
			
			ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form);		
			ui.setSessionAttribute(Constant.AUTH_EMAIL_ADDRESS, form.getEmailAddress());
			ui.setSessionAttribute(Constant.AUTH_PASSWORD, form.getPassword());
			request.getSession().setAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS,"OK");
			
		}
		if (!Utility.isEmpty(emailSubject)) {
			form.setSubject(emailSubject);
		}
		String toEmailAddress=(String) request.getSession().getAttribute(Constant.TO_EMAIL_ADDRESS);
		if (!Utility.isEmpty(toEmailAddress)) {
			form.setToEmailAddress(toEmailAddress);
		}
		LOG.info("Email Marketing formBackingObject End....");
		return form;
		
	} 
	 
	
	public AgentReplyQuoteController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
	
		LOG.info("Email Marketing Onsubmit begin....");
		 
		Utility.setRequest(request,response);
		
		Integer currentAgentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		
		EmailServerForm form = (EmailServerForm) command;	   	
		
		String toEmailAddress=(String) request.getSession().getAttribute(Constant.TO_EMAIL_ADDRESS);
		if (!Utility.isEmpty(toEmailAddress)) {
			form.setToEmailAddress(toEmailAddress);
		}
		
		String emailSubject =(String) request.getSession().getAttribute(Constant.EMAIL_SUBJECT);	  
		
		if (!Utility.isEmpty(emailSubject)) {
			form.setSubject(emailSubject);
		}
	 	String gmailAddress = (String) request.getSession().getAttribute(Constant.LOGIN_AGENT_EMAIL);
	 	if (!Utility.isEmpty(gmailAddress)) {
			
			if (Utility.isEmpty(form.getFromEmailAddress())) {
				form.setFromEmailAddress(gmailAddress);
			}
	 	}
		if (currentAgentId==null) {
			ModelAndView mode = new ModelAndView("EmailMarketing"); 	
			mode.addObject(Constant.EMAIL_SERVER_FORM, form);		
			LOG.info("Email Marketing Onsubmit End bacuase current agent id NOT Found!"); 
			return mode; 
		}
		
		ui.setSessionAttribute(Constant.EMAIL_SEND_STATUS, null);
		String actionType = form.getActionType();
		
	    if (Constant.ACTION_CLEAN_EMAIL.equalsIgnoreCase(actionType)) {
	    	
	    	form.setEmailContent("");
	    	form.setSubject("");
	    	form.setAttachment1("");
	    	form.setAttachment2("");
	    	form.setAttachment3("");
	    	form.setAttachment4("");
	    	form.setAttachment5("");
	    	form.setEmailList("");
	    	form.setFromEmailAddress("");
	    	ui.setSessionAttributeObject(Constant.ATTACHMENT_LIST, null);	 
	    	ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form);	
	    	request.getSession().setAttribute(Constant.EMAIL_LIST_FIELD,null);
		}	
		if (Constant.ACTION_UPLOAD_EMAIL_CONTENT.equalsIgnoreCase(actionType)) {
			
			Files files=ProcessUploadFile.emailContentReadOnly(Constant.UPLOAD_FILE_CONTENT,request);	 
			
			form.setEmailContent(ui.deCodeHTML(files.getAssciContent()));
		}
		
        if (Constant.ACTION_DOWNLOAD_EMAIL_CONTENT.equalsIgnoreCase(actionType)) {
        	
			if (form.getEmailContent()!=null ) {
				LOG.info("Download begin");
				ProcessDownloadFile.downloadEmailContent(response, "text/html", form.getEmailContent().getBytes(),form.getSubject());
				LOG.info("Download end");
			}
			
		}

        if (Constant.ACTION_GENERATE_AGENT_EMAIL.equalsIgnoreCase(actionType)) {
        	/**
        	 *  RequestFilter only recognized the encode agentId
        	 */
        	String encodedAgentId = ui.getEncodedString(currentAgentId.toString());
        	
        	String targetURL = SysPath.getHostName()+"/"+Constant.AGENT_EMAIL_HTML+"?"
        	+Constant.PARAM_AGENT_ID+"="+encodedAgentId+"&"+Constant.PULL_ALL_AGENT_DATA+"=yes";

        	
        	String paramEmail = form.getFromEmailAddress();
        	LOG.info(" paramEmail ="+ paramEmail ); 
        	if (paramEmail!=null) {
        		targetURL =targetURL+"&paramMyEmail="+paramEmail;
        	}
        	
            /**
             *  Post Agent Id first and get Response from 
             */
        	 LOG.info("targetURL ="+targetURL);
        	 String emailContent = ui.getEmailContentFromURL(targetURL);
        	 LOG.info("emailContent ="+emailContent);
         
        	form.setEmailContent(ui.deCodeHTML(emailContent));
        }
        
        if (Constant.ACTION_SEND_EMAIL.equalsIgnoreCase(actionType)) {
        	
        	quoteDBService = (QuoteDBService) SpringFramework.getBean("QuoteDBService");
    		Integer agentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID);
    		//agentId = 10007;
    		Agents agents = null;
    		if (agentId!=null) {
    			agents = quoteDBService.getAgentsDAO().findById(agentId);
    			 
    		}  
        	
        	String sendEmailTo = form.getSendEmailTo();
        	if (Constant.ACTION_SEND_TO_MYSELF.equalsIgnoreCase(sendEmailTo)) {
   			 
    		  
    			 
    		} 
    		if (Constant.ACTION_SEND_TO_EMAIL_LIST.equalsIgnoreCase(sendEmailTo)) {
    			  
    			LOG.info("TO Email Address is "+form.getToEmailAddress());
      		    
      		    List<RecipientVo> rList = eUtil.generateRecipientVoList(form.getToEmailAddress());
      		     		   
      		    
      		   if (ui.sendEmailForAgentWrap(request,agents,form,rList,false)) {
      			   LOG.info("Send to myself successfully");
      			   
      		   } else {
      			   LOG.info("Send to myself failed!");
      		   }
    			 
    		} 
        }
		
        LOG.info("form="+form);        
        request.getSession().setAttribute(Constant.PARAM_AGENT_REPLY_QUOTE, null);
    	
		request.getSession().setAttribute(Constant.EMAIL_SERVER_FORM, form);
		 
		ModelAndView mode = new ModelAndView("AgentReplyQuote"); 	
	    
		mode.addObject(Constant.EMAIL_SERVER_FORM, form);		
		
		LOG.info("Email Marketing Onsubmit End...."); 
		return mode; 
		 
	}
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}
	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	public AgentReviewService getAgentReviewService() {
		return agentReviewService;
	}
	public void setAgentReviewService(AgentReviewService agentReviewService) {
		this.agentReviewService = agentReviewService;
	}
	
} 

