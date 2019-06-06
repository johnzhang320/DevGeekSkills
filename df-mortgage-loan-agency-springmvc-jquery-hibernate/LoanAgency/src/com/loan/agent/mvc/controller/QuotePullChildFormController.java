package com.loan.agent.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 


import com.loan.agent.calculators.vo.AgentDataVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.AppCheckList;
import com.loan.agent.dao.AppCheckListDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.Quote;
import com.loan.agent.dao.QuoteDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.mvc.formbean.QuoteForm;
 
 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.UpdateEnvelop;
 

 
public class QuotePullChildFormController extends SimpleFormController {
Logger LOG = Logger.getLogger( QuotePullChildFormController.class);
@Autowired
@Resource(name="QuoteDBService")
private QuoteDBService quoteDBService;

	public QuotePullChildFormController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("QuotePullChildFormController formBackingObject begin!");
	
		ui.setRequest(request);
		ui.setSessionAttributeObject(Constant.AGENT_OBJECT, null);
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);
		
		String quoteIdStr = ServletRequestUtils.getStringParameter(request,Constant.QUOTE_ID);
		
		ui.setSessionAttributeObject(Constant.ACTION_TYPE,actionType);
		AgentForm agentForm = new AgentForm();	
		agentForm.setAgentFormAction(actionType);
		request.getSession().setAttribute(Constant.QUOTE_PULL_TO_EMAIL,null);
		
		/** 
		 *  Get initialized Form
		 */
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
	
		//LOG.info("actionType="+actionType+",quoteIdr="+quoteIdStr+",step 1") ;
		Integer quoteId = null;
		if (null!=quoteIdStr) {
		    quoteId = new Integer(quoteIdStr);	
		    
		}
		QuoteForm qForm  = null;
		LOG.info("QuotePullChildFormController formBackingObject  quoteId="+ quoteId);
		//if (null!=quoteId) {
			QuoteDAO quoteDao = quoteDBService.getQuoteDAO();
			Quote quote=quoteDao.findById(quoteId);
			LOG.info("QuotePullChildFormController formBackingObject  quote="+ quote.toString());
			qForm  = new QuoteForm();
			qForm.renderQuote(quote);
			
			qForm.setQuoteId(quoteIdStr);
			
			LOG.info("QuotePullChildFormController formBackingObject  qForm monthlyIncome="+ qForm.getMonthlyIncome()+",LoanToValue="+qForm.getLoanToValue()+",borrowerCreditScore="+qForm.getBorrowerCreditScore());
			
			request.getSession().setAttribute(Constant.QUOTE_FORM, qForm);
			request.getSession().setAttribute(Constant.QUOTE_ID, quoteId);
 		//}
		 
		
		
	
		 ui.setSessionAttributeObject(Constant.AGENT_OBJECT, null);
		 
		LOG.info("QuotePullChildFormController formBackingObject end!");
		return agentForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("QuotePullChildFormController onSubmit() begin!");		
		 
		
		ui.setRequest(request);
		
		AgentForm agentform =  (AgentForm) command;		
	 
		ui.setSessionAttributeObject(Constant.AGENT_OBJECT, null);
		ModelAndView mode = new ModelAndView(Constant.FORWARD_QUOTE_PULL_CHILD_FORM);	 		
	 
		
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);		
		
	    String actionType = agentform.getAgentFormAction();
		
	 	
		LOG.info("QuotePullChildFormController onSubmit() actionType="+actionType);
	
		Integer quoteId = (Integer) request.getSession().getAttribute(Constant.QUOTE_ID);
		
		if (null==quoteId) {
			LOG.info("QuotePullChildFormController quoteId==null");
			return mode;
		}
		QuoteForm qForm =(QuoteForm) request.getSession().getAttribute(Constant.QUOTE_FORM);   
		QuoteDAO quoteDao = quoteDBService.getQuoteDAO();	 	
		Quote quote=quoteDao.findById(quoteId);
		 
		
		if (Constant.ACTION_QUOTE_DELETE.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("Quote Pull Delete begin");						
		    quote.setQuoteId(quoteId);
		  
		    quoteDBService.deleteQuote(quoteId);		
			
			request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully Delete Current Quote Data");
			
			LOG.info("Quote Pull Delete end");		 
			
	   }
	   if (Constant.ACTION_QUOTE_PULL.equalsIgnoreCase(actionType)) {        	
			 
		   LOG.info("Quote Pull begin");	
		 
		   
		   if (null==qForm) {
				if (null!=quoteId) {					 
					qForm  = new QuoteForm();
					qForm.renderQuote(quote);
		 		}
		   }
		   qForm.setQuoteId(quoteId.toString());
		   request.getSession().setAttribute(Constant.QUOTE_FORM, qForm);
		   Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
	       String encodedAgentId = ui.getEncodedString(agentId.toString());
        	
        	String targetURL = SysPath.getHostName()+"/"+Constant.AGENT_EMAIL_HTML+"?"+Constant.PARAM_AGENT_ID+"="+encodedAgentId+"&quoteId="+quoteId.toString();
    	    
        	EmailServerForm form = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);   

        	String paramEmail = form.getToEmailAddress();
        	LOG.info(" paramEmail ="+ paramEmail ); 
        	if (paramEmail!=null) {
        		targetURL =targetURL+"&paramMyEmail="+paramEmail;
        	}
        	StringBuffer paramBuf = new StringBuffer();
        	List<AgentDataVo> list  =(List<AgentDataVo>) request.getSession().getAttribute(Constant.AGENT_DATA_LIST);
			if (null!=list) { 
				for (AgentDataVo vo : list) {					 
					String selected = vo.getSelected();
					if (Constant.CHECK_BOX_CHECKED.equalsIgnoreCase(selected)) {
						String AgentDataId = vo.getAgentDataId(); 
						paramBuf.append("&"+AgentDataId+"=checked"); 
					}
				}
				if (paramBuf.length()>0) {
					targetURL+=paramBuf.toString();
				}
			} 
	 
            /**
             *  Post Agent Id first and get Response from 
             */
        	LOG.info("targetURL ="+targetURL);
        	
         	String emailContent = ui.getEmailContentFromURL(targetURL);
         	 
        	/**
        	 *   Initialize the EmailServerForm 
        	 */
         	form.setEmailContent(ui.deCodeHTML(emailContent));         	
        	form.setToEmailAddress(qForm.getEmailAddress());
        	String subject =(String) request.getSession().getAttribute(Constant.LOGIN_AGENT_NAME)+" replys "+qForm.getFirstName()+" "+qForm.getLastName()+"'s loan quote from loans-agent.com";
        	form.setSubject(subject);
        	
        	
         	
         	ui.setRequest(request);
        	ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form); 
        	request.getSession().setAttribute(Constant.TO_EMAIL_ADDRESS, qForm.getEmailAddress());
		   request.getSession().setAttribute(Constant.QUOTE_PULL_TO_EMAIL,"yes");
			request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully Pull Current Quote Data,Please Close window and view Email Box.");
		   LOG.info("Quote Pull begin");	
		 	 
			
	   }
	   LOG.info("QuotePullChildFormController onSubmit() end!");			 	 
	   return mode; 
		
	}
	 
	 
}
