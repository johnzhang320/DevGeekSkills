package com.loan.agent.mvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 


import com.loan.agent.mvc.formbean.EmailServerForm;
 
 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 
import com.loan.agent.services.Constant;
import com.send.email.smtp.ssl.UpdateEnvelop;
 

 
public class UploadImageController extends SimpleFormController {
Logger LOG = Logger.getLogger( UploadImageController.class);
	 
	public UploadImageController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	   
		/**
		 *  GET form and initialize form
		 */
		
		EmailServerForm form = new EmailServerForm();
		ui.setRequest(request);
		form.setFilename(ui.getSessionAttribute(Constant.IMAGE_FILE_NAME));
		form.setFilesize(ui.getSessionAttribute(Constant.IMAGE_FILE_SIZE));		 
		return form;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
	
		
		LOG.info("UploadImageController onSubmit() begin!");
		EmailServerForm form =  (EmailServerForm) command;
		request.getSession().setAttribute(Constant.EMAILLIST_ERROR_MESSAGE,null);
		ui.setRequest(request);
		String actionType = form.getActionType();
		LOG.info("UploadImageController onSubmit() actionType="+actionType);
       
		ModelAndView mode = new ModelAndView("UploadImage");
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
	 
		if (Constant.UPLOAD_IMAGE.equalsIgnoreCase(actionType)) {
			Files files=  ProcessUploadFile.uploadCodedFile(
					agentId.toString(), 
					Constant.UPLOAD_FILE_CONTENT,Constant.PREFIX_AGENT_PICTURE_FILE,
					SysPath.getInstance().getAgentPicturePath(), 
					request );
			
			 
			if (files!=null) {
				form.setFilename(files.getFilename());
				form.setFilesize(files.getFilesize().toString());
				ui.setSessionAttribute(Constant.IMAGE_FILE_NAME, files.getFilename());
				ui.setSessionAttribute(Constant.IMAGE_FILE_TYPE, files.getType());
				ui.setSessionAttribute(Constant.IMAGE_FILE_SIZE, files.getFilesize().toString());
			}
			
		}
		
	    if (Constant.DELETE_IMAGE.equalsIgnoreCase(actionType)) {
	    	if (form.getFilename()==null || form.getFilename().trim().length()==0) {
	    		LOG.info("Can not delete picture file");
	    	}
	    		
	    	ProcessUploadFile.deleteAgentFile(agentId);
	    	ui.setSessionAttribute(Constant.IMAGE_FILE_NAME, null);
			ui.setSessionAttribute(Constant.IMAGE_FILE_TYPE, null);
			ui.setSessionAttribute(Constant.IMAGE_FILE_SIZE, null);
		}
	 
		LOG.info("UploadImageController onSubmit() End!");
		
		 
		
		mode.addObject("emailServerForm", form);
		 	 
		return mode; 
		
	}
	 
	 
}
