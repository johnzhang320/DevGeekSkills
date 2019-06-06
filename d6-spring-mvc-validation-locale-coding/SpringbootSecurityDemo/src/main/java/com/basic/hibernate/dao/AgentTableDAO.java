package com.basic.hibernate.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * AgentTable entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.basic.hibernate.dao.AgentTable
 * @author MyEclipse Persistence Tools
 */

public class AgentTableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AgentTableDAO.class);
	// property constants
	public static final String ADDRESS = "address";
	public static final String CELL_PHONE = "cellPhone";
	public static final String CITY = "city";
	public static final String COMPANY_NAME = "companyName";
	public static final String DECRYPTED_PASSWORD = "decryptedPassword";
	public static final String DESCRIPTION = "description";
	public static final String ENCRYPTED_PASSWORD = "encryptedPassword";
	public static final String FULL_NAME = "fullName";
	public static final String GENDER = "gender";
	public static final String HASHED_PASSWORD = "hashedPassword";
	public static final String IP_ADDRESS = "ipAddress";
	public static final String IP_ADDRESS_RANGE = "ipAddressRange";
	public static final String IP_ADDRESS_RANGE_SAMPLE = "ipAddressRangeSample";
	public static final String PASSWORD = "password";
	public static final String STATE = "state";
	public static final String WORK_PHONE = "workPhone";
	public static final String ZIP_CODE = "zipCode";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String EMAIL_ADDRESS = "emailAddress";

	public void save(AgentTable transientInstance) {
		log.debug("saving AgentTable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AgentTable persistentInstance) {
		log.debug("deleting AgentTable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AgentTable findById(java.lang.Integer id) {
		log.debug("getting AgentTable instance with id: " + id);
		try {
			AgentTable instance = (AgentTable) getSession().get(
					"com.basic.hibernate.dao.AgentTable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AgentTable> findByExample(AgentTable instance) {
		log.debug("finding AgentTable instance by example");
		try {
			List<AgentTable> results = (List<AgentTable>) getSession()
					.createCriteria("com.basic.hibernate.dao.AgentTable")
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
		log.debug("finding AgentTable instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AgentTable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AgentTable> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<AgentTable> findByCellPhone(Object cellPhone) {
		return findByProperty(CELL_PHONE, cellPhone);
	}

	public List<AgentTable> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<AgentTable> findByCompanyName(Object companyName) {
		return findByProperty(COMPANY_NAME, companyName);
	}

	public List<AgentTable> findByDecryptedPassword(Object decryptedPassword) {
		return findByProperty(DECRYPTED_PASSWORD, decryptedPassword);
	}

	public List<AgentTable> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<AgentTable> findByEncryptedPassword(Object encryptedPassword) {
		return findByProperty(ENCRYPTED_PASSWORD, encryptedPassword);
	}

	public List<AgentTable> findByFullName(Object fullName) {
		return findByProperty(FULL_NAME, fullName);
	}

	public List<AgentTable> findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List<AgentTable> findByHashedPassword(Object hashedPassword) {
		return findByProperty(HASHED_PASSWORD, hashedPassword);
	}

	public List<AgentTable> findByIpAddress(Object ipAddress) {
		return findByProperty(IP_ADDRESS, ipAddress);
	}

	public List<AgentTable> findByIpAddressRange(Object ipAddressRange) {
		return findByProperty(IP_ADDRESS_RANGE, ipAddressRange);
	}

	public List<AgentTable> findByIpAddressRangeSample(
			Object ipAddressRangeSample) {
		return findByProperty(IP_ADDRESS_RANGE_SAMPLE, ipAddressRangeSample);
	}

	public List<AgentTable> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<AgentTable> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<AgentTable> findByWorkPhone(Object workPhone) {
		return findByProperty(WORK_PHONE, workPhone);
	}

	public List<AgentTable> findByZipCode(Object zipCode) {
		return findByProperty(ZIP_CODE, zipCode);
	}

	public List<AgentTable> findByPublicKey(Object publicKey) {
		return findByProperty(PUBLIC_KEY, publicKey);
	}

	public List<AgentTable> findByEmailAddress(Object emailAddress) {
		return findByProperty(EMAIL_ADDRESS, emailAddress);
	}

	public List findAll() {
		log.debug("finding all AgentTable instances");
		try {
			String queryString = "from AgentTable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AgentTable merge(AgentTable detachedInstance) {
		log.debug("merging AgentTable instance");
		try {
			AgentTable result = (AgentTable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AgentTable instance) {
		log.debug("attaching dirty AgentTable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AgentTable instance) {
		log.debug("attaching clean AgentTable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}