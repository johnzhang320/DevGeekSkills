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

import com.hibernate.base.service.BaseHibernateDAO;
import com.hibernate.base.service.BaseTestDAO;
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
import com.hibernate.many.to.many.model.Group;
import com.hibernate.many.to.many.model.User;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
import com.hibernate.utils.SpringSessionFactoryCreate;
 
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
		session.beginTransaction();
		
	}
	@After
	public void after() {
		 
		session.getTransaction().commit();
		session.close();
		
	}
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
