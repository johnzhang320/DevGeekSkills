package com.loan.agent.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger; 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.loan.agent.mvc.formbean.AgentForm;
 
public class GenericActionController extends MultiActionController {
 
	static final Logger LOG = Logger.getLogger(GenericActionController.class);
 
	public ModelAndView agentSignupSuccessHandler (HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("agentSignupSuccessHandler() begin");
		ModelAndView mode = new ModelAndView("AgentSignupSuccess");
		AgentForm agentForm = (AgentForm) request.getSession().getAttribute("agentForm");
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		mode.addObject("agentForm",agentForm);
		LOG.info("agentSignupSuccessHandler() end");
		return mode;
	}	
	 
	public ModelAndView helpAgentSingupHandler (HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("helpAgentSignupHandler() begin");
		ModelAndView mode = new ModelAndView("HelpAgentSignup");		 
		LOG.info("helpAgentSignupHandler() end");
		return mode;
	}
}
