package com.loan.agent.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * CreditCard entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.CreditCard
 * @author MyEclipse Persistence Tools
 */

public class CreditCardDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CreditCardDAO.class);
	// property constants
	public static final String AGENT_ID = "agentId";
	public static final String HOLDER_FIRST_NAME = "holderFirstName";
	public static final String HOLDER_LAST_NAME = "holderLastName";
	public static final String CREDIT_CARD_NO = "creditCardNo";
	public static final String VERIFY_NO = "verifyNo";
	public static final String EXPIRE_DATE = "expireDate";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP_CODE = "zipCode";

	protected void initDao() {
		// do nothing
	}

	public void save(CreditCard transientInstance) {
		log.debug("saving CreditCard instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CreditCard persistentInstance) {
		log.debug("deleting CreditCard instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CreditCard findById(java.lang.Integer id) {
		log.debug("getting CreditCard instance with id: " + id);
		try {
			CreditCard instance = (CreditCard) getHibernateTemplate().get(
					"com.loan.agent.dao.CreditCard", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CreditCard instance) {
		log.debug("finding CreditCard instance by example");
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
		log.debug("finding CreditCard instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CreditCard as model where model."
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

	public List findByHolderFirstName(Object holderFirstName) {
		return findByProperty(HOLDER_FIRST_NAME, holderFirstName);
	}

	public List findByHolderLastName(Object holderLastName) {
		return findByProperty(HOLDER_LAST_NAME, holderLastName);
	}

	public List findByCreditCardNo(Object creditCardNo) {
		return findByProperty(CREDIT_CARD_NO, creditCardNo);
	}

	public List findByVerifyNo(Object verifyNo) {
		return findByProperty(VERIFY_NO, verifyNo);
	}

	public List findByExpireDate(Object expireDate) {
		return findByProperty(EXPIRE_DATE, expireDate);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByZipCode(Object zipCode) {
		return findByProperty(ZIP_CODE, zipCode);
	}

	public List findAll() {
		log.debug("finding all CreditCard instances");
		try {
			String queryString = "from CreditCard";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CreditCard merge(CreditCard detachedInstance) {
		log.debug("merging CreditCard instance");
		try {
			CreditCard result = (CreditCard) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CreditCard instance) {
		log.debug("attaching dirty CreditCard instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CreditCard instance) {
		log.debug("attaching clean CreditCard instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CreditCardDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CreditCardDAO) ctx.getBean("CreditCardDAO");
	}
}