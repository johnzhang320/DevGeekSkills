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
	@BeforeEach
	public void beforeEach() {
		//dao.tx_begin();
		Log.info("Start a test case");
	}
	@AfterEach
	public void afterEach() {
	//	dao.tx_commit();
	 //	dao.getSession().close();
		Log.info("finish a test case");
	}
}
