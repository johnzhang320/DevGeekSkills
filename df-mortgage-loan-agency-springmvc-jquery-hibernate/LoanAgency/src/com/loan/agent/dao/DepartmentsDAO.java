package com.loan.agent.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Departments entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.loan.agent.dao.Departments
 * @author MyEclipse Persistence Tools
 */

public class DepartmentsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DepartmentsDAO.class);
	// property constants
	public static final String DEPARTMENT_NAME = "departmentName";

	protected void initDao() {
		// do nothing
	}

	public void save(Departments transientInstance) {
		log.debug("saving Departments instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Departments persistentInstance) {
		log.debug("deleting Departments instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Departments findById(java.lang.Integer id) {
		log.debug("getting Departments instance with id: " + id);
		try {
			Departments instance = (Departments) getHibernateTemplate().get(
					"com.loan.agent.dao.Departments", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Departments instance) {
		log.debug("finding Departments instance by example");
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
		log.debug("finding Departments instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Departments as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDepartmentName(Object departmentName) {
		return findByProperty(DEPARTMENT_NAME, departmentName);
	}

	public List findAll() {
		log.debug("finding all Departments instances");
		try {
			String queryString = "from Departments";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Departments merge(Departments detachedInstance) {
		log.debug("merging Departments instance");
		try {
			Departments result = (Departments) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Departments instance) {
		log.debug("attaching dirty Departments instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Departments instance) {
		log.debug("attaching clean Departments instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DepartmentsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DepartmentsDAO) ctx.getBean("DepartmentsDAO");
	}
}