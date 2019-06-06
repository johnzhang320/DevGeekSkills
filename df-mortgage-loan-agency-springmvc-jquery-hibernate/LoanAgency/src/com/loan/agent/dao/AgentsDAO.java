package com.loan.agent.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loan.agent.services.impl.LookupDataInitialServiceImpl;

/**
 * A data access object (DAO) providing persistence and search support for
 * Agents entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.Agents
 * @author MyEclipse Persistence Tools
 */

public class AgentsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AgentsDAO.class);
	// property constants
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String PASSWORD = "password";
	public static final String COMPANY_NAME = "companyName";
	public static final String WORK_PHONE = "workPhone";
	public static final String CELL_PHONE = "cellPhone";
	public static final String PICTURE_CONTENT = "pictureContent";
	public static final String PICTURE_FILENAME = "pictureFilename";
	public static final String APPLICATION_FORM_FILENAME = "applicationFormFileName";
	public static final String PICTURE_TYPE = "pictureType";
	public static final String APP_FORM_TYPE = "appFormType";
	public static final String GENDAR = "gendar";
	public static final String ADDRESS = "address";
	public static final String STATE = "state";
	public static final String ZIP_CODE = "zipCode";
	public static final String DESCRIPTION = "description";
	public static final String LICENSE_NO = "licenseNo";
	public static final String DRE_NO = "dreNo";
	public static final String NMLS_NO = "nmlsNo";
	public static final String LICENSE_ELIGIBLE_STATE = "licenseEligibleState";
	public static final String CITY = "city";
	public static final String NICHE_INTRO = "nicheIntro";

	protected void initDao() {
		// do nothing
	}

	public void save(Agents transientInstance) {
		log.debug("saving Agents instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Agents persistentInstance) {
		log.debug("deleting Agents instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Agents findById(java.lang.Integer id) {
		/**
		 *  If no agent id is available , the pick up initialization agent id 
		 */
		if (id==null) {
			Agents agentInit = LookupDataInitialServiceImpl.getAgent();
			id= agentInit.getAgentId();
		}
		log.debug("getting Agents instance with id: " + id);
		try {
			Agents instance = (Agents) getHibernateTemplate().get(
					"com.loan.agent.dao.Agents", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Agents instance) {
		log.debug("finding Agents instance by example");
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
		log.debug("finding Agents instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Agents as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	public List findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	public List findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByCompanyName(Object companyName) {
		return findByProperty(COMPANY_NAME, companyName);
	}

	public List findByWorkPhone(Object workPhone) {
		return findByProperty(WORK_PHONE, workPhone);
	}

	public List findByCellPhone(Object cellPhone) {
		return findByProperty(CELL_PHONE, cellPhone);
	}

	public List findByPictureContent(Object pictureContent) {
		return findByProperty(PICTURE_CONTENT, pictureContent);
	}

	public List findByPictureFilename(Object pictureFilename) {
		return findByProperty(PICTURE_FILENAME, pictureFilename);
	}
	public List findByApplicationFormFilename(Object applicationFormFilename) {
		return findByProperty(APPLICATION_FORM_FILENAME, applicationFormFilename);
	}
	public List findByPictureType(Object pictureType) {
		return findByProperty(PICTURE_TYPE, pictureType);
	}
	public List findByAppFormType(Object appFormType) {
		return findByProperty(APP_FORM_TYPE, appFormType);
	}
	public List findByGendar(Object gendar) {
		return findByProperty(GENDAR, gendar);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByZipCode(Object zipCode) {
		return findByProperty(ZIP_CODE, zipCode);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByLicenseNo(Object licenseNo) {
		return findByProperty(LICENSE_NO, licenseNo);
	}
	public List findByDreNo(Object dreNo) {
		return findByProperty(DRE_NO, dreNo);
	}
	public List findByNmlsNo(Object nmlsNo) {
		return findByProperty(NMLS_NO, nmlsNo);
	}

	public List findByLicenseEligibleState(Object licenseEligibleState) {
		return findByProperty(LICENSE_ELIGIBLE_STATE, licenseEligibleState);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}
	public List findByNicheIntro(Object nicheIntro) {
		return findByProperty(NICHE_INTRO, nicheIntro);
	}
	public List findAll() {
		log.debug("finding all Agents instances");
		try {
			String queryString = "from Agents";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Agents merge(Agents detachedInstance) {
		log.debug("merging Agents instance");
		try {
			Agents result = (Agents) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Agents instance) {
		log.debug("attaching dirty Agents instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Agents instance) {
		log.debug("attaching clean Agents instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AgentsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AgentsDAO) ctx.getBean("AgentsDAO");
	}
}