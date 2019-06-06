package com.loan.agent.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppCheckList entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.loan.agent.dao.AppCheckList
 * @author MyEclipse Persistence Tools
 */

public class AppCheckListDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AppCheckListDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String NOTE = "note";
	public static final String CHOOSE = "choose";

	protected void initDao() {
		// do nothing
	}

	public void save(AppCheckList transientInstance) {
		log.debug("saving AppCheckList instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppCheckList persistentInstance) {
		log.debug("deleting AppCheckList instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppCheckList findById(java.lang.Integer id) {
		log.debug("getting AppCheckList instance with id: " + id);
		try {
			AppCheckList instance = (AppCheckList) getHibernateTemplate().get(
					"com.loan.agent.dao.AppCheckList", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppCheckList instance) {
		log.debug("finding AppCheckList instance by example");
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
		log.debug("finding AppCheckList instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AppCheckList as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByChoose(Object choose) {
		return findByProperty(CHOOSE, choose);
	}

	public List findAll() {
		log.debug("finding all AppCheckList instances");
		try {
			String queryString = "from AppCheckList";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppCheckList merge(AppCheckList detachedInstance) {
		log.debug("merging AppCheckList instance");
		try {
			AppCheckList result = (AppCheckList) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppCheckList instance) {
		log.debug("attaching dirty AppCheckList instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppCheckList instance) {
		log.debug("attaching clean AppCheckList instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AppCheckListDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AppCheckListDAO) ctx.getBean("AppCheckListDAO");
	}
}