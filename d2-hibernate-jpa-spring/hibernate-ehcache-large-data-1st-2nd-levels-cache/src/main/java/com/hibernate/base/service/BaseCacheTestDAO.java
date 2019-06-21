package com.hibernate.base.service;

 

import org.apache.log4j.Logger;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
 

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.hibernate.one.to.one.test.HibernateEHCacheMain;
/** 
 *  keep same session for first level cache without transaction between Query Test Cases 
 *  which it is very important to fetch large data set
 */
@RunWith(JUnitPlatform.class)
public class BaseCacheTestDAO {
	static Logger Log =Logger.getLogger(BaseCacheTestDAO.class);
	public static BaseHibernateDAO dao;
	public static Statistics stats;
	public BaseCacheTestDAO(SessionFactoryService service) {
		dao = new BaseHibernateDAO(service);
		
	}
	 
	@AfterAll
	public static void afterAll() {
		Log.info("after all begin");
		Log.info("Stats enabled="+stats.isStatisticsEnabled());
		dao.getSession().clear();
		dao.getSession().close();
		dao.getSessionFactory().close();
		Log.info("after all end");
	}
 /**
  * READ_ONLY: Used only for entities that never change (exception is thrown if an attempt to update such an entity is made). 
 * 				It is very simple and performant. Very suitable for some static reference data that don’t change
	NONSTRICT_READ_WRITE: Cache is updated after a transaction that changed the affected data has been committed. Thus, strong 
				consistency is not guaranteed and there is a small time window in which stale data may be obtained from cache. This kind of
	 			strategy is suitable for use cases that can tolerate eventual consistency
	READ_WRITE: This strategy guarantees strong consistency which it achieves by using ‘soft’ locks: When a cached entity 
				is updated, a soft lock is stored in the cache for that entity as well, which is released after the transaction is
	 			committed. All concurrent transactions that access soft-locked entries will fetch the corresponding data directly 
	 			from database
	TRANSACTIONAL: Cache changes are done in distributed XA transactions. A change in a cached entity is either committed or
	 			rolled back in both database and cache in the same XA transaction
  * 
  */
	 
	@BeforeEach
	public void beforeEach() {
		//dao.tx_begin();   // if READ_WRITE and take transaction, will not hit first level cache and second level cache at all
		Log.info("Start a test case");
	}
	@AfterEach
	public void afterEach() {
		//dao.tx_commit();
	 //	dao.getSession().close();        // keep session will ensure first level cache
		Log.info("finish a test case");
	}
}
