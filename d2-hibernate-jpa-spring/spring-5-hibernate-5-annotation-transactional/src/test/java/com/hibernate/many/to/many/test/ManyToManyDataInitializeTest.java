package com.hibernate.many.to.many.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
import com.hibernate.many.to.many.model.Group;
import com.hibernate.many.to.many.model.User;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
import com.hibernate.utils.SpringSessionFactoryCreate;
/**
 *  Spring Junit 4 running test method on order by alphabet of methods, add RunX to sort it
 *  @ContextConfiguration load the SpringSessionFactoryCreate.class which make possible Autowired the SessionFactory
 */
/**
* 
* @Transactional
*  

		****************Propagation******************
		
		Defines how transactions relate to each other. Common options
		
		Required: Code will always run in a transaction. Create a new transaction or reuse one if available.
		Requires_new: Code will always run in a new transaction. Suspend current transaction if one exist.
		Support: 
		@Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
		@Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
		
		*********************Isolation*******************
		
		Defines the data contract between transactions.
		
		Read Uncommitted: Allows dirty reads, means Specifies that statements can read rows that have been modified by
		 				  other transactions but not yet committed.Transactions running at the READ UNCOMMITTED level
		 				  do not issue shared locks to prevent other transactions from modifying data read by the current transaction.
		 				  
		Read Committed: Does not allow dirty reads, means Specifies that statements cannot read data that has been modified but not 
						committed by other transactions
						
		Repeatable Read: If a row is read twice in the same transaction, result will always be the same,means Specifies that 
						 statements cannot read data that has been modified but not yet committed by other transactions and
						 that no other transactions can modify data that has been read by the current transaction until the 
						 current transaction completes.
						 
		Serializable: 	Performs all transactions in a sequence, means Statements cannot read data that has been modified but 
						not yet committed by other transactions.
						No other transactions can modify data that has been read by the current transaction until the current 
						transaction completes.
						Other transactions cannot insert new rows with key values that would fall in the range of keys read by 
						any statements in the current transaction until the current transaction completes.
		
		Example:
		@Transactional(isolation = Isolation.SERIALIZABLE)

*
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryCreate.class }, loader = AnnotationConfigContextLoader.class)
public class ManyToManyDataInitializeTest  {
	static Logger Log = Logger.getLogger( ManyToManyDataInitializeTest.class);
	

	Session session;
	@Autowired
	SessionFactory sessionFactory;
	
	@Before
	public void before() {
		
		session = sessionFactory.openSession();
		//session.beginTransaction();
		
	}
	@After
	public void after() {
		 
		//session.getTransaction().commit();
		session.close();
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Test
	public void Run1_createManyToManyBrandNew() {
	    Log.info(" Run1_createManyToManyBrandNew()");
	
		Group groupAdmin = new Group("Administrator Group");
        Group groupGuest = new Group("Guest Group");
         
        User user1 = new User("Tom", "tomcat", "tom@codejava.net");
        User user2 = new User("Mary", "mary", "mary@codejava.net");
         
        groupAdmin.addUser(user1);
        groupAdmin.addUser(user2);
         
        groupGuest.addUser(user1);
         
        user1.addGroup(groupAdmin);
        user2.addGroup(groupAdmin);
        user1.addGroup(groupGuest);
        Log.info("saving groupAdmin!");
        session.save(groupAdmin);
        Log.info("Completed saving groupAdmin!");
        Log.info("saving groupGuest!");
        session.save(groupGuest);
        Log.info("saved groupGuest!");
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Test
	public void Run2_createManyToManyUserByGroup() {
	    Log.info(" Run2_createManyToManyBrandNew()");	
	 
		String hql = "from Group where name=:group_name";
		Query query = session.createQuery(hql);
		query.setParameter("group_name" ,"Administrator Group");
		List<Group> list = query.getResultList();
		
		Group groupAdmin =list.get(0);
		
        Log.info("Fins groupAdmin="+groupAdmin.getName());
         
        User user1 = new User("JerryLiu", "tomcat", "jerryliu@yahoo.com");
        User user2 = new User("LeoWang", "leom", "leowang@gmail.comt");
         
        groupAdmin.addUser(user1);
        groupAdmin.addUser(user2);
           
        user1.addGroup(groupAdmin);
        user2.addGroup(groupAdmin);
      
        Log.info("saving groupAdmin!");
        session.save(groupAdmin);
        Log.info("Completed saving groupAdmin!");
        
		
	}
 
}
