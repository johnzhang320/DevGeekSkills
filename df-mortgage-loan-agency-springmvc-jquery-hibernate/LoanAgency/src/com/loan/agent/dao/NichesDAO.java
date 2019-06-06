package com.loan.agent.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Niches entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.Niches
 * @author MyEclipse Persistence Tools
 */

public class NichesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(NichesDAO.class);
	// property constants
	public static final String AGENT_ID = "agentId";
	public static final String NICHE_TITLE = "nicheTitle";
	public static final String NICHE_NOTE = "nicheNote";
	public static final String NICHE_CHOOSE = "nicheChoose";

	protected void initDao() {
		// do nothing
	}

	public void save(Niches transientInstance) {
		log.debug("saving Niches instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Niches persistentInstance) {
		log.debug("deleting Niches instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Niches findById(java.lang.Integer id) {
		log.debug("getting Niches instance with id: " + id);
		try {
			Niches instance = (Niches) getHibernateTemplate().get(
					"com.loan.agent.dao.Niches", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Niches instance) {
		log.debug("finding Niches instance by example");
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
		log.debug("finding Niches instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Niches as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAgentId(Object agentId) {
		return findByProperty(AGENT_ID, agentId);
	}

	public List findByNicheTitle(Object nicheTitle) {
		return findByProperty(NICHE_TITLE, nicheTitle);
	}

	public List findByNicheNote(Object nicheNote) {
		return findByProperty(NICHE_NOTE, nicheNote);
	}

	public List findByNicheChoose(Object nicheChoose) {
		return findByProperty(NICHE_CHOOSE, nicheChoose);
	}

	public List findAll() {
		log.debug("finding all Niches instances");
		try {
			String queryString = "from Niches";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Niches merge(Niches detachedInstance) {
		log.debug("merging Niches instance");
		try {
			Niches result = (Niches) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Niches instance) {
		log.debug("attaching dirty Niches instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Niches instance) {
		log.debug("attaching clean Niches instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NichesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (NichesDAO) ctx.getBean("NichesDAO");
	}
}