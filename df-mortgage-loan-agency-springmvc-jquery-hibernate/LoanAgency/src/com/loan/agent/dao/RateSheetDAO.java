package com.loan.agent.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * RateSheet entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.RateSheet
 * @author MyEclipse Persistence Tools
 */

public class RateSheetDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RateSheetDAO.class);
	// property constants
	public static final String AGENT_ID = "agentId";
	public static final String TERM_ID = "termId";
	public static final String TERM = "term";
	public static final String CONFORM_RATE = "conformRate";
	public static final String SUPER_CONFORM_RATE = "superConformRate";
	public static final String JUMBO_RATE = "jumboRate";
	public static final String CONFORM_APR = "conformApr";
	public static final String SUPER_CONFORM_APR = "superConformApr";
	public static final String CREDIT_SCORE = "creditScore";
	public static final String LOAN_TO_VALUE = "loanToValue";

	protected void initDao() {
		// do nothing
	}

	public void save(RateSheet transientInstance) {
		log.debug("saving RateSheet instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RateSheet persistentInstance) {
		log.debug("deleting RateSheet instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RateSheet findById(java.lang.Integer id) {
		log.debug("getting RateSheet instance with id: " + id);
		try {
			RateSheet instance = (RateSheet) getHibernateTemplate().get(
					"com.loan.agent.dao.RateSheet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RateSheet instance) {
		log.debug("finding RateSheet instance by example");
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
		log.debug("finding RateSheet instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RateSheet as model where model."
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

	public List findByTermId(Object termId) {
		return findByProperty(TERM_ID, termId);
	}

	public List findByTerm(Object term) {
		return findByProperty(TERM, term);
	}

	public List findByConformRate(Object conformRate) {
		return findByProperty(CONFORM_RATE, conformRate);
	}

	public List findBySuperConformRate(Object superConformRate) {
		return findByProperty(SUPER_CONFORM_RATE, superConformRate);
	}

	public List findByJumboRate(Object jumboRate) {
		return findByProperty(JUMBO_RATE, jumboRate);
	}

	public List findByConformApr(Object conformApr) {
		return findByProperty(CONFORM_APR, conformApr);
	}

	public List findBySuperConformApr(Object superConformApr) {
		return findByProperty(SUPER_CONFORM_APR, superConformApr);
	}

	public List findByCreditScore(Object creditScore) {
		return findByProperty(CREDIT_SCORE, creditScore);
	}

	public List findByLoanToValue(Object loanToValue) {
		return findByProperty(LOAN_TO_VALUE, loanToValue);
	}

	public List findAll() {
		log.debug("finding all RateSheet instances");
		try {
			String queryString = "from RateSheet";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RateSheet merge(RateSheet detachedInstance) {
		log.debug("merging RateSheet instance");
		try {
			RateSheet result = (RateSheet) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RateSheet instance) {
		log.debug("attaching dirty RateSheet instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RateSheet instance) {
		log.debug("attaching clean RateSheet instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RateSheetDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RateSheetDAO) ctx.getBean("RateSheetDAO");
	}
}