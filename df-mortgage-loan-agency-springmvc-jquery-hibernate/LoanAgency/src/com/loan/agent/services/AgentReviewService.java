package com.loan.agent.services;

import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.dao.Agents;

import java.util.List;

public interface AgentReviewService {
	public List<AgentReviewQuoteVo> getAgentQuoteInquire(Integer agentId);
	public Integer getAgentOnDuty();
	public Integer findAgentIdByEmailPassword(String email, String password);
	public void updateAgents (Agents agents);
	public Integer findAgentIdByEmail(String email);
}
