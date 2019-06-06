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
import com.loan.agent.services.SingletonData;

 
public class GenericActionController extends MultiActionController {
 
	static final Logger LOG = Logger.getLogger(GenericActionController.class);
	 /**
     * If your product supports 10+ language, you have to put languages into form select/option drop down list
     * and once user select a language,web page language should be changed then the chosen language always is displayed 
     * regardless page switched or reloaded
     * Here is implementation.
     * (1) Synchronize
     * jQuery $ajax calls MultiActionController which only set locale in session and return null.
     * set async to be false, wait for tile layout call all subroutines and finally dispatcher send response
     * back to $.ajax then it javascript reload current page, ensure the synchronized
     * (2) For Select and Option html based on session
     */
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
             *  convert string language keyword to Locale Object.               
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
         *  Set select language in Current Language list and put list to session
         */
        		
         SingletonData.getInstance().setCurrentLanguage(paramLocaleName, request);
         
        /**
         *  Return Null only if we use MultiActionController
         */
     	LOG.info("localeChangeHandler end");
		return null;
		
		
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
