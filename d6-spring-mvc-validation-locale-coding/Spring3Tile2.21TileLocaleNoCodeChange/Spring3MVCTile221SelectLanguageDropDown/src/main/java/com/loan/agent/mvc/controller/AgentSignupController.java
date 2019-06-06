package com.loan.agent.mvc.controller;

import java.io.File;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContext;


import com.loan.agent.dao.Agents;
 

import com.loan.agent.mvc.formbean.AgentForm;
 
 
import com.loan.agent.services.Constant;
 
 

public class AgentSignupController extends SimpleFormController {
 static final Logger LOG = Logger.getLogger(AgentSignupController.class);
	
 	 
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LOG.info("formBackingObject begin");
		
		AgentForm agentForm = (AgentForm) request.getSession().getAttribute("agentForm");
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		LOG.info("formBackingObject end");
		return agentForm;
	} 
	
	@Override
	public Map referenceData(HttpServletRequest request) {
		
		Map referenceData = new HashMap(); 
		 
		Map<String,String> stateMap = new HashMap<String,String>();
		
		stateMap.put("AL","Alabama");
		stateMap.put("AK","Alaska");
		stateMap.put("AZ","Arizona");
		stateMap.put("AR","Arkansas");
		stateMap.put("CA","California");
		stateMap.put("CO","Colorado");
		stateMap.put("CT","Connecticut");
		stateMap.put("DE","Delaware");
		stateMap.put("DC","District of Columbia");
		stateMap.put("FL","Florida");
		stateMap.put("GA","Georgia");
		stateMap.put("HI","Hawaii");
		stateMap.put("ID","Idaho");
		stateMap.put("IL","Illinois");
		stateMap.put("IN","Indiana");
		stateMap.put("IA","Iowa");
		stateMap.put("KS","Kansas");
		stateMap.put("KY","Kentucky");
		stateMap.put("LA","Louisiana");
		stateMap.put("ME","Maine");
		stateMap.put("MD","Maryland");
		stateMap.put("MA","Massachusetts");
		stateMap.put("MI","Michigan");
		stateMap.put("MN","Minnesota");
		stateMap.put("MS","Mississippi");
		stateMap.put("MO","Missouri");
		stateMap.put("MT","Montana");
		stateMap.put("NE","Nebraska");
		stateMap.put("NV","Nevada");
		stateMap.put("NH","New Hampshire");
		stateMap.put("NJ","New Jersey");
		stateMap.put("NM","New Mexico");
		stateMap.put("NY","New York");
		stateMap.put("NC","North Carolina");
		stateMap.put("ND","North Dakota");
		stateMap.put("OH","Ohio");
		stateMap.put("OK","Oklahoma");
		stateMap.put("OR","Oregon");
		stateMap.put("PA","Pennsylvania");
		stateMap.put("PR","Puerto Rico");
		stateMap.put("RI","Rhode Island");
		stateMap.put("SC","South Carolina");
		stateMap.put("SD","South Dakota");
		stateMap.put("TN","Tennessee");
		stateMap.put("TX","Texas");
		stateMap.put("UT","Utah");
		stateMap.put("VT","Vermont");
		stateMap.put("VA","Virginia");
		stateMap.put("WA","Washington");
		stateMap.put("WV","West Virginia");
		stateMap.put("WI","Wisconsin");
		stateMap.put("WY","Wyoming");
		 
		referenceData.put("stateMap", stateMap);		
 		 
		return referenceData;
	}
	
	
	public AgentSignupController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
		
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 
		LOG.info("AgentSignupController OnSubmit() begin");
	
		AgentForm agentForm = (AgentForm) command;
		
		Agents agents = agentForm.modelAgents();
		
		LOG.info("Save Agent to Database ..... user email is "+agentForm.getEmailAddress());
		/**
	     * Forward by tile definition AgentSignupSuccess
	     */ 
		//ModelAndView mode = new ModelAndView("AgentSignupSuccess");
		/**
		 *  Redirect to agentSignupSuccess.html not by tile definition directly and save command 
		 *  object to session
		 */
		 ModelAndView mode = new ModelAndView("redirect:agentSignupSuccess.html");
		 
		 /**
		  *  Redirect to different domain website 
		  */
		//ModelAndView mode = new ModelAndView("redirect:https://www.loans-agent.com");
		 
		mode.addObject("agentForm", agentForm);
		
		/**
		 *  Spring Command Model Data is available on page only,  cross pages must save command object to session scope
		 */
		request.getSession().setAttribute("agentForm", agentForm);
			
		LOG.info("AgentSignupController OnSubmit() end");
	 		
	 
		return mode; 
		 
	}
	 
	
} 

