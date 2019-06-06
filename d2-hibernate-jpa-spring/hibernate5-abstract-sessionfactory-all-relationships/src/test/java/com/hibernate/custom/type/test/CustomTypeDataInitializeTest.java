package com.hibernate.custom.type.test;

 

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
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
import com.hibernate.custom.type.model.Address;
import com.hibernate.custom.type.model.OfficeEmployee;
import com.hibernate.custom.type.model.PhoneNumber;
import com.hibernate.custom.type.model.Salary;
import com.hibernate.utils.LocalDateStringType;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 * 
 */
@RunWith(JUnitPlatform.class)
public class CustomTypeDataInitializeTest extends BaseTestDAO {
	
	public CustomTypeDataInitializeTest() {
		super(new CustomTypeServiceImpl());
	}
	static Logger Log = Logger.getLogger( CustomTypeDataInitializeTest.class);
	   @Test
	    public void givenEmployee_whenSavedWithCustomTypes_thenEntityIsSaved() throws IOException {

	        final OfficeEmployee e = new OfficeEmployee();
	        e.setDateOfJoining(LocalDate.now());

	        PhoneNumber number = new PhoneNumber(1, 222, 8781902);
	        e.setEmployeeNumber(number);

	        Address empAdd = new Address();
	        empAdd.setAddressLine1("8445 Great America Parkway");
	        empAdd.setAddressLine2("");
	        empAdd.setCity("Santa Clara");
	        empAdd.setState("California");
	        empAdd.setCountry("USA");
	        empAdd.setZipCode(100);

	        e.setEmpAddress(empAdd);

	        Salary empSalary = new Salary();
	        empSalary.setAmount(1000L);
	        empSalary.setCurrency("USD");
	        e.setSalary(empSalary);
	        
	        Session session = dao.getSession();
	        Log.info("Saving Office Employee");
            session.save(e);
            boolean contains = session.contains(e);
            Assert.assertTrue(contains);
	      
            Log.info("Saved Office Employee");
	    }

	    @Test
	    public void givenEmployee_whenCustomTypeInQuery_thenReturnEntity() throws IOException {

	        final OfficeEmployee e = new OfficeEmployee();
	        e.setDateOfJoining(LocalDate.now());

	        PhoneNumber number = new PhoneNumber(1, 222, 8781902);
	        e.setEmployeeNumber(number);

	        Address empAdd = new Address();
	        empAdd.setAddressLine1("1474 Flamingo Way");
	        empAdd.setAddressLine2("");
	        empAdd.setCity("Sunnyvale");
	        empAdd.setState("California");
	        empAdd.setCountry("USA");
	        empAdd.setZipCode(94087);
	        e.setEmpAddress(empAdd);

	        Salary empSalary = new Salary();
	        empSalary.setAmount(1400L);
	        empSalary.setCurrency("USD");
	        e.setSalary(empSalary);
	        Session session = dao.getSession();
            session.save(e);

            Query query = session.createQuery("FROM OfficeEmployee OE WHERE OE.empAddress.zipcode = :pinCode");
            query.setParameter("pinCode",94087);
            
            List<OfficeEmployee> list = query.list();
            Assert.assertEquals(1, list.size());
            list.forEach(x->System.out.println(x.getId()+","+x.getDateOfJoining()+","+x.getEmpAddress().getAddressLine1()+","+x.getEmpAddress().getState()));

	    }
 
	
 
 
}
