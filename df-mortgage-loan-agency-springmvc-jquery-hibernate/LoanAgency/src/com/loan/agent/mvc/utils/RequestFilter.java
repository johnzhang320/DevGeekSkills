package com.loan.agent.mvc.utils;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.loan.agent.services.Constant;
import com.loan.agent.services.QuoteDBService;

public class RequestFilter implements Filter {
    Logger LOG = Logger.getLogger(RequestFilter.class);
 
   
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//LOG.info("doFilter() begin");
		
	
		HttpServletRequest req = (HttpServletRequest) request;		
		HttpServletResponse resp = (HttpServletResponse) response;
		
	 
		String paramAgentId = req.getParameter(Constant.PARAM_AGENT_ID); 
		String turnOffNiche = req.getParameter(Constant.TURN_OFF_NICHE); 
		
		//LOG.info("doFilter()  turnOffNiche="+turnOffNiche);
		if (null!=turnOffNiche) {
			req.getSession().setAttribute(Constant.TURN_OFF_NICHE, turnOffNiche);
		}
		 
		
		if (paramAgentId!=null) {
		     paramAgentId = ui.getDecodedString(paramAgentId);
		//	 LOG.info("doFilter() Found paramAgentId="+paramAgentId +" , saving to session and database");
			 req.getSession().setAttribute(Constant.PARAM_AGENT_ID, new Integer(paramAgentId));
		}
		 
		/**
		 *  Process Agent Id
		 */
	
		Utility.getInstance().AgentIdProcessor(req); 
		
		//LOG.info("doFilter() end");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}