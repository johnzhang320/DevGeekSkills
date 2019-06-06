package com.loan.agent.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Zipcodes entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.Zipcodes
 * @author MyEclipse Persistence Tools
 */

public class ZipcodesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ZipcodesDAO.class);
	// property constants
	public static final String ZIP = "zip";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COUNTY = "county";
	public static final String TYPE = "type";
	public static final String STATE_NAME = "stateName";

	protected void initDao() {
		// do nothing
	}

	public void save(Zipcodes transientInstance) {
		log.debug("saving Zipcodes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Zipcodes persistentInstance) {
		log.debug("deleting Zipcodes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zipcodes findById(java.lang.Integer id) {
		log.debug("getting Zipcodes instance with id: " + id);
		try {
			Zipcodes instance = (Zipcodes) getHibernateTemplate().get(
					"com.loan.agent.dao.Zipcodes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Zipcodes instance) {
		log.debug("finding Zipcodes instance by example");
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
		log.debug("finding Zipcodes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Zipcodes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByZip(Object zip) {
		try {
			String queryString = "select model.state,model.stateName,model.county from Zipcodes as model where model."
					+ "zip" + "= ?";
			return getHibernateTemplate().find(queryString, zip);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			 
		}
		return null;
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByCounty(Object county) {
		return findByProperty(COUNTY, county);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByStateName(Object stateName) {
		return findByProperty(STATE_NAME, stateName);
	}

	public List findAll() {
		log.debug("finding all Zipcodes instances");
		try {
			String queryString = "from Zipcodes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Zipcodes merge(Zipcodes detachedInstance) {
		log.debug("merging Zipcodes instance");
		try {
			Zipcodes result = (Zipcodes) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Zipcodes instance) {
		log.debug("attaching dirty Zipcodes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zipcodes instance) {
		log.debug("attaching clean Zipcodes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ZipcodesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ZipcodesDAO) ctx.getBean("ZipcodesDAO");
	}
}