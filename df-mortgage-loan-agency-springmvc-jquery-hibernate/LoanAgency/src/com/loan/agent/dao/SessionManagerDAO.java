package com.loan.agent.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SessionManager entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.loan.agent.dao.SessionManager
 * @author MyEclipse Persistence Tools
 */

public class SessionManagerDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SessionManagerDAO.class);
	// property constants
	public static final String CLIENT_MACHINE_NAME = "clientMachineName";
	public static final String LOGIN_AGENT_SESSION_ID = "loginAgentSessionId";
	public static final String LOGIN_AGENT_ID = "loginAgentId";
	public static final String PARAM_AGENT_ID = "paramAgentId";
	public static final String LOGIN_AGENT_NAME = "loginAgentName";
	public static final String PARAM_AGENT_NAME = "paramAgentName";

	protected void initDao() {
		// do nothing
	}

	public void save(SessionManager transientInstance) {
		log.debug("saving SessionManager instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SessionManager persistentInstance) {
		log.debug("deleting SessionManager instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SessionManager findById(java.lang.Integer id) {
		log.debug("getting SessionManager instance with id: " + id);
		try {
			SessionManager instance = (SessionManager) getHibernateTemplate()
					.get("com.loan.agent.dao.SessionManager", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SessionManager instance) {
		log.debug("finding SessionManager instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SessionManager instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SessionManager as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClientMachineName(Object clientMachineName) {
		return findByProperty(CLIENT_MACHINE_NAME, clientMachineName);
	}

	public List findByLoginAgentSessionId(Object loginAgentSessionId) {
		return findByProperty(LOGIN_AGENT_SESSION_ID, loginAgentSessionId);
	}

	public List findByLoginAgentId(Object loginAgentId) {
		return findByProperty(LOGIN_AGENT_ID, loginAgentId);
	}

	public List findByParamAgentId(Object paramAgentId) {
		return findByProperty(PARAM_AGENT_ID, paramAgentId);
	}

	public List findByLoginAgentName(Object loginAgentName) {
		return findByProperty(LOGIN_AGENT_NAME, loginAgentName);
	}

	public List findByParamAgentName(Object paramAgentName) {
		return findByProperty(PARAM_AGENT_NAME, paramAgentName);
	}

	public List findAll() {
		log.debug("finding all SessionManager instances");
		try {
			String queryString = "from SessionManager";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SessionManager merge(SessionManager detachedInstance) {
		log.debug("merging SessionManager instance");
		try {
			SessionManager result = (SessionManager) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SessionManager instance) {
		log.debug("attaching dirty SessionManager instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SessionManager instance) {
		log.debug("attaching clean SessionManager instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SessionManagerDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SessionManagerDAO) ctx.getBean("SessionManagerDAO");
	}
}