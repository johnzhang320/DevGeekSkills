package com.loan.agent.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 

import com.frontend.encrypt.utils.KeyPairManager;
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.dao.SessionManager;
import com.loan.agent.dao.SessionManagerDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.RateSheetForm;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.loan.mvc.validator.AgentValidator;
 
public class AgentLoginController extends SimpleFormController {
Logger LOG = Logger.getLogger( AgentLoginController.class);
	
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
	
	@Autowired
	@Resource(name="AgentReviewService")
	private AgentReviewService agentReviewService;
		
	 
	public AgentLoginController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	    Utility.setRequest(request);
		/**
		 *  GET form and initialize form
		 */
		AgentForm agentForm = new AgentForm();
		String agentLoginDirectly = Utility.getStringParameter(Constant.AGENT_LOGIN_DIRECT);
	    if ("yes".equalsIgnoreCase(agentLoginDirectly)) {
	     	 request.getSession().setAttribute(Constant.AGENT_LOGIN_FOR,Constant.AGENT_LOGIN_DIRECT);	
	    }
	
	    String resetPassword = (String) request.getSession().getAttribute(Constant.PASSWORD_RESET_STATUS);
	    if (!Utility.isEmpty(resetPassword)) {
	    	String username = (String) request.getSession().getAttribute(Constant.EMAIL_ADDRESS);
	    	agentForm.setEmailAddress(username);
	    }
	    
	
		
		return agentForm;
		
	} 
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
	/*	AgentValidator va = new AgentValidator();
		if (!va.validate(command, request, errors)) {
			ModelAndView mode = new ModelAndView(Constant.FORWARD_AGENT_LOGIN);
			return mode;
		}*/
		AgentForm agentForm =  (AgentForm) command;
		request.getSession().setAttribute(Constant.PASSWORD_RESET_STATUS,null);
		String email = agentForm.getEmailAddress();
		
		/**
		 *  We already encrypted password by applying front end public key encryption
		 */
		String password = agentForm.getPassword();		
		
	 
		Utility.setParamAgentId(request);	
	 
	   
		ModelAndView mode=null;
		
		Integer agentId = agentReviewService.findAgentIdByEmailPassword(email, password);
		
	//	LOG.info("Login Agent Id="+agentId);
		
		if (agentId==null) {
			//throw new Exception ("Login Agent could not find agentId");
			mode = new ModelAndView(Constant.FORWARD_AGENT_LOGIN);
			return mode;
		}
		/**
		 *  Save LOGIN AGENT_ID and login agent name
		 *  
		 */
		
		request.getSession().setAttribute(Constant.LOGIN_AGENT_ID,agentId);
		
		Agents agents = quoteDBService.getAgentsDAO().findById(agentId);	
		
		String loginAgentName = agents.getFirstName()+" "+agents.getLastName();
		
		request.getSession().setAttribute(Constant.LOGIN_AGENT_NAME,loginAgentName);
		
		request.getSession().setAttribute(Constant.AGENT_DATA_LIST,null);
		
		/**
		 *  Save agent gmail account for email marketing and reply borrower quote
		 */
		request.getSession().setAttribute(Constant.LOGIN_AGENT_EMAIL,email);
		String decryptedPassword  =  KeyPairManager.getInstance().decrypt(agentForm.getOriginalPassword());
		request.getSession().setAttribute(Constant.LOGIN_AGENT_PASSWORD,decryptedPassword);
		/**
		 *  Save db session 
		 */
		String localMachineName = java.net.InetAddress.getLocalHost().getHostName();
		HttpSession session = request.getSession(true);
		
		LOG.info("Utility.AgentIdProcessor() localMachineName="+localMachineName+",sessionId="+session.getId()); 
		
	    SessionManagerDAO sessionHandler =quoteDBService.getSessionManagerDAO();  
	    
	 
	    SessionManager SM =new SessionManager();
	    
	   
			SM.setClientMachineName(localMachineName);
			SM.setLoginAgentSessionId(session.getId());
			SM.setLoginAgentId(agentId);   
			SM.setParamAgentId(agentId);			
	    	SM.setLoginAgentName(loginAgentName);
	    	SM.setParamAgentName(loginAgentName);
	     
	    	List<SessionManager> smList = sessionHandler.findByClientMachineName(localMachineName);	
	     
    		if (null==smList || 0==smList.size()) {
    			sessionHandler.save(SM);
    		} 
    		if (null!=smList && smList.size()>0) {
    			SessionManager existSM= smList.get(0);
    			SM.setManagerId(existSM.getManagerId());
    			sessionHandler.merge(SM);
    		}
    	
      
		/**
		 *  Once login successfully, must refresh current agentId in session
		 */
		
		if (Utility.getInstance().setCookie(response,Constant.PARAM_AGENT_ID,agentId.toString(), Constant.COOKIE_AGE_DAYS)) {
			 
			LOG.info("AgentLoginController onSubmit() set cookie with login agent Id:"+agentId+" successfully");
		}	
	 
		
		String agentName=null;
		
		//LOG.info("Login authorized agentId="+agentId);
	
		String agentLoginFor = (String) request.getSession().getAttribute(Constant.AGENT_LOGIN_FOR);		
		
		
		if (agentId!=null) {
			/**
			 *  Check Agent Login For (1) just login, (2) agent review (3) agent enter form
			 *  Using redirect because we want to start new session 
			 */
			
			if (Constant.AGENT_REVIEW_QUOTE.equalsIgnoreCase(agentLoginFor) || Constant.AGENT_LOGIN_DIRECT.equalsIgnoreCase(agentLoginFor)) {
				//LOG.info("Agent Review not Login Agent yet....");
				
				mode =new ModelAndView(Constant.AGENT_ENTER_RATE_SHEET_REDIRECT);
				
			} 
			if (Constant.AGENT_NICHE_PROGRAM.equalsIgnoreCase(agentLoginFor)) {
				LOG.info("Agent Niche Program....");
				
				mode =new ModelAndView(Constant.AGENT_NICHE_PROGRAM_REDIRECT);
				
			} 
			if (Constant.AGENT_ENTER_RATE_SHEET.equalsIgnoreCase(agentLoginFor) ) {
				LOG.info("Agent Enter Rate not Login Agent yet....");			
			
				
				   mode =new ModelAndView(Constant.AGENT_ENTER_RATE_SHEET_REDIRECT);		 
			}
			if (Constant.AGENT_RELY_QUOTE_LOGIN.equalsIgnoreCase(agentLoginFor)) {
				LOG.info("Agent Reply Quote....");
				
				mode =new ModelAndView(Constant.AGENT_RELY_QUOTE_LOGIN_REDIRECT);
				
			} 
			if (Constant.AGENT_EMAIL_MARKETING.equalsIgnoreCase(agentLoginFor)) {
				LOG.info("Agent EmailMarketing....");
				
				mode =new ModelAndView(Constant.AGENT_EMAIL_MARKETING_REDIRECT);
				
			} 
		    
		}  
	 	 
		return mode;
		
	}
	 
}
