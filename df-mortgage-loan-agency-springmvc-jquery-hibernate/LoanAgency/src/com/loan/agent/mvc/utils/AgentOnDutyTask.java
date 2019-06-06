package com.loan.agent.mvc.utils;

import java.util.TimerTask;



import org.apache.log4j.Logger;


import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.LookupDataInitialService;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

 

public class AgentOnDutyTask extends TimerTask {
	 
	
	private static final Logger LOG  = Logger.getLogger (AgentOnDutyTask.class);

	@Override
	public void run() {
        LOG.info("AgentOnDutyTask begin");
		 
		AgentReviewService agentReviewService =(AgentReviewService) SpringFramework.getBean("AgentReviewService");
		Integer agentId = agentReviewService.getAgentOnDuty();		
		
		
		/**
		 *   Refresh system holding on duty agent information
		 */
		LookupDataInitialService lookupDataInitialService =(LookupDataInitialService) SpringFramework.getBean("LookupDataInitialService");
	
		lookupDataInitialService.fetchAgentOnDuty(agentId);
		
		LOG.info("Hello "+LookupDataInitialServiceImpl.getAgent().getFirstName());
		LOG.info("AgentOnDutyTask end");
		
	}
}
