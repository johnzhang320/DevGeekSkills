package com.loan.agent.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Reply
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.loan.agent.dao.Reply
 * @author MyEclipse Persistence Tools
 */

public class ReplyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ReplyDAO.class);
	// property constants
	public static final String AGENT_ID = "agentId";
	 
	public static final String USER_ID = "userId";
	public static final String TERM = "term";
	public static final String REMAIN_BALANCE = "remainBalance";
	public static final String TERM_VO = "termVo";
	public static final String INT_RATE_VO = "intRateVo";
	public static final String CLOSING_FEE = "closingFee";
	public static final String POINT = "point";
	public static final String MONTH_PAYMENT = "monthPayment";
	public static final String MONTH_SAVING = "monthSaving";
	public static final String BREAK_EVENT_POINT = "breakEventPoint";
	public static final String TIMES_ALREADY_PAID = "timesAlreadyPaid";
	public static final String REMAIN_TIMES = "remainTimes";
	public static final String PAID_INTEREST = "paidInterest";
	public static final String UNPAID_INTERETS = "unpaidInterets";
	public static final String UNPAIN_LOAN_AMT = "unpainLoanAmt";
	public static final String AFFORD_APRICE = "affordAprice";
	public static final String AFFORD_CPRICE = "affordCprice";
	public static final String LOAN_AMT = "loanAmt";
	public static final String HOME_PRICE = "homePrice";
	public static final String ADVICE_NOTE = "adviceNote";

	protected void initDao() {
		// do nothing
	}

	public void save(Reply transientInstance) {
		log.debug("saving Reply instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Reply persistentInstance) {
		log.debug("deleting Reply instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reply findById(com.loan.agent.dao.ReplyId id) {
		log.debug("getting Reply instance with id: " + id);
		try {
			Reply instance = (Reply) getHibernateTemplate().get(
					"com.loan.agent.dao.Reply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Reply instance) {
		log.debug("finding Reply instance by example");
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
		log.debug("finding Reply instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reply as model where model."
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

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByTerm(Object term) {
		return findByProperty(TERM, term);
	}

	public List findByRemainBalance(Object remainBalance) {
		return findByProperty(REMAIN_BALANCE, remainBalance);
	}

	public List findByTermVo(Object termVo) {
		return findByProperty(TERM_VO, termVo);
	}

	public List findByIntRateVo(Object intRateVo) {
		return findByProperty(INT_RATE_VO, intRateVo);
	}

	public List findByClosingFee(Object closingFee) {
		return findByProperty(CLOSING_FEE, closingFee);
	}

	public List findByPoint(Object point) {
		return findByProperty(POINT, point);
	}

	public List findByMonthPayment(Object monthPayment) {
		return findByProperty(MONTH_PAYMENT, monthPayment);
	}

	public List findByMonthSaving(Object monthSaving) {
		return findByProperty(MONTH_SAVING, monthSaving);
	}

	public List findByBreakEventPoint(Object breakEventPoint) {
		return findByProperty(BREAK_EVENT_POINT, breakEventPoint);
	}

	public List findByTimesAlreadyPaid(Object timesAlreadyPaid) {
		return findByProperty(TIMES_ALREADY_PAID, timesAlreadyPaid);
	}

	public List findByRemainTimes(Object remainTimes) {
		return findByProperty(REMAIN_TIMES, remainTimes);
	}

	public List findByPaidInterest(Object paidInterest) {
		return findByProperty(PAID_INTEREST, paidInterest);
	}

	public List findByUnpaidInterets(Object unpaidInterets) {
		return findByProperty(UNPAID_INTERETS, unpaidInterets);
	}

	public List findByUnpainLoanAmt(Object unpainLoanAmt) {
		return findByProperty(UNPAIN_LOAN_AMT, unpainLoanAmt);
	}

	public List findByAffordAprice(Object affordAprice) {
		return findByProperty(AFFORD_APRICE, affordAprice);
	}

	public List findByAffordCprice(Object affordCprice) {
		return findByProperty(AFFORD_CPRICE, affordCprice);
	}

	public List findByLoanAmt(Object loanAmt) {
		return findByProperty(LOAN_AMT, loanAmt);
	}

	public List findByHomePrice(Object homePrice) {
		return findByProperty(HOME_PRICE, homePrice);
	}

	public List findByAdviceNote(Object adviceNote) {
		return findByProperty(ADVICE_NOTE, adviceNote);
	}

	public List findAll() {
		log.debug("finding all Reply instances");
		try {
			String queryString = "from Reply";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Reply merge(Reply detachedInstance) {
		log.debug("merging Reply instance");
		try {
			Reply result = (Reply) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reply instance) {
		log.debug("attaching dirty Reply instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reply instance) {
		log.debug("attaching clean Reply instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReplyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReplyDAO) ctx.getBean("ReplyDAO");
	}
}