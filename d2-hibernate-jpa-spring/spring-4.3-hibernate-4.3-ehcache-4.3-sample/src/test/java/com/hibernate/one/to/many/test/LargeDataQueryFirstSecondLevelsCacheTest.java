package com.hibernate.one.to.many.test;

 
import java.util.List;
 

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
import org.hibernate.stat.Statistics;
 
 
import com.hibernate.one.to.many.model.DmarcDetailReports;
import com.hibernate.one.to.many.model.DmarcMasterReports;
 
import com.hibernate.utils.SpringSessionFactoryValidate;
import com.hibernate.one.to.many.model.DmarcMasterReports;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
/*
 * Note: This test always keep session open between Test Cases , which means we use session cache data at first level cache
 * 1. In AfterEach(), we do not close the session, ensure first level cache be used in Test Case 2 and Test Case 3 
 * 2. We can see the test case 2/3 dramatically reduce the running time from 1331 to 2 or 6 ms
 * 3. the run time exclude log display time and only count the query runnint time
 * 
	    Test 1 finish testCriteriaApplication, it took:270 ms,
		Test 2 finish testQuerySameData, it took:4 ms,
		Test 3 finish ttestClearCurrenSessionRead2ndLevelCache, it took 38 ms
		Test 4 finish testQueryMoreThanSameData, it took:8 ms,
		Master Report records:2020
		Detail Repost records:131625
		
		***** Test Case 4 *****
		Fetch Count=0
		Second Level Hit Count=2000
		Second Level Miss Count=0
		Second Level Put Count=163425
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryValidate.class }, loader = AnnotationConfigContextLoader.class)
public class LargeDataQueryFirstSecondLevelsCacheTest {
	static Logger Log = Logger.getLogger(LargeDataQueryFirstSecondLevelsCacheTest.class);
	static Long testCase1=0L;
	static Long testCase2=0L;
	static Long testCase3=0L;
	static Long testCase4=0L;
	static Long testCase5=0L;
	static int MAX_RESULTS=2000;
	static int MAX_DETAIL_RESUILT=1000;
	static int realDetailResult=0;
	static Criteria criteria;
	public static Statistics stats;
	static  Long start =0L;
	static   List<DmarcMasterReports> dmarcMasterReports ;
	
	@Autowired
	SessionFactory sessionFactory;
	
	Session session=null;
	
	@Before 
	public  void before() {
		 if (session==null || !session.isOpen()) {
			 session =sessionFactory.openSession();
		 }
		 Log.info("Begin a test case");
		
	}
	@After 
	public void after() {
		 
		Log.info("End a test case");
		 
	}	
	
	@Test 
	public void A1_testCriteriaApplication() {  // use Criteria and Restriction
		System.out.println("before all begin");
	
		stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		printStats(stats, 0);
		System.out.println("before all end");
		System.out.println( "Test 1 testCriteriaApplication begin");
		System.out.println( "\n\nPLEASE WAIT.......\n\n");		
		
		Long start = System.currentTimeMillis();
		Criteria criteria = session.createCriteria(DmarcMasterReports.class);
		criteria.addOrder(org.hibernate.criterion.Order.asc("masterId"));
		criteria.setMaxResults(MAX_RESULTS);		
		criteria.setCacheRegion("dmarcMasterReports");
		criteria.setCacheable(true);
		List<DmarcMasterReports> dmarcMasterReports = criteria.list(); 
		 
		testCase1 = System.currentTimeMillis() -start;
	 
		showDmarcMasterReports(dmarcMasterReports);
		
		System.out.println( "\nTest 1 testCriteriaApplication end and it took:"+testCase1+" ms\n");
		
		printStats(stats, 1);
	 
/* 	}   // The test is running in order, therefore I enforce them run in my order
	@Test
	public void A2_testQuerySameDataAgain() {*/
		System.out.println( "Test2 testQuerySameData begin");
		stats = sessionFactory.getStatistics();
		start = System.currentTimeMillis();
		criteria = session.createCriteria(DmarcMasterReports.class);
		criteria.addOrder(org.hibernate.criterion.Order.asc("masterId"));
		criteria.setMaxResults(MAX_RESULTS);		
		criteria.setCacheRegion("dmarcMasterReports");
		criteria.setCacheable(true);
		 dmarcMasterReports = criteria.list(); 
		testCase2  = System.currentTimeMillis() -start;
		showDmarcMasterReports(dmarcMasterReports);
		
		System.out.println( "\nTest 1 finish testCriteriaApplication, it took:"+testCase1+" ms"
				+"\n"+"Test 2 finish testQuerySameData, it took "+testCase2 +" ms"
				+"\n"+"Master Report records:"+dmarcMasterReports.size() 
				+"\n"+"Detail Repost records:"+realDetailResult); 
		
		printStats(stats, 2);
		
