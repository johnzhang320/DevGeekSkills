package com.loan.agent.mvc.controller;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loan.agent.calculators.vo.SelectedStateVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.AgentsDAO;

import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;

import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

public class ResetPasswordController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(ResetPasswordController.class);
	
 	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
	 
		
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	
		AgentForm agentForm = new AgentForm();
	 
		String paramAgentIdStr = request.getParameter(Constant.PARAM_AGENT_ID);
		/**
		 *  Decode the loginAgentId
		 */
		paramAgentIdStr = ui.getDecodedString(paramAgentIdStr);
		if (null==paramAgentIdStr) {
			LOG.info("ResetPasswordController formBackingObject paramAgentIdStr = null");
			return agentForm;
		}
		Integer paramAgentId = new Integer(paramAgentIdStr);
		request.getSession().setAttribute(Constant.PARAM_AGENT_ID, paramAgentId);
		agentForm.setPassword("");
		return agentForm;
		
	} 
	
	 
	
	
	public ResetPasswordController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
		LOG.info("OnSubmitted function........");
		
		AgentForm agentForm = (AgentForm) command;
		
		LOG.info("Hashed Password="+agentForm.getPassword()); 
		
	 	
	    AgentsDAO agentsDAO = quoteDBService.getAgentsDAO();	
		
		LOG.info("OnSubmitted...........");
		Utility.setRequest(request,response);
	
	
		    /** 
		     *  Must check if current login agent id
		     */
		  
		    Integer agentId = (Integer) request.getSession().getAttribute(Constant.PARAM_AGENT_ID);
		    
		    Agents agentObj = null;
		    
		   		
		  ModelAndView mode = new ModelAndView(Constant.FORWARD_AGENT_LOGIN); 	
		   boolean resetSuccess = false; 	 
		   if (null!=agentId) {		    	 
		    	agentObj =quoteDBService.getAgentsDAO().findById(agentId);
		    	
		    	agentObj.setPassword(agentForm.getPassword());
		    	agentsDAO.merge(agentObj); 
		    	resetSuccess = true; 
		    	 request.getSession().setAttribute(Constant.PASSWORD_RESET_STATUS,"Reset Password Successfully, Please login using this password");
		    	 String username = (String) request.getSession().getAttribute(Constant.EMAIL_ADDRESS);
		    	 agentForm.setEmailAddress(username);
		    	 mode.addObject(agentForm);
		    } 
		   
		  if (!resetSuccess) {
			  mode = new ModelAndView("ResetPasswordFail"); 	
		  }
		 
		return mode; 
		 
	}
	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}
	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
 
	
} 

