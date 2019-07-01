package com.loan.agent.mvc.controller;

/*
 *   Copyright 2018, 2019 John Zhang (Or Jian Zhang)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */ 

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger; 
 
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basic.hibernate.dao.AgentTable;
import com.loan.agent.services.Constant;
import com.loan.agent.services.SingletonData;
import com.locale.message.utils.LocaleMsg;
/**
 * Multiple Controllers should be defined in method level 
 * @author johnzhang
 *
 */
@Controller
@RequestMapping("/")
public class GenericActionController  {
 
	static final Logger LOG = Logger.getLogger(GenericActionController.class);
	  /**
   	 * If cookie not exists , create new cookie, if exist , only modify by new cookieValue
   	 * 
   	 * @param response
   	 * @param cookieName
   	 * @param cookieValue
   	 * @param days
   	 * @return
   	 */
	public boolean setCookie(HttpServletResponse response,
			                 HttpServletRequest request, 
			                 String cookieName,
			                 String cookieValue) {
		//LOG.info("setCookie  begin");
		
		Cookie cookie= getCookie(request,cookieName);
		/**
		 *  Cookie exist
		 */
		if (cookie!=null) {
			 LOG.info("setCookie  cookie name "+cookieName+", exists!");
			 cookie.setValue(cookieValue);			 
			 response.addCookie(cookie);
			 LOG.info("setCookie Modify cookie  end!");
			 return true;
		}
		
	  /**
	   * Cookie not exists, create one
	   *  if days ==-1 , 'never expires'
       */
    	int maxAge=-1;    
	    cookie = new Cookie(cookieName,cookieValue);
	    cookie.setPath(request.getContextPath());
		cookie.setMaxAge(maxAge); //days
		response.addCookie(cookie);
		LOG.info("setCookie  end");
		return true;
	}
	public Cookie getCookie(HttpServletRequest request, String name) {
		//LOG.info("getCookie  begin");
        if (null!=request.getCookies()) {
        	LOG.info("request.getCookies().length = "+request.getCookies().length);
            for (Cookie cookie : request.getCookies()) {
            	LOG.info("Cookie Name= "+cookie.getName());
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie;
                }
            }
        } else {
        	LOG.info("request.getCookies() == null");
        }
       // LOG.info("getCookie  end");
       return null;
    }
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
            Locale locale =(Locale) localeEditor.getValue();
            
            localeResolver.setLocale(request, response, locale);
            
            /**
             *  change cookieLocale and reload language properties file
             */
            LOG.info("Changed Locale Languge is "+locale.getLanguage());
            
            setCookie(response,
	                 request, 
	                 "currLanguage",   //String cookieName,
	                 paramLocaleName); //String cookieNameValue
       	    
       	    
       	    
       	    LocaleMsg.initProperties(request);
            
        	LOG.info("localeChange() Changed !");
        }	
        
        
        /**
         *  Set select language in Current Language list and put list to session
         */
        		
         SingletonData.getInstance().setCurrentLanguage(paramLocaleName, request);
         
        /**
         *  Return Null only if we use MultiActionController
         */
     	AgentTable agentTable = (AgentTable) request.getSession().getAttribute("agentTable");
		if (null == agentTable) {
			agentTable = new AgentTable();
		}
		model.addAttribute("agentTable",agentTable);
     	LOG.info("localeChange end");
     	
		return null;
 	}
	
	@RequestMapping( value = "/agentSignupSuccess.html", method = RequestMethod.GET)	
	public String agentSignupSuccess (Model model, HttpServletRequest request) throws Exception {
		LOG.info("agentSignupSuccess () begin");
	 
		AgentTable agentTable = (AgentTable) request.getSession().getAttribute(Constant.AGENT_FORM);
		if (null == agentTable) {
			agentTable = new AgentTable();
		}
		model.addAttribute("agentTable",agentTable);
		LOG.info("agentSignupSuccess () end");
		/**
		 *  Mapping Handler: AgentSignupSuccessis defined in tiles-spring.xml
		 */
		return "AgentSignupSuccess";
	}
	@RequestMapping( value = "/agentLoginSuccess.html", method = RequestMethod.GET)	
	public String agentLoginSuccess (Model model, HttpServletRequest request) throws Exception {
		LOG.info("agentSignupSuccess () begin");
	 
		AgentTable agentTable = (AgentTable) request.getSession().getAttribute(Constant.AGENT_FORM);
		if (null == agentTable) {
			agentTable = new AgentTable();
		}
		model.addAttribute("agentTable",agentTable);
		LOG.info("agentSignupSuccess () end");
		/**
		 *  Mapping Handler: AgentSignupSuccessis defined in tiles-spring.xml
		 */
		return "AgentLoginSuccess";
	}
	@RequestMapping( value = "/helpAgentSingup.html", method = RequestMethod.GET)	 
	public String helpAgentSingup () throws Exception {
		LOG.info("helpAgentSingup() begin");
		String tileDefinition = "HelpAgentSignup";
		LOG.info("helpAgentSingup() end");
		return tileDefinition;
	}
	@RequestMapping( value = "/helpOktaSSO.html", method = RequestMethod.GET)	 
	public String helpOktaSSO () throws Exception {
		LOG.info("helpOktaSSO() begin");
		String tileDefinition = "HelpOktaSSO";
		LOG.info("helpOktaSSO() end");
		return tileDefinition;
	}
	
	@RequestMapping( value = "/helpAgentLogin.html", method = RequestMethod.GET)	 
	public String helpAgentLogin () throws Exception {
		LOG.info("helpAgentLogin() begin");
		String tileDefinition = "HelpAgentLogin";
		LOG.info("helpAgentLogin() end");
		return tileDefinition;
	}
	
	 
	@RequestMapping( value = "/helpDmarcReport.html", method = RequestMethod.GET)	 
	public String helpDmarcReport () throws Exception {
		LOG.info("helphelpDmarcReport() begin");
		String tileDefinition = "HelpDmarcReport";
		LOG.info("helphelpDmarcReport() end");
		return tileDefinition;
	}
}
