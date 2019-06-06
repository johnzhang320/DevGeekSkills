package com.loan.agent.mvc.controller;

import java.io.File;
import java.util.ArrayList;
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
 

 
public class UploadAttachmentController extends SimpleFormController {
Logger LOG = Logger.getLogger( UploadAttachmentController.class);
	 
	public UploadAttachmentController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	   
		/**
		 *  GET form and initialize form
		 */
		ui.setRequest(request);
		EmailServerForm form = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);
		if (form==null) {
		  form = new EmailServerForm();
		}
		request.getSession().setAttribute(Constant.EMAILLIST_ERROR_MESSAGE,null); 
			 
		return form;
		
	}  
	
	
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		//Integer agent_id = 10007;
		
		LOG.info("UploadAttachmentController onSubmit() begin!");
		EmailServerForm form =  (EmailServerForm) command;
		request.getSession().setAttribute(Constant.EMAILLIST_ERROR_MESSAGE,null);
		LOG.info("UploadAttachmentController onSubmit() actionType="+form.getActionType());

		ModelAndView mode = new ModelAndView("UploadAttachment");
	
		String destPath = SysPath.getInstance().getAgentEmailAttachmentPath();
		 
		 
	
		
		String [] actionTypes = form.getActionType().split("-");
		
		if ("upload".equalsIgnoreCase(actionTypes[0])) {
			if (Constant.ATTACHMENT1.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment1());
				Files file=ProcessUploadFile.WriterOriginalFileNameToServer("fileContent1", destPath, request, false);
				form.setAttachment1(file.getFilename());				 
			}
			if (Constant.ATTACHMENT2.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment2());
				Files file=ProcessUploadFile.WriterOriginalFileNameToServer("fileContent2", destPath, request, false);
				form.setAttachment2(file.getFilename());				 
			}
			if (Constant.ATTACHMENT3.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment3());
				Files file=ProcessUploadFile.WriterOriginalFileNameToServer("fileContent3", destPath, request, false);
				form.setAttachment3(file.getFilename());				 
			}
			if (Constant.ATTACHMENT4.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment4());
				Files file=ProcessUploadFile.WriterOriginalFileNameToServer("fileContent4", destPath, request, false);
				form.setAttachment4(file.getFilename());				 
			}
			if (Constant.ATTACHMENT5.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment5());
				Files file=ProcessUploadFile.WriterOriginalFileNameToServer("fileContent5", destPath, request, false);
				form.setAttachment5(file.getFilename());				 
			}		
			
		}
			
			
		if ("delete".equalsIgnoreCase(actionTypes[0])) {
			if (Constant.ATTACHMENT1.equalsIgnoreCase(actionTypes[1])) {				 
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment1());
				form.setAttachment1(null);				 
			}
			if (Constant.ATTACHMENT2.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment2());
				form.setAttachment2(null);			 
			}
			if (Constant.ATTACHMENT3.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment3());
				form.setAttachment3(null);				 
			}
			if (Constant.ATTACHMENT4.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment4());
				form.setAttachment4(null);				 
			}
			if (Constant.ATTACHMENT5.equalsIgnoreCase(actionTypes[1])) {
				ProcessUploadFile.deleteAttachmentFile(destPath,form.getAttachment5());
				form.setAttachment5(null);				 
			}
		}
		
		 
		ui.setRequest(request);
		ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM, form);		
		
		List<String> list = new ArrayList<String>(); 
		
		if (ui.isNotEmpty(form.getAttachment1())) {
		   list.add(form.getAttachment1());
		}
		if (ui.isNotEmpty(form.getAttachment2())) {
			   list.add(form.getAttachment2());
		}
		if (ui.isNotEmpty(form.getAttachment3())) {
			   list.add(form.getAttachment3());
		}
		if (ui.isNotEmpty(form.getAttachment4())) {
			   list.add(form.getAttachment4());
		}
		if (ui.isNotEmpty(form.getAttachment5())) {
			   list.add(form.getAttachment5());
		}
		if (list.size()==0) {
			ui.setSessionAttributeObject(Constant.ATTACHMENT_LIST, null);	
		} else {
			ui.setSessionAttributeObject(Constant.ATTACHMENT_LIST, list);	
		}
		
		LOG.info("UploadAttachmentController onSubmit() End!");	
		 
		
		mode.addObject(Constant.EMAIL_SERVER_FORM, form);
		 	 
		return mode; 
		
	}
	 
	 
}
