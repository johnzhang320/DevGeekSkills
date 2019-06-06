package com.loan.agent.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * County entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.County
 * @author MyEclipse Persistence Tools
 */

public class CountyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(CountyDAO.class);
	// property constants
	public static final String COUNTY_NAME = "countyName";
	public static final String STATE_SYMBOL = "stateSymbol";

	protected void initDao() {
		// do nothing
	}

	public void save(County transientInstance) {
		log.debug("saving County instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(County persistentInstance) {
		log.debug("deleting County instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public County findById(java.lang.Integer id) {
		log.debug("getting County instance with id: " + id);
		try {
			County instance = (County) getHibernateTemplate().get(
					"com.loan.agent.dao.County", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(County instance) {
		log.debug("finding County instance by example");
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
		log.debug("finding County instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from County as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCountyName(Object countyName) {
		return findByProperty(COUNTY_NAME, countyName);
	}

	public List findByStateSymbol(Object stateSymbol) {
		return findByProperty(STATE_SYMBOL, stateSymbol);
	}

	public List findAll() {
		log.debug("finding all County instances");
		try {
			String queryString = "from County";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public County merge(County detachedInstance) {
		log.debug("merging County instance");
		try {
			County result = (County) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(County instance) {
		log.debug("attaching dirty County instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(County instance) {
		log.debug("attaching clean County instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CountyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CountyDAO) ctx.getBean("CountyDAO");
	}
}