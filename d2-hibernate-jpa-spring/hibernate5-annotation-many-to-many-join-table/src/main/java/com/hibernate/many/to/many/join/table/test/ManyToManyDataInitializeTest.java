package com.hibernate.many.to.many.join.table.test;

import java.util.Date;
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

import com.hibernate.many.to.many.join.table.model.Group;
import com.hibernate.many.to.many.join.table.model.User;
import com.hibernate.many.to.many.join.table.model.UserGroup;
import com.hibernate.utils.BaseHibernateDAO;
import com.hibernate.utils.BaseTestDAO;
/*
 *  If you want to create create the table , change below property to 'create'
 *    <property name="hibernate.hbm2ddl.auto">create</property>  
 *    in hibernate.cfg.xml
 *  Keep test order, junit 5 run on the order in which methods present
 */
@RunWith(JUnitPlatform.class)
public class ManyToManyDataInitializeTest extends BaseTestDAO {
	static Logger Log = Logger.getLogger( ManyToManyDataInitializeTest.class);
	 
	@Test
	public void createManyToManyBrandNew() {
		
		Session session =dao.getSession();
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
