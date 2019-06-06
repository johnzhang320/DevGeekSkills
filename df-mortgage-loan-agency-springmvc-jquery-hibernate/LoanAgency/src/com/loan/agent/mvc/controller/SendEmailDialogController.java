package com.loan.agent.mvc.controller;

 

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 


import com.loan.agent.dao.Agents;
 
import com.loan.agent.mvc.formbean.EmailServerForm;
 
import com.loan.agent.mvc.utils.SpringFramework;
 
import com.loan.agent.mvc.utils.ui;
 
 
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.eUtil;
 
 

 
public class SendEmailDialogController extends SimpleFormController {
Logger LOG = Logger.getLogger( SendEmailDialogController.class);
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 

	public SendEmailDialogController() {
		setCommandName("emailServerForm");
		setCommandClass(EmailServerForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		ui.setRequest(request);
		/** 
		 *  Get initialized Form
		 */
		EmailServerForm form = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);
	 
		if (form ==null) {
			   form = new EmailServerForm();
		}
		 
	    
	 	 
		return form;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("SendEmailDialogController onSubmit() begin!");
		ui.setRequest(request);
		EmailServerForm form =  (EmailServerForm) command;
		request.getSession().setAttribute(Constant.EMAILLIST_ERROR_MESSAGE,null);
		LOG.info("SendEmailDialogController onSubmit() actionType="+form.getActionType());

		ModelAndView mode = new ModelAndView("SendEmailDialog");
		quoteDBService = (QuoteDBService) SpringFramework.getBean("QuoteDBService");
		Integer agentId =(Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		//agentId = 10007;
		Agents agents = null;
		if (agentId!=null) {
			agents = quoteDBService.getAgentsDAO().findById(agentId);
			 
		} else {
			LOG.info("SendEmailDialogController Can not find agent by agent_id="+agentId);
			return mode;
		}
		
	 
		if (Constant.ACTION_SEND_EMAIL_TO_MYSELF.equalsIgnoreCase(form.getActionType())) {
			 
			LOG.info("From Email Address is "+form.getFromEmailAddress());
		    
		    List<RecipientVo> rList = eUtil.generateRecipientVoList(form.getFromEmailAddress());
		     		   
		    
		   if (ui.sendEmailForAgentWrap(request,agents,form,rList,false)) {
			   LOG.info("Send to myself successfully");
			   
		   } else {
			   LOG.info("Send to myself failed!");
		   }
			 
		} 
		if (Constant.ACTION_SEND_BY_EMAIL_LIST.equalsIgnoreCase(form.getActionType())) {
			  
			  List<RecipientVo> rList =(List<RecipientVo>) ui.getSessionAttributeObject(Constant.RECIPIENT_EMAIL_LIST);
			  if (ui.sendEmailForAgentWrap(request,agents,form,rList,false)) {
				   LOG.info("Send by emaillist successfully");
				   
			   } else {
				   LOG.info("Send by emailList failed!");
			   }
			 
		}
		LOG.info("SendEmailDialogController onSubmit() End!");
		
		ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM, form); 
		
		mode.addObject(Constant.EMAIL_SERVER_FORM, form);
		 	 
		return mode; 
		
	}
	 
	 
}

