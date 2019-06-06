package com.loan.agent.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.Constant;

public class RedirectController extends ParameterizableViewController {
	// inject this message from property of bean of ParameterizableViewController 
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	protected ModelAndView handeRequestInternal(HttpServletRequest request,	
			HttpServletResponse response) throws Exception { 
		    // Display Advertisement
		    	Utility.setRequest(request,response);
		    ModelAndView mode = new ModelAndView(getViewName());
		    if (message!=null) {
		    	mode.addObject(Constant.REDIRECT_MESSAGE,message);
		    }
		    return mode;
		
	}
}
