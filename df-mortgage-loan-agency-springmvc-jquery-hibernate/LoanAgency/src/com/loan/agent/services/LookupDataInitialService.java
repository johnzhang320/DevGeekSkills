package com.loan.agent.services;

import java.util.Map;

import com.loan.agent.dao.Agents;
import com.loan.agent.services.impl.LookupDataInitialServiceImpl;



public interface LookupDataInitialService {
	public void initialize();
	public void cleanUp();
	 
	public void fetchAgentOnDuty(Integer agentId);
	 
}
