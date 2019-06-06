package com.loan.agent.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Quote
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.loan.agent.dao.Quote
 * @author MyEclipse Persistence Tools
 */

public class QuoteDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(QuoteDAO.class);
	// property constants
	public static final String LOAN_TYPE = "loanType";
	public static final String LOAN_AMOUNT = "loanAmount";
	public static final String PURCHASE_PRICE = "purchasePrice";
	public static final String PROPERTY_COUNTY = "propertyCounty";
	public static final String PURCHASE_STAGE = "purchaseStage";
	public static final String PROPERTY_ADDRESS = "propertyAddress";
	public static final String PROPERTY_CITY = "propertyCity";
	public static final String PROPERTY_ZIP_CODE = "propertyZipCode";
	public static final String PROPERTY_TYPE = "propertyType";
	public static final String OCCUPANCY_STATUS = "occupancyStatus";
	public static final String NOTE = "note";
	public static final String ESTIMATE_HOME_VALUE = "estimateHomeValue";
	public static final String REFINANCE_CASH_OUT = "refinanceCashOut";
	public static final String FIRST_LOAN_BALANCE = "firstLoanBalance";
	public static final String FIRST_LOAN_RATE = "firstLoanRate";
	public static final String FIRST_LOAN_TERM = "firstLoanTerm";
	public static final String SECOND_LOAN_BALANCE = "secondLoanBalance";
	public static final String SECOND_LOAN_RATE = "secondLoanRate";
	public static final String SECOND_LOAN_TERM = "secondLoanTerm";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String PHONE_NO = "phoneNo";
	public static final String ANNUAL_INCOME = "annualIncome";
	public static final String BORROWER_CREDIT_SCORE = "borrowerCreditScore";
	public static final String CO_BORROWER_CREDIT_SCORE = "coBorrowerCreditScore";
	
	protected void initDao() {
		// do nothing
	}

	public void save(Quote transientInstance) {
		log.debug("saving Quote instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Quote persistentInstance) {
		log.debug("deleting Quote instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Quote findById(java.lang.Integer id) {
		log.debug("getting Quote instance with id: " + id);
		try {
			Quote instance = (Quote) getHibernateTemplate().get(
					"com.loan.agent.dao.Quote", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Quote instance) {
		log.debug("finding Quote instance by example");
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
		log.debug("finding Quote instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Quote as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoanType(Object loanType) {
		return findByProperty(LOAN_TYPE, loanType);
	}

	public List findByLoanAmount(Object loanAmount) {
		return findByProperty(LOAN_AMOUNT, loanAmount);
	}

	public List findByPurchasePrice(Object purchasePrice) {
		return findByProperty(PURCHASE_PRICE, purchasePrice);
	}

	public List findByPropertyCounty(Object propertyCounty) {
		return findByProperty(PROPERTY_COUNTY, propertyCounty);
	}

	public List findByPurchaseStage(Object purchaseStage) {
		return findByProperty(PURCHASE_STAGE, purchaseStage);
	}

	public List findByPropertyAddress(Object propertyAddress) {
		return findByProperty(PROPERTY_ADDRESS, propertyAddress);
	}

	public List findByPropertyCity(Object propertyCity) {
		return findByProperty(PROPERTY_CITY, propertyCity);
	}

	public List findByPropertyZipCode(Object propertyZipCode) {
		return findByProperty(PROPERTY_ZIP_CODE, propertyZipCode);
	}

	public List findByPropertyType(Object propertyType) {
		return findByProperty(PROPERTY_TYPE, propertyType);
	}

	public List findByOccupancyStatus(Object occupancyStatus) {
		return findByProperty(OCCUPANCY_STATUS, occupancyStatus);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByEstimateHomeValue(Object estimateHomeValue) {
		return findByProperty(ESTIMATE_HOME_VALUE, estimateHomeValue);
	}

	public List findByRefinanceCashOut(Object refinanceCashOut) {
		return findByProperty(REFINANCE_CASH_OUT, refinanceCashOut);
	}

	public List findByFirstLoanBalance(Object firstLoanBalance) {
		return findByProperty(FIRST_LOAN_BALANCE, firstLoanBalance);
	}

	public List findByFirstLoanRate(Object firstLoanRate) {
		return findByProperty(FIRST_LOAN_RATE, firstLoanRate);
	}

	public List findByFirstLoanTerm(Object firstLoanTerm) {
		return findByProperty(FIRST_LOAN_TERM, firstLoanTerm);
	}

	public List findBySecondLoanBalance(Object secondLoanBalance) {
		return findByProperty(SECOND_LOAN_BALANCE, secondLoanBalance);
	}

	public List findBySecondLoanRate(Object secondLoanRate) {
		return findByProperty(SECOND_LOAN_RATE, secondLoanRate);
	}

	public List findBySecondLoanTerm(Object secondLoanTerm) {
		return findByProperty(SECOND_LOAN_TERM, secondLoanTerm);
	}
	public List findByFirstNamen(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}
	public List findByEmailAddress(Object emailAddress) {
		return findByProperty(SECOND_LOAN_TERM, emailAddress);
	}
	public List findByLastName(Object lastName) {
		return findByProperty(SECOND_LOAN_TERM, lastName);
	}
	public List findByPhoneNo(Object phoneNo) {
		return findByProperty(SECOND_LOAN_TERM, phoneNo);
	} 
	public List findByAnnualIncome(Object annualIncome) {
		return findByProperty(ANNUAL_INCOME, annualIncome);
	} 
	public List findByBorrowerCreditScore(Object borrowerCreditScore) {
		return findByProperty(BORROWER_CREDIT_SCORE, borrowerCreditScore);
	} 
	public List findByCoBorrowerCreditScore(Object coBorrowerCreditScore) {
		return findByProperty(CO_BORROWER_CREDIT_SCORE, coBorrowerCreditScore);
	}  
	public List findAll() {
		log.debug("finding all Quote instances");
		try {
			String queryString = "from Quote";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Quote merge(Quote detachedInstance) {
		log.debug("merging Quote instance");
		try {
			Quote result = (Quote) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Quote instance) {
		log.debug("attaching dirty Quote instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Quote instance) {
		log.debug("attaching clean Quote instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static QuoteDAO getFromApplicationContext(ApplicationContext ctx) {
		return (QuoteDAO) ctx.getBean("QuoteDAO");
	}
}