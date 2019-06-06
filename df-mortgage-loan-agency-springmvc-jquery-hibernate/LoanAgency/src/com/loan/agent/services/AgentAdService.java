package com.loan.agent.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.loan.agent.calculators.vo.AgentIdVo;
import com.loan.agent.dao.RateSheet;

public interface AgentAdService {
	public void publishRateToSession(HttpServletRequest request,List<RateSheet> list) ;
	public byte[] getImageByteArray(Integer agentId, String fileType);
	public AgentIdVo getAgentId(HttpServletRequest request);
	public void setAgentProfile(HttpServletRequest request,Integer agentId);
	
}
