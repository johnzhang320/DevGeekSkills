package com.loan.agent.mvc.controller;

import java.io.File;
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

 


import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;
import com.loan.agent.dao.Niches;
import com.loan.agent.dao.NichesDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.NicheForm;
import com.loan.agent.mvc.formbean.NicheForm;
 
 
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
 

 
public class DownloadApplicationFormController extends SimpleFormController {
Logger LOG = Logger.getLogger( DownloadApplicationFormController.class);
@Autowired
@Resource(name="QuoteDBService")
private QuoteDBService quoteDBService;

	public DownloadApplicationFormController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("DownloadApplicationFormController formBackingObject begin!");
		
		ui.setRequest(request);
		AgentForm agentForm = new AgentForm();
		
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);
		
	    
		
		ui.setSessionAttributeObject(Constant.ACTION_TYPE,actionType);		
	   
		/** 
		 *  Get initialized Form
		 */
		request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,null);
		
		 
		Integer agentId= (Integer) request.getSession().getAttribute(Constant.AGENT_ID);
		
		if (null==agentId) {
			
			request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Agent Id not found!");
			return agentForm;
		}
		
		//Integer agentId = 10007;  // testing
		Agents agents = quoteDBService.getAgentsDAO().findById(agentId); 
		
		String appParent = SysPath.getInstance().getLoanApplicationFormsPath();
		 
		String fileExt =Utility.getFileExtention(agents.getApplicationFormFilename());
		
		String orgPathFile =SysPath.getInstance().getLoanApplicationFormsPath()+Constant.PREFIX_APP_FORM_FILE+agentId.toString()+fileExt;					
		
		File file = new File(orgPathFile);
		request.getSession().setAttribute(Constant.APPLICATION_FORM_PATH,null);
		
		if (!file.exists()) {
			LOG.info("Not Found File :"+orgPathFile);
			request.getSession().setAttribute(Constant.SUCCESS_MESSAGE,"Application Form not found!");
		} else {
			LOG.info("Application Form Path="+orgPathFile);
			request.getSession().setAttribute(Constant.APPLICATION_FORM_PATH,orgPathFile);
		}
 		
		return agentForm;
		
	}  
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		LOG.info("DownloadApplicationFormController onSubmit() begin!");
		
		ui.setRequest(request);
		
		ModelAndView  mode = new ModelAndView(Constant.FORWARD_DOWNLOAD_APPLICATION_FORM); 
		
		String appFormPath =(String) request.getSession().getAttribute(Constant.APPLICATION_FORM_PATH);		 
		 
		String actionType=ServletRequestUtils.getStringParameter(request,Constant.ACTION_TYPE);
		
		if (Constant.ACTION_DOWNLOAD_APP_FORM.equalsIgnoreCase(actionType)) {        	
			 
			LOG.info("Download begin");			
			 
			 mode = new ModelAndView(Constant.REDIRECT_DOWNLOAD_APPLICATION_FORM); 
			
			request.setAttribute(Constant.SUCCESS_MESSAGE, "Successfully begin download application form");
			
			LOG.info("Download End");
			
	   }
		return mode; 
		
	}
	 
	 
}
