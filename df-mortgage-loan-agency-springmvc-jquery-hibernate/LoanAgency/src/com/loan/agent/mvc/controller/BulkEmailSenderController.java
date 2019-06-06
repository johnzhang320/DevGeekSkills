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
 

public class BulkEmailSenderController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(BulkEmailSenderController.class);
	
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
			
	 	String gmailAddress = (String) request.getSession().getAttribute(Constant.AUTH_EMAIL_ADDRESS);
		String gmailPassword =(String) request.getSession().getAttribute(Constant.AUTH_PASSWORD);	  
		
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
		 request.getSession().setAttribute(Constant.PARAM_AGENT_REPLY_QUOTE, null);
		LOG.info("Email Marketing formBackingObject End....");
		return form;
		
	} 
	 
	
	public BulkEmailSenderController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
	
		LOG.info("Email Marketing Onsubmit begin....");
		 
		Utility.setRequest(request,response);
		
		EmailServerForm form = (EmailServerForm) command;	   	
		
		String emailSubject =(String) request.getSession().getAttribute(Constant.EMAIL_SUBJECT);	  
		
		if (!Utility.isEmpty(emailSubject)) {
			form.setSubject(emailSubject);
		}
	 	String gmailAddress = (String) request.getSession().getAttribute(Constant.AUTH_EMAIL_ADDRESS);
	 	if (!Utility.isEmpty(gmailAddress)) {
			
			if (Utility.isEmpty(form.getFromEmailAddress())) {
				form.setFromEmailAddress(gmailAddress);
			}
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
	    	ui.setSessionAttributeObject(Constant.ATTACHMENT_LIST, null);	 
	    	ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form);	
	    	request.getSession().setAttribute(Constant.CURRENT_UPLOADING_FILE, null);
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

        
        
        if (Constant.ACTION_SEND_EMAIL.equalsIgnoreCase(actionType)) {
        	
        	quoteDBService = (QuoteDBService) SpringFramework.getBean("QuoteDBService");
    		
        	
        	String sendEmailTo = form.getSendEmailTo();
        	if (Constant.ACTION_SEND_TO_MYSELF.equalsIgnoreCase(sendEmailTo)) {
   			 
    		    LOG.info("From Email Address is "+form.getFromEmailAddress());
    		    
    		    List<RecipientVo> rList = eUtil.generateRecipientVoList(form.getFromEmailAddress());
    		     		   
    		    
    		   if (ui.sendEmailForAgentWrap(request,form,rList,false)) {
    			   LOG.info("Send to myself successfully");
    			   
    		   } else {
    			   LOG.info("Send to myself failed!");
    		   }
    			 
    		} 
    		if (Constant.ACTION_SEND_TO_EMAIL_LIST.equalsIgnoreCase(sendEmailTo)) {
    			  
    			  List<RecipientVo> rList =(List<RecipientVo>) ui.getSessionAttributeObject(Constant.RECIPIENT_EMAIL_LIST);
    			  gmailAddress = (String) request.getSession().getAttribute(Constant.AUTH_EMAIL_ADDRESS);
  
    			  /**
    			   *  If send to email list, from email address must be agent email address
    			   */
    			  form.setFromEmailAddress(gmailAddress);
    			  
    			  if (ui.sendEmailForAgentWrap(request,form,rList,false)) {
    				   LOG.info("Send by emaillist successfully");
    				   
    			   } else {
    				   LOG.info("Send by emailList failed!");
    			   }
    			 
    		} 
        }
		
        LOG.info("form="+form);        
        request.getSession().setAttribute(Constant.PARAM_AGENT_REPLY_QUOTE, null);
    	
		request.getSession().setAttribute(Constant.EMAIL_SERVER_FORM, form);
		 
		ModelAndView mode = new ModelAndView(Constant.FORWARD_BULK_EMAIL_SENDER); 	
	    
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

