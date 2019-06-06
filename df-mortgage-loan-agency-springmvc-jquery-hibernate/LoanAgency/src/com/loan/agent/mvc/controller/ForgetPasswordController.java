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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;

 
import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.dao.Agents;
import com.loan.agent.dao.RateSheet;
import com.loan.agent.dao.RateSheetDAO;
import com.loan.agent.dao.SessionManager;
import com.loan.agent.dao.SessionManagerDAO;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.EmailServerForm;
import com.loan.agent.mvc.formbean.RateSheetForm;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.mvc.utils.ui;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;
import com.send.email.smtp.ssl.RecipientVo;
import com.send.email.smtp.ssl.eUtil;
 
public class ForgetPasswordController extends SimpleFormController {
Logger LOG = Logger.getLogger( ForgetPasswordController.class);
	
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService; 
	
	@Autowired
	@Resource(name="AgentReviewService")
	private AgentReviewService agentReviewService;
		
	 
	public ForgetPasswordController() {
		setCommandName("agentForm");
		setCommandClass(AgentForm.class);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
	    Utility.setRequest(request);
		String prevPageEmailAddress = (String) request.getParameter(Constant.PREV_PAGE_EMAILADDRESS);		
		AgentForm agentForm = new AgentForm();
		agentForm.setEmailAddress(prevPageEmailAddress);
		return agentForm;
		
	} 
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		
		AgentForm agentForm =  (AgentForm) command;
		
		String email = agentForm.getEmailAddress();		
	 
		Utility.setParamAgentId(request);		 
	   
		ModelAndView mode=null;
		
		Integer agentId = agentReviewService.findAgentIdByEmail(email);
		
	 
		
		if (agentId==null) {
			throw new Exception ("Login Agent could not find agentId");
		}
	 
		
		Agents agents = quoteDBService.getAgentsDAO().findById(agentId);	
		
		EmailServerForm form = new EmailServerForm();		
		
		 
		String paramAgentIdStr = ui.getEncodedString(agentId.toString());	   	   
	   	   
   	    String resetPasswordLink =SysPath.getHostName()+"/resetPassword.html?paramAgentId="+paramAgentIdStr;
   	    
   	    LOG.info("resetPasswordLink ="+resetPasswordLink);
   	    
   	    String maskLink = "<a href=\""+resetPasswordLink+"\"><span style=\"font-size:12px\">Reset Password Click here.</span> </a>";
   	    
   	    String agentName = agents.getFirstName();
   	    
		String content="<!--[if lt IE 9]>		<script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>	<![endif]-->"+		
		               "<dir>We received your password reset request.<br/>Thanks for your requesting loans-agent.com. <br/><br/>"+
				       "Your username is "+email+".<br/>"+
		               "Please click on below link to reset your password:"+"<br/>"+maskLink+"<br/><br/>"+
				       "Please contact us if you have any problem.<br/><br/>"+
				       "Thanks and Best Regards !<br/><br/>"+
		               "Support@loans-agent.com</div>";                      
		               
		
		String subject = "Reset Password for Loans-agent.com";
		
		form.setEmailContent(content);
		
	    String RecipientEmailLine =agentName+","+ email;
		
		form.setFromEmailAddress(Constant.DELIVER_FROM_EMAIL);
		
		form.setSubject(subject);			 
		
		
	 	LOG.info("From Email Address is "+form.getFromEmailAddress());
		    
		List<RecipientVo> rList = eUtil.generateRecipientVoList(RecipientEmailLine);
		boolean result= ui.sendSingleEmailWrap (
				         request,			 
				         form,
				         Constant.DELIVER_SMTP_SERVER,
				         Constant.DELIVER_AUTH_USERNAME,
				         Constant.DELIVER_AUTH_PASSWORD,
				         rList
				);     		   
		    
		if (result) {
		     LOG.info("From "+form.getFromEmailAddress()+", send reset password email to "+email+" successfully");
		     request.getSession().setAttribute(Constant.EMAIL_ADDRESS, form.getEmailAddress());
		     mode =new ModelAndView(Constant.FORWARD_FORGET_PASSWARD_SUCCESS);   
	    } else {
		   LOG.info("Send to myself failed!");
	    }
	 	 
		return mode;
		
	}
	 
}
