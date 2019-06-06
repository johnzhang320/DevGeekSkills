package com.loan.agent.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for State
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.loan.agent.dao.State
 * @author MyEclipse Persistence Tools
 */

public class StateDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(StateDAO.class);
	// property constants
	public static final String STATE_SYMBOL = "stateSymbol";
	public static final String STATE_NAME = "stateName";

	protected void initDao() {
		// do nothing
	}

	public void save(State transientInstance) {
		log.debug("saving State instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(State persistentInstance) {
		log.debug("deleting State instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public State findById(java.lang.Integer id) {
		log.debug("getting State instance with id: " + id);
		try {
			State instance = (State) getHibernateTemplate().get(
					"com.loan.agent.dao.State", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(State instance) {
		log.debug("finding State instance by example");
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
		log.debug("finding State instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from State as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStateSymbol(Object stateSymbol) {
		return findByProperty(STATE_SYMBOL, stateSymbol);
	}

	public List findByStateName(Object stateName) {
		return findByProperty(STATE_NAME, stateName);
	}

	public List findAll() {
		log.debug("finding all State instances");
		try {
			String queryString = "from State";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public State merge(State detachedInstance) {
		log.debug("merging State instance");
		try {
			State result = (State) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(State instance) {
		log.debug("attaching dirty State instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(State instance) {
		log.debug("attaching clean State instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StateDAO) ctx.getBean("StateDAO");
	}
}