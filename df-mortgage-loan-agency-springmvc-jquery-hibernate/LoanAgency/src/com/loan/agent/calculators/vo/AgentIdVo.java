package com.loan.agent.calculators.vo;

import java.io.Serializable;

import com.loan.agent.dao.Agents;

public class AgentIdVo implements Serializable {
	 private static final long serialVersionUID = 1L;
	Integer agentId=null;
	/**
	 * public static final String LOGIN_AGENT_ID ="LoginAgentId";
	 *
     *
	 *
	 *  public static final String PARAM_SAVED_AGENT_ID ="ParamSavedAgentId";
	 *
	 */
	String agentIdType=null;
	
	Agents agents=null;
	
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	public String getAgentIdType() {
		return agentIdType;
	}
	public void setAgentIdType(String agentIdType) {
		this.agentIdType = agentIdType;
	}
	public Agents getAgents() {
		return agents;
	}
	public void setAgents(Agents agents) {
		this.agents = agents;
	}
	
	
}
