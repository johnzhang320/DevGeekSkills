package com.hibernate.base.service;

 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.hibernate.utils.SpringSessionFactoryValidate;

 
public class BaseTestDAO {
	public static BaseHibernateDAO dao;
	public BaseTestDAO(SessionFactoryService service) {
		dao = new BaseHibernateDAO(service);
	}

	
	@Before
	public void beforeEach() {
		dao.tx_begin();
	}
	@After
	public void afterEach() {
		dao.tx_commit();
		dao.getSession().close();
	}
}
