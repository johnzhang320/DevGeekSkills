package com.hibernate.one.to.many.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.one.to.many.model.DmarcDetailReports;

import static org.hibernate.criterion.Example.create;
 

/**
 * A data access object (DAO) providing persistence and search support for
 * DmarcDetailReports entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.basic.hibernate.dao.DmarcDetailReports
 * @author MyEclipse Persistence Tools
 */

public class DmarcDetailReportsDAO extends BaseHibernateDAO {
	private static final Logger log = Logger
			.getLogger(DmarcDetailReportsDAO.class);
	// property constants
	public static final String SOURCE_IP = "sourceIp";
	public static final String COUNT = "count";
	public static final String DISPOSITION = "disposition";
	public static final String REASON_COMMENT = "reasonComment";
	public static final String REASON_TYPE = "reasonType";
	public static final String FROM_HEADER_DOMAIN = "fromHeaderDomain";
	public static final String DKIM_DOMAIN = "dkimDomain";
	public static final String DKIM_RESULT = "dkimResult";
	public static final String DKIM_HUMAN_RESULT = "dkimHumanResult";
	public static final String SPF_DOMAIN = "spfDomain";
	public static final String SPF_RESULT = "spfResult";
	public static final String ALIGNMENT = "alignment";
	public static final String ALIGNMENT_DESCRIPTION = "alignmentDescription";
	public static final String ROW_COUNT = "rowCount";

	public void save(DmarcDetailReports transientInstance) {
		log.debug("saving DmarcDetailReports instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DmarcDetailReports persistentInstance) {
		log.debug("deleting DmarcDetailReports instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DmarcDetailReports findById(java.lang.Integer id) {
		log.debug("getting DmarcDetailReports instance with id: " + id);
		try {
			DmarcDetailReports instance = (DmarcDetailReports) getSession()
					.get("com.basic.hibernate.dao.DmarcDetailReports", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DmarcDetailReports> findByExample(DmarcDetailReports instance) {
		log.debug("finding DmarcDetailReports instance by example");
		try {
			List<DmarcDetailReports> results = (List<DmarcDetailReports>) getSession()
					.createCriteria(
							"com.basic.hibernate.dao.DmarcDetailReports")
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
		log.debug("finding DmarcDetailReports instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DmarcDetailReports as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DmarcDetailReports> findBySourceIp(Object sourceIp) {
		return findByProperty(SOURCE_IP, sourceIp);
	}

	public List<DmarcDetailReports> findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List<DmarcDetailReports> findByDisposition(Object disposition) {
		return findByProperty(DISPOSITION, disposition);
	}

	public List<DmarcDetailReports> findByReasonComment(Object reasonComment) {
		return findByProperty(REASON_COMMENT, reasonComment);
	}

	public List<DmarcDetailReports> findByReasonType(Object reasonType) {
		return findByProperty(REASON_TYPE, reasonType);
	}

	public List<DmarcDetailReports> findByFromHeaderDomain(
			Object fromHeaderDomain) {
		return findByProperty(FROM_HEADER_DOMAIN, fromHeaderDomain);
	}

	public List<DmarcDetailReports> findByDkimDomain(Object dkimDomain) {
		return findByProperty(DKIM_DOMAIN, dkimDomain);
	}

	public List<DmarcDetailReports> findByDkimResult(Object dkimResult) {
		return findByProperty(DKIM_RESULT, dkimResult);
	}

	public List<DmarcDetailReports> findByDkimHumanResult(Object dkimHumanResult) {
		return findByProperty(DKIM_HUMAN_RESULT, dkimHumanResult);
	}

	public List<DmarcDetailReports> findBySpfDomain(Object spfDomain) {
		return findByProperty(SPF_DOMAIN, spfDomain);
	}

	public List<DmarcDetailReports> findBySpfResult(Object spfResult) {
		return findByProperty(SPF_RESULT, spfResult);
	}

	public List<DmarcDetailReports> findByAlignment(Object alignment) {
		return findByProperty(ALIGNMENT, alignment);
	}

	public List<DmarcDetailReports> findByAlignmentDescription(
			Object alignmentDescription) {
		return findByProperty(ALIGNMENT_DESCRIPTION, alignmentDescription);
	}

	public List<DmarcDetailReports> findByRowCount(Object rowCount) {
		return findByProperty(ROW_COUNT, rowCount);
	}

	public List findAll() {
		log.debug("finding all DmarcDetailReports instances");
		try {
			String queryString = "from DmarcDetailReports";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DmarcDetailReports merge(DmarcDetailReports detachedInstance) {
		log.debug("merging DmarcDetailReports instance");
		try {
			DmarcDetailReports result = (DmarcDetailReports) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DmarcDetailReports instance) {
		log.debug("attaching dirty DmarcDetailReports instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DmarcDetailReports instance) {
		log.debug("attaching clean DmarcDetailReports instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}