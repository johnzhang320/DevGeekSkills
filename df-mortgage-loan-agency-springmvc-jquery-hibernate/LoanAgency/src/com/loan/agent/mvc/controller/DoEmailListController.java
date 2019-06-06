package com.loan.agent.mvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 


import com.loan.agent.mvc.formbean.EmailListForm;

 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

 
public class DoEmailListController extends SimpleFormController {
Logger LOG = Logger.getLogger( DoEmailListController.class);
	
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
	
	@Autowired
	@Resource(name="AgentReviewService")
	private AgentReviewService agentReviewService;
		
	 
	public DoEmailListController() {
		setCommandName("emailListForm");
		setCommandClass(EmailListForm.class);
	}
	
/*	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	   // Utility.setRequest(request);
		 
	    LOG.info("DoEmailListController formBackingObject begin!");
		EmailListForm emailListForm = new EmailListForm();
		
		String sEmail = (String) request.getSession().getAttribute(Constant.EMAIL_LIST_FIELD);
		 
		if (sEmail!=null ) {
			emailListForm.setEmailList(sEmail);
			 	
		}
		LOG.info("DoEmailListController formBackingObject end!"); 
		return emailListForm;
		
	} 
	*/
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("DoEmailListController onSubmit() begin!");
		
		EmailListForm emailListForm =  (EmailListForm) command;
		
		ModelAndView mode = new ModelAndView("DoEmailList");
		
        String actiontype = emailListForm.getActionType();		
        
        LOG.info("DoEmailListController actiontype ="+actiontype); 
        
        if (Constant.UPLOAD_EMAIL_LIST_FILE.equalsIgnoreCase(actiontype)) {
        	
        	Files files = ProcessUploadFile.fileReaderOnly(Constant.UPLOAD_FILE_CONTENT, request);
        	if (files==null) {
        		return mode;
        	}
    		String contentType = files.getFileExt();
    		
    		LOG.info("contentType ="+contentType);
    		
    		if (ProcessUploadFile.isAllowedEmailFileType(contentType)) {
    			
    			 String emailList = files.getFile().toString();
    			 emailListForm.setEmailList(emailList);
    		}
    		
        }
        if (Constant.SAVE_EMAIL_LIST.equalsIgnoreCase(actiontype)) {
        	
        	String emailListField = emailListForm.getEmailList();
        	
        }
	 
		
		mode.addObject("EmailListForm",emailListForm);	
	 
		LOG.info("DoEmailListController onSubmit() end!"); 
		return mode;
		
	}
	 
}
