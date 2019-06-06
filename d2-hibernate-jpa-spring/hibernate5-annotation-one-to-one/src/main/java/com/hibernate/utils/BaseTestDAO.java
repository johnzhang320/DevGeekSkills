package com.hibernate.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
 
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/*
 *  If you want to create create the table , change below property to 'create'
 *    <property name="hibernate.hbm2ddl.auto">create</property>  
 *    in hibernate.cfg.xml
 *  Keep test order, junit 5 run on the order in which methods present
 */
@RunWith(JUnitPlatform.class)
public class BaseTestDAO {
	public static BaseHibernateDAO dao;
	@BeforeAll
	public static void setup() {
		dao = new BaseHibernateDAO();
 	}
	@AfterAll
	public static void after() {
	 
		dao.getSessionFactory().close();
	}
	@BeforeEach
	public void beforeEach() {
		dao.tx_begin();
	}
	@AfterEach
	public void afterEach() {
		dao.tx_commit();
		dao.getSession().close();
	}
}
