package com.loan.agent.mvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.services.Constant;

 
public class GenericActionController extends MultiActionController {
 
	static final Logger LOG = Logger.getLogger(GenericActionController.class);
	
	public ModelAndView localeChangeHandler (
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		LOG.info("localeChangeHandler() begin");
		
		/**
		 *  get language parameter from sepcific request
		 */
        String paramLocaleName = ServletRequestUtils.getStringParameter(request, "language");
        /**
         *  Acquire the localeResolver from Servlet Context
         */
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);                

        if (paramLocaleName != null) {
        	
            LocaleEditor localeEditor = new LocaleEditor();
            /**
             *  localeEditor is using StringUtils.parseLocaleString(text) to
             *  convert string language keyword to Locale Object.             *  
             *  language Locale instance are defined in Locale class
             *  e.g. static public final Locale SIMPLIFIED_CHINESE = createSingleton("zh_CN_", "zh", "CN");
             *  zh represents language and CN represents country
             */
            localeEditor.setAsText(paramLocaleName);

            /**
             *  set the new locale and locale object return from  (Locale) localeEditor.getValue()
             */
            localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());
        }	
        /**
         *  In this example, we forward to AgentSignup just for demo, usually we should use javascript to call this
         *  controller which return null and javascript refresh current page. see text project.
         */
		ModelAndView mode = new ModelAndView("AgentSignup");
		 
		AgentForm agentForm = (AgentForm) request.getSession().getAttribute(Constant.AGENT_FORM);
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		mode.addObject("agentForm",agentForm);
		LOG.info("localeChangeHandler() end");
		return mode;
		
	}
	
	public ModelAndView agentSignupSuccessHandler (
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		LOG.info("agentSignupSuccessHandler() begin");
		ModelAndView mode = new ModelAndView("AgentSignupSuccess");
		AgentForm agentForm = (AgentForm) request.getSession().getAttribute(Constant.AGENT_FORM);
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		mode.addObject("agentForm",agentForm);
		LOG.info("agentSignupSuccessHandler() end");
		return mode;
	}
	
	 
	public ModelAndView helpAgentSingupHandler (
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		LOG.info("helpAgentSignupHandler() begin");
		ModelAndView mode = new ModelAndView("HelpAgentSignup");		 
		LOG.info("helpAgentSignupHandler() end");
		return mode;
	}
}
