package com.hibernate.custom.type.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
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

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.custom.type.dao.CustomTypeServiceImpl;
import com.hibernate.custom.type.model.OfficeEmployee;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(JUnitPlatform.class)
public class CustomTypeDataQueryTest extends BaseTestDAO {
	public CustomTypeDataQueryTest() {
		super(new CustomTypeServiceImpl());
	}
	static Logger Log = Logger.getLogger( CustomTypeDataQueryTest.class);
	   Query query = dao.getSession().createQuery("FROM OfficeEmployee OE WHERE OE.empAddress.zipcode = :pinCode");
  
 
}
