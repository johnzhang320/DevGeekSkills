package com.basic.hibernate.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.basic.hibernate.dao.AgentTable;
import com.basic.hibernate.dao.AgentTableDAO;

 

public class AgentTableServiceImpl implements AgentTableService {
	static Logger Log= Logger.getLogger(AgentTableServiceImpl.class);
	
	public AgentTableDAO getAgentTableDAO() {
		return new AgentTableDAO();
	}
	public boolean saveOrUpdate(AgentTable agentTable) {
		AgentTableDAO dao = new AgentTableDAO();
		Session session = dao.getSession();
		List<AgentTable> emails = dao.findByFullName(agentTable.getEmailAddress()); 
		if (emails!=null || emails.size()>0) {
			Log.error("Email Address " + agentTable.getEmailAddress()+" alteady exist!");
			return false;
		}
		List<AgentTable> fullName = dao.findByFullName(agentTable.getFullName()); 
		if (fullName!=null || fullName.size()>0) {
			Log.error("Full Name " + agentTable.getFullName()+" alteady exist!");
			return false;
		}
		
		try {
 		    
 		    session.beginTransaction();
 		    dao.attachDirty(agentTable);
 		    session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			Log.error(e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
}
