package com.basic.hibernate.service;

import org.springframework.stereotype.Service;

import com.basic.hibernate.dao.AgentTable;
import com.basic.hibernate.dao.AgentTableDAO;
 
public interface AgentTableService {
	public AgentTableDAO getAgentTableDAO();
	public boolean saveOrUpdate(AgentTable agentTable);
}