/*	}
	
	@Test
	public void A3_testClearCurrenSessionRead2ndLevelCache() {*/
		
		session.clear();   // clean up all objects in session and enforce code use second level cache
		stats = sessionFactory.getStatistics();
		System.out.println( "Test 3 testClearCurrenSessionRead2ndLevelCache begin");
		System.out.println( "Test 3 testClearCurrenSessionRead2ndLevelCache clear all objects in current session to enforce read second level cache");
		start = System.currentTimeMillis();
		criteria = session.createCriteria(DmarcMasterReports.class);
		criteria.addOrder(org.hibernate.criterion.Order.asc("masterId"));
		criteria.setMaxResults(MAX_RESULTS);		
		criteria.setCacheRegion("dmarcMasterReports");
		criteria.setCacheable(true);
		 dmarcMasterReports = criteria.list(); 
		testCase3  = System.currentTimeMillis() -start;
		showDmarcMasterReports(dmarcMasterReports);
		
		System.out.println( "\nTest 1 finish testCriteriaApplication, it took:"+testCase1+" ms"
				+"\n"+"Test 2 finish testQuerySameData, it took "+testCase2 +" ms"
				+"\n"+"Test 3 finish testClearCurrenSessionRead2ndLevelCache, it took "+testCase3 +" ms"
				+"\n"+"Master Report records:"+dmarcMasterReports.size() 
				+"\n"+"Detail Repost records:"+realDetailResult); 
		
		printStats(stats, 3);
		
/*	}
	@Test
	public void A4_testCloseSessionOpenNewOneRead2ndLevelCache() {*/
		 
		session.close();   // close the session, clean up threadLocal and enforce code use second level cache
		session = sessionFactory.openSession();
		stats = sessionFactory.getStatistics();
		System.out.println( "Test 4 testCloseSessionOpenNewOneRead2ndLevelCache begin");
		System.out.println( "Test 4 testCloseSessionOpenNewOneRead2ndLevelCache close session to enforce read second level cache");
		start = System.currentTimeMillis();
		criteria = session.createCriteria(DmarcMasterReports.class);   // session will be creating new session
		criteria.addOrder(org.hibernate.criterion.Order.asc("masterId"));
		criteria.setMaxResults(MAX_RESULTS);		
		criteria.setCacheRegion("dmarcMasterReports");
		criteria.setCacheable(true);
		 dmarcMasterReports = criteria.list(); 
		testCase4  = System.currentTimeMillis() -start;
		showDmarcMasterReports(dmarcMasterReports);
		
		System.out.println( "\nTest 1 finish testCriteriaApplication, it took:"+testCase1+" ms"
				+"\n"+"Test 2 finish testQuerySameData, it took "+testCase2 +" ms"
				+"\n"+"Test 3 finish testClearCurrenSessionRead2ndLevelCache, it took "+testCase3 +" ms"
				+"\n"+"Test 4 finish testCloseSessionOpenNewOneRead2ndLevelCache, it took "+testCase4 +" ms"
				+"\n"+"Master Report records:"+dmarcMasterReports.size() 
				+"\n"+"Detail Repost records:"+realDetailResult); 
		
		printStats(stats, 4);
		
/*	}
	@Test
	public void A5_testQueryMoreThanSameData() {*/
		//session.evict(dmarcMasterReports);
		System.out.println( "Test5 testAddMoreDataToCachedOne (50 master rows) begin");
		stats = sessionFactory.getStatistics();
		start = System.currentTimeMillis();
		 criteria = session.createCriteria(DmarcMasterReports.class);
		criteria.addOrder(org.hibernate.criterion.Order.asc("masterId"));
		criteria.setMaxResults(MAX_RESULTS+50);		
		criteria.setCacheRegion("dmarcMasterReports");
		criteria.setCacheable(true);
		dmarcMasterReports = criteria.list(); 
		testCase5= System.currentTimeMillis() -start;
		showDmarcMasterReports(dmarcMasterReports);
		
		System.out.println( "\nTest 1 finish testCriteriaApplication, it took:"+testCase1+" ms,"
				+"\n"+"Test 2 finish testQuerySameData, it took:"+testCase2+" ms,"
				+"\n"+"Test 3 finish testClearCurrenSessionRead2ndLevelCache, it took "+testCase3 +" ms"
				+"\n"+"Test 4 finish testCloseSessionOpenNewOneRead2ndLevelCache, it took "+testCase4 +" ms"
				+"\n"+"Test 5 finish testAddMoreDataToCachedOne (50 master rows), it took:"+testCase5+" ms,"
				+"\n"+"Master Report records:"+dmarcMasterReports.size() 
				+"\n"+"Detail Repost records:"+realDetailResult); 
		
		printStats(stats, 5);
		
	}
	 
	
	public void showDmarcMasterReports(List<DmarcMasterReports> list) {
		realDetailResult=0;
		for (DmarcMasterReports p:list) {
			Log.debug("show DmarcMasterReportss:"+p.toString());
			int count=0;
		 for (DmarcDetailReports o:p.getDmarcDetailReportses()) {
				Log.debug("Under Master Id "+p.getMasterId()+", DetailReport "+count+","+o.toString());
				realDetailResult++;
				if (count++>MAX_DETAIL_RESUILT) break;
				
			}
		}
		
	}
 
	public static void printStats(Statistics stats, int i) {
		System.out.println("\n***** Test Case " + i + " *****");
		System.out.println("Fetch Count="
				+ stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count="
				+ stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count="
						+ stats
								.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count="
				+ stats.getSecondLevelCachePutCount()+"\n");
	}	
}
