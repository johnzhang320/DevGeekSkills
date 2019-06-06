package com.hibernate.base.service;

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

@RunWith(JUnitPlatform.class)
public class BaseTestDAO {
	public static BaseHibernateDAO dao;
	public BaseTestDAO(SessionFactoryService service) {
		dao = new BaseHibernateDAO(service);
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
