package com.loan.agent.mvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.calculators.vo.UserReviewQuoteVo;
import com.loan.agent.dao.Users;
import com.loan.agent.mvc.formbean.AgentForm;
import com.loan.agent.mvc.formbean.LoginForm;
import com.loan.agent.mvc.formbean.QuoteForm;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.UserReviewService;

public class UserLoginController extends SimpleFormController {
	Logger LOG = Logger.getLogger(UserLoginController.class);
	
	@Autowired
	@Resource(name="UserReviewService")
	private UserReviewService userReviewService;
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	private String viewForm=null;
	public UserLoginController() {
		setCommandName("loginForm");
		setCommandClass(LoginForm.class);	
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception { 	
		Utility.setParamAgentId(request);
		LoginForm userForm =  (LoginForm) command;
		
		String email = userForm.getEmailAddress();
		
		String password= userForm.getPassword();
		
		LOG.info("email="+email+",password="+password);
		
	    request.getSession().setAttribute("userAction","login");
	   
		ModelAndView mode=null;
		
		Integer userId =  userReviewService.findUserIdByEmailPassword(email, password);
	 
		
		LOG.info("Login authorized userId="+userId);
		/**
		 *  If username and password are authorised 
		 */
		String userName =null;
		if (userId!=null) {
			 		// fetch list from Database
			List<UserReviewQuoteVo> list = userReviewService.getUserQuoteInquire(userId);
			UserReviewQuoteVo currentVo  = list.get(0);
			 userName = currentVo.getFirst_name()+" "+ currentVo.getLast_name();  
			
		 
			request.getSession().setAttribute("QuoteList", list);
			
			for (UserReviewQuoteVo vo:list) vo=null;
			
			request.getSession().setAttribute("LoginUserId", userId);
			 

		    mode =new ModelAndView("UserReviewQuote");
		    
		    
		}  
		request.getSession().setAttribute("LoginUserId", userId);
		request.getSession().setAttribute("UserName", userName); 
		return mode;
		
	}

	public UserReviewService getUserReviewService() {
		return userReviewService;
	}

	public void setUserReviewService(UserReviewService userReviewService) {
		this.userReviewService = userReviewService;
	}

	 
	 
}
