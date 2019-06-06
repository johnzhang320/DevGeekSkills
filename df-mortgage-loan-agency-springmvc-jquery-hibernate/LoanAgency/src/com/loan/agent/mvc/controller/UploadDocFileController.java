package com.loan.agent.mvc.controller;

import java.io.InputStream;
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

 


import com.email.list.reader.EmailDynamicVO;
import com.email.list.reader.EmailListFactory;
import com.email.list.reader.EmailListService;
import com.email.list.reader.ReadExcelEmailList;
import com.email.list.reader.SimpleEmailLineVO;
import com.loan.agent.calculators.vo.SelectedValueVo;
import com.loan.agent.mvc.formbean.EmailServerForm;
 
 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 
import com.loan.agent.services.Constant;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.UpdateEnvelop;
 

 
public class UploadDocFileController extends SimpleFormController {
Logger LOG = Logger.getLogger( UploadDocFileController.class);
private static final boolean PRE_LOAD_EMAIL_LIST=true;
private static final boolean LOAD_EMAIL_LIST=false;
private static final int DUMMY_PTR=-1;
	public UploadDocFileController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("UploadDocFileController formBackingObject begin!");
	 
		ui.setRequest(request);
		/** 
		 *  Get initialized Form
		 */
		request.getSession().setAttribute(Constant.EMAILLIST_ERROR_MESSAGE,null);
		EmailServerForm serverForm = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);
	    if (serverForm ==null) {
		   serverForm = new EmailServerForm();
		   
		  
	    }
	    String emailList = (String) request.getSession().getAttribute(Constant.EMAIL_LIST_FIELD);    
	    
	    if (!Utility.isEmpty(emailList)) {
	    	
	    	serverForm.setEmailList(emailList);
	    }
		LOG.info("UploadDocFileController formBackingObject end!");
		return serverForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("UploadDocFileController onSubmit() begin!");
	
		ui.setRequest(request);
		EmailServerForm form =  (EmailServerForm) command;
		request.getSession().setAttribute(Constant.EMAILLIST_ERROR_MESSAGE,null);
		String actionType = form.getActionType();
		LOG.info("UploadDocFileController onSubmit() actionType="+actionType);

		ModelAndView mode = new ModelAndView("UploadDocFile");
	
		if (Constant.ACTION_DOWNLOAD_EMAIL_LIST.equalsIgnoreCase(actionType)) {
			LOG.info("Download begin");
			Long No = System.currentTimeMillis()/100;
			ProcessDownloadFile.downloadEmailContent(response, "text/plain", form.getEmailList().getBytes(),"my-email-list(No."+No.toString()+")");
			LOG.info("Download end");
	   }
		
		if (Constant.ACTION_UPLOAD_DOC_FILE.equalsIgnoreCase(form.getActionType())) {
			
			//List<SimpleEmailLineVO> list= ReadExcelEmailList.readEmailListFromHTML(Constant.UPLOAD_FILE_CONTENT, request,DUMMY_PTR,DUMMY_PTR,PRE_LOAD_EMAIL_LIST);
			
			List<EmailDynamicVO> dyList =EmailListFactory.buildDynamicEmailList(Constant.UPLOAD_FILE_CONTENT, request);
			EmailListService handler = EmailListFactory.getHandler();
			 
			request.getSession().setAttribute(Constant.EMAIL_DYNAMIC_LIST,dyList);
			
			String currentFile =EmailListFactory.getCurrentUploadFile();
		
			request.getSession().setAttribute(Constant.CURRENT_UPLOADING_FILE, currentFile);
			 	
			int totalCols= dyList.get(0).getTotalCols();  
				  
			List<SelectedValueVo> firstNamePtrList = new ArrayList<SelectedValueVo>();
			List<SelectedValueVo> emailPtrList = new ArrayList<SelectedValueVo>();
			for (int i=0;i<totalCols;i++) {		
			    String ptr= new Integer(i).toString();
				SelectedValueVo vo = new SelectedValueVo(ptr,ptr,"false");
				firstNamePtrList.add(vo);
				emailPtrList.add(vo);
			}
			request.getSession().setAttribute(Constant.FIRSTNAME_PTR_LIST,firstNamePtrList);
			request.getSession().setAttribute(Constant.EMAIL_PTR_LIST,emailPtrList);
			 			
		}	
 		
		if (Constant.ACTION_CONFIRM_CHOSEN.equalsIgnoreCase(form.getActionType())) {
			
			List<SimpleEmailLineVO> list = null;			
			 String firstNameCount=form.getFirstNamePtr();
			 String emailCount=form.getEmailPtr(); 
			 Integer firstNameCol = new Integer(firstNameCount); 
			 Integer emailCol = new Integer(emailCount);			 
			 request.getSession().setAttribute(Constant.FIRST_NAME_PTR,firstNameCol);
			 request.getSession().setAttribute(Constant.EMAIL_PTR,emailCol);
     		 if (emailCol>=0 && firstNameCol>=0) {			
     			 
     			  List<EmailDynamicVO> dyList = (List<EmailDynamicVO>) request.getSession().getAttribute(Constant.EMAIL_DYNAMIC_LIST); 
     			  LOG.info("dyList ="+dyList);
     			  if (null==dyList || 0==dyList.size()) {
     				  LOG.info("dyList is empty");
     				  mode.addObject("emailServerForm", form);
					  return mode;
     			  }
     			
     			  list = EmailListFactory.getFirstNameEmail(dyList, firstNameCol, emailCol);
			  } 
			  if (null!=list) {
			      request.getSession().setAttribute(Constant.EMAIL_DYNAMIC_LIST,null);
				  StringBuffer buf = new StringBuffer();
				  for (SimpleEmailLineVO vo:list) {
					 if (!Utility.isEmpty(vo.getEmailAddress())) {
						 String emailAddress = vo.getEmailAddress().toLowerCase();
						 String name = vo.getFirstName().toLowerCase();
						 if (emailAddress.indexOf("email")==-1 && name.indexOf("name")==-1) {
							 buf.append(ui.delInvalidChar(vo.getFirstName())+", "+ui.delInvalidChar(vo.getEmailAddress())+"\n");
					 	}
					 }
				 }
				 form.setEmailList(buf.toString());
				 request.getSession().setAttribute(Constant.EMAIL_LIST_FIELD,buf.toString());
			 } else {
				ui.setSessionAttribute(Constant.EMAILLIST_ERROR_MESSAGE, "Email File Conversion failed!");	
		    	mode.addObject("emailServerForm", form);
				return mode;
		      }
			 
 		}
	
		 
		if (Constant.ACTION_SAVE_CHANGED_EMAILLIST.equalsIgnoreCase(form.getActionType())) {
			
			if (form.getEmailList()==null || form.getEmailList().trim().length()==0) {
				ui.setSessionAttribute(Constant.EMAILLIST_ERROR_MESSAGE, "You have not create or upload email list yet!");
				LOG.info("You have not create or upload email list yet!");	
			} else {
				LOG.info("You save changed email!");
				if (!ui.convertEmailStringToList(form.getEmailList())) {
					ui.setSessionAttribute(Constant.EMAILLIST_ERROR_MESSAGE, "Convert Recipient Email List failed!");
					LOG.info("Convert Recipient Email List failed!");	
				} else {
					ui.setSessionAttribute(Constant.EMAILLIST_ERROR_MESSAGE, "Convert and save Recipient Email List successfully!");
					LOG.info("Convert and save Recipient Email List successfully!");	
					
					ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM, form); 
				}
				
			}
		}
		 
		LOG.info("UploadDocFileController onSubmit() End!");
		
		String message = ui.getSessionAttribute(Constant.EMAILLIST_ERROR_MESSAGE);
		
		ui.setSessionAttribute(Constant.EMAIL_LIST_STATUS,null);
		
		if ("Convert and save Recipient Email List successfully!".equalsIgnoreCase(message)) {
			ui.setSessionAttribute(Constant.EMAIL_LIST_STATUS,"OK");
		}
		
		//ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM, form); 
		
		mode.addObject("emailServerForm", form);
		 	 
		return mode; 
		
	}
	 
	 
}
