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
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.AppCheckListForm;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.NicheForm;
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
 

 
public class AgentDataFormController extends SimpleFormController {
Logger LOG = Logger.getLogger( AgentDataFormController.class);
@Autowired
@Resource(name="QuoteDBService")
private QuoteDBService quoteDBService;

public AgentDataFormController() {
	setCommandName("agentForm");
	setCommandClass(AgentForm.class);
}	 
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("AgentDataFormController formBackingObject begin!");
	 
		ui.setRequest(request);
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		 
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
		
		List<AgentDataVo> list =null;
		list =(List<AgentDataVo>) request.getSession().getAttribute(Constant.AGENT_DATA_LIST);
		//LOG.info("AgentDataFormController Agent Data List ="+list); 
		if (list==null) {
			list = new ArrayList<AgentDataVo>();
			
			if (null!=agentId) {
				Agents agents =quoteDBService.getAgentsDAO().findById(agentId);
				String agentName = agents.getFirstName()+" "+agents.getLastName();
				ui.setSessionAttribute(Constant.LOGIN_AGENT_NAME, agentName);
				RateSheetDAO rateDao = quoteDBService.getRateSheetDAO(); 
				List<RateSheet> rateList =  rateDao.findByAgentId(agentId);
			
				if (null!=rateList && rateList.size()>0) {
					AgentDataVo vo = new AgentDataVo();
					vo.setSelected(Constant.CHECK_BOX_CHECKED);
					vo.setValue("Interet Rate is available to select for "+agentName);
					vo.setAgentDataId(Constant.INTEREST_RATE_ID);					
					list.add(vo);					
				}
				NichesDAO nicheDao  = quoteDBService.getNichesDAO();
				List<Niches> nicheList =  nicheDao.findByAgentId(agentId);
				if (null!=nicheList && nicheList.size()>0) {
					AgentDataVo vo = new AgentDataVo();
					vo.setSelected(Constant.CHECK_BOX_CHECKED);
					vo.setValue("Niche Loan Program is available to select for "+agentName);
					vo.setAgentDataId(Constant.NICHE_PROGRAM_ID);	
					list.add(vo);
				}
				if (!Utility.isEmpty(agents.getApplicationFormFilename())) {
					AgentDataVo vo = new AgentDataVo();
					vo.setSelected(Constant.CHECK_BOX_CHECKED);
					vo.setValue(agentName+"'s Application Form is available to download ");
					vo.setAgentDataId(Constant.APPLICATION_FORM_ID);	
					list.add(vo);
				}
				
				AgentDataVo vo = new AgentDataVo();
				vo.setSelected(Constant.CHECK_BOX_CHECKED);
				vo.setValue("Loans-Agent.com Links are available to select");
				vo.setAgentDataId(Constant.LOANS_AGENT_LINKS_ID);	
				list.add(vo);
				
				if (!Utility.isEmpty(agents.getPictureFilename())) {
					  vo = new AgentDataVo();
					  vo.setSelected(Constant.CHECK_BOX_CHECKED);
					vo.setValue(agentName+" picture is available to select");
					vo.setAgentDataId(Constant.AGENT_PICTURE_ID);	
					list.add(vo);
				}
			}
		}
	    
		request.getSession().setAttribute(Constant.AGENT_DATA_LIST,list);
		//LOG.info("AgentDataFormController Agent Data List Size="+list.size());
		//for (AgentDataVo vo:list) {
		//	LOG.info("AgentDataFormController agentDataId="+vo.getAgentDataId()+",Selected="+vo.getSelected());
		//}
		LOG.info("AgentDataFormController formBackingObject end!");
		
		AgentForm agentForm =new AgentForm();
		return agentForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("AgentDataFormController onSubmit() begin!");
		ModelAndView mode = new ModelAndView(Constant.AGENT_DATA_FORM);
		
		ui.setRequest(request);
		
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);	
		Integer agentId = (Integer) request.getSession().getAttribute(Constant.LOGIN_AGENT_ID);
		//agentId = 10001;  // for test
		if (Constant.ACTION_CURRENT_CHECK.equalsIgnoreCase(actionType)) {			
			String currentCheckStatus=ServletRequestUtils.getStringParameter(request,Constant.CURRENT_CHECK_STATUS);		
			String currentCheckStatusId = ServletRequestUtils.getStringParameter(request,Constant.CURRENT_CHECK_STATUS_ID);
			LOG.info("AgentDataFormController onSubmit() currentCheckStatusId="+currentCheckStatusId+",currentCheckStatus ="+currentCheckStatus );
			if (null==currentCheckStatus) {
				LOG.info("AgentDataFormController onSubmit() LcurrentCheckStatus is null");			
				return mode; 
			}
		
			List<AgentDataVo> list  =(List<AgentDataVo>) request.getSession().getAttribute(Constant.AGENT_DATA_LIST);
			int i=0;
			for (AgentDataVo vo : list) {
				if (vo.getAgentDataId().equals(currentCheckStatusId)) {		 
					list.get(i).setSelected(currentCheckStatus); // change member of list				
				}
				i++;
			}
		 
			request.getSession().setAttribute(Constant.AGENT_DATA_LIST,list);
		}
		if (Constant.ACTION_SELECT_ALL_CHECK.equalsIgnoreCase(actionType)) {
			String selectAllStatus=ServletRequestUtils.getStringParameter(request,Constant.SELECT_ALL_STATUS);	
			List<AgentDataVo> list  =(List<AgentDataVo>) request.getSession().getAttribute(Constant.AGENT_DATA_LIST);
			int i=0;
			for (AgentDataVo vo : list) {					 
				list.get(i).setSelected(selectAllStatus); // change member of list		
				i++;
			}	
			request.getSession().setAttribute(Constant.AGENT_DATA_LIST,list);
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
        	List<AgentDataVo> list  =(List<AgentDataVo>) request.getSession().getAttribute(Constant.AGENT_DATA_LIST);
			 
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
			
			/**
			 *  Keep pull Quote data
			 */
			QuoteForm qForm=(QuoteForm) request.getSession().getAttribute(Constant.QUOTE_FORM);
	 		    
				
        	if (null!=qForm) {
        		String quoteId= qForm.getQuoteId();
        		targetURL+="&quoteId="+quoteId;
        	}
        	
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
            /**
             *  Post Agent Id first and get Response from 
             */
        	LOG.info("targetURL ="+targetURL);
        	
         	String emailContent = ui.getEmailContentFromURL(targetURL);
         	 
        	form.setEmailContent(ui.deCodeHTML(emailContent));
        	ui.setRequest(request);
        	ui.setSessionAttributeObject(Constant.EMAIL_SERVER_FORM,form);
     		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Successfully pull selected data!  Please close this window to see email content box!");	

		}

		return mode; 
		
	}
	 
	 
}
