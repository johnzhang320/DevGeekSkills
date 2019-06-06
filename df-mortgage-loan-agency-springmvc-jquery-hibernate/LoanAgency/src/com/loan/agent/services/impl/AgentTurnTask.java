package com.loan.agent.services.impl;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.loan.agent.mvc.controller.CalculatorController;
import com.loan.agent.services.AgentReviewService;
import com.loan.agent.services.LookupDataInitialService;

public class AgentTurnTask extends TimerTask {
	static Logger LOG = Logger.getLogger(CalculatorController.class);
	/*@Autowired
	@Resource(name="AgentReviewService")	 
	private AgentReviewService agentReviewService;
	
	@Autowired
	@Resource(name="LookupDataInitialService")	 
	private LookupDataInitialService lookupDataInitialService;
	
	 
	public AgentReviewService getAgentReviewService() {
		return agentReviewService;
	}
	public void setAgentReviewService(AgentReviewService agentReviewService) {
		this.agentReviewService = agentReviewService;
	}
	public LookupDataInitialService getLookupDataInitialService() {
		return lookupDataInitialService;
	}
	public void setLookupDataInitialService(
			LookupDataInitialService lookupDataInitialService) {
		this.lookupDataInitialService = lookupDataInitialService;
	}*/
	public void run() {
		/**
		 *  obtain next agent following last agent on duty
		 */
	//	Integer agentId = agentReviewService.getAgentOnDuty();
		/**
		 *   Refresh system holding on duty agent information
		 */
	//	lookupDataInitialService.fetchAgentOnDuty(agentId);
		
	//	LOG.info("On Duty Agent="+LookupDataInitialServiceImpl.getAgent().getFirstName()+",email="+LookupDataInitialServiceImpl.getAgent().getEmailAddress());
		LOG.info("Hello World!");
		
	}
	public static void main(String[] args) {
		
		 AgentTurnTask aTask = new AgentTurnTask();
	    	Timer timer = new Timer(true);
		 
		 
	 	 timer.scheduleAtFixedRate(new AgentTurnTask(), 1000,2000);
	}
}
