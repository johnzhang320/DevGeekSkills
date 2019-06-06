package com.loan.agent.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Compare entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.loan.agent.dao.Compare
 * @author MyEclipse Persistence Tools
 */

public class CompareDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(CompareDAO.class);
	// property constants
	public static final String LOAN_TERM = "loanTerm";
	public static final String LOAN_TYPE = "loanType";
	public static final String LOAN_AMOUNT = "loanAmount";
	public static final String INTEREST_RATE = "interestRate";
	public static final String LOAN1_TERM = "loan1Term";
	public static final String LOAN1_AMOUNT = "loan1Amount";
	public static final String LOAN1_INTEREST_RATE = "loan1InterestRate";
	public static final String LOAN1_CLOSING_COST = "loan1ClosingCost";
	public static final String LOAN1_POINTS = "loan1Points";
	public static final String LOAN2_TERM = "loan2Term";
	public static final String LOAN2_AMOUNT = "loan2Amount";
	public static final String LOAN2_INTEREST_RATE = "loan2InterestRate";
	public static final String LOAN2_CLOSING_COST = "loan2ClosingCost";
	public static final String LOAN2_POINTS = "loan2Points";
	public static final String LOAN3_TERM = "loan3Term";
	public static final String LOAN3_AMOUNT = "loan3Amount";
	public static final String LOAN3_INTEREST_RATE = "loan3InterestRate";
	public static final String LOAN3_CLOSING_COST = "loan3ClosingCost";
	public static final String LOAN3_POINTS = "loan3Points";
	public static final String COMPARE_RESULT = "compareResult";
	public static final String RESULT_FILE = "resultFile";

	protected void initDao() {
		// do nothing
	}

	public void save(Compare transientInstance) {
		log.debug("saving Compare instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Compare persistentInstance) {
		log.debug("deleting Compare instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Compare findById(java.lang.Integer id) {
		log.debug("getting Compare instance with id: " + id);
		try {
			Compare instance = (Compare) getHibernateTemplate().get(
					"com.loan.agent.dao.Compare", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Compare instance) {
		log.debug("finding Compare instance by example");
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
		log.debug("finding Compare instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Compare as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoanTerm(Object loanTerm) {
		return findByProperty(LOAN_TERM, loanTerm);
	}

	public List findByLoanType(Object loanType) {
		return findByProperty(LOAN_TYPE, loanType);
	}

	public List findByLoanAmount(Object loanAmount) {
		return findByProperty(LOAN_AMOUNT, loanAmount);
	}

	public List findByInterestRate(Object interestRate) {
		return findByProperty(INTEREST_RATE, interestRate);
	}

	public List findByLoan1Term(Object loan1Term) {
		return findByProperty(LOAN1_TERM, loan1Term);
	}

	public List findByLoan1Amount(Object loan1Amount) {
		return findByProperty(LOAN1_AMOUNT, loan1Amount);
	}

	public List findByLoan1InterestRate(Object loan1InterestRate) {
		return findByProperty(LOAN1_INTEREST_RATE, loan1InterestRate);
	}

	public List findByLoan1ClosingCost(Object loan1ClosingCost) {
		return findByProperty(LOAN1_CLOSING_COST, loan1ClosingCost);
	}

	public List findByLoan1Points(Object loan1Points) {
		return findByProperty(LOAN1_POINTS, loan1Points);
	}

	public List findByLoan2Term(Object loan2Term) {
		return findByProperty(LOAN2_TERM, loan2Term);
	}

	public List findByLoan2Amount(Object loan2Amount) {
		return findByProperty(LOAN2_AMOUNT, loan2Amount);
	}

	public List findByLoan2InterestRate(Object loan2InterestRate) {
		return findByProperty(LOAN2_INTEREST_RATE, loan2InterestRate);
	}

	public List findByLoan2ClosingCost(Object loan2ClosingCost) {
		return findByProperty(LOAN2_CLOSING_COST, loan2ClosingCost);
	}

	public List findByLoan2Points(Object loan2Points) {
		return findByProperty(LOAN2_POINTS, loan2Points);
	}

	public List findByLoan3Term(Object loan3Term) {
		return findByProperty(LOAN3_TERM, loan3Term);
	}

	public List findByLoan3Amount(Object loan3Amount) {
		return findByProperty(LOAN3_AMOUNT, loan3Amount);
	}

	public List findByLoan3InterestRate(Object loan3InterestRate) {
		return findByProperty(LOAN3_INTEREST_RATE, loan3InterestRate);
	}

	public List findByLoan3ClosingCost(Object loan3ClosingCost) {
		return findByProperty(LOAN3_CLOSING_COST, loan3ClosingCost);
	}

	public List findByLoan3Points(Object loan3Points) {
		return findByProperty(LOAN3_POINTS, loan3Points);
	}

	public List findByCompareResult(Object compareResult) {
		return findByProperty(COMPARE_RESULT, compareResult);
	}

	public List findByResultFile(Object resultFile) {
		return findByProperty(RESULT_FILE, resultFile);
	}

	public List findAll() {
		log.debug("finding all Compare instances");
		try {
			String queryString = "from Compare";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Compare merge(Compare detachedInstance) {
		log.debug("merging Compare instance");
		try {
			Compare result = (Compare) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Compare instance) {
		log.debug("attaching dirty Compare instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Compare instance) {
		log.debug("attaching clean Compare instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CompareDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CompareDAO) ctx.getBean("CompareDAO");
	}
}