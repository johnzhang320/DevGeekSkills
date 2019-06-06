package com.loan.agent.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loan.agent.dao.Users;
import com.loan.agent.dao.UsersDAO;
import com.loan.agent.mvc.formbean.LoginForm;
import com.loan.agent.mvc.formbean.QuoteForm;
import com.loan.agent.mvc.utils.SpringFramework;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.QuoteDBService;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

import org.apache.log4j.*;

public class UserSignupController extends SimpleFormController {
	Logger LOG = Logger.getLogger(UserSignupController.class);
	
	@Autowired
	@Resource(name="QuoteDBService")
	private QuoteDBService quoteDBService;
	
	private String viewForm=null;
	public UserSignupController() {
		setCommandName("loginForm");
		setCommandClass(LoginForm.class);
		
		 
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		LoginForm loginForm = new LoginForm();
		Integer userId =(Integer) request.getSession().getAttribute("LoginUserId");
		if (userId!=null) {
			Users users = quoteDBService.getUsersDAO().findById(userId);
			if (users!=null) {
				loginForm.renderUser(users);
			}
		}
		Utility.setParamAgentId(request);
		return loginForm;
		
	}

 
	@Override
	public Map referenceData(HttpServletRequest request) {
		Map referenceData = new HashMap(); 
		 
		Map<String,String> stateMap = LookupDataInitialServiceImpl.getStateMap();
		 
		referenceData.put("stateMap", stateMap);

		 
		return referenceData;
	}

	public QuoteDBService getQuoteDBService() {
		return quoteDBService;
	}

	public void setQuoteDBService(QuoteDBService quoteDBService) {
		this.quoteDBService = quoteDBService;
	}
	
	
}
