package com.loan.agent.mvc.controller;

 

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
 
 
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 

import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.services.Constant;
import com.loan.agent.services.SingletonData;
/**
 * Multiple Controllers should be defined in method level 
 * @author johnzhang
 *
 */
@Controller
public class GenericActionController  {
 
	static final Logger LOG = Logger.getLogger(GenericActionController.class);
	
	@RequestMapping( value = "/localeChange.html", method = RequestMethod.POST)	
	public String localeChange (Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		LOG.info("localeChange() begin");
		
		/**
		 *  get language parameter from sepcific request
		 */
        String paramLocaleName = ServletRequestUtils.getStringParameter(request, "language");
        
        LOG.info("localeChange() paramLocaleName = "+paramLocaleName );
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
        	LOG.info("localeChange() Changed !");
        }	
        /**
         *  Set select language in Current Language list and put list to session
         */
        		
         SingletonData.getInstance().setCurrentLanguage(paramLocaleName, request);
         
        /**
         *  Return Null only if we use MultiActionController
         */
     	AgentForm agentForm = (AgentForm) request.getSession().getAttribute("agentForm");
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		model.addAttribute("agentForm",agentForm);
     	LOG.info("localeChange end");
     	
		return null;
 	}
	
	@RequestMapping( value = "/agentSignupSuccess.html", method = RequestMethod.GET)	
	public String agentSignupSuccess (Model model, HttpServletRequest request) throws Exception {
		LOG.info("agentSignupSuccess () begin");
	 
		AgentForm agentForm = (AgentForm) request.getSession().getAttribute(Constant.AGENT_FORM);
		if (null == agentForm) {
			agentForm = new AgentForm();
		}
		model.addAttribute("agentForm",agentForm);
		LOG.info("agentSignupSuccess () end");
		/**
		 *  Mapping Handler: AgentSignupSuccessis defined in tiles-spring.xml
		 */
		return "AgentSignupSuccess";
	}
	
	@RequestMapping( value = "/helpAgentSingup.html", method = RequestMethod.GET)	 
	public String helpAgentSingup () throws Exception {
		LOG.info("helpAgentSingup() begin");
		String tileDefinition = "HelpAgentSignup";
		LOG.info("helpAgentSingup() end");
		return tileDefinition;
	}
}
