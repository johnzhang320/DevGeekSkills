package com.hibernate.one.to.many.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
 
import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.one.to.many.model.DmarcMasterReports;

/**
 * A data access object (DAO) providing persistence and search support for
 * DmarcMasterReports entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.basic.hibernate.dao.DmarcMasterReports
 * @author MyEclipse Persistence Tools
 */

public class DmarcMasterReportsDAO extends BaseHibernateDAO {
	private static final Logger log = Logger
			.getLogger(DmarcMasterReportsDAO.class);
	// property constants
	public static final String ORG_NAME = "orgName";
	public static final String EMAIL = "email";
	public static final String EXTRA_CONTRACT_INFO = "extraContractInfo";
	public static final String REPORT_ID = "reportId";
	public static final String DOMAIN = "domain";
	public static final String ADKIM = "adkim";
	public static final String ASPF = "aspf";
	public static final String PCT = "pct";
	public static final String SP = "sp";
	public static final String TRANSACTION_DONE = "transactionDone";
	public static final String HOST_NAME = "hostName";
	public static final String RUA_FILE_NAME = "ruaFileName";
	public static final String POLICY_STRING = "policyString";

	public void save(DmarcMasterReports transientInstance) {
		log.debug("saving DmarcMasterReports instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DmarcMasterReports persistentInstance) {
		log.debug("deleting DmarcMasterReports instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DmarcMasterReports findById(java.lang.Integer id) {
		log.debug("getting DmarcMasterReports instance with id: " + id);
		try {
			DmarcMasterReports instance = (DmarcMasterReports) getSession()
					.get("com.basic.hibernate.dao.DmarcMasterReports", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DmarcMasterReports> findByExample(DmarcMasterReports instance) {
		log.debug("finding DmarcMasterReports instance by example");
		try {
			List<DmarcMasterReports> results = (List<DmarcMasterReports>) getSession()
					.createCriteria(
							"com.basic.hibernate.dao.DmarcMasterReports")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DmarcMasterReports instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DmarcMasterReports as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DmarcMasterReports> findByOrgName(Object orgName) {
		return findByProperty(ORG_NAME, orgName);
	}

	public List<DmarcMasterReports> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<DmarcMasterReports> findByExtraContractInfo(
			Object extraContractInfo) {
		return findByProperty(EXTRA_CONTRACT_INFO, extraContractInfo);
	}

	public List<DmarcMasterReports> findByReportId(Object reportId) {
		return findByProperty(REPORT_ID, reportId);
	}

	public List<DmarcMasterReports> findByDomain(Object domain) {
		return findByProperty(DOMAIN, domain);
	}

	public List<DmarcMasterReports> findByAdkim(Object adkim) {
		return findByProperty(ADKIM, adkim);
	}

	public List<DmarcMasterReports> findByAspf(Object aspf) {
		return findByProperty(ASPF, aspf);
	}

	public List<DmarcMasterReports> findByPct(Object pct) {
		return findByProperty(PCT, pct);
	}

	public List<DmarcMasterReports> findBySp(Object sp) {
		return findByProperty(SP, sp);
	}

	public List<DmarcMasterReports> findByTransactionDone(Object transactionDone) {
		return findByProperty(TRANSACTION_DONE, transactionDone);
	}

	public List<DmarcMasterReports> findByHostName(Object hostName) {
		return findByProperty(HOST_NAME, hostName);
	}

	public List<DmarcMasterReports> findByRuaFileName(Object ruaFileName) {
		return findByProperty(RUA_FILE_NAME, ruaFileName);
	}

	public List<DmarcMasterReports> findByPolicyString(Object policyString) {
		return findByProperty(POLICY_STRING, policyString);
	}

	public List findAll() {
		log.debug("finding all DmarcMasterReports instances");
		try {
			String queryString = "from DmarcMasterReports";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DmarcMasterReports merge(DmarcMasterReports detachedInstance) {
		log.debug("merging DmarcMasterReports instance");
		try {
			DmarcMasterReports result = (DmarcMasterReports) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DmarcMasterReports instance) {
		log.debug("attaching dirty DmarcMasterReports instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DmarcMasterReports instance) {
		log.debug("attaching clean DmarcMasterReports instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}