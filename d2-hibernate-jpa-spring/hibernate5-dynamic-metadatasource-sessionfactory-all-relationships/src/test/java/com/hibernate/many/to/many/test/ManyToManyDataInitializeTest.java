package com.hibernate.many.to.many.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

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
import com.hibernate.many.to.many.dao.ManyToManyServiceImpl;
import com.hibernate.many.to.many.model.Group;
import com.hibernate.many.to.many.model.User;
import com.hibernate.one.to.many.dao.OneToManyServiceImpl;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(JUnitPlatform.class)
public class ManyToManyDataInitializeTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( ManyToManyDataInitializeTest.class);
	public ManyToManyDataInitializeTest() {
		super(new ManyToManyServiceImpl());
	}
 
	@Test
	public void createManyToManyBrandNew() {
		
		Session session =dao.getSession();
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
	public void createManyToManyUserByGroup() {
		
		Session session =dao.getSession();
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
