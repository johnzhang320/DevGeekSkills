package com.hibernate.join.table.many.to.many.test;

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
import com.hibernate.utils.SpringSessionFactoryValidate;
/*
 * Note:
 * 1. If first time run this project , please check run configuration --> delete all items under Junit 
 * 2. Make sure that JRE is java 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = { SpringSessionFactoryValidate.class }, loader = AnnotationConfigContextLoader.class)
public class JoinTableManyToManyDataQueryTest  {
	static Logger Log = Logger.getLogger( JoinTableManyToManyDataQueryTest.class);
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
	public void Run1_showAllUsersPerGroupsTest() {
		Log.info("--------------------showAllUsersPerGroupsTest()-------------------------");
		 
		String hql = "from Group ";
		Query query = session.createQuery(hql);
		Log.info("Showing all user per groups!"); 
		List<Group> list = query.getResultList();
		Log.info("Group List size="+list.size());
		
		showAllUsersByGroups(list);
	}
	
	@Test
	public void Run2_showAllGroupsPerUserTest() {
		Log.info("--------------------showAllGroupsPerUserTest()-------------------------");
		 
		String hql = "from User ";
		Query query = session.createQuery(hql);
		Log.info("Showing all group per users!"); 
		List<User> list = query.getResultList();
		Log.info("user List size="+list.size());
		showAllGroupsByUsers(list);
	}
	public void showAllUsersByGroups(List<Group> grps) {
		for (Group g:grps) {
			Log.info("Group "+g.getId()+","+g.getName());
			for (UserGroup u: g.getUserGroups()) {
				Log.info("User "+u.getId()+", username="+u.getUser().getUsername()+",password="+u.getUser().getPassword()+",email="+u.getUser().getEmail());
			}
		}
		Log.info("Completed Show all user per groups!");
	}
	
	public void showAllGroupsByUsers(List<User> users) {
	
		for (User u: users) {
			Log.info("User "+u.getId()+", username="+u.getUsername()+",password="+u.getPassword()+",email="+u.getEmail());
			for (UserGroup g:u.getUserGroups()) {
				Log.info("Group "+g.getId()+","+g.getGroup().getName());
			}
		}
		 
		Log.info("Completed Show all groups per users!");
	}
}
