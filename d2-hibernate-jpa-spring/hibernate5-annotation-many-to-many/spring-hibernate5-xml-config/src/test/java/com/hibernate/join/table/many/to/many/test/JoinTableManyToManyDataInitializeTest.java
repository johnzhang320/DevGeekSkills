package com.hibernate.join.table.many.to.many.test;

import java.util.Date;
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
import com.hibernate.join.table.many.to.many.dao.JoinTableManyToManyServiceImpl;
import com.hibernate.join.table.many.to.many.model.Group;
import com.hibernate.join.table.many.to.many.model.User;
import com.hibernate.join.table.many.to.many.model.UserGroup;
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
import com.hibernate.utils.SpringSessionFactoryCreate;
/*
 *  If you want to create create the table , change below property to 'create'
 *    <property name="hibernate.hbm2ddl.auto">create</property>  
 *    in hibernate.cfg.xml
 *  Keep test order, junit 5 run on the order in which methods present
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryCreate.class }, loader = AnnotationConfigContextLoader.class)
public class JoinTableManyToManyDataInitializeTest  {
	static Logger Log = Logger.getLogger( JoinTableManyToManyDataInitializeTest.class);
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
		
		 
		Group groupAdmin = new Group("Administrator Group");
        Group groupGuest = new Group("Guest Group");
         
        User user1 = new User("Tom", "tomcat", "tom@codejava.net");
        User user2 = new User("Mary", "mary", "mary@codejava.net");
        
      
        UserGroup userGroup = new UserGroup();
        userGroup.setGroup(groupAdmin);
        userGroup.setUser(user1);
        userGroup.setActivated(true);
        userGroup.setRegisteredDate(new Date());
        Log.info("saving UserGroup 1");
        session.save(userGroup);
        Log.info("saved UserGroup 1");  
        userGroup = new UserGroup();
        userGroup.setGroup(groupGuest);
        userGroup.setUser(user2);
        userGroup.setActivated(true);
        userGroup.setRegisteredDate(new Date());
        Log.info("saving UserGroup 2");
        session.save(userGroup); 
        Log.info("saved UserGroup 2");
  		
	}
	
	@Test
	public void Run2_createManyToManyUserByGroup() {
		
	 
		String hql = "from Group where name=:group_name";
		Query query = session.createQuery(hql);
		query.setParameter("group_name" ,"Administrator Group");
		List<Group> list = query.getResultList();
		
		Group groupAdmin =list.get(0);
		
        Log.info("Fins groupAdmin="+groupAdmin.getName());
         
        User user1 = new User("JerryLiu", "tomcat", "jerryliu@yahoo.com");
        User user2 = new User("LeoWang", "leom", "leowang@gmail.comt");
         

        UserGroup userGroup = new UserGroup();
        userGroup.setGroup(groupAdmin);
        userGroup.setUser(user1);
        userGroup.setActivated(true);
        userGroup.setRegisteredDate(new Date());
        Log.info("saving User 1 to computer group");
        session.save(userGroup);
        Log.info("saved User 1 to computer group");  
        userGroup = new UserGroup();
        userGroup.setGroup(groupAdmin);
        userGroup.setUser(user2);
        userGroup.setActivated(true);
        userGroup.setRegisteredDate(new Date());
        Log.info("saving User 2 to computer group");
        session.save(userGroup); 
        Log.info("saved User 2 to computer group");
		
	}
 
}
