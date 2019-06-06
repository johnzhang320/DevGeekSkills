package com.basic.hibernate.service;

import com.basic.hibernate.dao.AgentTable;
import com.basic.hibernate.dao.AgentTableDAO;

public interface AgentTableService {
	public AgentTableDAO getAgentTableDAO();
	public boolean saveOrUpdate(AgentTable agentTable);
}
