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
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.AppCheckListForm;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.NicheForm;
 
 
import com.loan.agent.mvc.utils.Files;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
 
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.UpdateEnvelop;
 

 
public class QuotePullFormController extends SimpleFormController {
Logger LOG = Logger.getLogger( QuotePullFormController.class);
@Autowired
@Resource(name="QuoteDBService")
private QuoteDBService quoteDBService;

@Autowired
@Resource(name="AgentReviewService")
private AgentReviewService agentReviewService;

public QuotePullFormController() {
	setCommandName("agentForm");
	setCommandClass(AgentForm.class);
}	 
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("QuotePullFormController formBackingObject begin!");
	 
		ui.setRequest(request);
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		LOG.info("AgentId="+agentId);
		
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
		ModelAndView mode = new ModelAndView(Constant.FORWARD_QUOTE_PULL_FORM);
	
		AgentForm agentForm = new AgentForm();	
		mode.addObject("agentForm", agentForm);
		
		if (agentId==null) {	 
			LOG.info("AgentId==null");
			return mode;
			
		}
		List<AgentReviewQuoteVo> list = agentReviewService.getAgentQuoteInquire(agentId);

		 
		if (null==list) {
			// fetch list from Database
			
			if (list==null || list.size()==0) {
				LOG.info("List<AgentReviewQuoteVo> list =null");
				return null;
			}
 	   		
		}
		
		request.getSession().setAttribute(Constant.QUOTE_LIST, list);
		
	//	for (AgentReviewQuoteVo vo:list) {
	//		LOG.info("quoteId="+vo.getQuote_id()+",quoteId="+vo.getQuoteId()+",firstName="+vo.getFirst_name());
	//	} 
		return agentForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("QuotePullFormController onSubmit() begin!");
		ModelAndView mode = new ModelAndView(Constant.FORWARD_QUOTE_PULL_FORM);
		
		ui.setRequest(request);
		
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);	
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
	
		if (Constant.ACTION_CURRENT_CHECK.equalsIgnoreCase(actionType)) {			
			String currentCheckStatus=ServletRequestUtils.getStringParameter(request,Constant.CURRENT_CHECK_STATUS);		
			String currentCheckStatusId = ServletRequestUtils.getStringParameter(request,Constant.CURRENT_CHECK_STATUS_ID);
			LOG.info("QuotePullFormController onSubmit() currentCheckStatusId="+currentCheckStatusId+",currentCheckStatus ="+currentCheckStatus );
			if (null==currentCheckStatus) {
				LOG.info("QuotePullFormController onSubmit() LcurrentCheckStatus is null");			
				return mode; 
			}
		
			List<AgentReviewQuoteVo> list  =(List<AgentReviewQuoteVo>) request.getSession().getAttribute(Constant.QUOTE_LIST);
			int i=0;
			for (AgentReviewQuoteVo vo : list) {
				if (vo.getQuoteId().equals(currentCheckStatusId)) {		 
					list.get(i).setSelected(currentCheckStatus); // change member of list				
				}
				i++;
			}
		 
			request.getSession().setAttribute(Constant.QUOTE_LIST,list);
		}
		if (Constant.ACTION_SELECT_ALL_CHECK.equalsIgnoreCase(actionType)) {
			String selectAllStatus=ServletRequestUtils.getStringParameter(request,Constant.SELECT_ALL_STATUS);	
			List<AgentReviewQuoteVo> list  =(List<AgentReviewQuoteVo>) request.getSession().getAttribute(Constant.QUOTE_LIST);
			int i=0;
			for (AgentReviewQuoteVo vo : list) {					 
				list.get(i).setSelected(selectAllStatus); // change member of list		
				i++;
			}	
			request.getSession().setAttribute(Constant.QUOTE_LIST,list);
    	}
		if (Constant.ACTION_PULL_SELECTED_DATA.equalsIgnoreCase(actionType)) {
			
		 
			String encodedAgentId = ui.getEncodedString(agentId.toString());
        	
        	String targetURL = SysPath.getHostName()+"/"+Constant.AGENT_EMAIL_HTML+"?"+Constant.PARAM_AGENT_ID+"="+encodedAgentId;
    	  
        	EmailServerForm form = (EmailServerForm) ui.getSessionAttributeObject(Constant.EMAIL_SERVER_FORM);   

        	String paramEmail = form.getFromEmailAddress();
        	LOG.info(" paramEmail ="+ paramEmail ); 
        	if (paramEmail!=null) {
        		targetURL =targetURL+"&paramMyEmail="+paramEmail;
        	}
        	StringBuffer paramBuf = new StringBuffer();
        	List<AgentReviewQuoteVo> list  =(List<AgentReviewQuoteVo>) request.getSession().getAttribute(Constant.QUOTE_LIST);
			 
			for (AgentReviewQuoteVo vo : list) {					 
				String selected = vo.getSelected();
				if (Constant.CHECK_BOX_CHECKED.equalsIgnoreCase(selected)) {
					String AgentDataId = vo.getQuoteId(); 
					paramBuf.append("&"+AgentDataId+"=checked"); 
				}
			}
			if (paramBuf.length()>0) {
				targetURL+=paramBuf.toString();
			}
            /**
             *  Post Agent Id first and get Response from 
             */
        	LOG.info("targetURL ="+targetURL);
        	paramBuf = new StringBuffer();
        	List<AppCheckListForm> chlist  =(List<AppCheckListForm>) request.getSession().getAttribute(Constant.APP_CHECK_LIST_LIST);
        	 
        	StringBuffer chBuf = new StringBuffer();
            boolean checked = false;
            if (null!=chlist) {
            	for (AppCheckListForm vo : chlist) {					 
            		String selected = vo.getSelected();
            		if (Constant.CHECK_BOX_CHECKED.equalsIgnoreCase(selected)) {
            			String CheckId = vo.getCheckId()+"|"; 
            			checked = true;
            			chBuf.append(CheckId);
            		}
            	}
            	String checkIdStr = chBuf.toString();
            	/**
            	 *  Remove last '|'
            	 */
            	checkIdStr = ui.delLastChar(checkIdStr);
			
            	paramBuf.append("&"+Constant.PARAM_APP_CHECKLIST_IDS+"="+checkIdStr); 
            }
			
			if (paramBuf.length()>0) {
				targetURL+=paramBuf.toString();
			}
			paramBuf = new StringBuffer();
        	List<AgentDataVo> aglist  =(List<AgentDataVo>) request.getSession().getAttribute(Constant.AGENT_DATA_LIST);
			if (null!=aglist) { 
				for (AgentDataVo vo : aglist) {					 
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
         	String emailContent = ui.getEmailContentFromURL(targetURL);
         	 
        	form.setEmailContent(ui.deCodeHTML(emailContent));
        	ui.setRequest(request);
        	ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form);
     		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Successfully pull selected data!  Please close this window to see email content box!");	

		}

		return mode; 
		
	}
	 
	 
}
